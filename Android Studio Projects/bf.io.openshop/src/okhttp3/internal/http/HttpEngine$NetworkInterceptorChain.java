package okhttp3.internal.http;

import java.net.*;
import okhttp3.*;
import okio.*;
import java.io.*;

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
            HttpEngine.access$000(HttpEngine.this).writeRequestHeaders(request);
            HttpEngine.access$102(HttpEngine.this, request);
            if (HttpEngine.this.permitsRequestBody(request) && request.body() != null) {
                final BufferedSink buffer = Okio.buffer(HttpEngine.access$000(HttpEngine.this).createRequestBody(request, request.body().contentLength()));
                request.body().writeTo(buffer);
                buffer.close();
            }
            final Response access$200 = HttpEngine.access$200(HttpEngine.this);
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
