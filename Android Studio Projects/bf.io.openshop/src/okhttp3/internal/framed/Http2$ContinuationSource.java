package okhttp3.internal.framed;

import java.util.logging.*;
import java.io.*;
import okio.*;

static final class ContinuationSource implements Source
{
    byte flags;
    int left;
    int length;
    short padding;
    private final BufferedSource source;
    int streamId;
    
    public ContinuationSource(final BufferedSource source) {
        this.source = source;
    }
    
    private void readContinuationHeader() throws IOException {
        final int streamId = this.streamId;
        final int access$300 = Http2.access$300(this.source);
        this.left = access$300;
        this.length = access$300;
        final byte b = (byte)(0xFF & this.source.readByte());
        this.flags = (byte)(0xFF & this.source.readByte());
        if (Http2.access$100().isLoggable(Level.FINE)) {
            Http2.access$100().fine(FrameLogger.formatHeader(true, this.streamId, this.length, b, this.flags));
        }
        this.streamId = (Integer.MAX_VALUE & this.source.readInt());
        if (b != 9) {
            throw Http2.access$200("%s != TYPE_CONTINUATION", new Object[] { b });
        }
        if (this.streamId != streamId) {
            throw Http2.access$200("TYPE_CONTINUATION streamId changed", new Object[0]);
        }
    }
    
    @Override
    public void close() throws IOException {
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        while (this.left == 0) {
            this.source.skip(this.padding);
            this.padding = 0;
            if ((0x4 & this.flags) != 0x0) {
                return -1L;
            }
            this.readContinuationHeader();
        }
        final long read = this.source.read(buffer, Math.min(n, this.left));
        if (read == -1L) {
            return -1L;
        }
        this.left -= (int)read;
        return read;
    }
    
    @Override
    public Timeout timeout() {
        return this.source.timeout();
    }
}
