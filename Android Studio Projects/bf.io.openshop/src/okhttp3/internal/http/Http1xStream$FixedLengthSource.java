package okhttp3.internal.http;

import java.io.*;
import java.util.concurrent.*;
import okhttp3.internal.*;
import okio.*;
import java.net.*;

private class FixedLengthSource extends AbstractSource
{
    private long bytesRemaining;
    
    public FixedLengthSource(final long bytesRemaining) throws IOException {
        this.bytesRemaining = bytesRemaining;
        if (this.bytesRemaining == 0L) {
            ((AbstractSource)this).endOfInput(true);
        }
    }
    
    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.bytesRemaining != 0L && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
            ((AbstractSource)this).endOfInput(false);
        }
        this.closed = true;
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long read;
        if (this.bytesRemaining == 0L) {
            read = -1L;
        }
        else {
            read = Http1xStream.access$600(Http1xStream.this).read(buffer, Math.min(this.bytesRemaining, n));
            if (read == -1L) {
                ((AbstractSource)this).endOfInput(false);
                throw new ProtocolException("unexpected end of stream");
            }
            this.bytesRemaining -= read;
            if (this.bytesRemaining == 0L) {
                ((AbstractSource)this).endOfInput(true);
                return read;
            }
        }
        return read;
    }
}
