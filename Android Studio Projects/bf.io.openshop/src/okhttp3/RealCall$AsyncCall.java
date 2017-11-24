package okhttp3;

import java.io.*;
import okhttp3.internal.*;
import java.util.logging.*;

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
            final Response access$100 = RealCall.access$100(RealCall.this, this.forWebSocket);
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
                Internal.logger.log(Level.INFO, "Callback failure for " + RealCall.access$200(RealCall.this), ex);
            }
            else {
                this.responseCallback.onFailure(RealCall.this, ex);
            }
        }
        finally {
            RealCall.access$300(RealCall.this).dispatcher().finished(this);
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
