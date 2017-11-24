package okhttp3;

import java.net.*;
import javax.net.*;
import javax.net.ssl.*;
import okhttp3.internal.tls.*;
import java.util.*;
import java.util.concurrent.*;
import okhttp3.internal.*;

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
        this.protocols = (List<Protocol>)OkHttpClient.access$000();
        this.connectionSpecs = (List<ConnectionSpec>)OkHttpClient.access$100();
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
