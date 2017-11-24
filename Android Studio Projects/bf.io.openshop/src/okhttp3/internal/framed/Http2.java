package okhttp3.internal.framed;

import java.io.*;
import okhttp3.*;
import java.util.logging.*;
import okio.*;
import java.util.*;

public final class Http2 implements Variant
{
    private static final ByteString CONNECTION_PREFACE;
    static final byte FLAG_ACK = 1;
    static final byte FLAG_COMPRESSED = 32;
    static final byte FLAG_END_HEADERS = 4;
    static final byte FLAG_END_PUSH_PROMISE = 4;
    static final byte FLAG_END_STREAM = 1;
    static final byte FLAG_NONE = 0;
    static final byte FLAG_PADDED = 8;
    static final byte FLAG_PRIORITY = 32;
    static final int INITIAL_MAX_FRAME_SIZE = 16384;
    static final byte TYPE_CONTINUATION = 9;
    static final byte TYPE_DATA = 0;
    static final byte TYPE_GOAWAY = 7;
    static final byte TYPE_HEADERS = 1;
    static final byte TYPE_PING = 6;
    static final byte TYPE_PRIORITY = 2;
    static final byte TYPE_PUSH_PROMISE = 5;
    static final byte TYPE_RST_STREAM = 3;
    static final byte TYPE_SETTINGS = 4;
    static final byte TYPE_WINDOW_UPDATE = 8;
    private static final Logger logger;
    
    static {
        logger = Logger.getLogger(FrameLogger.class.getName());
        CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    }
    
    private static IllegalArgumentException illegalArgument(final String s, final Object... array) {
        throw new IllegalArgumentException(String.format(s, array));
    }
    
    private static IOException ioException(final String s, final Object... array) throws IOException {
        throw new IOException(String.format(s, array));
    }
    
    private static int lengthWithoutPadding(int n, final byte b, final short n2) throws IOException {
        if ((b & 0x8) != 0x0) {
            --n;
        }
        if (n2 > n) {
            throw ioException("PROTOCOL_ERROR padding %s > remaining length %s", n2, n);
        }
        return (short)(n - n2);
    }
    
    private static int readMedium(final BufferedSource bufferedSource) throws IOException {
        return (0xFF & bufferedSource.readByte()) << 16 | (0xFF & bufferedSource.readByte()) << 8 | (0xFF & bufferedSource.readByte());
    }
    
    private static void writeMedium(final BufferedSink bufferedSink, final int n) throws IOException {
        bufferedSink.writeByte(0xFF & n >>> 16);
        bufferedSink.writeByte(0xFF & n >>> 8);
        bufferedSink.writeByte(n & 0xFF);
    }
    
    @Override
    public Protocol getProtocol() {
        return Protocol.HTTP_2;
    }
    
    @Override
    public FrameReader newReader(final BufferedSource bufferedSource, final boolean b) {
        return new Reader(bufferedSource, 4096, b);
    }
    
