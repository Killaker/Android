package okhttp3.internal.http;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import okhttp3.internal.*;
import okio.*;

private class ChunkedSource extends AbstractSource
{
    private static final long NO_CHUNK_YET = -1L;
    private long bytesRemainingInChunk;
    private boolean hasMoreChunks;
    private final HttpEngine httpEngine;
    
    ChunkedSource(final HttpEngine httpEngine) throws IOException {
        this.bytesRemainingInChunk = -1L;
        this.hasMoreChunks = true;
        this.httpEngine = httpEngine;
    }
    
    private void readChunkSize() throws IOException {
        if (this.bytesRemainingInChunk != -1L) {
            Http1xStream.access$600(Http1xStream.this).readUtf8LineStrict();
        }
        try {
            this.bytesRemainingInChunk = Http1xStream.access$600(Http1xStream.this).readHexadecimalUnsignedLong();
            final String trim = Http1xStream.access$600(Http1xStream.this).readUtf8LineStrict().trim();
            if (this.bytesRemainingInChunk < 0L || (!trim.isEmpty() && !trim.startsWith(";"))) {
                throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
            }
        }
        catch (NumberFormatException ex) {
            throw new ProtocolException(ex.getMessage());
        }
        if (this.bytesRemainingInChunk == 0L) {
            this.hasMoreChunks = false;
            this.httpEngine.receiveHeaders(Http1xStream.this.readHeaders());
            ((AbstractSource)this).endOfInput(true);
        }
    }
    
    @Override
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
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
        if (!this.hasMoreChunks) {
            return -1L;
        }
        if (this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
            this.readChunkSize();
            if (!this.hasMoreChunks) {
                return -1L;
            }
        }
        final long read = Http1xStream.access$600(Http1xStream.this).read(buffer, Math.min(n, this.bytesRemainingInChunk));
        if (read == -1L) {
            ((AbstractSource)this).endOfInput(false);
            throw new ProtocolException("unexpected end of stream");
        }
        this.bytesRemainingInChunk -= read;
        return read;
    }
}
