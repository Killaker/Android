package okhttp3.internal.io;

import java.lang.ref.*;
import java.util.*;
import java.security.cert.*;
import okhttp3.internal.tls.*;
import javax.net.ssl.*;
import java.util.concurrent.*;
import okhttp3.*;
import okio.*;
import okhttp3.internal.*;
import okhttp3.internal.http.*;
import java.net.*;
import okhttp3.internal.framed.*;
import java.io.*;

public final class RealConnection extends Listener implements Connection
{
    public int allocationLimit;
    public final List<Reference<StreamAllocation>> allocations;
    public volatile FramedConnection framedConnection;
    private Handshake handshake;
    public long idleAtNanos;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    public BufferedSink sink;
    public Socket socket;
    public BufferedSource source;
    public int successCount;
    
    public RealConnection(final Route route) {
        this.allocations = new ArrayList<Reference<StreamAllocation>>();
        this.idleAtNanos = Long.MAX_VALUE;
        this.route = route;
    }
    
    private void connectSocket(final int n, final int soTimeout, final int n2, final ConnectionSpecSelector connectionSpecSelector) throws IOException {
        while (true) {
            this.rawSocket.setSoTimeout(soTimeout);
            while (true) {
                try {
                    Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), n);
                    this.source = Okio.buffer(Okio.source(this.rawSocket));
                    this.sink = Okio.buffer(Okio.sink(this.rawSocket));
                    if (this.route.address().sslSocketFactory() != null) {
                        this.connectTls(soTimeout, n2, connectionSpecSelector);
                        if (this.protocol == Protocol.SPDY_3 || this.protocol == Protocol.HTTP_2) {
                            this.socket.setSoTimeout(0);
                            final FramedConnection build = new FramedConnection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).protocol(this.protocol).listener(this).build();
                            build.sendConnectionPreface();
                            this.allocationLimit = build.maxConcurrentStreams();
                            this.framedConnection = build;
                            return;
                        }
                        break;
                    }
                }
                catch (ConnectException ex) {
                    throw new ConnectException("Failed to connect to " + this.route.socketAddress());
                }
                this.protocol = Protocol.HTTP_1_1;
                this.socket = this.rawSocket;
                continue;
            }
        }
        this.allocationLimit = 1;
    }
    
    private void connectTls(final int n, final int n2, final ConnectionSpecSelector connectionSpecSelector) throws IOException {
        if (this.route.requiresTunnel()) {
            this.createTunnel(n, n2);
        }
        final Address address = this.route.address();
        final SSLSocketFactory sslSocketFactory = address.sslSocketFactory();
        SSLSocket socket = null;
        ConnectionSpec configureSecureSocket;
        Handshake value;
        try {
            socket = (SSLSocket)sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            configureSecureSocket = connectionSpecSelector.configureSecureSocket(socket);
            if (configureSecureSocket.supportsTlsExtensions()) {
                Platform.get().configureTlsExtensions(socket, address.url().host(), address.protocols());
            }
            socket.startHandshake();
            value = Handshake.get(socket.getSession());
            if (!address.hostnameVerifier().verify(address.url().host(), socket.getSession())) {
                final X509Certificate x509Certificate = value.peerCertificates().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified:" + "\n    certificate: " + CertificatePinner.pin(x509Certificate) + "\n    DN: " + x509Certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + OkHostnameVerifier.allSubjectAltNames(x509Certificate));
            }
        }
        catch (AssertionError assertionError) {
            try {
                if (Util.isAndroidGetsocknameError(assertionError)) {
                    throw new IOException(assertionError);
                }
                throw assertionError;
            }
            finally {
                if (socket != null) {
                    Platform.get().afterHandshake(socket);
                }
                if (!false) {
                    Util.closeQuietly(socket);
                }
            }
        }
        address.certificatePinner().check(address.url().host(), value.peerCertificates());
        String selectedProtocol;
        if (configureSecureSocket.supportsTlsExtensions()) {
            selectedProtocol = Platform.get().getSelectedProtocol(socket);
        }
        else {
            selectedProtocol = null;
        }
        this.socket = socket;
        this.source = Okio.buffer(Okio.source(this.socket));
        this.sink = Okio.buffer(Okio.sink(this.socket));
        this.handshake = value;
        Protocol protocol;
        if (selectedProtocol != null) {
            protocol = Protocol.get(selectedProtocol);
        }
        else {
            protocol = Protocol.HTTP_1_1;
        }
        this.protocol = protocol;
        if (socket != null) {
            Platform.get().afterHandshake(socket);
        }
        if (!true) {
            Util.closeQuietly(socket);
        }
    }
    
    private void createTunnel(final int n, final int n2) throws IOException {
        Request request = this.createTunnelRequest();
        final HttpUrl url = request.url();
        final String string = "CONNECT " + url.host() + ":" + url.port() + " HTTP/1.1";
        do {
            final Http1xStream http1xStream = new Http1xStream(null, this.source, this.sink);
            this.source.timeout().timeout(n, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout(n2, TimeUnit.MILLISECONDS);
            http1xStream.writeRequest(request.headers(), string);
            http1xStream.finishRequest();
            final Response build = http1xStream.readResponse().request(request).build();
            long contentLength = OkHeaders.contentLength(build);
            if (contentLength == -1L) {
                contentLength = 0L;
            }
            final Source fixedLengthSource = http1xStream.newFixedLengthSource(contentLength);
            Util.skipAll(fixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            fixedLengthSource.close();
            switch (build.code()) {
                default: {
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
                }
                case 200: {
                    if (!this.source.buffer().exhausted() || !this.sink.buffer().exhausted()) {
                        throw new IOException("TLS tunnel buffered too many bytes!");
                    }
                    return;
                }
                case 407: {
                    request = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    continue;
                }
            }
        } while (request != null);
        throw new IOException("Failed to authenticate with proxy");
    }
    
    private Request createTunnelRequest() throws IOException {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url())).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }
    
    public void cancel() {
        Util.closeQuietly(this.rawSocket);
    }
    
    public void connect(final int n, final int n2, final int n3, final List<ConnectionSpec> list, final boolean b) throws RouteException {
        if (this.protocol != null) {
            throw new IllegalStateException("already connected");
        }
        final ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(list);
        final Proxy proxy = this.route.proxy();
        final Address address = this.route.address();
        final SSLSocketFactory sslSocketFactory = this.route.address().sslSocketFactory();
        RouteException ex = null;
    Label_0285:
        while (true) {
            if (sslSocketFactory != null) {
                break Label_0149;
            }
            final boolean contains = list.contains(ConnectionSpec.CLEARTEXT);
            ex = null;
            if (!contains) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not supported: " + list));
            }
            break Label_0149;
            try {
                while (true) {
                    Socket socket = new Socket(proxy);
                    while (true) {
                        this.rawSocket = socket;
                        this.connectSocket(n, n2, n3, connectionSpecSelector);
                        if (this.protocol != null) {
                            break Label_0285;
                        }
                        if (proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.HTTP) {
                            break;
                        }
                        socket = address.socketFactory().createSocket();
                    }
                }
            }
            catch (IOException ex2) {
                Util.closeQuietly(this.socket);
                Util.closeQuietly(this.rawSocket);
                this.socket = null;
                this.rawSocket = null;
                this.source = null;
                this.sink = null;
                this.handshake = null;
                this.protocol = null;
                if (ex == null) {
                    ex = new RouteException(ex2);
                }
                else {
                    ex.addConnectException(ex2);
                }
                if (!b || !connectionSpecSelector.connectionFailed(ex2)) {
                    throw ex;
                }
                continue;
            }
            break;
        }
    }
    
    @Override
    public Handshake handshake() {
        return this.handshake;
    }
    
    boolean isConnected() {
        return this.protocol != null;
    }
    
    public boolean isHealthy(final boolean b) {
        boolean b2 = true;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            b2 = false;
        }
        else if (this.framedConnection == null && b) {
            try {
                final int soTimeout = this.socket.getSoTimeout();
                try {
                    this.socket.setSoTimeout(1);
                    return !this.source.exhausted() && b2;
                }
                finally {
                    this.socket.setSoTimeout(soTimeout);
                }
            }
            catch (IOException ex) {
                return false;
            }
            catch (SocketTimeoutException ex2) {
                return b2;
            }
        }
        return b2;
    }
    
    public boolean isMultiplexed() {
        return this.framedConnection != null;
    }
    
    @Override
    public void onSettings(final FramedConnection framedConnection) {
        this.allocationLimit = framedConnection.maxConcurrentStreams();
    }
    
    @Override
    public void onStream(final FramedStream framedStream) throws IOException {
        framedStream.close(ErrorCode.REFUSED_STREAM);
    }
    
    @Override
    public Protocol protocol() {
        if (this.framedConnection != null) {
            return this.framedConnection.getProtocol();
        }
        if (this.protocol != null) {
            return this.protocol;
        }
        return Protocol.HTTP_1_1;
    }
    
    @Override
    public Route route() {
        return this.route;
    }
    
    @Override
    public Socket socket() {
        return this.socket;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Connection{").append(this.route.address().url().host()).append(":").append(this.route.address().url().port()).append(", proxy=").append(this.route.proxy()).append(" hostAddress=").append(this.route.socketAddress()).append(" cipherSuite=");
        Serializable cipherSuite;
        if (this.handshake != null) {
            cipherSuite = this.handshake.cipherSuite();
        }
        else {
            cipherSuite = "none";
        }
        return append.append(cipherSuite).append(" protocol=").append(this.protocol).append('}').toString();
    }
}