    @Override
    public FrameWriter newWriter(final BufferedSink bufferedSink, final boolean b) {
        return new Writer(bufferedSink, b);
    }
    
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
            final int access$300 = readMedium(this.source);
            this.left = access$300;
            this.length = access$300;
            final byte b = (byte)(0xFF & this.source.readByte());
            this.flags = (byte)(0xFF & this.source.readByte());
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, b, this.flags));
            }
            this.streamId = (Integer.MAX_VALUE & this.source.readInt());
            if (b != 9) {
                throw ioException("%s != TYPE_CONTINUATION", new Object[] { b });
            }
            if (this.streamId != streamId) {
                throw ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
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
    
    static final class FrameLogger
    {
        private static final String[] BINARY;
        private static final String[] FLAGS;
        private static final String[] TYPES;
        
        static {
            TYPES = new String[] { "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION" };
            FLAGS = new String[64];
            BINARY = new String[256];
            for (int i = 0; i < FrameLogger.BINARY.length; ++i) {
                FrameLogger.BINARY[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
            }
            FrameLogger.FLAGS[0] = "";
            FrameLogger.FLAGS[1] = "END_STREAM";
            final int[] array = { 1 };
            FrameLogger.FLAGS[8] = "PADDED";
            for (final int n : array) {
                FrameLogger.FLAGS[n | 0x8] = FrameLogger.FLAGS[n] + "|PADDED";
            }
            FrameLogger.FLAGS[4] = "END_HEADERS";
            FrameLogger.FLAGS[32] = "PRIORITY";
            FrameLogger.FLAGS[36] = "END_HEADERS|PRIORITY";
            for (final int n2 : new int[] { 4, 32, 36 }) {
                for (final int n3 : array) {
                    FrameLogger.FLAGS[n3 | n2] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2];
                    FrameLogger.FLAGS[0x8 | (n3 | n2)] = FrameLogger.FLAGS[n3] + '|' + FrameLogger.FLAGS[n2] + "|PADDED";
                }
            }
            for (int n4 = 0; n4 < FrameLogger.FLAGS.length; ++n4) {
                if (FrameLogger.FLAGS[n4] == null) {
                    FrameLogger.FLAGS[n4] = FrameLogger.BINARY[n4];
                }
            }
        }
        
        static String formatFlags(final byte b, final byte b2) {
            if (b2 == 0) {
                return "";
            }
            switch (b) {
                default: {
                    String s;
                    if (b2 < FrameLogger.FLAGS.length) {
                        s = FrameLogger.FLAGS[b2];
                    }
                    else {
                        s = FrameLogger.BINARY[b2];
                    }
                    if (b == 5 && (b2 & 0x4) != 0x0) {
                        return s.replace("HEADERS", "PUSH_PROMISE");
                    }
                    if (b == 0 && (b2 & 0x20) != 0x0) {
                        return s.replace("PRIORITY", "COMPRESSED");
                    }
                    return s;
                }
                case 4:
                case 6: {
                    if (b2 == 1) {
                        return "ACK";
                    }
                    return FrameLogger.BINARY[b2];
                }
                case 2:
                case 3:
                case 7:
                case 8: {
                    return FrameLogger.BINARY[b2];
                }
            }
        }
        
        static String formatHeader(final boolean b, final int n, final int n2, final byte b2, final byte b3) {
            String format;
            if (b2 < FrameLogger.TYPES.length) {
                format = FrameLogger.TYPES[b2];
            }
            else {
                format = String.format("0x%02x", b2);
            }
            final String formatFlags = formatFlags(b2, b3);
            final Object[] array = new Object[5];
            String s;
            if (b) {
                s = "<<";
            }
            else {
                s = ">>";
            }
            array[0] = s;
            array[1] = n;
            array[2] = n2;
            array[3] = format;
            array[4] = formatFlags;
            return String.format("%s 0x%08x %5d %-13s %s", array);
        }
    }
    
    static final class Reader implements FrameReader
    {
        private final boolean client;
        private final ContinuationSource continuation;
        final Hpack.Reader hpackReader;
        private final BufferedSource source;
        
        Reader(final BufferedSource source, final int n, final boolean client) {
            this.source = source;
            this.client = client;
            this.continuation = new ContinuationSource(this.source);
            this.hpackReader = new Hpack.Reader(n, this.continuation);
        }
        
        private void readData(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            boolean b2 = true;
            final boolean b3 = (b & 0x1) != 0x0 && b2;
            if ((b & 0x20) == 0x0) {
                b2 = false;
            }
            if (b2) {
                throw ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
            }
            final byte b4 = (byte)(b & 0x8);
            short n3 = 0;
            if (b4 != 0) {
                n3 = (short)(0xFF & this.source.readByte());
            }
            handler.data(b3, n2, this.source, lengthWithoutPadding(n, b, n3));
            this.source.skip(n3);
        }
        
        private void readGoAway(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n < 8) {
                throw ioException("TYPE_GOAWAY length < 8: %s", new Object[] { n });
            }
            if (n2 != 0) {
                throw ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            }
            final int int1 = this.source.readInt();
            final int int2 = this.source.readInt();
            final int n3 = n - 8;
            final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int2);
            if (fromHttp2 == null) {
                throw ioException("TYPE_GOAWAY unexpected error code: %d", new Object[] { int2 });
            }
            ByteString byteString = ByteString.EMPTY;
            if (n3 > 0) {
                byteString = this.source.readByteString(n3);
            }
            handler.goAway(int1, fromHttp2, byteString);
        }
        
        private List<Header> readHeaderBlock(final int n, final short padding, final byte flags, final int streamId) throws IOException {
            final ContinuationSource continuation = this.continuation;
            this.continuation.left = n;
            continuation.length = n;
            this.continuation.padding = padding;
            this.continuation.flags = flags;
            this.continuation.streamId = streamId;
            this.hpackReader.readHeaders();
            return this.hpackReader.getAndResetHeaderList();
        }
        
        private void readHeaders(final Handler handler, int n, final byte b, final int n2) throws IOException {
            if (n2 == 0) {
                throw ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            }
            final boolean b2 = (b & 0x1) != 0x0;
            short n3;
            if ((b & 0x8) != 0x0) {
                n3 = (short)(0xFF & this.source.readByte());
            }
            else {
                n3 = 0;
            }
            if ((b & 0x20) != 0x0) {
                this.readPriority(handler, n2);
                n -= 5;
            }
            handler.headers(false, b2, n2, -1, this.readHeaderBlock(lengthWithoutPadding(n, b, n3), n3, b, n2), HeadersMode.HTTP_20_HEADERS);
        }
        
        private void readPing(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            int n3 = 1;
            if (n != 8) {
                final Object[] array = new Object[n3];
                array[0] = n;
                throw ioException("TYPE_PING length != 8: %s", array);
            }
            if (n2 != 0) {
                throw ioException("TYPE_PING streamId != 0", new Object[0]);
            }
            final int int1 = this.source.readInt();
            final int int2 = this.source.readInt();
            if ((b & 0x1) == 0x0) {
                n3 = 0;
            }
            handler.ping(n3 != 0, int1, int2);
        }
        
        private void readPriority(final Handler handler, final int n) throws IOException {
            final int int1 = this.source.readInt();
            handler.priority(n, int1 & Integer.MAX_VALUE, 1 + (0xFF & this.source.readByte()), (Integer.MIN_VALUE & int1) != 0x0);
        }
        
        private void readPriority(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n != 5) {
                throw ioException("TYPE_PRIORITY length: %d != 5", new Object[] { n });
            }
            if (n2 == 0) {
                throw ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            }
            this.readPriority(handler, n2);
        }
        
        private void readPushPromise(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n2 == 0) {
                throw ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            }
            final byte b2 = (byte)(b & 0x8);
            short n3 = 0;
            if (b2 != 0) {
                n3 = (short)(0xFF & this.source.readByte());
            }
            handler.pushPromise(n2, Integer.MAX_VALUE & this.source.readInt(), this.readHeaderBlock(lengthWithoutPadding(n - 4, b, n3), n3, b, n2));
        }
        
        private void readRstStream(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n != 4) {
                throw ioException("TYPE_RST_STREAM length: %d != 4", new Object[] { n });
            }
            if (n2 == 0) {
                throw ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            }
            final int int1 = this.source.readInt();
            final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int1);
            if (fromHttp2 == null) {
                throw ioException("TYPE_RST_STREAM unexpected error code: %d", new Object[] { int1 });
            }
            handler.rstStream(n2, fromHttp2);
        }
        
        private void readSettings(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n2 != 0) {
                throw ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            }
            if ((b & 0x1) != 0x0) {
                if (n != 0) {
                    throw ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
                handler.ackSettings();
            }
            else {
                if (n % 6 != 0) {
                    throw ioException("TYPE_SETTINGS length %% 6 != 0: %s", new Object[] { n });
                }
                final Settings settings = new Settings();
                for (int i = 0; i < n; i += 6) {
                    int short1 = this.source.readShort();
                    final int int1 = this.source.readInt();
                    switch (short1) {
                        case 2: {
                            if (int1 != 0 && int1 != 1) {
                                throw ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                            }
                            break;
                        }
                        case 3: {
                            short1 = 4;
                            break;
                        }
                        case 4: {
                            short1 = 7;
                            if (int1 < 0) {
                                throw ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                            }
                            break;
                        }
                        case 5: {
                            if (int1 < 16384 || int1 > 16777215) {
                                throw ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[] { int1 });
                            }
                            break;
                        }
                    }
                    settings.set(short1, 0, int1);
                }
                handler.settings(false, settings);
                if (settings.getHeaderTableSize() >= 0) {
                    this.hpackReader.headerTableSizeSetting(settings.getHeaderTableSize());
                }
            }
        }
        
        private void readWindowUpdate(final Handler handler, final int n, final byte b, final int n2) throws IOException {
            if (n != 4) {
                throw ioException("TYPE_WINDOW_UPDATE length !=4: %s", new Object[] { n });
            }
            final long n3 = 0x7FFFFFFFL & this.source.readInt();
            if (n3 == 0L) {
                throw ioException("windowSizeIncrement was 0", new Object[] { n3 });
            }
            handler.windowUpdate(n2, n3);
        }
        
        @Override
        public void close() throws IOException {
            this.source.close();
        }
        
        @Override
        public boolean nextFrame(final Handler handler) throws IOException {
            int access$300;
            try {
                this.source.require(9L);
                access$300 = readMedium(this.source);
                if (access$300 < 0 || access$300 > 16384) {
                    throw ioException("FRAME_SIZE_ERROR: %s", new Object[] { access$300 });
                }
            }
            catch (IOException ex) {
                return false;
            }
            final byte b = (byte)(0xFF & this.source.readByte());
            final byte b2 = (byte)(0xFF & this.source.readByte());
            final int n = Integer.MAX_VALUE & this.source.readInt();
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, n, access$300, b, b2));
            }
            switch (b) {
                default: {
                    this.source.skip(access$300);
                    return true;
                }
                case 0: {
                    this.readData(handler, access$300, b2, n);
                    return true;
                }
                case 1: {
                    this.readHeaders(handler, access$300, b2, n);
                    return true;
                }
                case 2: {
                    this.readPriority(handler, access$300, b2, n);
                    return true;
                }
                case 3: {
                    this.readRstStream(handler, access$300, b2, n);
                    return true;
                }
                case 4: {
                    this.readSettings(handler, access$300, b2, n);
                    return true;
                }
                case 5: {
                    this.readPushPromise(handler, access$300, b2, n);
                    return true;
                }
                case 6: {
                    this.readPing(handler, access$300, b2, n);
                    return true;
                }
                case 7: {
                    this.readGoAway(handler, access$300, b2, n);
                    return true;
                }
                case 8: {
                    this.readWindowUpdate(handler, access$300, b2, n);
                    return true;
                }
            }
        }
        
        @Override
        public void readConnectionPreface() throws IOException {
            if (!this.client) {
                final ByteString byteString = this.source.readByteString(Http2.CONNECTION_PREFACE.size());
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format("<< CONNECTION %s", byteString.hex()));
                }
                if (!Http2.CONNECTION_PREFACE.equals(byteString)) {
                    throw ioException("Expected a connection header but was %s", new Object[] { byteString.utf8() });
                }
            }
        }
    }
    
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
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
                }
                this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
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
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(false, n, n2, b, b2));
            }
            if (n2 > this.maxFrameSize) {
                throw illegalArgument("FRAME_SIZE_ERROR length > %d: %d", new Object[] { this.maxFrameSize, n2 });
            }
            if ((Integer.MIN_VALUE & n) != 0x0) {
                throw illegalArgument("reserved bit set: %s", new Object[] { n });
            }
            writeMedium(this.sink, n2);
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
                throw illegalArgument("errorCode.httpCode == -1", new Object[0]);
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
                throw illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[] { n2 });
            }
            this.frameHeader(n, 4, (byte)8, (byte)0);
            this.sink.writeInt((int)n2);
            this.sink.flush();
        }
        // monitorexit(this)
    }
}
