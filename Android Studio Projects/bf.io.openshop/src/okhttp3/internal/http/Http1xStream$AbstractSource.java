package okhttp3.internal.http;

import java.io.*;
import okio.*;

private abstract class AbstractSource implements Source
{
    protected boolean closed;
    protected final ForwardingTimeout timeout;
    
    private AbstractSource() {
        this.timeout = new ForwardingTimeout(Http1xStream.access$600(Http1xStream.this).timeout());
    }
    
    protected final void endOfInput(final boolean b) throws IOException {
        if (Http1xStream.access$500(Http1xStream.this) != 6) {
            if (Http1xStream.access$500(Http1xStream.this) != 5) {
                throw new IllegalStateException("state: " + Http1xStream.access$500(Http1xStream.this));
            }
            Http1xStream.access$400(Http1xStream.this, this.timeout);
            Http1xStream.access$502(Http1xStream.this, 6);
            if (Http1xStream.access$700(Http1xStream.this) != null) {
                Http1xStream.access$700(Http1xStream.this).streamFinished(!b, Http1xStream.this);
            }
        }
    }
    
    @Override
    public Timeout timeout() {
        return this.timeout;
    }
}
