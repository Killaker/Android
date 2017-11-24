package okhttp3.internal.http;

import java.io.*;
import okio.*;

private class UnknownLengthSource extends AbstractSource
{
    private boolean inputExhausted;
    
    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        if (!this.inputExhausted) {
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
        if (this.inputExhausted) {
            read = -1L;
        }
        else {
            read = Http1xStream.access$600(Http1xStream.this).read(buffer, n);
            if (read == -1L) {
                ((AbstractSource)this).endOfInput(this.inputExhausted = true);
                return -1L;
            }
        }
        return read;
    }
}
