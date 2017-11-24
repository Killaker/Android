package okhttp3.internal.framed;

import okhttp3.internal.*;
import okhttp3.*;
import java.util.*;
import java.net.*;
import java.util.zip.*;
import java.io.*;
import okio.*;

public final class Spdy3 implements Variant
{
    static final byte[] DICTIONARY;
    static final int FLAG_FIN = 1;
    static final int FLAG_UNIDIRECTIONAL = 2;
    static final int TYPE_DATA = 0;
    static final int TYPE_GOAWAY = 7;
    static final int TYPE_HEADERS = 8;
    static final int TYPE_PING = 6;
    static final int TYPE_RST_STREAM = 3;
    static final int TYPE_SETTINGS = 4;
    static final int TYPE_SYN_REPLY = 2;
    static final int TYPE_SYN_STREAM = 1;
    static final int TYPE_WINDOW_UPDATE = 9;
    static final int VERSION = 3;
    
    static {
        try {
            DICTIONARY = "\u0000\u0000\u0000\u0007options\u0000\u0000\u0000\u0004head\u0000\u0000\u0000\u0004post\u0000\u0000\u0000\u0003put\u0000\u0000\u0000\u0006delete\u0000\u0000\u0000\u0005trace\u0000\u0000\u0000\u0006accept\u0000\u0000\u0000\u000eaccept-charset\u0000\u0000\u0000\u000faccept-encoding\u0000\u0000\u0000\u000faccept-language\u0000\u0000\u0000\raccept-ranges\u0000\u0000\u0000\u0003age\u0000\u0000\u0000\u0005allow\u0000\u0000\u0000\rauthorization\u0000\u0000\u0000\rcache-control\u0000\u0000\u0000\nconnection\u0000\u0000\u0000\fcontent-base\u0000\u0000\u0000\u0010content-encoding\u0000\u0000\u0000\u0010content-language\u0000\u0000\u0000\u000econtent-length\u0000\u0000\u0000\u0010content-location\u0000\u0000\u0000\u000bcontent-md5\u0000\u0000\u0000\rcontent-range\u0000\u0000\u0000\fcontent-type\u0000\u0000\u0000\u0004date\u0000\u0000\u0000\u0004etag\u0000\u0000\u0000\u0006expect\u0000\u0000\u0000\u0007expires\u0000\u0000\u0000\u0004from\u0000\u0000\u0000\u0004host\u0000\u0000\u0000\bif-match\u0000\u0000\u0000\u0011if-modified-since\u0000\u0000\u0000\rif-none-match\u0000\u0000\u0000\bif-range\u0000\u0000\u0000\u0013if-unmodified-since\u0000\u0000\u0000\rlast-modified\u0000\u0000\u0000\blocation\u0000\u0000\u0000\fmax-forwards\u0000\u0000\u0000\u0006pragma\u0000\u0000\u0000\u0012proxy-authenticate\u0000\u0000\u0000\u0013proxy-authorization\u0000\u0000\u0000\u0005range\u0000\u0000\u0000\u0007referer\u0000\u0000\u0000\u000bretry-after\u0000\u0000\u0000\u0006server\u0000\u0000\u0000\u0002te\u0000\u0000\u0000\u0007trailer\u0000\u0000\u0000\u0011transfer-encoding\u0000\u0000\u0000\u0007upgrade\u0000\u0000\u0000\nuser-agent\u0000\u0000\u0000\u0004vary\u0000\u0000\u0000\u0003via\u0000\u0000\u0000\u0007warning\u0000\u0000\u0000\u0010www-authenticate\u0000\u0000\u0000\u0006method\u0000\u0000\u0000\u0003get\u0000\u0000\u0000\u0006status\u0000\u0000\u0000\u0006200 OK\u0000\u0000\u0000\u0007version\u0000\u0000\u0000\bHTTP/1.1\u0000\u0000\u0000\u0003url\u0000\u0000\u0000\u0006public\u0000\u0000\u0000\nset-cookie\u0000\u0000\u0000\nkeep-alive\u0000\u0000\u0000\u0006origin100101201202205206300302303304305306307402405406407408409410411412413414415416417502504505203 Non-Authoritative Information204 No Content301 Moved Permanently400 Bad Request401 Unauthorized403 Forbidden404 Not Found500 Internal Server Error501 Not Implemented503 Service UnavailableJan Feb Mar Apr May Jun Jul Aug Sept Oct Nov Dec 00:00:00 Mon, Tue, Wed, Thu, Fri, Sat, Sun, GMTchunked,text/html,image/png,image/jpg,image/gif,application/xml,application/xhtml+xml,text/plain,text/javascript,publicprivatemax-age=gzip,deflate,sdchcharset=utf-8charset=iso-8859-1,utf-,*,enq=0.".getBytes(Util.UTF_8.name());
        }
        catch (UnsupportedEncodingException ex) {
            throw new AssertionError();
        }
    }
    
