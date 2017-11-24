package okhttp3.internal.http;

import java.util.concurrent.*;
import javax.net.ssl.*;
import okhttp3.internal.*;
import okio.*;
import java.util.*;
import java.io.*;
import java.net.*;
import okhttp3.internal.io.*;
import okhttp3.*;

public final class HttpEngine
{
    private static final ResponseBody EMPTY_BODY;
    public static final int MAX_FOLLOW_UPS = 20;
    public final boolean bufferRequestBody;
    private BufferedSink bufferedRequestBody;
    private Response cacheResponse;
    private CacheStrategy cacheStrategy;
    private final boolean callerWritesRequestBody;
    final OkHttpClient client;
    private final boolean forWebSocket;
    private HttpStream httpStream;
    private Request networkRequest;
    private final Response priorResponse;
    private Sink requestBodyOut;
    long sentRequestMillis;
    private CacheRequest storeRequest;
    public final StreamAllocation streamAllocation;
    private boolean transparentGzip;
    private final Request userRequest;
    private Response userResponse;
    
    static {
        EMPTY_BODY = new ResponseBody() {
            @Override
            public long contentLength() {
                return 0L;
            }
            
            @Override
            public MediaType contentType() {
                return null;
            }
            
            @Override
            public BufferedSource source() {
                return new Buffer();
            }
        };
    }
    
    public HttpEngine(final OkHttpClient client, final Request userRequest, final boolean bufferRequestBody, final boolean callerWritesRequestBody, final boolean forWebSocket, StreamAllocation streamAllocation, final RetryableSink requestBodyOut, final Response priorResponse) {
        this.sentRequestMillis = -1L;
        this.client = client;
        this.userRequest = userRequest;
        this.bufferRequestBody = bufferRequestBody;
        this.callerWritesRequestBody = callerWritesRequestBody;
        this.forWebSocket = forWebSocket;
        if (streamAllocation == null) {
            streamAllocation = new StreamAllocation(client.connectionPool(), createAddress(client, userRequest));
        }
        this.streamAllocation = streamAllocation;
        this.requestBodyOut = requestBodyOut;
        this.priorResponse = priorResponse;
    }
    
