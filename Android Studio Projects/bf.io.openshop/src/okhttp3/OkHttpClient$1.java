package okhttp3;

import javax.net.ssl.*;
import okhttp3.internal.http.*;
import okhttp3.internal.io.*;
import java.net.*;
import okhttp3.internal.*;

static final class OkHttpClient$1 extends Internal {
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
}