    @Override
    public Protocol getProtocol() {
        return Protocol.SPDY_3;
    }
    
    @Override
    public FrameReader newReader(final BufferedSource bufferedSource, final boolean b) {
        return new Reader(bufferedSource, b);
    }
    
    @Override
    public FrameWriter newWriter(final BufferedSink bufferedSink, final boolean b) {
        return new Writer(bufferedSink, b);
    }
    
    static final class Reader implements FrameReader
    {
        private final boolean client;
        private final NameValueBlockReader headerBlockReader;
        private final BufferedSource source;
        
        Reader(final BufferedSource source, final boolean client) {
            this.source = source;
            this.headerBlockReader = new NameValueBlockReader(this.source);
            this.client = client;
        }
        
        private static IOException ioException(final String s, final Object... array) throws IOException {
            throw new IOException(String.format(s, array));
        }
        
        private void readGoAway(final Handler handler, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_GOAWAY length: %d != 8", n2);
            }
            final int n3 = Integer.MAX_VALUE & this.source.readInt();
            final int int1 = this.source.readInt();
            final ErrorCode fromSpdyGoAway = ErrorCode.fromSpdyGoAway(int1);
            if (fromSpdyGoAway == null) {
                throw ioException("TYPE_GOAWAY unexpected error code: %d", int1);
            }
            handler.goAway(n3, fromSpdyGoAway, ByteString.EMPTY);
        }
        
        private void readHeaders(final Handler handler, final int n, final int n2) throws IOException {
            handler.headers(false, false, Integer.MAX_VALUE & this.source.readInt(), -1, this.headerBlockReader.readNameValueBlock(n2 - 4), HeadersMode.SPDY_HEADERS);
        }
        
        private void readPing(final Handler handler, final int n, final int n2) throws IOException {
            int n3 = 1;
            if (n2 != 4) {
                final Object[] array = new Object[n3];
                array[0] = n2;
                throw ioException("TYPE_PING length: %d != 4", array);
            }
            final int int1 = this.source.readInt();
            if (this.client != ((int1 & 0x1) == n3 && n3)) {
                n3 = 0;
            }
            handler.ping(n3 != 0, int1, 0);
        }
        
