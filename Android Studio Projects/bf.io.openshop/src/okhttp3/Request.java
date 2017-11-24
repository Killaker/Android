package okhttp3;

import java.util.*;
import okhttp3.internal.http.*;
import java.net.*;

public final class Request
{
    private final RequestBody body;
    private volatile CacheControl cacheControl;
    private final Headers headers;
    private volatile URI javaNetUri;
    private final String method;
    private final Object tag;
    private final HttpUrl url;
    
    private Request(final Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        Object access$400;
        if (builder.tag != null) {
            access$400 = builder.tag;
        }
        else {
            access$400 = this;
        }
        this.tag = access$400;
    }
    
    public RequestBody body() {
        return this.body;
    }
    
    public CacheControl cacheControl() {
        final CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        return this.cacheControl = CacheControl.parse(this.headers);
    }
    
    public String header(final String s) {
        return this.headers.get(s);
    }
    
    public List<String> headers(final String s) {
        return this.headers.values(s);
    }
    
    public Headers headers() {
        return this.headers;
    }
    
    public boolean isHttps() {
        return this.url.isHttps();
    }
    
    public String method() {
        return this.method;
    }
    
    public Builder newBuilder() {
        return new Builder(this);
    }
    
    public Object tag() {
        return this.tag;
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder().append("Request{method=").append(this.method).append(", url=").append(this.url).append(", tag=");
        Object tag;
        if (this.tag != this) {
            tag = this.tag;
        }
        else {
            tag = null;
        }
        return append.append(tag).append('}').toString();
    }
    
    public HttpUrl url() {
        return this.url;
    }
    
    public static class Builder
    {
        private RequestBody body;
        private Headers.Builder headers;
        private String method;
        private Object tag;
        private HttpUrl url;
        
        public Builder() {
            this.method = "GET";
            this.headers = new Headers.Builder();
        }
        
        private Builder(final Request request) {
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
        }
        
        public Builder addHeader(final String s, final String s2) {
            this.headers.add(s, s2);
            return this;
        }
        
        public Request build() {
            if (this.url == null) {
                throw new IllegalStateException("url == null");
            }
            return new Request(this, null);
        }
        
        public Builder cacheControl(final CacheControl cacheControl) {
            final String string = cacheControl.toString();
            if (string.isEmpty()) {
                return this.removeHeader("Cache-Control");
            }
            return this.header("Cache-Control", string);
        }
        
        public Builder delete() {
            return this.delete(RequestBody.create(null, new byte[0]));
        }
        
        public Builder delete(final RequestBody requestBody) {
            return this.method("DELETE", requestBody);
        }
        
        public Builder get() {
            return this.method("GET", null);
        }
        
        public Builder head() {
            return this.method("HEAD", null);
        }
        
        public Builder header(final String s, final String s2) {
            this.headers.set(s, s2);
            return this;
        }
        
        public Builder headers(final Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }
        
        public Builder method(final String method, final RequestBody body) {
            if (method == null || method.length() == 0) {
                throw new IllegalArgumentException("method == null || method.length() == 0");
            }
            if (body != null && !HttpMethod.permitsRequestBody(method)) {
                throw new IllegalArgumentException("method " + method + " must not have a request body.");
            }
            if (body == null && HttpMethod.requiresRequestBody(method)) {
                throw new IllegalArgumentException("method " + method + " must have a request body.");
            }
            this.method = method;
            this.body = body;
            return this;
        }
        
        public Builder patch(final RequestBody requestBody) {
            return this.method("PATCH", requestBody);
        }
        
        public Builder post(final RequestBody requestBody) {
            return this.method("POST", requestBody);
        }
        
        public Builder put(final RequestBody requestBody) {
            return this.method("PUT", requestBody);
        }
        
        public Builder removeHeader(final String s) {
            this.headers.removeAll(s);
            return this;
        }
        
        public Builder tag(final Object tag) {
            this.tag = tag;
            return this;
        }
        
        public Builder url(String s) {
            if (s == null) {
                throw new IllegalArgumentException("url == null");
            }
            if (s.regionMatches(true, 0, "ws:", 0, 3)) {
                s = "http:" + s.substring(3);
            }
            else if (s.regionMatches(true, 0, "wss:", 0, 4)) {
                s = "https:" + s.substring(4);
            }
            final HttpUrl parse = HttpUrl.parse(s);
            if (parse == null) {
                throw new IllegalArgumentException("unexpected url: " + s);
            }
            return this.url(parse);
        }
        
        public Builder url(final URL url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            final HttpUrl value = HttpUrl.get(url);
            if (value == null) {
                throw new IllegalArgumentException("unexpected url: " + url);
            }
            return this.url(value);
        }
        
        public Builder url(final HttpUrl url) {
            if (url == null) {
                throw new IllegalArgumentException("url == null");
            }
            this.url = url;
            return this;
        }
    }
}
