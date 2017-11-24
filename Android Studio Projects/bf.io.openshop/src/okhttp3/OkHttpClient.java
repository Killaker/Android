package okhttp3;

import javax.net.*;
import okhttp3.internal.http.*;
import okhttp3.internal.io.*;
import java.net.*;
import java.security.*;
import okhttp3.internal.*;
import javax.net.ssl.*;
import okhttp3.internal.tls.*;
import java.util.*;
import java.util.concurrent.*;

public class OkHttpClient implements Cloneable, Factory
{
    private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS;
    private static final List<Protocol> DEFAULT_PROTOCOLS;
    final Authenticator authenticator;
    final Cache cache;
    final CertificatePinner certificatePinner;
    final int connectTimeout;
    final ConnectionPool connectionPool;
    final List<ConnectionSpec> connectionSpecs;
    final CookieJar cookieJar;
    final Dispatcher dispatcher;
    final Dns dns;
    final boolean followRedirects;
    final boolean followSslRedirects;
    final HostnameVerifier hostnameVerifier;
    final List<Interceptor> interceptors;
    final InternalCache internalCache;
    final List<Interceptor> networkInterceptors;
    final List<Protocol> protocols;
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final int readTimeout;
    final boolean retryOnConnectionFailure;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final TrustRootIndex trustRootIndex;
    final int writeTimeout;
    
    static {
        DEFAULT_PROTOCOLS = Util.immutableList(Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
        DEFAULT_CONNECTION_SPECS = Util.immutableList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.CLEARTEXT);
        Internal.instance = new Internal() {
            @Override
            public void addLenient(final Headers.Builder builder, final String s) {
                builder.addLenient(s);
            }
            
            @Override
            public void addLenient(final Headers.Builder builder, final String s, final String s2) {
                builder.addLenient(s, s2);
            }
            
            @Override
            public void apply(final ConnectionSpec connectionSpec, final SSLSocket sslSocket, final boolean b) {
                connectionSpec.apply(sslSocket, b);
            }
            
            @Override
            public StreamAllocation callEngineGetStreamAllocation(final Call call) {
                return ((RealCall)call).engine.streamAllocation;
            }
            
            @Override
            public void callEnqueue(final Call call, final Callback callback, final boolean b) {
                ((RealCall)call).enqueue(callback, b);
            }
            
            @Override
            public boolean connectionBecameIdle(final ConnectionPool connectionPool, final RealConnection realConnection) {
                return connectionPool.connectionBecameIdle(realConnection);
            }
            
            @Override
            public RealConnection get(final ConnectionPool connectionPool, final Address address, final StreamAllocation streamAllocation) {
                return connectionPool.get(address, streamAllocation);
            }
            
            @Override
            public HttpUrl getHttpUrlChecked(final String s) throws MalformedURLException, UnknownHostException {
                return HttpUrl.getChecked(s);
            }
            
            @Override
            public InternalCache internalCache(final OkHttpClient okHttpClient) {
                return okHttpClient.internalCache();
            }
            
            @Override
            public void put(final ConnectionPool connectionPool, final RealConnection realConnection) {
                connectionPool.put(realConnection);
            }
            
            @Override
            public RouteDatabase routeDatabase(final ConnectionPool connectionPool) {
                return connectionPool.routeDatabase;
            }
            
            @Override
            public void setCache(final Builder builder, final InternalCache internalCache) {
                builder.setInternalCache(internalCache);
            }
        };
    }
    
    public OkHttpClient() {
        this(new Builder());
    }
    
