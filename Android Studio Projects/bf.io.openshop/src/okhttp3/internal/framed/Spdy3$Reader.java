package okhttp3.internal.framed;

import java.io.*;
import okio.*;
import java.util.*;
import java.net.*;

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
