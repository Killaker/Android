package okhttp3;

import okhttp3.internal.http.*;
import java.net.*;

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
        this.url = Request.access$600(request);
        this.method = Request.access$700(request);
        this.body = Request.access$800(request);
        this.tag = Request.access$900(request);
        this.headers = Request.access$1000(request).newBuilder();
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
