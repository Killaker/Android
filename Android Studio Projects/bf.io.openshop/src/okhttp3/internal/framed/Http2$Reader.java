package okhttp3.internal.framed;

import java.io.*;
import okio.*;
import java.util.*;
import java.util.logging.*;

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
            throw Http2.access$200("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }
        final byte b4 = (byte)(b & 0x8);
        short n3 = 0;
        if (b4 != 0) {
            n3 = (short)(0xFF & this.source.readByte());
        }
        handler.data(b3, n2, this.source, Http2.access$400(n, b, n3));
        this.source.skip(n3);
    }
    
    private void readGoAway(final Handler handler, final int n, final byte b, final int n2) throws IOException {
        if (n < 8) {
            throw Http2.access$200("TYPE_GOAWAY length < 8: %s", new Object[] { n });
        }
        if (n2 != 0) {
            throw Http2.access$200("TYPE_GOAWAY streamId != 0", new Object[0]);
        }
        final int int1 = this.source.readInt();
        final int int2 = this.source.readInt();
        final int n3 = n - 8;
        final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int2);
        if (fromHttp2 == null) {
            throw Http2.access$200("TYPE_GOAWAY unexpected error code: %d", new Object[] { int2 });
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
            throw Http2.access$200("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
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
        handler.headers(false, b2, n2, -1, this.readHeaderBlock(Http2.access$400(n, b, n3), n3, b, n2), HeadersMode.HTTP_20_HEADERS);
    }
    
    private void readPing(final Handler handler, final int n, final byte b, final int n2) throws IOException {
        int n3 = 1;
        if (n != 8) {
            final Object[] array = new Object[n3];
            array[0] = n;
            throw Http2.access$200("TYPE_PING length != 8: %s", array);
        }
        if (n2 != 0) {
            throw Http2.access$200("TYPE_PING streamId != 0", new Object[0]);
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
            throw Http2.access$200("TYPE_PRIORITY length: %d != 5", new Object[] { n });
        }
        if (n2 == 0) {
            throw Http2.access$200("TYPE_PRIORITY streamId == 0", new Object[0]);
        }
        this.readPriority(handler, n2);
    }
    
    private void readPushPromise(final Handler handler, final int n, final byte b, final int n2) throws IOException {
        if (n2 == 0) {
            throw Http2.access$200("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }
        final byte b2 = (byte)(b & 0x8);
        short n3 = 0;
        if (b2 != 0) {
            n3 = (short)(0xFF & this.source.readByte());
        }
        handler.pushPromise(n2, Integer.MAX_VALUE & this.source.readInt(), this.readHeaderBlock(Http2.access$400(n - 4, b, n3), n3, b, n2));
    }
    
    private void readRstStream(final Handler handler, final int n, final byte b, final int n2) throws IOException {
        if (n != 4) {
            throw Http2.access$200("TYPE_RST_STREAM length: %d != 4", new Object[] { n });
        }
        if (n2 == 0) {
            throw Http2.access$200("TYPE_RST_STREAM streamId == 0", new Object[0]);
        }
        final int int1 = this.source.readInt();
        final ErrorCode fromHttp2 = ErrorCode.fromHttp2(int1);
        if (fromHttp2 == null) {
            throw Http2.access$200("TYPE_RST_STREAM unexpected error code: %d", new Object[] { int1 });
        }
        handler.rstStream(n2, fromHttp2);
    }
    
    private void readSettings(final Handler handler, final int n, final byte b, final int n2) throws IOException {
        if (n2 != 0) {
            throw Http2.access$200("TYPE_SETTINGS streamId != 0", new Object[0]);
        }
        if ((b & 0x1) != 0x0) {
            if (n != 0) {
                throw Http2.access$200("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
            }
            handler.ackSettings();
        }
        else {
            if (n % 6 != 0) {
                throw Http2.access$200("TYPE_SETTINGS length %% 6 != 0: %s", new Object[] { n });
            }
            final Settings settings = new Settings();
            for (int i = 0; i < n; i += 6) {
                int short1 = this.source.readShort();
                final int int1 = this.source.readInt();
                switch (short1) {
                    case 2: {
                        if (int1 != 0 && int1 != 1) {
                            throw Http2.access$200("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
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
                            throw Http2.access$200("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        }
                        break;
                    }
                    case 5: {
                        if (int1 < 16384 || int1 > 16777215) {
                            throw Http2.access$200("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", new Object[] { int1 });
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
            throw Http2.access$200("TYPE_WINDOW_UPDATE length !=4: %s", new Object[] { n });
        }
        final long n3 = 0x7FFFFFFFL & this.source.readInt();
        if (n3 == 0L) {
            throw Http2.access$200("windowSizeIncrement was 0", new Object[] { n3 });
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
            access$300 = Http2.access$300(this.source);
            if (access$300 < 0 || access$300 > 16384) {
                throw Http2.access$200("FRAME_SIZE_ERROR: %s", new Object[] { access$300 });
            }
        }
        catch (IOException ex) {
            return false;
        }
        final byte b = (byte)(0xFF & this.source.readByte());
        final byte b2 = (byte)(0xFF & this.source.readByte());
        final int n = Integer.MAX_VALUE & this.source.readInt();
        if (Http2.access$100().isLoggable(Level.FINE)) {
            Http2.access$100().fine(FrameLogger.formatHeader(true, n, access$300, b, b2));
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
            final ByteString byteString = this.source.readByteString(Http2.access$000().size());
            if (Http2.access$100().isLoggable(Level.FINE)) {
                Http2.access$100().fine(String.format("<< CONNECTION %s", byteString.hex()));
            }
            if (!Http2.access$000().equals(byteString)) {
                throw Http2.access$200("Expected a connection header but was %s", new Object[] { byteString.utf8() });
            }
        }
    }
}
