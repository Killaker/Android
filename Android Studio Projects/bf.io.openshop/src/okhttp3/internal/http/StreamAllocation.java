package okhttp3.internal.http;

import okhttp3.internal.io.*;
import okhttp3.*;
import java.io.*;
import java.net.*;
import java.security.cert.*;
import javax.net.ssl.*;
import okhttp3.internal.*;
import java.lang.ref.*;
import java.util.concurrent.*;
import okio.*;

public final class StreamAllocation
{
    public final Address address;
    private boolean canceled;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private boolean released;
    private Route route;
    private RouteSelector routeSelector;
    private HttpStream stream;
    
    public StreamAllocation(final ConnectionPool connectionPool, final Address address) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.routeSelector = new RouteSelector(address, this.routeDatabase());
    }
    
    private void deallocate(final boolean b, final boolean b2, final boolean b3) {
        final ConnectionPool connectionPool = this.connectionPool;
        // monitorenter(connectionPool)
        Label_0018: {
            if (!b3) {
                break Label_0018;
            }
            try {
                this.stream = null;
                if (b2) {
                    this.released = true;
                }
                final RealConnection connection = this.connection;
                RealConnection connection2 = null;
                Label_0166: {
                    if (connection != null) {
                        if (b) {
                            this.connection.noNewStreams = true;
                        }
                        final HttpStream stream = this.stream;
                        connection2 = null;
                        if (stream == null) {
                            if (!this.released) {
                                final boolean noNewStreams = this.connection.noNewStreams;
                                connection2 = null;
                                if (!noNewStreams) {
                                    break Label_0166;
                                }
                            }
                            this.release(this.connection);
                            final boolean empty = this.connection.allocations.isEmpty();
                            connection2 = null;
                            if (empty) {
                                this.connection.idleAtNanos = System.nanoTime();
                                final boolean connectionBecameIdle = Internal.instance.connectionBecameIdle(this.connectionPool, this.connection);
                                connection2 = null;
                                if (connectionBecameIdle) {
                                    connection2 = this.connection;
                                }
                            }
                            this.connection = null;
                        }
                    }
                }
                // monitorexit(connectionPool)
                if (connection2 != null) {
                    Util.closeQuietly(connection2.socket());
                }
            }
            finally {
            }
            // monitorexit(connectionPool)
        }
    }
    
    private RealConnection findConnection(final int n, final int n2, final int n3, final boolean b) throws IOException, RouteException {
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
        }
        if (this.stream != null) {
            throw new IllegalStateException("stream != null");
        }
        if (this.canceled) {
            throw new IOException("Canceled");
        }
        final RealConnection connection = this.connection;
        if (connection != null && !connection.noNewStreams) {
            // monitorexit(connectionPool)
            return connection;
        }
        final RealConnection value = Internal.instance.get(this.connectionPool, this.address, this);
        if (value != null) {
            this.connection = value;
            // monitorexit(connectionPool)
            return value;
        }
        Route route = this.route;
        // monitorexit(connectionPool)
        while (true) {
            if (route == null) {
                route = this.routeSelector.next();
                final RealConnection connection2;
                synchronized (this.connectionPool) {
                    this.route = route;
                    // monitorexit(this.connectionPool)
                    connection2 = new RealConnection(route);
                    this.acquire(connection2);
                    synchronized (this.connectionPool) {
                        Internal.instance.put(this.connectionPool, connection2);
                        this.connection = connection2;
                        if (this.canceled) {
                            throw new IOException("Canceled");
                        }
                    }
                }
                // monitorexit(connectionPool2)
                connection2.connect(n, n2, n3, this.address.connectionSpecs(), b);
                this.routeDatabase().connected(connection2.route());
                return connection2;
            }
            continue;
        }
    }
    
    private RealConnection findHealthyConnection(final int n, final int n2, final int n3, final boolean b, final boolean b2) throws IOException, RouteException {
        RealConnection connection;
        while (true) {
            connection = this.findConnection(n, n2, n3, b);
            synchronized (this.connectionPool) {
                if (connection.successCount == 0) {
                    return connection;
                }
                // monitorexit(this.connectionPool)
                if (!connection.isHealthy(b2)) {
                    this.connectionFailed(new IOException());
                    continue;
                }
            }
            break;
        }
        return connection;
    }
    
    private boolean isRecoverable(final IOException ex) {
        if (!(ex instanceof ProtocolException)) {
            if (ex instanceof InterruptedIOException) {
                return ex instanceof SocketTimeoutException;
            }
            if ((!(ex instanceof SSLHandshakeException) || !(ex.getCause() instanceof CertificateException)) && !(ex instanceof SSLPeerUnverifiedException)) {
                return true;
            }
        }
        return false;
    }
    
    private void release(final RealConnection realConnection) {
        for (int i = 0; i < realConnection.allocations.size(); ++i) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }
    
    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }
    
    public void acquire(final RealConnection realConnection) {
        realConnection.allocations.add(new WeakReference<StreamAllocation>(this));
    }
    
    public void cancel() {
        RealConnection connection;
        while (true) {
            synchronized (this.connectionPool) {
                this.canceled = true;
                final HttpStream stream = this.stream;
                connection = this.connection;
                // monitorexit(this.connectionPool)
                if (stream != null) {
                    stream.cancel();
                    return;
                }
            }
            if (connection != null) {
                break;
            }
            return;
        }
        connection.cancel();
    }
    
    public RealConnection connection() {
        synchronized (this) {
            return this.connection;
        }
    }
    
    public void connectionFailed(final IOException ex) {
        synchronized (this.connectionPool) {
            if (this.connection != null && this.connection.successCount == 0) {
                if (this.route != null && ex != null) {
                    this.routeSelector.connectFailed(this.route, ex);
                }
                this.route = null;
            }
            // monitorexit(this.connectionPool)
            this.deallocate(true, false, true);
        }
    }
    
    public HttpStream newStream(final int n, final int soTimeout, final int n2, final boolean b, final boolean b2) throws RouteException, IOException {
        try {
            final RealConnection healthyConnection = this.findHealthyConnection(n, soTimeout, n2, b, b2);
            Label_0057: {
                if (healthyConnection.framedConnection == null) {
                    break Label_0057;
                }
                HttpStream stream = new Http2xStream(this, healthyConnection.framedConnection);
                while (true) {
                    synchronized (this.connectionPool) {
                        return this.stream = stream;
                        healthyConnection.socket().setSoTimeout(soTimeout);
                        healthyConnection.source.timeout().timeout(soTimeout, TimeUnit.MILLISECONDS);
                        healthyConnection.sink.timeout().timeout(n2, TimeUnit.MILLISECONDS);
                        stream = new Http1xStream(this, healthyConnection.source, healthyConnection.sink);
                    }
                }
            }
        }
        catch (IOException ex) {
            throw new RouteException(ex);
        }
    }
    
    public void noNewStreams() {
        this.deallocate(true, false, false);
    }
    
    public boolean recover(final IOException ex, final Sink sink) {
        boolean b = true;
        if (this.connection != null) {
            this.connectionFailed(ex);
        }
        final boolean b2 = (sink == null || sink instanceof RetryableSink) && b;
        if ((this.routeSelector != null && !this.routeSelector.hasNext()) || !this.isRecoverable(ex) || !b2) {
            b = false;
        }
        return b;
    }
    
    public void release() {
        this.deallocate(false, true, false);
    }
    
    public HttpStream stream() {
        synchronized (this.connectionPool) {
            return this.stream;
        }
    }
    
    public void streamFinished(final boolean b, final HttpStream httpStream) {
        final ConnectionPool connectionPool = this.connectionPool;
        // monitorenter(connectionPool)
        while (true) {
            if (httpStream != null) {
                try {
                    if (httpStream != this.stream) {
                        throw new IllegalStateException("expected " + this.stream + " but was " + httpStream);
                    }
                }
                finally {
                }
                // monitorexit(connectionPool)
                if (!b) {
                    final RealConnection connection = this.connection;
                    ++connection.successCount;
                }
                // monitorexit(connectionPool)
                this.deallocate(b, false, true);
                return;
            }
            continue;
        }
    }
    
    @Override
    public String toString() {
        return this.address.toString();
    }
}
