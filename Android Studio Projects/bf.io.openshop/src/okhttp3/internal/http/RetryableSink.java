package okhttp3.internal.http;

import java.net.*;
import java.io.*;
import okio.*;
import okhttp3.internal.*;

public final class RetryableSink implements Sink
{
    private boolean closed;
    private final Buffer content;
    private final int limit;
    
    public RetryableSink() {
        this(-1);
    }
    
    public RetryableSink(final int limit) {
        this.content = new Buffer();
        this.limit = limit;
    }
    
    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (this.content.size() < this.limit) {
                throw new ProtocolException("content-length promised " + this.limit + " bytes, but received " + this.content.size());
            }
        }
    }
    
    public long contentLength() throws IOException {
        return this.content.size();
    }
    
    @Override
    public void flush() throws IOException {
    }
    
    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        Util.checkOffsetAndCount(buffer.size(), 0L, n);
        if (this.limit != -1 && this.content.size() > this.limit - n) {
            throw new ProtocolException("exceeded content-length limit of " + this.limit + " bytes");
        }
        this.content.write(buffer, n);
    }
    
    public void writeToSocket(final Sink sink) throws IOException {
        final Buffer buffer = new Buffer();
        this.content.copyTo(buffer, 0L, this.content.size());
        sink.write(buffer, buffer.size());
    }
}