    private OkHttpClient(final Builder builder) {
        this.dispatcher = builder.dispatcher;
        this.proxy = builder.proxy;
        this.protocols = builder.protocols;
        this.connectionSpecs = builder.connectionSpecs;
        this.interceptors = Util.immutableList(builder.interceptors);
        this.networkInterceptors = Util.immutableList(builder.networkInterceptors);
        this.proxySelector = builder.proxySelector;
        this.cookieJar = builder.cookieJar;
        this.cache = builder.cache;
        this.internalCache = builder.internalCache;
        this.socketFactory = builder.socketFactory;
        int n = 0;
        for (final ConnectionSpec connectionSpec : this.connectionSpecs) {
            if (n != 0 || connectionSpec.isTls()) {
                n = 1;
            }
            else {
                n = 0;
            }
        }
        Label_0316: {
            final X509TrustManager trustManager;
            Label_0283: {
                Label_0171: {
                    if (builder.sslSocketFactory == null && n != 0) {
                        try {
                            final SSLContext instance = SSLContext.getInstance("TLS");
                            instance.init(null, null, null);
                            this.sslSocketFactory = instance.getSocketFactory();
                            break Label_0171;
                        }
                        catch (GeneralSecurityException ex) {
                            throw new AssertionError();
                        }
                        break Label_0283;
                    }
                    this.sslSocketFactory = builder.sslSocketFactory;
                }
                if (this.sslSocketFactory == null || builder.trustRootIndex != null) {
                    this.trustRootIndex = builder.trustRootIndex;
                    this.certificatePinner = builder.certificatePinner;
                    break Label_0316;
                }
                trustManager = Platform.get().trustManager(this.sslSocketFactory);
                if (trustManager == null) {
                    throw new IllegalStateException("Unable to extract the trust manager on " + Platform.get() + ", sslSocketFactory is " + this.sslSocketFactory.getClass());
                }
            }
            this.trustRootIndex = Platform.get().trustRootIndex(trustManager);
            this.certificatePinner = builder.certificatePinner.newBuilder().trustRootIndex(this.trustRootIndex).build();
        }
        this.hostnameVerifier = builder.hostnameVerifier;
        this.proxyAuthenticator = builder.proxyAuthenticator;
        this.authenticator = builder.authenticator;
        this.connectionPool = builder.connectionPool;
        this.dns = builder.dns;
        this.followSslRedirects = builder.followSslRedirects;
        this.followRedirects = builder.followRedirects;
        this.retryOnConnectionFailure = builder.retryOnConnectionFailure;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.writeTimeout = builder.writeTimeout;
    }
    
    public Authenticator authenticator() {
        return this.authenticator;
    }
    
    public Cache cache() {
        return this.cache;
    }
    