    private Response cacheWritingResponse(final CacheRequest cacheRequest, final Response response) throws IOException {
        if (cacheRequest != null) {
            final Sink body = cacheRequest.body();
            if (body != null) {
                return response.newBuilder().body(new RealResponseBody(response.headers(), Okio.buffer(new Source() {
                    boolean cacheRequestClosed;
                    final /* synthetic */ BufferedSink val$cacheBody = Okio.buffer(body);
                    final /* synthetic */ BufferedSource val$source = response.body().source();
                    
                    @Override
                    public void close() throws IOException {
                        if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                            this.cacheRequestClosed = true;
                            cacheRequest.abort();
                        }
                        this.val$source.close();
                    }
                    
                    @Override
                    public long read(final Buffer buffer, final long n) throws IOException {
                        long read;
                        try {
                            read = this.val$source.read(buffer, n);
                            if (read == -1L) {
                                if (!this.cacheRequestClosed) {
                                    this.cacheRequestClosed = true;
                                    this.val$cacheBody.close();
                                }
                                return -1L;
                            }
                        }
                        catch (IOException ex) {
                            if (!this.cacheRequestClosed) {
                                this.cacheRequestClosed = true;
                                cacheRequest.abort();
                            }
                            throw ex;
                        }
                        buffer.copyTo(this.val$cacheBody.buffer(), buffer.size() - read, read);
                        this.val$cacheBody.emitCompleteSegments();
                        return read;
                    }
                    
                    @Override
                    public Timeout timeout() {
                        return this.val$source.timeout();
                    }
                }))).build();
            }
        }
        return response;
    }
    
    private static Headers combine(final Headers headers, final Headers headers2) throws IOException {
        final Headers.Builder builder = new Headers.Builder();
        for (int i = 0; i < headers.size(); ++i) {
            final String name = headers.name(i);
            final String value = headers.value(i);
            if ((!"Warning".equalsIgnoreCase(name) || !value.startsWith("1")) && (!OkHeaders.isEndToEnd(name) || headers2.get(name) == null)) {
                builder.add(name, value);
            }
        }
        for (int j = 0; j < headers2.size(); ++j) {
            final String name2 = headers2.name(j);
            if (!"Content-Length".equalsIgnoreCase(name2) && OkHeaders.isEndToEnd(name2)) {
                builder.add(name2, headers2.value(j));
            }
        }
        return builder.build();
    }
    
    private HttpStream connect() throws RouteException, RequestException, IOException {
        return this.streamAllocation.newStream(this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis(), this.client.retryOnConnectionFailure(), !this.networkRequest.method().equals("GET"));
    }
    
    private String cookieHeader(final List<Cookie> list) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            if (i > 0) {
                sb.append("; ");
            }
            final Cookie cookie = list.get(i);
            sb.append(cookie.name()).append('=').append(cookie.value());
        }
        return sb.toString();
    }
    
    private static Address createAddress(final OkHttpClient okHttpClient, final Request request) {
        final boolean https = request.isHttps();
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (https) {
            sslSocketFactory = okHttpClient.sslSocketFactory();
            hostnameVerifier = okHttpClient.hostnameVerifier();
            certificatePinner = okHttpClient.certificatePinner();
        }
        return new Address(request.url().host(), request.url().port(), okHttpClient.dns(), okHttpClient.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, okHttpClient.proxyAuthenticator(), okHttpClient.proxy(), okHttpClient.protocols(), okHttpClient.connectionSpecs(), okHttpClient.proxySelector());
    }
    
    public static boolean hasBody(final Response response) {
        if (!response.request().method().equals("HEAD")) {
            final int code = response.code();
            if ((code < 100 || code >= 200) && code != 204 && code != 304) {
                return true;
            }
            if (OkHeaders.contentLength(response) != -1L || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
                return true;
            }
        }
        return false;
    }
    
    private void maybeCache() throws IOException {
        final InternalCache internalCache = Internal.instance.internalCache(this.client);
        if (internalCache != null) {
            if (!CacheStrategy.isCacheable(this.userResponse, this.networkRequest)) {
                if (!HttpMethod.invalidatesCache(this.networkRequest.method())) {
                    return;
                }
                try {
                    internalCache.remove(this.networkRequest);
                    return;
                }
                catch (IOException ex) {
                    return;
                }
            }
            this.storeRequest = internalCache.put(stripBody(this.userResponse));
        }
    }
    
    private Request networkRequest(final Request request) throws IOException {
        final Request.Builder builder = request.newBuilder();
        if (request.header("Host") == null) {
            builder.header("Host", Util.hostHeader(request.url()));
        }
        if (request.header("Connection") == null) {
            builder.header("Connection", "Keep-Alive");
        }
        if (request.header("Accept-Encoding") == null) {
            this.transparentGzip = true;
            builder.header("Accept-Encoding", "gzip");
        }
        final List<Cookie> loadForRequest = this.client.cookieJar().loadForRequest(request.url());
        if (!loadForRequest.isEmpty()) {
            builder.header("Cookie", this.cookieHeader(loadForRequest));
        }
        if (request.header("User-Agent") == null) {
            builder.header("User-Agent", Version.userAgent());
        }
        return builder.build();
    }
    
    private Response readNetworkResponse() throws IOException {
        this.httpStream.finishRequest();
        Response response = this.httpStream.readResponseHeaders().request(this.networkRequest).handshake(this.streamAllocation.connection().handshake()).header(OkHeaders.SENT_MILLIS, Long.toString(this.sentRequestMillis)).header(OkHeaders.RECEIVED_MILLIS, Long.toString(System.currentTimeMillis())).build();
        if (!this.forWebSocket) {
            response = response.newBuilder().body(this.httpStream.openResponseBody(response)).build();
        }
        if ("close".equalsIgnoreCase(response.request().header("Connection")) || "close".equalsIgnoreCase(response.header("Connection"))) {
            this.streamAllocation.noNewStreams();
        }
        return response;
    }
    
    private static Response stripBody(Response build) {
        if (build != null && build.body() != null) {
            build = build.newBuilder().body(null).build();
        }
        return build;
    }
    
    private Response unzip(final Response response) throws IOException {
        if (this.transparentGzip && "gzip".equalsIgnoreCase(this.userResponse.header("Content-Encoding")) && response.body() != null) {
            final GzipSource gzipSource = new GzipSource(response.body().source());
            final Headers build = response.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build();
            return response.newBuilder().headers(build).body(new RealResponseBody(build, Okio.buffer(gzipSource))).build();
        }
        return response;
    }
    
    private static boolean validate(final Response response, final Response response2) {
        if (response2.code() != 304) {
            final Date date = response.headers().getDate("Last-Modified");
            if (date != null) {
                final Date date2 = response2.headers().getDate("Last-Modified");
                if (date2 != null && date2.getTime() < date.getTime()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
    private boolean writeRequestHeadersEagerly() {
        return this.callerWritesRequestBody && this.permitsRequestBody(this.networkRequest) && this.requestBodyOut == null;
    }
    
    public void cancel() {
        this.streamAllocation.cancel();
    }
    
    public StreamAllocation close() {
        if (this.bufferedRequestBody != null) {
            Util.closeQuietly(this.bufferedRequestBody);
        }
        else if (this.requestBodyOut != null) {
            Util.closeQuietly(this.requestBodyOut);
        }
        if (this.userResponse != null) {
            Util.closeQuietly(this.userResponse.body());
        }
        else {
            this.streamAllocation.connectionFailed(null);
        }
        return this.streamAllocation;
    }
    
    public Request followUpRequest() throws IOException {
        if (this.userResponse == null) {
            throw new IllegalStateException();
        }
        final RealConnection connection = this.streamAllocation.connection();
        Route route;
        if (connection != null) {
            route = connection.route();
        }
        else {
            route = null;
        }
        final int code = this.userResponse.code();
        final String method = this.userRequest.method();
        Label_0226: {
            switch (code) {
                case 407: {
                    Proxy proxy;
                    if (route != null) {
                        proxy = route.proxy();
                    }
                    else {
                        proxy = this.client.proxy();
                    }
                    if (proxy.type() != Proxy.Type.HTTP) {
                        throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                    }
                    return this.client.authenticator().authenticate(route, this.userResponse);
                }
                case 401: {
                    return this.client.authenticator().authenticate(route, this.userResponse);
                }
                case 307:
                case 308: {
                    if (method.equals("GET") || method.equals("HEAD")) {
                        break Label_0226;
                    }
                    break;
                }
                case 300:
                case 301:
                case 302:
                case 303: {
                    if (!this.client.followRedirects()) {
                        break;
                    }
                    final String header = this.userResponse.header("Location");
                    if (header == null) {
                        break;
                    }
                    final HttpUrl resolve = this.userRequest.url().resolve(header);
                    if (resolve != null && (resolve.scheme().equals(this.userRequest.url().scheme()) || this.client.followSslRedirects())) {
                        final Request.Builder builder = this.userRequest.newBuilder();
                        if (HttpMethod.permitsRequestBody(method)) {
                            if (HttpMethod.redirectsToGet(method)) {
                                builder.method("GET", null);
                            }
                            else {
                                builder.method(method, null);
                            }
                            builder.removeHeader("Transfer-Encoding");
                            builder.removeHeader("Content-Length");
                            builder.removeHeader("Content-Type");
                        }
                        if (!this.sameConnection(resolve)) {
                            builder.removeHeader("Authorization");
                        }
                        return builder.url(resolve).build();
                    }
                    break;
                }
                case 408: {
                    boolean b;
                    if (this.requestBodyOut == null || this.requestBodyOut instanceof RetryableSink) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    if (!this.callerWritesRequestBody || b) {
                        return this.userRequest;
                    }
                    break;
                }
            }
        }
        return null;
    }
    
    public BufferedSink getBufferedRequestBody() {
        final BufferedSink bufferedRequestBody = this.bufferedRequestBody;
        if (bufferedRequestBody != null) {
            return bufferedRequestBody;
        }
        final Sink requestBody = this.getRequestBody();
        BufferedSink buffer;
        if (requestBody != null) {
            buffer = Okio.buffer(requestBody);
            this.bufferedRequestBody = buffer;
        }
        else {
            buffer = null;
        }
        return buffer;
    }
    
    public Connection getConnection() {
        return this.streamAllocation.connection();
    }
    
    public Request getRequest() {
        return this.userRequest;
    }
    
    public Sink getRequestBody() {
        if (this.cacheStrategy == null) {
            throw new IllegalStateException();
        }
        return this.requestBodyOut;
    }
    
    public Response getResponse() {
        if (this.userResponse == null) {
            throw new IllegalStateException();
        }
        return this.userResponse;
    }
    
    public boolean hasResponse() {
        return this.userResponse != null;
    }
    
    boolean permitsRequestBody(final Request request) {
        return HttpMethod.permitsRequestBody(request.method());
    }
    
    public void readResponse() throws IOException {
        if (this.userResponse == null) {
            if (this.networkRequest == null && this.cacheResponse == null) {
                throw new IllegalStateException("call sendRequest() first!");
            }
            if (this.networkRequest != null) {
                Response response;
                if (this.forWebSocket) {
                    this.httpStream.writeRequestHeaders(this.networkRequest);
                    response = this.readNetworkResponse();
                }
                else if (!this.callerWritesRequestBody) {
                    response = new NetworkInterceptorChain(0, this.networkRequest).proceed(this.networkRequest);
                }
                else {
                    if (this.bufferedRequestBody != null && this.bufferedRequestBody.buffer().size() > 0L) {
                        this.bufferedRequestBody.emit();
                    }
                    if (this.sentRequestMillis == -1L) {
                        if (OkHeaders.contentLength(this.networkRequest) == -1L && this.requestBodyOut instanceof RetryableSink) {
                            this.networkRequest = this.networkRequest.newBuilder().header("Content-Length", Long.toString(((RetryableSink)this.requestBodyOut).contentLength())).build();
                        }
                        this.httpStream.writeRequestHeaders(this.networkRequest);
                    }
                    if (this.requestBodyOut != null) {
                        if (this.bufferedRequestBody != null) {
                            this.bufferedRequestBody.close();
                        }
                        else {
                            this.requestBodyOut.close();
                        }
                        if (this.requestBodyOut instanceof RetryableSink) {
                            this.httpStream.writeRequestBody((RetryableSink)this.requestBodyOut);
                        }
                    }
                    response = this.readNetworkResponse();
                }
                this.receiveHeaders(response.headers());
                if (this.cacheResponse != null) {
                    if (validate(this.cacheResponse, response)) {
                        this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).headers(combine(this.cacheResponse.headers(), response.headers())).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(response)).build();
                        response.body().close();
                        this.releaseStreamAllocation();
                        final InternalCache internalCache = Internal.instance.internalCache(this.client);
                        internalCache.trackConditionalCacheHit();
                        internalCache.update(this.cacheResponse, stripBody(this.userResponse));
                        this.userResponse = this.unzip(this.userResponse);
                        return;
                    }
                    Util.closeQuietly(this.cacheResponse.body());
                }
                this.userResponse = response.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).networkResponse(stripBody(response)).build();
                if (hasBody(this.userResponse)) {
                    this.maybeCache();
                    this.userResponse = this.unzip(this.cacheWritingResponse(this.storeRequest, this.userResponse));
                }
            }
        }
    }
    
    public void receiveHeaders(final Headers headers) throws IOException {
        if (this.client.cookieJar() != CookieJar.NO_COOKIES) {
            final List<Cookie> all = Cookie.parseAll(this.userRequest.url(), headers);
            if (!all.isEmpty()) {
                this.client.cookieJar().saveFromResponse(this.userRequest.url(), all);
            }
        }
    }
    
    public HttpEngine recover(final IOException ex) {
        return this.recover(ex, this.requestBodyOut);
    }
    
    public HttpEngine recover(final IOException ex, final Sink sink) {
        if (this.streamAllocation.recover(ex, sink) && this.client.retryOnConnectionFailure()) {
            return new HttpEngine(this.client, this.userRequest, this.bufferRequestBody, this.callerWritesRequestBody, this.forWebSocket, this.close(), (RetryableSink)sink, this.priorResponse);
        }
        return null;
    }
    
    public void releaseStreamAllocation() throws IOException {
        this.streamAllocation.release();
    }
    
    public boolean sameConnection(final HttpUrl httpUrl) {
        final HttpUrl url = this.userRequest.url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }
    
    public void sendRequest() throws RequestException, RouteException, IOException {
        if (this.cacheStrategy == null) {
            if (this.httpStream != null) {
                throw new IllegalStateException();
            }
            final Request networkRequest = this.networkRequest(this.userRequest);
            final InternalCache internalCache = Internal.instance.internalCache(this.client);
            Response value;
            if (internalCache != null) {
                value = internalCache.get(networkRequest);
            }
            else {
                value = null;
            }
            this.cacheStrategy = new CacheStrategy.Factory(System.currentTimeMillis(), networkRequest, value).get();
            this.networkRequest = this.cacheStrategy.networkRequest;
            this.cacheResponse = this.cacheStrategy.cacheResponse;
            if (internalCache != null) {
                internalCache.trackResponse(this.cacheStrategy);
            }
            if (value != null && this.cacheResponse == null) {
                Util.closeQuietly(value.body());
            }
            if (this.networkRequest == null && this.cacheResponse == null) {
                this.userResponse = new Response.Builder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(HttpEngine.EMPTY_BODY).build();
                return;
            }
            if (this.networkRequest == null) {
                this.userResponse = this.cacheResponse.newBuilder().request(this.userRequest).priorResponse(stripBody(this.priorResponse)).cacheResponse(stripBody(this.cacheResponse)).build();
                this.userResponse = this.unzip(this.userResponse);
                return;
            }
            while (true) {
                long contentLength = 0L;
                Label_0408: {
                    try {
                        (this.httpStream = this.connect()).setHttpEngine(this);
                        if (!this.writeRequestHeadersEagerly()) {
                            break Label_0378;
                        }
                        contentLength = OkHeaders.contentLength(networkRequest);
                        if (!this.bufferRequestBody) {
                            break Label_0408;
                        }
                        if (contentLength > 2147483647L) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        }
                    }
                    finally {
                        if (!false && value != null) {
                            Util.closeQuietly(value.body());
                        }
                    }
                    if (contentLength != -1L) {
                        this.httpStream.writeRequestHeaders(this.networkRequest);
                        this.requestBodyOut = new RetryableSink((int)contentLength);
                    }
                    else {
                        this.requestBodyOut = new RetryableSink();
                    }
                    if (!true && value != null) {
                        Util.closeQuietly(value.body());
                        return;
                    }
                    return;
                }
                this.httpStream.writeRequestHeaders(this.networkRequest);
                this.requestBodyOut = this.httpStream.createRequestBody(this.networkRequest, contentLength);
                continue;
            }
        }
    }
    
    public void writingRequestHeaders() {
        if (this.sentRequestMillis != -1L) {
            throw new IllegalStateException();
        }
        this.sentRequestMillis = System.currentTimeMillis();
    }
    
    class NetworkInterceptorChain implements Chain
    {
        private int calls;
        private final int index;
        private final Request request;
        
        NetworkInterceptorChain(final int index, final Request request) {
            this.index = index;
            this.request = request;
        }
        
        @Override
        public Connection connection() {
            return HttpEngine.this.streamAllocation.connection();
        }
        
        @Override
        public Response proceed(final Request request) throws IOException {
            ++this.calls;
            if (this.index > 0) {
                final Interceptor interceptor = HttpEngine.this.client.networkInterceptors().get(-1 + this.index);
                final Address address = this.connection().route().address();
                if (!request.url().host().equals(address.url().host()) || request.url().port() != address.url().port()) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must retain the same host and port");
                }
                if (this.calls > 1) {
                    throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
                }
            }
            Response intercept;
            if (this.index < HttpEngine.this.client.networkInterceptors().size()) {
                final NetworkInterceptorChain networkInterceptorChain = new NetworkInterceptorChain(1 + this.index, request);
                final Interceptor interceptor2 = HttpEngine.this.client.networkInterceptors().get(this.index);
                intercept = interceptor2.intercept((Interceptor.Chain)networkInterceptorChain);
                if (networkInterceptorChain.calls != 1) {
                    throw new IllegalStateException("network interceptor " + interceptor2 + " must call proceed() exactly once");
                }
                if (intercept == null) {
                    throw new NullPointerException("network interceptor " + interceptor2 + " returned null");
                }
            }
            else {
                HttpEngine.this.httpStream.writeRequestHeaders(request);
                HttpEngine.this.networkRequest = request;
                if (HttpEngine.this.permitsRequestBody(request) && request.body() != null) {
                    final BufferedSink buffer = Okio.buffer(HttpEngine.this.httpStream.createRequestBody(request, request.body().contentLength()));
                    request.body().writeTo(buffer);
                    buffer.close();
                }
                final Response access$200 = HttpEngine.this.readNetworkResponse();
                final int code = access$200.code();
                if ((code == 204 || code == 205) && access$200.body().contentLength() > 0L) {
                    throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + access$200.body().contentLength());
                }
                intercept = access$200;
            }
            return intercept;
        }
        
        @Override
        public Request request() {
            return this.request;
        }
    }
}
