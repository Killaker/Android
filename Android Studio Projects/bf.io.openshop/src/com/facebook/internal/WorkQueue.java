package com.facebook.internal;

import java.util.concurrent.*;
import com.facebook.*;

public class WorkQueue
{
    public static final int DEFAULT_MAX_CONCURRENT = 8;
    private final Executor executor;
    private final int maxConcurrent;
    private WorkNode pendingJobs;
    private int runningCount;
    private WorkNode runningJobs;
    private final Object workLock;
    
    public WorkQueue() {
        this(8);
    }
    
    public WorkQueue(final int n) {
        this(n, FacebookSdk.getExecutor());
    }
    
    public WorkQueue(final int maxConcurrent, final Executor executor) {
        this.workLock = new Object();
        this.runningJobs = null;
        this.runningCount = 0;
        this.maxConcurrent = maxConcurrent;
        this.executor = executor;
    }
    
    private void execute(final WorkNode workNode) {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    workNode.getCallback().run();
                }
                finally {
                    WorkQueue.this.finishItemAndStartNew(workNode);
                }
            }
        });
    }
    
    private void finishItemAndStartNew(final WorkNode workNode) {
        final Object workLock = this.workLock;
        // monitorenter(workLock)
        Label_0033: {
            if (workNode == null) {
                break Label_0033;
            }
            try {
                this.runningJobs = workNode.removeFromList(this.runningJobs);
                --this.runningCount;
                final int runningCount = this.runningCount;
                final int maxConcurrent = this.maxConcurrent;
                WorkNode pendingJobs = null;
                if (runningCount < maxConcurrent) {
                    pendingJobs = this.pendingJobs;
                    if (pendingJobs != null) {
                        this.pendingJobs = pendingJobs.removeFromList(this.pendingJobs);
                        this.runningJobs = pendingJobs.addToList(this.runningJobs, false);
                        ++this.runningCount;
                        pendingJobs.setIsRunning(true);
                    }
                }
                // monitorexit(workLock)
                if (pendingJobs != null) {
                    this.execute(pendingJobs);
                }
            }
            finally {
            }
            // monitorexit(workLock)
        }
    }
    
    private void startItem() {
        this.finishItemAndStartNew(null);
    }
    
    public WorkItem addActiveWorkItem(final Runnable runnable) {
        return this.addActiveWorkItem(runnable, true);
    }
    
    public WorkItem addActiveWorkItem(final Runnable runnable, final boolean b) {
        final WorkNode workNode = new WorkNode(runnable);
        synchronized (this.workLock) {
            this.pendingJobs = workNode.addToList(this.pendingJobs, b);
            // monitorexit(this.workLock)
            this.startItem();
            return (WorkItem)workNode;
        }
    }
    
    public void validate() {
        synchronized (this.workLock) {
            final WorkNode runningJobs = this.runningJobs;
            int n = 0;
            if (runningJobs != null) {
                WorkNode workNode = this.runningJobs;
                do {
                    workNode.verify(true);
                    ++n;
                    workNode = workNode.getNext();
                } while (workNode != this.runningJobs);
            }
            assert this.runningCount == n;
        }
    }
    // monitorexit(o)
    
    public interface WorkItem
    {
        boolean cancel();
        
        boolean isRunning();
        
        void moveToFront();
    }
    
    private class WorkNode implements WorkItem
    {
        private final Runnable callback;
        private boolean isRunning;
        private WorkNode next;
        private WorkNode prev;
        
        WorkNode(final Runnable callback) {
            this.callback = callback;
        }
        
        WorkNode addToList(WorkNode next, final boolean b) {
            assert this.next == null;
            assert this.prev == null;
            if (next == null) {
                this.prev = this;
                this.next = this;
                next = this;
            }
            else {
                this.next = next;
                this.prev = next.prev;
                final WorkNode next2 = this.next;
                this.prev.next = this;
                next2.prev = this;
            }
            if (b) {
                return this;
            }
            return next;
        }
        
        @Override
        public boolean cancel() {
            synchronized (WorkQueue.this.workLock) {
                if (!this.isRunning()) {
                    WorkQueue.this.pendingJobs = this.removeFromList(WorkQueue.this.pendingJobs);
                    return true;
                }
                return false;
            }
        }
        
        Runnable getCallback() {
            return this.callback;
        }
        
        WorkNode getNext() {
            return this.next;
        }
        
        @Override
        public boolean isRunning() {
            return this.isRunning;
        }
        
        @Override
        public void moveToFront() {
            synchronized (WorkQueue.this.workLock) {
                if (!this.isRunning()) {
                    WorkQueue.this.pendingJobs = this.removeFromList(WorkQueue.this.pendingJobs);
                    WorkQueue.this.pendingJobs = this.addToList(WorkQueue.this.pendingJobs, true);
                }
            }
        }
        
        WorkNode removeFromList(WorkNode next) {
            assert this.next != null;
            assert this.prev != null;
            if (next == this) {
                if (this.next == this) {
                    next = null;
                }
                else {
                    next = this.next;
                }
            }
            this.next.prev = this.prev;
            this.prev.next = this.next;
            this.prev = null;
            this.next = null;
            return next;
        }
        
        void setIsRunning(final boolean isRunning) {
            this.isRunning = isRunning;
        }
        
        void verify(final boolean b) {
            assert this.prev.next == this;
            assert this.next.prev == this;
            assert this.isRunning() == b;
        }
    }
}