    public CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }
    
    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }
    
    public ConnectionPool connectionPool() {
        return this.connectionPool;
    }
    
    public List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }
    
    public CookieJar cookieJar() {
        return this.cookieJar;
    }
    
    public Dispatcher dispatcher() {
        return this.dispatcher;
    }
    
    public Dns dns() {
        return this.dns;
    }
    
    public boolean followRedirects() {
        return this.followRedirects;
    }
    
    public boolean followSslRedirects() {
        return this.followSslRedirects;
    }
    
    public HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }
    
    public List<Interceptor> interceptors() {
        return this.interceptors;
    }
    
    InternalCache internalCache() {
        if (this.cache != null) {
            return this.cache.internalCache;
        }
        return this.internalCache;
    }
    
    public List<Interceptor> networkInterceptors() {
        return this.networkInterceptors;
    }
    
    public Builder newBuilder() {
        return new Builder(this);
    }
    
    @Override
    public Call newCall(final Request request) {
        return new RealCall(this, request);
    }
    
    public List<Protocol> protocols() {
        return this.protocols;
    }
    
    public Proxy proxy() {
        return this.proxy;
    }
    
    public Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }
    
    public ProxySelector proxySelector() {
        return this.proxySelector;
    }
    
    public int readTimeoutMillis() {
        return this.readTimeout;
    }
    
    public boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }
    
    public SocketFactory socketFactory() {
        return this.socketFactory;
    }
    
    public SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }
    
    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }
    
    public static final class Builder
    {
        Authenticator authenticator;
        Cache cache;
        CertificatePinner certificatePinner;
        int connectTimeout;
        ConnectionPool connectionPool;
        List<ConnectionSpec> connectionSpecs;
        CookieJar cookieJar;
        Dispatcher dispatcher;
        Dns dns;
        boolean followRedirects;
        boolean followSslRedirects;
        HostnameVerifier hostnameVerifier;
        final List<Interceptor> interceptors;
        InternalCache internalCache;
        final List<Interceptor> networkInterceptors;
        List<Protocol> protocols;
        Proxy proxy;
        Authenticator proxyAuthenticator;
        ProxySelector proxySelector;
        int readTimeout;
        boolean retryOnConnectionFailure;
        SocketFactory socketFactory;
        SSLSocketFactory sslSocketFactory;
        TrustRootIndex trustRootIndex;
        int writeTimeout;
        
        public Builder() {
            this.interceptors = new ArrayList<Interceptor>();
            this.networkInterceptors = new ArrayList<Interceptor>();
            this.dispatcher = new Dispatcher();
            this.protocols = OkHttpClient.DEFAULT_PROTOCOLS;
            this.connectionSpecs = OkHttpClient.DEFAULT_CONNECTION_SPECS;
            this.proxySelector = ProxySelector.getDefault();
            this.cookieJar = CookieJar.NO_COOKIES;
            this.socketFactory = SocketFactory.getDefault();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.proxyAuthenticator = Authenticator.NONE;
            this.authenticator = Authenticator.NONE;
            this.connectionPool = new ConnectionPool();
            this.dns = Dns.SYSTEM;
            this.followSslRedirects = true;
            this.followRedirects = true;
            this.retryOnConnectionFailure = true;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
        }
        
        Builder(final OkHttpClient okHttpClient) {
            this.interceptors = new ArrayList<Interceptor>();
            this.networkInterceptors = new ArrayList<Interceptor>();
            this.dispatcher = okHttpClient.dispatcher;
            this.proxy = okHttpClient.proxy;
            this.protocols = okHttpClient.protocols;
            this.connectionSpecs = okHttpClient.connectionSpecs;
            this.interceptors.addAll(okHttpClient.interceptors);
            this.networkInterceptors.addAll(okHttpClient.networkInterceptors);
            this.proxySelector = okHttpClient.proxySelector;
            this.cookieJar = okHttpClient.cookieJar;
            this.internalCache = okHttpClient.internalCache;
            this.cache = okHttpClient.cache;
            this.socketFactory = okHttpClient.socketFactory;
            this.sslSocketFactory = okHttpClient.sslSocketFactory;
            this.trustRootIndex = okHttpClient.trustRootIndex;
            this.hostnameVerifier = okHttpClient.hostnameVerifier;
            this.certificatePinner = okHttpClient.certificatePinner;
            this.proxyAuthenticator = okHttpClient.proxyAuthenticator;
            this.authenticator = okHttpClient.authenticator;
            this.connectionPool = okHttpClient.connectionPool;
            this.dns = okHttpClient.dns;
            this.followSslRedirects = okHttpClient.followSslRedirects;
            this.followRedirects = okHttpClient.followRedirects;
            this.retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure;
            this.connectTimeout = okHttpClient.connectTimeout;
            this.readTimeout = okHttpClient.readTimeout;
            this.writeTimeout = okHttpClient.writeTimeout;
        }
        
        public Builder addInterceptor(final Interceptor interceptor) {
            this.interceptors.add(interceptor);
            return this;
        }
        
        public Builder addNetworkInterceptor(final Interceptor interceptor) {
            this.networkInterceptors.add(interceptor);
            return this;
        }
        
        public Builder authenticator(final Authenticator authenticator) {
            if (authenticator == null) {
                throw new NullPointerException("authenticator == null");
            }
            this.authenticator = authenticator;
            return this;
        }
        
        public OkHttpClient build() {
            return new OkHttpClient(this, null);
        }
        
        public Builder cache(final Cache cache) {
            this.cache = cache;
            this.internalCache = null;
            return this;
        }
        
        public Builder certificatePinner(final CertificatePinner certificatePinner) {
            if (certificatePinner == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.certificatePinner = certificatePinner;
            return this;
        }
        
        public Builder connectTimeout(final long n, final TimeUnit timeUnit) {
            if (n < 0L) {
                throw new IllegalArgumentException("timeout < 0");
            }
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            final long millis = timeUnit.toMillis(n);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            }
            if (millis == 0L && n > 0L) {
                throw new IllegalArgumentException("Timeout too small.");
            }
            this.connectTimeout = (int)millis;
            return this;
        }
        
        public Builder connectionPool(final ConnectionPool connectionPool) {
            if (connectionPool == null) {
                throw new NullPointerException("connectionPool == null");
            }
            this.connectionPool = connectionPool;
            return this;
        }
        
        public Builder connectionSpecs(final List<ConnectionSpec> list) {
            this.connectionSpecs = Util.immutableList(list);
            return this;
        }
        
        public Builder cookieJar(final CookieJar cookieJar) {
            if (cookieJar == null) {
                throw new NullPointerException("cookieJar == null");
            }
            this.cookieJar = cookieJar;
            return this;
        }
        
        public Builder dispatcher(final Dispatcher dispatcher) {
            if (dispatcher == null) {
                throw new IllegalArgumentException("dispatcher == null");
            }
            this.dispatcher = dispatcher;
            return this;
        }
        
        public Builder dns(final Dns dns) {
            if (dns == null) {
                throw new NullPointerException("dns == null");
            }
            this.dns = dns;
            return this;
        }
        
        public Builder followRedirects(final boolean followRedirects) {
            this.followRedirects = followRedirects;
            return this;
        }
        
        public Builder followSslRedirects(final boolean followSslRedirects) {
            this.followSslRedirects = followSslRedirects;
            return this;
        }
        
        public Builder hostnameVerifier(final HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier == null) {
                throw new NullPointerException("hostnameVerifier == null");
            }
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }
        
        public List<Interceptor> interceptors() {
            return this.interceptors;
        }
        
        public List<Interceptor> networkInterceptors() {
            return this.networkInterceptors;
        }
        
        public Builder protocols(final List<Protocol> list) {
            final List<Protocol> immutableList = Util.immutableList(list);
            if (!immutableList.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException("protocols doesn't contain http/1.1: " + immutableList);
            }
            if (immutableList.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException("protocols must not contain http/1.0: " + immutableList);
            }
            if (immutableList.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            this.protocols = Util.immutableList(immutableList);
            return this;
        }
        
        public Builder proxy(final Proxy proxy) {
            this.proxy = proxy;
            return this;
        }
        
        public Builder proxyAuthenticator(final Authenticator proxyAuthenticator) {
            if (proxyAuthenticator == null) {
                throw new NullPointerException("proxyAuthenticator == null");
            }
            this.proxyAuthenticator = proxyAuthenticator;
            return this;
        }
        
        public Builder proxySelector(final ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
            return this;
        }
        
        public Builder readTimeout(final long n, final TimeUnit timeUnit) {
            if (n < 0L) {
                throw new IllegalArgumentException("timeout < 0");
            }
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            final long millis = timeUnit.toMillis(n);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            }
            if (millis == 0L && n > 0L) {
                throw new IllegalArgumentException("Timeout too small.");
            }
            this.readTimeout = (int)millis;
            return this;
        }
        
        public Builder retryOnConnectionFailure(final boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }
        
        void setInternalCache(final InternalCache internalCache) {
            this.internalCache = internalCache;
            this.cache = null;
        }
        
        public Builder socketFactory(final SocketFactory socketFactory) {
            if (socketFactory == null) {
                throw new NullPointerException("socketFactory == null");
            }
            this.socketFactory = socketFactory;
            return this;
        }
        
        public Builder sslSocketFactory(final SSLSocketFactory sslSocketFactory) {
            if (sslSocketFactory == null) {
                throw new NullPointerException("sslSocketFactory == null");
            }
            this.sslSocketFactory = sslSocketFactory;
            this.trustRootIndex = null;
            return this;
        }
        
        public Builder writeTimeout(final long n, final TimeUnit timeUnit) {
            if (n < 0L) {
                throw new IllegalArgumentException("timeout < 0");
            }
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            final long millis = timeUnit.toMillis(n);
            if (millis > 2147483647L) {
                throw new IllegalArgumentException("Timeout too large.");
            }
            if (millis == 0L && n > 0L) {
                throw new IllegalArgumentException("Timeout too small.");
            }
            this.writeTimeout = (int)millis;
            return this;
        }
    }
}
