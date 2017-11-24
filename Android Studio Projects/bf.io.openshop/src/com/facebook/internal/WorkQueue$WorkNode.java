package com.facebook.internal;

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
        synchronized (WorkQueue.access$100(WorkQueue.this)) {
            if (!this.isRunning()) {
                WorkQueue.access$202(WorkQueue.this, this.removeFromList(WorkQueue.access$200(WorkQueue.this)));
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
        synchronized (WorkQueue.access$100(WorkQueue.this)) {
            if (!this.isRunning()) {
                WorkQueue.access$202(WorkQueue.this, this.removeFromList(WorkQueue.access$200(WorkQueue.this)));
                WorkQueue.access$202(WorkQueue.this, this.addToList(WorkQueue.access$200(WorkQueue.this), true));
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
