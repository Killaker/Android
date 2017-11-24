package okhttp3;

import java.io.*;

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
        if (this.index < RealCall.access$300(RealCall.this).interceptors().size()) {
            final ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(1 + this.index, request, this.forWebSocket);
            final Interceptor interceptor = RealCall.access$300(RealCall.this).interceptors().get(this.index);
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
