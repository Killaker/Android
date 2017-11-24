package bolts;

import java.io.*;
import java.util.concurrent.*;
import java.util.*;

public class CancellationTokenSource implements Closeable
{
    private boolean cancellationRequested;
    private boolean closed;
    private final ScheduledExecutorService executor;
    private final Object lock;
    private final List<CancellationTokenRegistration> registrations;
    private ScheduledFuture<?> scheduledCancellation;
    
    public CancellationTokenSource() {
        this.lock = new Object();
        this.registrations = new ArrayList<CancellationTokenRegistration>();
        this.executor = BoltsExecutors.scheduled();
    }
    
    private void cancelAfter(final long n, final TimeUnit timeUnit) {
        if (n < -1L) {
            throw new IllegalArgumentException("Delay must be >= -1");
        }
        if (n == 0L) {
            this.cancel();
            return;
        }
        synchronized (this.lock) {
            if (this.cancellationRequested) {
                return;
            }
        }
        this.cancelScheduledCancellation();
        if (n != -1L) {
            this.scheduledCancellation = this.executor.schedule(new Runnable() {
                @Override
                public void run() {
                    synchronized (CancellationTokenSource.this.lock) {
                        CancellationTokenSource.this.scheduledCancellation = null;
                        // monitorexit(CancellationTokenSource.access$000(this.this$0))
                        CancellationTokenSource.this.cancel();
                    }
                }
            }, n, timeUnit);
        }
    }
    // monitorexit(o)
    
    private void cancelScheduledCancellation() {
        if (this.scheduledCancellation != null) {
            this.scheduledCancellation.cancel(true);
            this.scheduledCancellation = null;
        }
    }
    
    private void notifyListeners(final List<CancellationTokenRegistration> list) {
        final Iterator<CancellationTokenRegistration> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().runAction();
        }
    }
    
    private void throwIfClosed() {
        if (this.closed) {
            throw new IllegalStateException("Object already closed");
        }
    }
    
    public void cancel() {
        synchronized (this.lock) {
            this.throwIfClosed();
            if (this.cancellationRequested) {
                return;
            }
            this.cancelScheduledCancellation();
            this.cancellationRequested = true;
            final ArrayList<CancellationTokenRegistration> list = new ArrayList<CancellationTokenRegistration>(this.registrations);
            // monitorexit(this.lock)
            this.notifyListeners(list);
        }
    }
    
    public void cancelAfter(final long n) {
        this.cancelAfter(n, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void close() {
        synchronized (this.lock) {
            if (this.closed) {
                return;
            }
            this.cancelScheduledCancellation();
            final Iterator<CancellationTokenRegistration> iterator = this.registrations.iterator();
            while (iterator.hasNext()) {
                iterator.next().close();
            }
        }
        this.registrations.clear();
        this.closed = true;
    }
    // monitorexit(o)
    
    public CancellationToken getToken() {
        synchronized (this.lock) {
            this.throwIfClosed();
            return new CancellationToken(this);
        }
    }
    
    public boolean isCancellationRequested() {
        synchronized (this.lock) {
            this.throwIfClosed();
            return this.cancellationRequested;
        }
    }
    
    CancellationTokenRegistration register(final Runnable runnable) {
        synchronized (this.lock) {
            this.throwIfClosed();
            final CancellationTokenRegistration cancellationTokenRegistration = new CancellationTokenRegistration(this, runnable);
            if (this.cancellationRequested) {
                cancellationTokenRegistration.runAction();
            }
            else {
                this.registrations.add(cancellationTokenRegistration);
            }
            return cancellationTokenRegistration;
        }
    }
    
    void throwIfCancellationRequested() throws CancellationException {
        synchronized (this.lock) {
            this.throwIfClosed();
            if (this.cancellationRequested) {
                throw new CancellationException();
            }
        }
    }
    // monitorexit(o)
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s@%s[cancellationRequested=%s]", this.getClass().getName(), Integer.toHexString(this.hashCode()), Boolean.toString(this.isCancellationRequested()));
    }
    
    void unregister(final CancellationTokenRegistration cancellationTokenRegistration) {
        synchronized (this.lock) {
            this.throwIfClosed();
            this.registrations.remove(cancellationTokenRegistration);
        }
    }
}
