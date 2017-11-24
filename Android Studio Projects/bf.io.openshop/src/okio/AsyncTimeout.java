package okio;

import java.io.*;

public class AsyncTimeout extends Timeout
{
    private static AsyncTimeout head;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;
    
    private static AsyncTimeout awaitTimeout() throws InterruptedException {
        synchronized (AsyncTimeout.class) {
            AsyncTimeout next = AsyncTimeout.head.next;
            if (next == null) {
                AsyncTimeout.class.wait();
                next = null;
            }
            else {
                final long remainingNanos = next.remainingNanos(System.nanoTime());
                if (remainingNanos > 0L) {
                    final long n = remainingNanos / 1000000L;
                    AsyncTimeout.class.wait(n, (int)(remainingNanos - n * 1000000L));
                    next = null;
                }
                else {
                    AsyncTimeout.head.next = next.next;
                    next.next = null;
                }
            }
            return next;
        }
    }
    
    private static boolean cancelScheduledTimeout(final AsyncTimeout asyncTimeout) {
        synchronized (AsyncTimeout.class) {
            for (AsyncTimeout asyncTimeout2 = AsyncTimeout.head; asyncTimeout2 != null; asyncTimeout2 = asyncTimeout2.next) {
                if (asyncTimeout2.next == asyncTimeout) {
                    asyncTimeout2.next = asyncTimeout.next;
                    asyncTimeout.next = null;
                    return false;
                }
            }
            return true;
        }
    }
    
    private long remainingNanos(final long n) {
        return this.timeoutAt - n;
    }
    
    private static void scheduleTimeout(final AsyncTimeout next, final long n, final boolean b) {
    Label_0075_Outer:
        while (true) {
            while (true) {
                AsyncTimeout asyncTimeout = null;
                Label_0183: {
                    while (true) {
                        Label_0160: {
                            synchronized (AsyncTimeout.class) {
                                if (AsyncTimeout.head == null) {
                                    AsyncTimeout.head = new AsyncTimeout();
                                    new Watchdog().start();
                                }
                                final long nanoTime = System.nanoTime();
                                if (n != 0L && b) {
                                    next.timeoutAt = nanoTime + Math.min(n, next.deadlineNanoTime() - nanoTime);
                                }
                                else {
                                    if (n == 0L) {
                                        break Label_0160;
                                    }
                                    next.timeoutAt = nanoTime + n;
                                }
                                final long remainingNanos = next.remainingNanos(nanoTime);
                                asyncTimeout = AsyncTimeout.head;
                                if (asyncTimeout.next == null || remainingNanos < asyncTimeout.next.remainingNanos(nanoTime)) {
                                    next.next = asyncTimeout.next;
                                    asyncTimeout.next = next;
                                    if (asyncTimeout == AsyncTimeout.head) {
                                        AsyncTimeout.class.notify();
                                    }
                                    return;
                                }
                                break Label_0183;
                            }
                        }
                        if (b) {
                            next.timeoutAt = next.deadlineNanoTime();
                            continue Label_0075_Outer;
                        }
                        break;
                    }
                    break;
                }
                asyncTimeout = asyncTimeout.next;
                continue;
            }
        }
        throw new AssertionError();
    }
    
    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        final long timeoutNanos = this.timeoutNanos();
        final boolean hasDeadline = this.hasDeadline();
        if (timeoutNanos == 0L && !hasDeadline) {
            return;
        }
        this.inQueue = true;
        scheduleTimeout(this, timeoutNanos, hasDeadline);
    }
    
    final IOException exit(final IOException ex) throws IOException {
        if (!this.exit()) {
            return ex;
        }
        return this.newTimeoutException(ex);
    }
    
    final void exit(final boolean b) throws IOException {
        if (this.exit() && b) {
            throw this.newTimeoutException(null);
        }
    }
    
    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }
    
    protected IOException newTimeoutException(final IOException ex) {
        final InterruptedIOException ex2 = new InterruptedIOException("timeout");
        if (ex != null) {
            ex2.initCause(ex);
        }
        return ex2;
    }
    
    public final Sink sink(final Sink sink) {
        return new Sink() {
            @Override
            public void close() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.close();
                    AsyncTimeout.this.exit(true);
                }
                catch (IOException ex) {
                    throw AsyncTimeout.this.exit(ex);
                }
                finally {
                    AsyncTimeout.this.exit(false);
                }
            }
            
            @Override
            public void flush() throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.flush();
                    AsyncTimeout.this.exit(true);
                }
                catch (IOException ex) {
                    throw AsyncTimeout.this.exit(ex);
                }
                finally {
                    AsyncTimeout.this.exit(false);
                }
            }
            
            @Override
            public Timeout timeout() {
                return AsyncTimeout.this;
            }
            
            @Override
            public String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
            
            @Override
            public void write(final Buffer buffer, final long n) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    sink.write(buffer, n);
                    AsyncTimeout.this.exit(true);
                }
                catch (IOException ex) {
                    throw AsyncTimeout.this.exit(ex);
                }
                finally {
                    AsyncTimeout.this.exit(false);
                }
            }
        };
    }
    
    public final Source source(final Source source) {
        return new Source() {
            @Override
            public void close() throws IOException {
                try {
                    source.close();
                    AsyncTimeout.this.exit(true);
                }
                catch (IOException ex) {
                    throw AsyncTimeout.this.exit(ex);
                }
                finally {
                    AsyncTimeout.this.exit(false);
                }
            }
            
            @Override
            public long read(final Buffer buffer, final long n) throws IOException {
                AsyncTimeout.this.enter();
                try {
                    final long read = source.read(buffer, n);
                    AsyncTimeout.this.exit(true);
                    return read;
                }
                catch (IOException ex) {
                    throw AsyncTimeout.this.exit(ex);
                }
                finally {
                    AsyncTimeout.this.exit(false);
                }
            }
            
            @Override
            public Timeout timeout() {
                return AsyncTimeout.this;
            }
            
            @Override
            public String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }
    
    protected void timedOut() {
    }
    
    private static final class Watchdog extends Thread
    {
        public Watchdog() {
            super("Okio Watchdog");
            this.setDaemon(true);
        }
        
        @Override
        public void run() {
            while (true) {
                try {
                    while (true) {
                        final AsyncTimeout access$000 = awaitTimeout();
                        if (access$000 != null) {
                            access$000.timedOut();
                        }
                    }
                }
                catch (InterruptedException ex) {
                    continue;
                }
                break;
            }
        }
    }
}