        private void readRstStream(final Handler handler, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_RST_STREAM length: %d != 8", n2);
            }
            final int n3 = Integer.MAX_VALUE & this.source.readInt();
            final int int1 = this.source.readInt();
            final ErrorCode fromSpdy3Rst = ErrorCode.fromSpdy3Rst(int1);
            if (fromSpdy3Rst == null) {
                throw ioException("TYPE_RST_STREAM unexpected error code: %d", int1);
            }
            handler.rstStream(n3, fromSpdy3Rst);
        }
        
        private void readSettings(final Handler handler, final int n, final int n2) throws IOException {
            int n3 = 1;
            final int int1 = this.source.readInt();
            if (n2 != 4 + int1 * 8) {
                final Object[] array = { n2, null };
                array[n3] = int1;
                throw ioException("TYPE_SETTINGS length: %d != 4 + 8 * %d", array);
            }
            final Settings settings = new Settings();
            for (int i = 0; i < int1; ++i) {
                final int int2 = this.source.readInt();
                settings.set(int2 & 0xFFFFFF, (0xFF000000 & int2) >>> 24, this.source.readInt());
            }
            if ((n & 0x1) == 0x0) {
                n3 = 0;
            }
            handler.settings(n3 != 0, settings);
        }
        
        private void readSynReply(final Handler handler, final int n, final int n2) throws IOException {
            handler.headers(false, (n & 0x1) != 0x0, Integer.MAX_VALUE & this.source.readInt(), -1, this.headerBlockReader.readNameValueBlock(n2 - 4), HeadersMode.SPDY_REPLY);
        }
        
        private void readSynStream(final Handler handler, final int n, final int n2) throws IOException {
            boolean b = true;
            final int int1 = this.source.readInt();
            final int int2 = this.source.readInt();
            final int n3 = int1 & Integer.MAX_VALUE;
            final int n4 = int2 & Integer.MAX_VALUE;
            this.source.readShort();
            final List<Header> nameValueBlock = this.headerBlockReader.readNameValueBlock(n2 - 10);
            final boolean b2 = (n & 0x1) != 0x0 && b;
            if ((n & 0x2) == 0x0) {
                b = false;
            }
            handler.headers(b, b2, n3, n4, nameValueBlock, HeadersMode.SPDY_SYN_STREAM);
        }
        
        private void readWindowUpdate(final Handler handler, final int n, final int n2) throws IOException {
            if (n2 != 8) {
                throw ioException("TYPE_WINDOW_UPDATE length: %d != 8", n2);
            }
            final int int1 = this.source.readInt();
            final int int2 = this.source.readInt();
            final int n3 = int1 & Integer.MAX_VALUE;
            final long n4 = int2 & Integer.MAX_VALUE;
            if (n4 == 0L) {
                throw ioException("windowSizeIncrement was 0", n4);
            }
            handler.windowUpdate(n3, n4);
        }
        
        @Override
        public void close() throws IOException {
            this.headerBlockReader.close();
        }
        
        @Override
        public boolean nextFrame(final Handler handler) throws IOException {
            while (true) {
                int int1 = 0;
                int n2 = 0;
                int n3 = 0;
                Label_0266: {
                    int n5 = 0;
                Label_0111:
                    while (true) {
                        try {
                            int1 = this.source.readInt();
                            final int int2 = this.source.readInt();
                            if ((Integer.MIN_VALUE & int1) != 0x0) {
                                final int n = 1;
                                n2 = (0xFF000000 & int2) >>> 24;
                                n3 = (int2 & 0xFFFFFF);
                                if (n == 0) {
                                    break Label_0266;
                                }
                                final int n4 = (0x7FFF0000 & int1) >>> 16;
                                n5 = (int1 & 0xFFFF);
                                if (n4 != 3) {
                                    throw new ProtocolException("version != 3: " + n4);
                                }
                                break Label_0111;
                            }
                        }
                        catch (IOException ex) {
                            return false;
                        }
                        final int n = 0;
                        continue;
                    }
                    switch (n5) {
                        default: {
                            this.source.skip(n3);
                            return true;
                        }
                        case 1: {
                            this.readSynStream(handler, n2, n3);
                            return true;
                        }
                        case 2: {
                            this.readSynReply(handler, n2, n3);
                            return true;
                        }
                        case 3: {
                            this.readRstStream(handler, n2, n3);
                            return true;
                        }
                        case 4: {
                            this.readSettings(handler, n2, n3);
                            return true;
                        }
                        case 6: {
                            this.readPing(handler, n2, n3);
                            return true;
                        }
                        case 7: {
                            this.readGoAway(handler, n2, n3);
                            return true;
                        }
                        case 8: {
                            this.readHeaders(handler, n2, n3);
                            return true;
                        }
                        case 9: {
                            this.readWindowUpdate(handler, n2, n3);
                            return true;
                        }
                    }
                }
                final int n6 = int1 & Integer.MAX_VALUE;
                final int n7 = n2 & 0x1;
                boolean b = false;
                if (n7 != 0) {
                    b = true;
                }
                handler.data(b, n6, this.source, n3);
                return true;
            }
        }
        
        @Override
        public void readConnectionPreface() {
        }
    }
    
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
}
