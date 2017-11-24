package okhttp3.internal.http;

import java.net.*;
import java.io.*;
import okio.*;
import okhttp3.internal.*;

private final class FixedLengthSink implements Sink
{
    private long bytesRemaining;
    private boolean closed;
    private final ForwardingTimeout timeout;
    
    private FixedLengthSink(final long bytesRemaining) {
        this.timeout = new ForwardingTimeout(Http1xStream.access$300(Http1xStream.this).timeout());
        this.bytesRemaining = bytesRemaining;
    }
    
    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        if (this.bytesRemaining > 0L) {
            throw new ProtocolException("unexpected end of stream");
        }
        Http1xStream.access$400(Http1xStream.this, this.timeout);
        Http1xStream.access$502(Http1xStream.this, 3);
    }
    
    @Override
    public void flush() throws IOException {
        if (this.closed) {
            return;
        }
        Http1xStream.access$300(Http1xStream.this).flush();
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
        Util.checkOffsetAndCount(buffer.size(), 0L, n);
        if (n > this.bytesRemaining) {
            throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + n);
        }
        Http1xStream.access$300(Http1xStream.this).write(buffer, n);
        this.bytesRemaining -= n;
    }
}
