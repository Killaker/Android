package okhttp3.internal.framed;

import java.util.zip.*;
import java.util.*;
import okhttp3.internal.*;
import java.io.*;
import okio.*;

static final class Writer implements FrameWriter
{
    private final boolean client;
    private boolean closed;
    private final Buffer headerBlockBuffer;
    private final BufferedSink headerBlockOut;
    private final BufferedSink sink;
    
    Writer(final BufferedSink sink, final boolean client) {
        this.sink = sink;
        this.client = client;
        final Deflater deflater = new Deflater();
        deflater.setDictionary(Spdy3.DICTIONARY);
        this.headerBlockBuffer = new Buffer();
        this.headerBlockOut = Okio.buffer(new DeflaterSink((Sink)this.headerBlockBuffer, deflater));
    }
    
    private void writeNameValueBlockToBuffer(final List<Header> list) throws IOException {
        this.headerBlockOut.writeInt(list.size());
        for (int i = 0; i < list.size(); ++i) {
            final ByteString name = list.get(i).name;
            this.headerBlockOut.writeInt(name.size());
            this.headerBlockOut.write(name);
            final ByteString value = list.get(i).value;
            this.headerBlockOut.writeInt(value.size());
            this.headerBlockOut.write(value);
        }
        this.headerBlockOut.flush();
    }
    
    @Override
    public void ackSettings(final Settings settings) {
    }
    
    @Override
    public void close() throws IOException {
        synchronized (this) {
            this.closed = true;
            Util.closeAll(this.sink, this.headerBlockOut);
        }
    }
    
    @Override
    public void connectionPreface() {
    }
    // monitorenter(this)
    // monitorexit(this)
    
    @Override
    public void data(final boolean b, final int n, final Buffer buffer, final int n2) throws IOException {
        // monitorenter(this)
        Label_0022: {
            if (!b) {
                break Label_0022;
            }
            int n3 = 1;
            try {
                while (true) {
                    this.sendDataFrame(n, n3, buffer, n2);
                    return;
                    n3 = 0;
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
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
    
    @Override
    public void goAway(final int n, final ErrorCode errorCode, final byte[] array) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (errorCode.spdyGoAwayCode == -1) {
            throw new IllegalArgumentException("errorCode.spdyGoAwayCode == -1");
        }
        this.sink.writeInt(-2147287033);
        this.sink.writeInt(8);
        this.sink.writeInt(n);
        this.sink.writeInt(errorCode.spdyGoAwayCode);
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
        this.writeNameValueBlockToBuffer(list);
        final int n2 = (int)(4L + this.headerBlockBuffer.size());
        this.sink.writeInt(-2147287032);
        this.sink.writeInt(0x0 | (0xFFFFFF & n2));
        this.sink.writeInt(Integer.MAX_VALUE & n);
        this.sink.writeAll(this.headerBlockBuffer);
    }
    // monitorexit(this)
    
    @Override
    public int maxDataLength() {
        return 16383;
    }
    
    @Override
    public void ping(final boolean b, final int n, final int n2) throws IOException {
        boolean b2 = true;
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (this.client == ((n & 0x1) == (b2 ? 1 : 0) && b2)) {
            b2 = false;
        }
        if (b != b2) {
            throw new IllegalArgumentException("payload != reply");
        }
        this.sink.writeInt(-2147287034);
        this.sink.writeInt(4);
        this.sink.writeInt(n);
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void pushPromise(final int n, final int n2, final List<Header> list) throws IOException {
    }
    
    @Override
    public void rstStream(final int n, final ErrorCode errorCode) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        if (errorCode.spdyRstCode == -1) {
            throw new IllegalArgumentException();
        }
        this.sink.writeInt(-2147287037);
        this.sink.writeInt(8);
        this.sink.writeInt(Integer.MAX_VALUE & n);
        this.sink.writeInt(errorCode.spdyRstCode);
        this.sink.flush();
    }
    // monitorexit(this)
    
    void sendDataFrame(final int n, final int n2, final Buffer buffer, final int n3) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        if (n3 > 16777215L) {
            throw new IllegalArgumentException("FRAME_TOO_LARGE max size is 16Mib: " + n3);
        }
        this.sink.writeInt(Integer.MAX_VALUE & n);
        this.sink.writeInt((n2 & 0xFF) << 24 | (0xFFFFFF & n3));
        if (n3 > 0) {
            this.sink.write(buffer, n3);
        }
    }
    
    @Override
    public void settings(final Settings settings) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        final int size = settings.size();
        final int n = 4 + size * 8;
        this.sink.writeInt(-2147287036);
        this.sink.writeInt(0x0 | (n & 0xFFFFFF));
        this.sink.writeInt(size);
        for (int i = 0; i <= 10; ++i) {
            if (settings.isSet(i)) {
                this.sink.writeInt((settings.flags(i) & 0xFF) << 24 | (i & 0xFFFFFF));
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
        this.writeNameValueBlockToBuffer(list);
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final int n2 = (int)(4L + this.headerBlockBuffer.size());
        this.sink.writeInt(-2147287038);
        this.sink.writeInt(((b2 ? 1 : 0) & 0xFF) << 24 | (0xFFFFFF & n2));
        this.sink.writeInt(Integer.MAX_VALUE & n);
        this.sink.writeAll(this.headerBlockBuffer);
        this.sink.flush();
    }
    // monitorexit(this)
    
    @Override
    public void synStream(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
        }
        this.writeNameValueBlockToBuffer(list);
        final int n3 = (int)(10L + this.headerBlockBuffer.size());
        boolean b3;
        if (b) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        int n4;
        if (b2) {
            n4 = 2;
        }
        else {
            n4 = 0;
        }
        final boolean b4 = ((b3 ? 1 : 0) | n4) != 0x0;
        this.sink.writeInt(-2147287039);
        this.sink.writeInt(((b4 ? 1 : 0) & 0xFF) << 24 | (0xFFFFFF & n3));
        this.sink.writeInt(Integer.MAX_VALUE & n);
        this.sink.writeInt(Integer.MAX_VALUE & n2);
        this.sink.writeShort(0);
        this.sink.writeAll(this.headerBlockBuffer);
        this.sink.flush();
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
            throw new IllegalArgumentException("windowSizeIncrement must be between 1 and 0x7fffffff: " + n2);
        }
        this.sink.writeInt(-2147287031);
        this.sink.writeInt(8);
        this.sink.writeInt(n);
        this.sink.writeInt((int)n2);
        this.sink.flush();
    }
    // monitorexit(this)
}
