package okhttp3;

import okhttp3.internal.io.*;
import java.util.concurrent.*;
import java.lang.ref.*;
import okhttp3.internal.http.*;
import okhttp3.internal.*;
import java.util.*;

public final class ConnectionPool
{
    private static final Executor executor;
    private final Runnable cleanupRunnable;
    boolean cleanupRunning;
    private final Deque<RealConnection> connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    final RouteDatabase routeDatabase;
    
    static {
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp ConnectionPool", true));
    }
    
    public ConnectionPool() {
        this(5, 5L, TimeUnit.MINUTES);
    }
    
    public ConnectionPool(final int maxIdleConnections, final long n, final TimeUnit timeUnit) {
        this.cleanupRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    final long cleanup = ConnectionPool.this.cleanup(System.nanoTime());
                    if (cleanup == -1L) {
                        break;
                    }
                    if (cleanup <= 0L) {
                        continue;
                    }
                    final long n = cleanup / 1000000L;
                    final long n2 = cleanup - n * 1000000L;
                    final ConnectionPool this$0 = ConnectionPool.this;
                    // monitorenter(this$0)
                    while (true) {
                        try {
                            try {
                                ConnectionPool.this.wait(n, (int)n2);
                            }
                            finally {
                            }
                            // monitorexit(this$0)
                        }
                        catch (InterruptedException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        };
        this.connections = new ArrayDeque<RealConnection>();
        this.routeDatabase = new RouteDatabase();
        this.maxIdleConnections = maxIdleConnections;
        this.keepAliveDurationNs = timeUnit.toNanos(n);
        if (n <= 0L) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + n);
        }
    }
    
    private int pruneAndGetAllocationCount(final RealConnection realConnection, final long n) {
        final List<Reference<StreamAllocation>> allocations = realConnection.allocations;
        int i = 0;
        while (i < allocations.size()) {
            if (allocations.get(i).get() != null) {
                ++i;
            }
            else {
                Internal.logger.warning("A connection to " + realConnection.route().address().url() + " was leaked. Did you forget to close a response body?");
                allocations.remove(i);
                realConnection.noNewStreams = true;
                if (allocations.isEmpty()) {
                    realConnection.idleAtNanos = n - this.keepAliveDurationNs;
                    return 0;
                }
                continue;
            }
        }
        return allocations.size();
    }
    
    long cleanup(final long n) {
        int n2 = 0;
        int n3 = 0;
        RealConnection realConnection = null;
        long n4 = Long.MIN_VALUE;
        synchronized (this) {
            for (final RealConnection realConnection2 : this.connections) {
                if (this.pruneAndGetAllocationCount(realConnection2, n) > 0) {
                    ++n2;
                }
                else {
                    ++n3;
                    final long n5 = n - realConnection2.idleAtNanos;
                    if (n5 <= n4) {
                        continue;
                    }
                    n4 = n5;
                    realConnection = realConnection2;
                }
            }
            if (n4 >= this.keepAliveDurationNs || n3 > this.maxIdleConnections) {
                this.connections.remove(realConnection);
                // monitorexit(this)
                Util.closeQuietly(realConnection.socket());
                return 0L;
            }
            if (n3 > 0) {
                return this.keepAliveDurationNs - n4;
            }
        }
        if (n2 > 0) {
            // monitorexit(this)
            return this.keepAliveDurationNs;
        }
        this.cleanupRunning = false;
        // monitorexit(this)
        return -1L;
    }
    
    boolean connectionBecameIdle(final RealConnection realConnection) {
        assert Thread.holdsLock(this);
        if (realConnection.noNewStreams || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        this.notifyAll();
        return false;
    }
    
    public int connectionCount() {
        synchronized (this) {
            return this.connections.size();
        }
    }
    
    public void evictAll() {
        final ArrayList<RealConnection> list = new ArrayList<RealConnection>();
        synchronized (this) {
            final Iterator<RealConnection> iterator = this.connections.iterator();
            while (iterator.hasNext()) {
                final RealConnection realConnection = iterator.next();
                if (realConnection.allocations.isEmpty()) {
                    realConnection.noNewStreams = true;
                    list.add(realConnection);
                    iterator.remove();
                }
            }
        }
        // monitorexit(this)
        final Iterator<Object> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            Util.closeQuietly(iterator2.next().socket());
        }
    }
    
    RealConnection get(final Address address, final StreamAllocation streamAllocation) {
        assert Thread.holdsLock(this);
        for (final RealConnection realConnection : this.connections) {
            if (realConnection.allocations.size() < realConnection.allocationLimit && address.equals(realConnection.route().address) && !realConnection.noNewStreams) {
                streamAllocation.acquire(realConnection);
                return realConnection;
            }
        }
        return null;
    }
    
    public int idleConnectionCount() {
        // monitorenter(this)
        int n = 0;
        try {
            final Iterator<RealConnection> iterator = this.connections.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().allocations.isEmpty()) {
                    ++n;
                }
            }
            return n;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    void put(final RealConnection realConnection) {
        assert Thread.holdsLock(this);
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            ConnectionPool.executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }
}
