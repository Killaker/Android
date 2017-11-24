package okhttp3.internal.http;

import java.io.*;
import okio.*;

private final class ChunkedSink implements Sink
{
    private boolean closed;
    private final ForwardingTimeout timeout;
    
    private ChunkedSink() {
        this.timeout = new ForwardingTimeout(Http1xStream.access$300(Http1xStream.this).timeout());
    }
    
    @Override
    public void close() throws IOException {
        synchronized (this) {
            if (!this.closed) {
                this.closed = true;
                Http1xStream.access$300(Http1xStream.this).writeUtf8("0\r\n\r\n");
                Http1xStream.access$400(Http1xStream.this, this.timeout);
                Http1xStream.access$502(Http1xStream.this, 3);
            }
        }
    }
    
    @Override
    public void flush() throws IOException {
        synchronized (this) {
            if (!this.closed) {
                Http1xStream.access$300(Http1xStream.this).flush();
            }
        }
    }
    
    @Override
    public Timeout timeout() {
        return this.timeout;
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (n == 0L) {
            return;
        }
        Http1xStream.access$300(Http1xStream.this).writeHexadecimalUnsignedLong(n);
        Http1xStream.access$300(Http1xStream.this).writeUtf8("\r\n");
        Http1xStream.access$300(Http1xStream.this).write(buffer, n);
        Http1xStream.access$300(Http1xStream.this).writeUtf8("\r\n");
    }
}
