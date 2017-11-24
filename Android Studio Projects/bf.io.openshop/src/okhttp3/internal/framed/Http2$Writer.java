package okhttp3.internal.framed;

import okio.*;
import java.io.*;
import java.util.logging.*;
import java.util.*;

static final class Writer implements FrameWriter
{
    private final boolean client;
    private boolean closed;
    private final Buffer hpackBuffer;
    private final Hpack.Writer hpackWriter;
    private int maxFrameSize;
    private final BufferedSink sink;
    
    Writer(final BufferedSink sink, final boolean client) {
        this.sink = sink;
        this.client = client;
        this.hpackBuffer = new Buffer();
        this.hpackWriter = new Hpack.Writer(this.hpackBuffer);
        this.maxFrameSize = 16384;
    }
    
    private void writeContinuationFrames(final int n, long n2) throws IOException {
        while (n2 > 0L) {
            final int n3 = (int)Math.min(this.maxFrameSize, n2);
            n2 -= n3;
            byte b;
            if (n2 == 0L) {
                b = 4;
            }
            else {
                b = 0;
            }
            this.frameHeader(n, n3, (byte)9, b);
            this.sink.write(this.hpackBuffer, n3);
        }
    }
    
    @Override
    public void ackSettings(final Settings settings) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
        this.frameHeader(0, 0, (byte)4, (byte)1);
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void close() throws IOException {
        synchronized (this) {
            this.closed = true;
            this.sink.close();
        }
    }
    
    @Override
    public void connectionPreface() throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (this.client) {
            if (Http2.access$100().isLoggable(Level.FINE)) {
                Http2.access$100().fine(String.format(">> CONNECTION %s", Http2.access$000().hex()));
            }
            this.sink.write(Http2.access$000().toByteArray());
            this.sink.flush();
        }
    }
    // monitorexit(this)
    
    @Override
    public void data(final boolean b, final int n, final Buffer buffer, final int n2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        byte b2 = 0;
        if (b) {
            b2 = 1;
        }
        this.dataFrame(n, b2, buffer, n2);
    }
    // monitorexit(this)
    
    void dataFrame(final int n, final byte b, final Buffer buffer, final int n2) throws IOException {
        this.frameHeader(n, n2, (byte)0, b);
        if (n2 > 0) {
            this.sink.write(buffer, n2);
        }
    }
    
    @Override
    public void flush() throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.sink.flush();
    }
    // monitorexit(this)
    
    void frameHeader(final int n, final int n2, final byte b, final byte b2) throws IOException {
        if (Http2.access$100().isLoggable(Level.FINE)) {
            Http2.access$100().fine(FrameLogger.formatHeader(false, n, n2, b, b2));
        }
        if (n2 > this.maxFrameSize) {
            throw Http2.access$500("FRAME_SIZE_ERROR length > %d: %d", new Object[] { this.maxFrameSize, n2 });
        }
        if ((Integer.MIN_VALUE & n) != 0x0) {
            throw Http2.access$500("reserved bit set: %s", new Object[] { n });
        }
        Http2.access$600(this.sink, n2);
        this.sink.writeByte(b & 0xFF);
        this.sink.writeByte(b2 & 0xFF);
        this.sink.writeInt(Integer.MAX_VALUE & n);
    }
    
    @Override
    public void goAway(final int n, final ErrorCode errorCode, final byte[] array) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (errorCode.httpCode == -1) {
            throw Http2.access$500("errorCode.httpCode == -1", new Object[0]);
        }
        this.frameHeader(0, 8 + array.length, (byte)7, (byte)0);
        this.sink.writeInt(n);
        this.sink.writeInt(errorCode.httpCode);
        if (array.length > 0) {
            this.sink.write(array);
        }
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void headers(final int n, final List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.headers(false, n, list);
    }
    // monitorexit(this)
    
    void headers(final boolean b, final int n, final List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        final long size = this.hpackBuffer.size();
        final int n2 = (int)Math.min(this.maxFrameSize, size);
        byte b2;
        if (size == n2) {
            b2 = 4;
        }
        else {
            b2 = 0;
        }
        if (b) {
            b2 |= 0x1;
        }
        this.frameHeader(n, n2, (byte)1, b2);
        this.sink.write(this.hpackBuffer, n2);
        if (size > n2) {
            this.writeContinuationFrames(n, size - n2);
        }
    }
    
    @Override
    public int maxDataLength() {
        return this.maxFrameSize;
    }
    
    @Override
    public void ping(final boolean b, final int n, final int n2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        byte b2;
        if (b) {
            b2 = 1;
        }
        else {
            b2 = 0;
        }
        this.frameHeader(0, 8, (byte)6, b2);
        this.sink.writeInt(n);
        this.sink.writeInt(n2);
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void pushPromise(final int n, final int n2, final List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.hpackWriter.writeHeaders(list);
        final long size = this.hpackBuffer.size();
        final int n3 = (int)Math.min(-4 + this.maxFrameSize, size);
        byte b;
        if (size == n3) {
            b = 4;
        }
        else {
            b = 0;
        }
        this.frameHeader(n, n3 + 4, (byte)5, b);
        this.sink.writeInt(Integer.MAX_VALUE & n2);
        this.sink.write(this.hpackBuffer, n3);
        if (size > n3) {
            this.writeContinuationFrames(n, size - n3);
        }
    }
    // monitorexit(this)
    
    @Override
    public void rstStream(final int n, final ErrorCode errorCode) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (errorCode.httpCode == -1) {
            throw new IllegalArgumentException();
        }
        this.frameHeader(n, 4, (byte)3, (byte)0);
        this.sink.writeInt(errorCode.httpCode);
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void settings(final Settings settings) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.frameHeader(0, 6 * settings.size(), (byte)4, (byte)0);
        for (int i = 0; i < 10; ++i) {
            if (settings.isSet(i)) {
                int n = i;
                if (n == 4) {
                    n = 3;
                }
                else if (n == 7) {
                    n = 4;
                }
                this.sink.writeShort(n);
                this.sink.writeInt(settings.get(i));
            }
        }
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void synReply(final boolean b, final int n, final List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.headers(b, n, list);
    }
    // monitorexit(this)
    
    @Override
    public void synStream(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list) throws IOException {
        // monitorenter(this)
        if (b2) {
            try {
                throw new UnsupportedOperationException();
            }
            finally {
            }
            // monitorexit(this)
        }
        if (this.closed) {
            throw new IOException("closed");
        }
        this.headers(b, n, list);
    }
    // monitorexit(this)
    
    @Override
    public void windowUpdate(final int n, final long n2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (n2 == 0L || n2 > 2147483647L) {
            throw Http2.access$500("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] { n2 });
        }
        this.frameHeader(n, 4, (byte)8, (byte)0);
        this.sink.writeInt((int)n2);
        this.sink.flush();
    }
    // monitorexit(this)
}
