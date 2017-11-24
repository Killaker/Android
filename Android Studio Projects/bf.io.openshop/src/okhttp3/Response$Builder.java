package okhttp3;

public static class Builder
{
    private ResponseBody body;
    private Response cacheResponse;
    private int code;
    private Handshake handshake;
    private Headers.Builder headers;
    private String message;
    private Response networkResponse;
    private Response priorResponse;
    private Protocol protocol;
    private Request request;
    
    public Builder() {
        this.code = -1;
        this.headers = new Headers.Builder();
    }
    
    private Builder(final Response response) {
        this.code = -1;
        this.request = Response.access$1100(response);
        this.protocol = Response.access$1200(response);
        this.code = Response.access$1300(response);
        this.message = Response.access$1400(response);
        this.handshake = Response.access$1500(response);
        this.headers = Response.access$1600(response).newBuilder();
        this.body = Response.access$1700(response);
        this.networkResponse = Response.access$1800(response);
        this.cacheResponse = Response.access$1900(response);
        this.priorResponse = Response.access$2000(response);
    }
    
    private void checkPriorResponse(final Response response) {
        if (Response.access$1700(response) != null) {
            throw new IllegalArgumentException("priorResponse.body != null");
        }
    }
    
    private void checkSupportResponse(final String s, final Response response) {
        if (Response.access$1700(response) != null) {
            throw new IllegalArgumentException(s + ".body != null");
        }
        if (Response.access$1800(response) != null) {
            throw new IllegalArgumentException(s + ".networkResponse != null");
        }
        if (Response.access$1900(response) != null) {
            throw new IllegalArgumentException(s + ".cacheResponse != null");
        }
        if (Response.access$2000(response) != null) {
            throw new IllegalArgumentException(s + ".priorResponse != null");
        }
    }
    
    public Builder addHeader(final String s, final String s2) {
        this.headers.add(s, s2);
        return this;
    }
    
    public Builder body(final ResponseBody body) {
        this.body = body;
        return this;
    }
    
    public Response build() {
        if (this.request == null) {
            throw new IllegalStateException("request == null");
        }
        if (this.protocol == null) {
            throw new IllegalStateException("protocol == null");
        }
        if (this.code < 0) {
            throw new IllegalStateException("code < 0: " + this.code);
        }
        return new Response(this, null);
    }
    
    public Builder cacheResponse(final Response cacheResponse) {
        if (cacheResponse != null) {
            this.checkSupportResponse("cacheResponse", cacheResponse);
        }
        this.cacheResponse = cacheResponse;
        return this;
    }
    
    public Builder code(final int code) {
        this.code = code;
        return this;
    }
    
    public Builder handshake(final Handshake handshake) {
        this.handshake = handshake;
        return this;
    }
    
    public Builder header(final String s, final String s2) {
        this.headers.set(s, s2);
        return this;
    }
    
    public Builder headers(final Headers headers) {
        this.headers = headers.newBuilder();
        return this;
    }
    
    public Builder message(final String message) {
        this.message = message;
        return this;
    }
    
    public Builder networkResponse(final Response networkResponse) {
        if (networkResponse != null) {
            this.checkSupportResponse("networkResponse", networkResponse);
        }
        this.networkResponse = networkResponse;
        return this;
    }
    
    public Builder priorResponse(final Response priorResponse) {
        if (priorResponse != null) {
            this.checkPriorResponse(priorResponse);
        }
        this.priorResponse = priorResponse;
        return this;
    }
    
    public Builder protocol(final Protocol protocol) {
        this.protocol = protocol;
        return this;
    }
    
    public Builder removeHeader(final String s) {
        this.headers.removeAll(s);
        return this;
    }
    
    public Builder request(final Request request) {
        this.request = request;
        return this;
    }
}
