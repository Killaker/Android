package okhttp3.internal.framed;

import java.io.*;
import okio.*;
import java.util.*;
import okhttp3.internal.*;
import java.util.logging.*;
import okhttp3.*;

class Reader extends NamedRunnable implements Handler
{
    final FrameReader frameReader;
    
    private Reader(final FrameReader frameReader) {
        super("OkHttp %s", new Object[] { FramedConnection.access$1100(FramedConnection.this) });
        this.frameReader = frameReader;
    }
    
    private void ackSettingsLater(final Settings settings) {
        FramedConnection.access$2100().execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[] { FramedConnection.access$1100(FramedConnection.this) }) {
            public void execute() {
                try {
                    FramedConnection.this.frameWriter.ackSettings(settings);
                }
                catch (IOException ex) {}
            }
        });
    }
    
    @Override
    public void ackSettings() {
    }
    
    @Override
    public void alternateService(final int n, final String s, final ByteString byteString, final String s2, final int n2, final long n3) {
    }
    
    @Override
    public void data(final boolean b, final int n, final BufferedSource bufferedSource, final int n2) throws IOException {
        if (FramedConnection.access$1300(FramedConnection.this, n)) {
            FramedConnection.access$1400(FramedConnection.this, n, bufferedSource, n2, b);
        }
        else {
            final FramedStream stream = FramedConnection.this.getStream(n);
            if (stream == null) {
                FramedConnection.this.writeSynResetLater(n, ErrorCode.INVALID_STREAM);
                bufferedSource.skip(n2);
                return;
            }
            stream.receiveData(bufferedSource, n2);
            if (b) {
                stream.receiveFin();
            }
        }
    }
    
    @Override
    protected void execute() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       okhttp3/internal/framed/ErrorCode.INTERNAL_ERROR:Lokhttp3/internal/framed/ErrorCode;
        //     3: astore_1       
        //     4: getstatic       okhttp3/internal/framed/ErrorCode.INTERNAL_ERROR:Lokhttp3/internal/framed/ErrorCode;
        //     7: astore_2       
        //     8: aload_0        
        //     9: getfield        okhttp3/internal/framed/FramedConnection$Reader.this$0:Lokhttp3/internal/framed/FramedConnection;
        //    12: getfield        okhttp3/internal/framed/FramedConnection.client:Z
        //    15: ifne            27
        //    18: aload_0        
        //    19: getfield        okhttp3/internal/framed/FramedConnection$Reader.frameReader:Lokhttp3/internal/framed/FrameReader;
        //    22: invokeinterface okhttp3/internal/framed/FrameReader.readConnectionPreface:()V
        //    27: aload_0        
        //    28: getfield        okhttp3/internal/framed/FramedConnection$Reader.frameReader:Lokhttp3/internal/framed/FrameReader;
        //    31: aload_0        
        //    32: invokeinterface okhttp3/internal/framed/FrameReader.nextFrame:(Lokhttp3/internal/framed/FrameReader$Handler;)Z
        //    37: ifne            27
        //    40: getstatic       okhttp3/internal/framed/ErrorCode.NO_ERROR:Lokhttp3/internal/framed/ErrorCode;
        //    43: astore_1       
        //    44: getstatic       okhttp3/internal/framed/ErrorCode.CANCEL:Lokhttp3/internal/framed/ErrorCode;
        //    47: astore          8
        //    49: aload_0        
        //    50: getfield        okhttp3/internal/framed/FramedConnection$Reader.this$0:Lokhttp3/internal/framed/FramedConnection;
        //    53: aload_1        
        //    54: aload           8
        //    56: invokestatic    okhttp3/internal/framed/FramedConnection.access$1200:(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
        //    59: aload_0        
        //    60: getfield        okhttp3/internal/framed/FramedConnection$Reader.frameReader:Lokhttp3/internal/framed/FrameReader;
        //    63: invokestatic    okhttp3/internal/Util.closeQuietly:(Ljava/io/Closeable;)V
        //    66: return         
        //    67: astore          5
        //    69: getstatic       okhttp3/internal/framed/ErrorCode.PROTOCOL_ERROR:Lokhttp3/internal/framed/ErrorCode;
        //    72: astore_1       
        //    73: getstatic       okhttp3/internal/framed/ErrorCode.PROTOCOL_ERROR:Lokhttp3/internal/framed/ErrorCode;
        //    76: astore          6
        //    78: aload_0        
        //    79: getfield        okhttp3/internal/framed/FramedConnection$Reader.this$0:Lokhttp3/internal/framed/FramedConnection;
        //    82: aload_1        
        //    83: aload           6
        //    85: invokestatic    okhttp3/internal/framed/FramedConnection.access$1200:(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
        //    88: aload_0        
        //    89: getfield        okhttp3/internal/framed/FramedConnection$Reader.frameReader:Lokhttp3/internal/framed/FrameReader;
        //    92: invokestatic    okhttp3/internal/Util.closeQuietly:(Ljava/io/Closeable;)V
        //    95: return         
        //    96: astore_3       
        //    97: aload_0        
        //    98: getfield        okhttp3/internal/framed/FramedConnection$Reader.this$0:Lokhttp3/internal/framed/FramedConnection;
        //   101: aload_1        
        //   102: aload_2        
        //   103: invokestatic    okhttp3/internal/framed/FramedConnection.access$1200:(Lokhttp3/internal/framed/FramedConnection;Lokhttp3/internal/framed/ErrorCode;Lokhttp3/internal/framed/ErrorCode;)V
        //   106: aload_0        
        //   107: getfield        okhttp3/internal/framed/FramedConnection$Reader.frameReader:Lokhttp3/internal/framed/FrameReader;
        //   110: invokestatic    okhttp3/internal/Util.closeQuietly:(Ljava/io/Closeable;)V
        //   113: aload_3        
        //   114: athrow         
        //   115: astore          4
        //   117: goto            106
        //   120: astore          7
        //   122: goto            88
        //   125: astore          9
        //   127: goto            59
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  8      27     67     96     Ljava/io/IOException;
        //  8      27     96     120    Any
        //  27     49     67     96     Ljava/io/IOException;
        //  27     49     96     120    Any
        //  49     59     125    130    Ljava/io/IOException;
        //  69     78     96     120    Any
        //  78     88     120    125    Ljava/io/IOException;
        //  97     106    115    120    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void goAway(final int n, final ErrorCode errorCode, final ByteString byteString) {
        if (byteString.size() > 0) {}
        synchronized (FramedConnection.this) {
            final FramedStream[] array = (FramedStream[])FramedConnection.access$1900(FramedConnection.this).values().toArray(new FramedStream[FramedConnection.access$1900(FramedConnection.this).size()]);
            FramedConnection.access$1602(FramedConnection.this, true);
            // monitorexit(this.this$0)
            for (final FramedStream framedStream : array) {
                if (framedStream.getId() > n && framedStream.isLocallyInitiated()) {
                    framedStream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    FramedConnection.this.removeStream(framedStream.getId());
                }
            }
        }
    }
    
    @Override
    public void headers(final boolean b, final boolean b2, final int n, final int n2, final List<Header> list, final HeadersMode headersMode) {
        if (FramedConnection.access$1300(FramedConnection.this, n)) {
            FramedConnection.access$1500(FramedConnection.this, n, list, b2);
        }
        else {
            synchronized (FramedConnection.this) {
                if (FramedConnection.access$1600(FramedConnection.this)) {
                    return;
                }
            }
            final FramedStream stream = FramedConnection.this.getStream(n);
            if (stream == null) {
                if (headersMode.failIfStreamAbsent()) {
                    FramedConnection.this.writeSynResetLater(n, ErrorCode.INVALID_STREAM);
                    // monitorexit(framedConnection)
                    return;
                }
                if (n <= FramedConnection.access$1700(FramedConnection.this)) {
                    // monitorexit(framedConnection)
                    return;
                }
                if (n % 2 == FramedConnection.access$1800(FramedConnection.this) % 2) {
                    // monitorexit(framedConnection)
                    return;
                }
                final FramedStream framedStream = new FramedStream(n, FramedConnection.this, b, b2, list);
                FramedConnection.access$1702(FramedConnection.this, n);
                FramedConnection.access$1900(FramedConnection.this).put(n, framedStream);
                FramedConnection.access$2100().execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { FramedConnection.access$1100(FramedConnection.this), n }) {
                    public void execute() {
                        try {
                            FramedConnection.access$2000(FramedConnection.this).onStream(framedStream);
                        }
                        catch (IOException ex) {
                            Internal.logger.log(Level.INFO, "FramedConnection.Listener failure for " + FramedConnection.access$1100(FramedConnection.this), ex);
                            try {
                                framedStream.close(ErrorCode.PROTOCOL_ERROR);
                            }
                            catch (IOException ex2) {}
                        }
                    }
                });
            }
            // monitorexit(framedConnection)
            else {
                // monitorexit(framedConnection)
                if (headersMode.failIfStreamPresent()) {
                    stream.closeLater(ErrorCode.PROTOCOL_ERROR);
                    FramedConnection.this.removeStream(n);
                    return;
                }
                stream.receiveHeaders(list, headersMode);
                if (b2) {
                    stream.receiveFin();
                }
            }
        }
    }
    
    @Override
    public void ping(final boolean b, final int n, final int n2) {
        if (b) {
            final Ping access$2400 = FramedConnection.access$2400(FramedConnection.this, n);
            if (access$2400 != null) {
                access$2400.receive();
            }
            return;
        }
        FramedConnection.access$2500(FramedConnection.this, true, n, n2, null);
    }
    
    @Override
    public void priority(final int n, final int n2, final int n3, final boolean b) {
    }
    
    @Override
    public void pushPromise(final int n, final int n2, final List<Header> list) {
        FramedConnection.access$2600(FramedConnection.this, n2, list);
    }
    
    @Override
    public void rstStream(final int n, final ErrorCode errorCode) {
        if (FramedConnection.access$1300(FramedConnection.this, n)) {
            FramedConnection.access$2200(FramedConnection.this, n, errorCode);
        }
        else {
            final FramedStream removeStream = FramedConnection.this.removeStream(n);
            if (removeStream != null) {
                removeStream.receiveRstStream(errorCode);
            }
        }
    }
    
    @Override
    public void settings(final boolean b, final Settings settings) {
        long n = 0L;
        final FramedConnection this$0 = FramedConnection.this;
        while (true) {
            int i;
            FramedStream framedStream;
            synchronized (this$0) {
                final int initialWindowSize = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                if (b) {
                    FramedConnection.this.peerSettings.clear();
                }
                FramedConnection.this.peerSettings.merge(settings);
                if (FramedConnection.this.getProtocol() == Protocol.HTTP_2) {
                    this.ackSettingsLater(settings);
                }
                final int initialWindowSize2 = FramedConnection.this.peerSettings.getInitialWindowSize(65536);
                FramedStream[] array = null;
                if (initialWindowSize2 != -1) {
                    array = null;
                    if (initialWindowSize2 != initialWindowSize) {
                        n = initialWindowSize2 - initialWindowSize;
                        if (!FramedConnection.access$2300(FramedConnection.this)) {
                            FramedConnection.this.addBytesToWriteWindow(n);
                            FramedConnection.access$2302(FramedConnection.this, true);
                        }
                        final boolean empty = FramedConnection.access$1900(FramedConnection.this).isEmpty();
                        array = null;
                        if (!empty) {
                            array = (FramedStream[])FramedConnection.access$1900(FramedConnection.this).values().toArray(new FramedStream[FramedConnection.access$1900(FramedConnection.this).size()]);
                        }
                    }
                }
                FramedConnection.access$2100().execute(new NamedRunnable("OkHttp %s settings", new Object[] { FramedConnection.access$1100(FramedConnection.this) }) {
                    public void execute() {
                        FramedConnection.access$2000(FramedConnection.this).onSettings(FramedConnection.this);
                    }
                });
                // monitorexit(this$0)
                if (array != null && n != 0L) {
                    final FramedStream[] array2 = array;
                    final int length = array2.length;
                    i = 0;
                    while (i < length) {
                        framedStream = array2[i];
                        // monitorenter(framedStream)
                        final FramedStream framedStream2 = framedStream;
                        final long n2 = n;
                        framedStream2.addBytesToWriteWindow(n2);
                        final FramedStream framedStream3 = framedStream;
                        // monitorexit(framedStream3)
                        ++i;
                    }
                }
                return;
            }
            try {
                final FramedStream framedStream2 = framedStream;
                final long n2 = n;
                framedStream2.addBytesToWriteWindow(n2);
                final FramedStream framedStream3 = framedStream;
                // monitorexit(framedStream3)
                ++i;
                continue;
            }
            finally {
            }
            // monitorexit(framedStream)
            break;
        }
    }
    
    @Override
    public void windowUpdate(final int n, final long n2) {
        if (n == 0) {
            synchronized (FramedConnection.this) {
                final FramedConnection this$0 = FramedConnection.this;
                this$0.bytesLeftInWriteWindow += n2;
                FramedConnection.this.notifyAll();
                return;
            }
        }
        final FramedStream stream = FramedConnection.this.getStream(n);
        if (stream != null) {
            synchronized (stream) {
                stream.addBytesToWriteWindow(n2);
            }
        }
    }
}
