package okio;

import java.io.*;

static final class Okio$2 implements Source {
    final /* synthetic */ InputStream val$in;
    final /* synthetic */ Timeout val$timeout;
    
    @Override
    public void close() throws IOException {
        this.val$in.close();
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("byteCount < 0: " + n);
        }
        if (n == 0L) {
            return 0L;
        }
        this.val$timeout.throwIfReached();
        final Segment writableSegment = buffer.writableSegment(1);
        final int read = this.val$in.read(writableSegment.data, writableSegment.limit, (int)Math.min(n, 2048 - writableSegment.limit));
        if (read == -1) {
            return -1L;
        }
        writableSegment.limit += read;
        buffer.size += read;
        return read;
    }
    
    @Override
    public Timeout timeout() {
        return this.val$timeout;
    }
    
    @Override
    public String toString() {
        return "source(" + this.val$in + ")";
    }
}