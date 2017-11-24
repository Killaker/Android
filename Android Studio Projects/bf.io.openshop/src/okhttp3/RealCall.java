package okhttp3;

import java.io.*;
import okhttp3.internal.http.*;
import okio.*;
import java.net.*;
import okhttp3.internal.*;
import java.util.logging.*;

final class RealCall implements Call
{
    volatile boolean canceled;
    private final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;
    
    protected RealCall(final OkHttpClient client, final Request originalRequest) {
        this.client = client;
        this.originalRequest = originalRequest;
    }
    
    private Response getResponseWithInterceptorChain(final boolean b) throws IOException {
        return ((Interceptor.Chain)new ApplicationInterceptorChain(0, this.originalRequest, b)).proceed(this.originalRequest);
    }
    
    private String toLoggableString() {
        String s;
        if (this.canceled) {
            s = "canceled call";
        }
        else {
            s = "call";
        }
        return s + " to " + this.originalRequest.url().resolve("/...");
    }
    
    @Override
    public void cancel() {
        this.canceled = true;
        if (this.engine != null) {
            this.engine.cancel();
        }
    }
    
    @Override
    public void enqueue(final Callback callback) {
        this.enqueue(callback, false);
    }
    
    void enqueue(final Callback callback, final boolean b) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.executed = true;
        // monitorexit(this)
        this.client.dispatcher().enqueue(new AsyncCall(callback, b));
    }
    
    @Override
    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
        }
        this.executed = true;
        // monitorexit(this)
        Response responseWithInterceptorChain;
        try {
            this.client.dispatcher().executed(this);
            responseWithInterceptorChain = this.getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain == null) {
                throw new IOException("Canceled");
            }
        }
        finally {
            this.client.dispatcher().finished(this);
        }
        this.client.dispatcher().finished(this);
        return responseWithInterceptorChain;
    }
    
    Response getResponse(Request build, final boolean b) throws IOException {
        final RequestBody body = build.body();
        if (body != null) {
            final Request.Builder builder = build.newBuilder();
            final MediaType contentType = body.contentType();
            if (contentType != null) {
                builder.header("Content-Type", contentType.toString());
            }
            final long contentLength = body.contentLength();
            if (contentLength != -1L) {
                builder.header("Content-Length", Long.toString(contentLength));
                builder.removeHeader("Transfer-Encoding");
            }
            else {
                builder.header("Transfer-Encoding", "chunked");
                builder.removeHeader("Content-Length");
            }
            build = builder.build();
        }
        this.engine = new HttpEngine(this.client, build, false, false, b, null, null, null);
        int n = 0;
    Block_17_Outer:
        while (!this.canceled) {
            Response response = null;
            Request followUpRequest = null;
            try {
                this.engine.sendRequest();
                this.engine.readResponse();
                if (false) {
                    this.engine.close().release();
                }
                response = this.engine.getResponse();
                followUpRequest = this.engine.followUpRequest();
                if (followUpRequest == null) {
                    if (!b) {
                        this.engine.releaseStreamAllocation();
                    }
                    return response;
                }
            }
            catch (RequestException ex) {
                throw ex.getCause();
                this.engine.close().release();
                while (true) {
                    this.engine.close().release();
                    continue Block_17_Outer;
                    final HttpEngine engine;
                    this.engine = engine;
                    continue;
                }
            }
            // iftrue(Label_0106:, !false)
            catch (RouteException ex3) {}
            catch (IOException ex4) {
                final IOException ex2;
                final HttpEngine recover = this.engine.recover(ex2, null);
                if (recover == null) {
                    throw ex2;
                }
                this.engine = recover;
                if (false) {
                    this.engine.close().release();
                    continue;
                }
                continue;
            }
            StreamAllocation close = this.engine.close();
            if (++n > 20) {
                close.release();
                throw new ProtocolException("Too many follow-up requests: " + n);
            }
            if (!this.engine.sameConnection(followUpRequest.url())) {
                close.release();
                close = null;
            }
            this.engine = new HttpEngine(this.client, followUpRequest, false, false, b, close, null, response);
        }
        this.engine.releaseStreamAllocation();
        throw new IOException("Canceled");
    }
    
    @Override
    public boolean isCanceled() {
        return this.canceled;
    }
    
    @Override
    public boolean isExecuted() {
        synchronized (this) {
            return this.executed;
        }
    }
    
    @Override
    public Request request() {
        return this.originalRequest;
    }
    
    Object tag() {
        return this.originalRequest.tag();
    }
    
    class ApplicationInterceptorChain implements Chain
    {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;
        
        ApplicationInterceptorChain(final int index, final Request request, final boolean forWebSocket) {
            this.index = index;
            this.request = request;
            this.forWebSocket = forWebSocket;
        }
        
        @Override
        public Connection connection() {
            return null;
        }
        
        @Override
        public Response proceed(final Request request) throws IOException {
            Response response;
            if (this.index < RealCall.this.client.interceptors().size()) {
                final ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(1 + this.index, request, this.forWebSocket);
                final Interceptor interceptor = RealCall.this.client.interceptors().get(this.index);
                response = interceptor.intercept((Interceptor.Chain)applicationInterceptorChain);
                if (response == null) {
                    throw new NullPointerException("application interceptor " + interceptor + " returned null");
                }
            }
            else {
                response = RealCall.this.getResponse(request, this.forWebSocket);
            }
            return response;
        }
        
        @Override
        public Request request() {
            return this.request;
        }
    }
    
    final class AsyncCall extends NamedRunnable
    {
        private final boolean forWebSocket;
        private final Callback responseCallback;
        
        private AsyncCall(final Callback responseCallback, final boolean forWebSocket) {
            super("OkHttp %s", new Object[] { RealCall.this.originalRequest.url().toString() });
            this.responseCallback = responseCallback;
            this.forWebSocket = forWebSocket;
        }
        
        void cancel() {
            RealCall.this.cancel();
        }
        
        @Override
        protected void execute() {
            boolean b = false;
            try {
                final Response access$100 = RealCall.this.getResponseWithInterceptorChain(this.forWebSocket);
                if (RealCall.this.canceled) {
                    b = true;
                    this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                }
                else {
                    b = true;
                    this.responseCallback.onResponse(RealCall.this, access$100);
                }
            }
            catch (IOException ex) {
                if (b) {
                    Internal.logger.log(Level.INFO, "Callback failure for " + RealCall.this.toLoggableString(), ex);
                }
                else {
                    this.responseCallback.onFailure(RealCall.this, ex);
                }
            }
            finally {
                RealCall.this.client.dispatcher().finished(this);
            }
        }
        
        RealCall get() {
            return RealCall.this;
        }
        
        String host() {
            return RealCall.this.originalRequest.url().host();
        }
        
        Request request() {
            return RealCall.this.originalRequest;
        }
        
        Object tag() {
            return RealCall.this.originalRequest.tag();
        }
    }
}
