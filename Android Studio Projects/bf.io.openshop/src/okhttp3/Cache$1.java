package okhttp3;

import okhttp3.internal.*;
import java.io.*;
import okhttp3.internal.http.*;

class Cache$1 implements InternalCache {
    @Override
    public Response get(final Request request) throws IOException {
        return Cache.this.get(request);
    }
    
    @Override
    public CacheRequest put(final Response response) throws IOException {
        return Cache.access$000(Cache.this, response);
    }
    
    @Override
    public void remove(final Request request) throws IOException {
        Cache.access$100(Cache.this, request);
    }
    
    @Override
    public void trackConditionalCacheHit() {
        Cache.access$300(Cache.this);
    }
    
    @Override
    public void trackResponse(final CacheStrategy cacheStrategy) {
        Cache.access$400(Cache.this, cacheStrategy);
    }
    
    @Override
    public void update(final Response response, final Response response2) throws IOException {
        Cache.access$200(Cache.this, response, response2);
    }
}