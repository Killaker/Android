package okhttp3.internal.framed;

import okhttp3.*;
import java.util.concurrent.*;
import java.util.*;
import java.io.*;
import java.net.*;
import okio.*;
import okhttp3.internal.*;
import java.util.logging.*;

public final class FramedConnection implements Closeable
{
    private static final int OKHTTP_CLIENT_WINDOW_SIZE = 16777216;
    private static final ExecutorService executor;
    long bytesLeftInWriteWindow;
    final boolean client;
    private final Set<Integer> currentPushRequests;
    final FrameWriter frameWriter;
    private final String hostname;
    private long idleStartTimeNs;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextPingId;
    private int nextStreamId;
    Settings okHttpSettings;
    final Settings peerSettings;
    private Map<Integer, Ping> pings;
    final Protocol protocol;
    private final ExecutorService pushExecutor;
    private final PushObserver pushObserver;
    final Reader readerRunnable;
    private boolean receivedInitialPeerSettings;
    private boolean shutdown;
    final Socket socket;
    private final Map<Integer, FramedStream> streams;
    long unacknowledgedBytesRead;
    final Variant variant;
    
    static {
        executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp FramedConnection", true));
    }
    
    private FramedConnection(final Builder builder) throws IOException {
        int nextPingId = 2;
        this.streams = new HashMap<Integer, FramedStream>();
        this.idleStartTimeNs = System.nanoTime();
        this.unacknowledgedBytesRead = 0L;
        this.okHttpSettings = new Settings();
        this.peerSettings = new Settings();
        this.receivedInitialPeerSettings = false;
        this.currentPushRequests = new LinkedHashSet<Integer>();
        this.protocol = builder.protocol;
        this.pushObserver = builder.pushObserver;
        this.client = builder.client;
        this.listener = builder.listener;
        int nextStreamId;
        if (builder.client) {
            nextStreamId = 1;
        }
        else {
            nextStreamId = nextPingId;
        }
        this.nextStreamId = nextStreamId;
        if (builder.client && this.protocol == Protocol.HTTP_2) {
            this.nextStreamId += 2;
        }
        if (builder.client) {
            nextPingId = 1;
        }
        this.nextPingId = nextPingId;
        if (builder.client) {
            this.okHttpSettings.set(7, 0, 16777216);
        }
        this.hostname = builder.hostname;
        if (this.protocol == Protocol.HTTP_2) {
            this.variant = new Http2();
            this.pushExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Util.threadFactory(String.format("OkHttp %s Push Observer", this.hostname), true));
            this.peerSettings.set(7, 0, 65535);
            this.peerSettings.set(5, 0, 16384);
        }
        else {
            if (this.protocol != Protocol.SPDY_3) {
                throw new AssertionError(this.protocol);
            }
            this.variant = new Spdy3();
            this.pushExecutor = null;
        }
        this.bytesLeftInWriteWindow = this.peerSettings.getInitialWindowSize(65536);
        this.socket = builder.socket;
        this.frameWriter = this.variant.newWriter(builder.sink, this.client);
        this.readerRunnable = new Reader(this.variant.newReader(builder.source, this.client));
        new Thread(this.readerRunnable).start();
    }
    
    private void close(final ErrorCode p0, final ErrorCode p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       okhttp3/internal/framed/FramedConnection.$assertionsDisabled:Z
        //     3: ifne            21
        //     6: aload_0        
        //     7: invokestatic    java/lang/Thread.holdsLock:(Ljava/lang/Object;)Z
        //    10: ifeq            21
        //    13: new             Ljava/lang/AssertionError;
        //    16: dup            
        //    17: invokespecial   java/lang/AssertionError.<init>:()V
        //    20: athrow         
        //    21: aconst_null    
        //    22: astore_3       
        //    23: aload_0        
        //    24: aload_1        
        //    25: invokevirtual   okhttp3/internal/framed/FramedConnection.shutdown:(Lokhttp3/internal/framed/ErrorCode;)V
        //    28: aload_0        
        //    29: monitorenter   
        //    30: aload_0        
        //    31: getfield        okhttp3/internal/framed/FramedConnection.streams:Ljava/util/Map;
        //    34: invokeinterface java/util/Map.isEmpty:()Z
        //    39: istore          6
        //    41: aconst_null    
        //    42: astore          7
        //    44: iload           6
        //    46: ifne            94
        //    49: aload_0        
        //    50: getfield        okhttp3/internal/framed/FramedConnection.streams:Ljava/util/Map;
        //    53: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //    58: aload_0        
        //    59: getfield        okhttp3/internal/framed/FramedConnection.streams:Ljava/util/Map;
        //    62: invokeinterface java/util/Map.size:()I
        //    67: anewarray       Lokhttp3/internal/framed/FramedStream;
        //    70: invokeinterface java/util/Collection.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //    75: checkcast       [Lokhttp3/internal/framed/FramedStream;
        //    78: astore          7
        //    80: aload_0        
        //    81: getfield        okhttp3/internal/framed/FramedConnection.streams:Ljava/util/Map;
        //    84: invokeinterface java/util/Map.clear:()V
        //    89: aload_0        
        //    90: iconst_0       
        //    91: invokespecial   okhttp3/internal/framed/FramedConnection.setIdle:(Z)V
        //    94: aload_0        
        //    95: getfield        okhttp3/internal/framed/FramedConnection.pings:Ljava/util/Map;
        //    98: astore          8
        //   100: aconst_null    
        //   101: astore          9
        //   103: aload           8
        //   105: ifnull          144
        //   108: aload_0        
        //   109: getfield        okhttp3/internal/framed/FramedConnection.pings:Ljava/util/Map;
        //   112: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   117: aload_0        
        //   118: getfield        okhttp3/internal/framed/FramedConnection.pings:Ljava/util/Map;
        //   121: invokeinterface java/util/Map.size:()I
        //   126: anewarray       Lokhttp3/internal/framed/Ping;
        //   129: invokeinterface java/util/Collection.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   134: checkcast       [Lokhttp3/internal/framed/Ping;
        //   137: astore          9
        //   139: aload_0        
        //   140: aconst_null    
        //   141: putfield        okhttp3/internal/framed/FramedConnection.pings:Ljava/util/Map;
        //   144: aload_0        
        //   145: monitorexit    
        //   146: aload           7
        //   148: ifnull          216
        //   151: aload           7
        //   153: astore          15
        //   155: aload           15
        //   157: arraylength    
        //   158: istore          16
        //   160: iconst_0       
        //   161: istore          17
        //   163: iload           17
        //   165: iload           16
        //   167: if_icmpge       216
        //   170: aload           15
        //   172: iload           17
        //   174: aaload         
        //   175: astore          18
        //   177: aload           18
        //   179: aload_2        
        //   180: invokevirtual   okhttp3/internal/framed/FramedStream.close:(Lokhttp3/internal/framed/ErrorCode;)V
        //   183: iinc            17, 1
        //   186: goto            163
        //   189: astore          4
        //   191: aload           4
        //   193: astore_3       
        //   194: goto            28
        //   197: astore          5
        //   199: aload_0        
        //   200: monitorexit    
        //   201: aload           5
        //   203: athrow         
        //   204: astore          19
        //   206: aload_3        
        //   207: ifnull          183
        //   210: aload           19
        //   212: astore_3       
        //   213: goto            183
        //   216: aload           9
        //   218: ifnull          254
        //   221: aload           9
        //   223: astore          12
        //   225: aload           12
        //   227: arraylength    
        //   228: istore          13
        //   230: iconst_0       
        //   231: istore          14
        //   233: iload           14
        //   235: iload           13
        //   237: if_icmpge       254
        //   240: aload           12
        //   242: iload           14
        //   244: aaload         
        //   245: invokevirtual   okhttp3/internal/framed/Ping.cancel:()V
        //   248: iinc            14, 1
        //   251: goto            233
        //   254: aload_0        
        //   255: getfield        okhttp3/internal/framed/FramedConnection.frameWriter:Lokhttp3/internal/framed/FrameWriter;
        //   258: invokeinterface okhttp3/internal/framed/FrameWriter.close:()V
        //   263: aload_0        
        //   264: getfield        okhttp3/internal/framed/FramedConnection.socket:Ljava/net/Socket;
        //   267: invokevirtual   java/net/Socket.close:()V
        //   270: aload_3        
        //   271: ifnull          296
        //   274: aload_3        
        //   275: athrow         
        //   276: astore          10
        //   278: aload_3        
        //   279: ifnonnull       263
        //   282: aload           10
        //   284: astore_3       
        //   285: goto            263
        //   288: astore          11
        //   290: aload           11
        //   292: astore_3       
        //   293: goto            270
        //   296: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  23     28     189    197    Ljava/io/IOException;
        //  30     41     197    204    Any
        //  49     94     197    204    Any
        //  94     100    197    204    Any
        //  108    144    197    204    Any
        //  144    146    197    204    Any
        //  177    183    204    216    Ljava/io/IOException;
        //  199    201    197    204    Any
        //  254    263    276    288    Ljava/io/IOException;
        //  263    270    288    296    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private FramedStream newStream(final int n, final List<Header> list, final boolean b, final boolean b2) throws IOException {
        boolean b3 = true;
        // monitorexit(this)
        // monitorexit(frameWriter)
        while (true) {
            Label_0060: {
                if (b) {
                    break Label_0060;
                }
                final boolean b4 = b3;
                if (b2) {
                    b3 = false;
                }
                Label_0072: {
                    synchronized (this.frameWriter) {
                        synchronized (this) {
                            if (this.shutdown) {
                                throw new IOException("shutdown");
                            }
                            break Label_0072;
                        }
                    }
                    break Label_0060;
                }
                final int nextStreamId = this.nextStreamId;
                this.nextStreamId += 2;
                final FramedStream framedStream = new FramedStream(nextStreamId, this, b4, b3, list);
                if (framedStream.isOpen()) {
                    this.streams.put(nextStreamId, framedStream);
                    this.setIdle(false);
                }
                if (n == 0) {
                    this.frameWriter.synStream(b4, b3, nextStreamId, n, list);
                }
                else {
                    if (this.client) {
                        throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                    }
                    this.frameWriter.pushPromise(n, nextStreamId, list);
                }
                if (!b) {
                    this.frameWriter.flush();
                }
                return framedStream;
            }
            final boolean b4 = false;
            continue;
        }
    }
    
    private void pushDataLater(final int n, final BufferedSource bufferedSource, final int n2, final boolean b) throws IOException {
        final Buffer buffer = new Buffer();
        bufferedSource.require(n2);
        bufferedSource.read(buffer, n2);
        if (buffer.size() != n2) {
            throw new IOException(buffer.size() + " != " + n2);
        }
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Data[%s]", new Object[] { this.hostname, n }) {
            public void execute() {
                try {
                    final boolean onData = FramedConnection.this.pushObserver.onData(n, buffer, n2, b);
                    if (onData) {
                        FramedConnection.this.frameWriter.rstStream(n, ErrorCode.CANCEL);
                    }
                    if (onData || b) {
                        synchronized (FramedConnection.this) {
                            FramedConnection.this.currentPushRequests.remove(n);
                        }
                    }
                }
                catch (IOException ex) {}
            }
        });
    }
    
    private void pushHeadersLater(final int n, final List<Header> list, final boolean b) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Headers[%s]", new Object[] { this.hostname, n }) {
            public void execute() {
                final boolean onHeaders = FramedConnection.this.pushObserver.onHeaders(n, list, b);
                while (true) {
                    if (onHeaders) {
                        try {
                            FramedConnection.this.frameWriter.rstStream(n, ErrorCode.CANCEL);
                            if (onHeaders || b) {
                                synchronized (FramedConnection.this) {
                                    FramedConnection.this.currentPushRequests.remove(n);
                                }
                            }
                        }
                        catch (IOException ex) {}
                        return;
                    }
                    continue;
                }
            }
        });
    }
    
    private void pushRequestLater(final int n, final List<Header> list) {
        synchronized (this) {
            if (this.currentPushRequests.contains(n)) {
                this.writeSynResetLater(n, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(n);
            // monitorexit(this)
            this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Request[%s]", new Object[] { this.hostname, n }) {
                public void execute() {
                    if (FramedConnection.this.pushObserver.onRequest(n, list)) {
                        try {
                            FramedConnection.this.frameWriter.rstStream(n, ErrorCode.CANCEL);
                            synchronized (FramedConnection.this) {
                                FramedConnection.this.currentPushRequests.remove(n);
                            }
                        }
                        catch (IOException ex) {}
                    }
                }
            });
        }
    }
    
    private void pushResetLater(final int n, final ErrorCode errorCode) {
        this.pushExecutor.execute(new NamedRunnable("OkHttp %s Push Reset[%s]", new Object[] { this.hostname, n }) {
            public void execute() {
                FramedConnection.this.pushObserver.onReset(n, errorCode);
                synchronized (FramedConnection.this) {
                    FramedConnection.this.currentPushRequests.remove(n);
                }
            }
        });
    }
    
    private boolean pushedStream(final int n) {
        return this.protocol == Protocol.HTTP_2 && n != 0 && (n & 0x1) == 0x0;
    }
    
    private Ping removePing(final int n) {
        synchronized (this) {
            Ping ping;
            if (this.pings != null) {
                ping = this.pings.remove(n);
            }
            else {
                ping = null;
            }
            return ping;
        }
    }
    
    private void setIdle(final boolean b) {
        // monitorenter(this)
        Label_0018: {
            if (!b) {
                break Label_0018;
            }
            try {
                long nanoTime = System.nanoTime();
                while (true) {
                    this.idleStartTimeNs = nanoTime;
                    return;
                    nanoTime = Long.MAX_VALUE;
                    continue;
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    private void writePing(final boolean b, final int n, final int n2, final Ping ping) throws IOException {
        final FrameWriter frameWriter = this.frameWriter;
        // monitorenter(frameWriter)
        Label_0019: {
            if (ping == null) {
                break Label_0019;
            }
            try {
                ping.send();
                this.frameWriter.ping(b, n, n2);
            }
            finally {
            }
            // monitorexit(frameWriter)
        }
    }
    
    private void writePingLater(final boolean b, final int n, final int n2, final Ping ping) {
        FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ping %08x%08x", new Object[] { this.hostname, n, n2 }) {
            public void execute() {
                try {
                    FramedConnection.this.writePing(b, n, n2, ping);
                }
                catch (IOException ex) {}
            }
        });
    }
    
    void addBytesToWriteWindow(final long n) {
        this.bytesLeftInWriteWindow += n;
        if (n > 0L) {
            this.notifyAll();
        }
    }
    
    @Override
    public void close() throws IOException {
        this.close(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }
    
    public void flush() throws IOException {
        this.frameWriter.flush();
    }
    
    public long getIdleStartTimeNs() {
        synchronized (this) {
            return this.idleStartTimeNs;
        }
    }
    
    public Protocol getProtocol() {
        return this.protocol;
    }
    
    FramedStream getStream(final int n) {
        synchronized (this) {
            return this.streams.get(n);
        }
    }
    
    public boolean isIdle() {
        synchronized (this) {
            return this.idleStartTimeNs != Long.MAX_VALUE;
        }
    }
    
    public int maxConcurrentStreams() {
        synchronized (this) {
            return this.peerSettings.getMaxConcurrentStreams(Integer.MAX_VALUE);
        }
    }
    
    public FramedStream newStream(final List<Header> list, final boolean b, final boolean b2) throws IOException {
        return this.newStream(0, list, b, b2);
    }
    
    public int openStreamCount() {
        synchronized (this) {
            return this.streams.size();
        }
    }
    
    public Ping ping() throws IOException {
        final Ping ping = new Ping();
        synchronized (this) {
            if (this.shutdown) {
                throw new IOException("shutdown");
            }
        }
        final int nextPingId = this.nextPingId;
        this.nextPingId += 2;
        if (this.pings == null) {
            this.pings = new HashMap<Integer, Ping>();
        }
        this.pings.put(nextPingId, ping);
        // monitorexit(this)
        this.writePing(false, nextPingId, 1330343787, ping);
        return ping;
    }
    
    public FramedStream pushStream(final int n, final List<Header> list, final boolean b) throws IOException {
        if (this.client) {
            throw new IllegalStateException("Client cannot push requests.");
        }
        if (this.protocol != Protocol.HTTP_2) {
            throw new IllegalStateException("protocol != HTTP_2");
        }
        return this.newStream(n, list, b, false);
    }
    
    FramedStream removeStream(final int n) {
        synchronized (this) {
            final FramedStream framedStream = this.streams.remove(n);
            if (framedStream != null && this.streams.isEmpty()) {
                this.setIdle(true);
            }
            this.notifyAll();
            return framedStream;
        }
    }
    
    public void sendConnectionPreface() throws IOException {
        this.frameWriter.connectionPreface();
        this.frameWriter.settings(this.okHttpSettings);
        final int initialWindowSize = this.okHttpSettings.getInitialWindowSize(65536);
        if (initialWindowSize != 65536) {
            this.frameWriter.windowUpdate(0, initialWindowSize - 65536);
        }
    }
    
    public void setSettings(final Settings settings) throws IOException {
        synchronized (this.frameWriter) {
            synchronized (this) {
                if (this.shutdown) {
                    throw new IOException("shutdown");
                }
            }
        }
        this.okHttpSettings.merge(settings);
        this.frameWriter.settings(settings);
    }
    // monitorexit(this)
    // monitorexit(frameWriter)
    
    public void shutdown(final ErrorCode p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        okhttp3/internal/framed/FramedConnection.frameWriter:Lokhttp3/internal/framed/FrameWriter;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        okhttp3/internal/framed/FramedConnection.shutdown:Z
        //    13: ifeq            21
        //    16: aload_0        
        //    17: monitorexit    
        //    18: aload_2        
        //    19: monitorexit    
        //    20: return         
        //    21: aload_0        
        //    22: iconst_1       
        //    23: putfield        okhttp3/internal/framed/FramedConnection.shutdown:Z
        //    26: aload_0        
        //    27: getfield        okhttp3/internal/framed/FramedConnection.lastGoodStreamId:I
        //    30: istore          5
        //    32: aload_0        
        //    33: monitorexit    
        //    34: aload_0        
        //    35: getfield        okhttp3/internal/framed/FramedConnection.frameWriter:Lokhttp3/internal/framed/FrameWriter;
        //    38: iload           5
        //    40: aload_1        
        //    41: getstatic       okhttp3/internal/Util.EMPTY_BYTE_ARRAY:[B
        //    44: invokeinterface okhttp3/internal/framed/FrameWriter.goAway:(ILokhttp3/internal/framed/ErrorCode;[B)V
        //    49: aload_2        
        //    50: monitorexit    
        //    51: return         
        //    52: astore_3       
        //    53: aload_2        
        //    54: monitorexit    
        //    55: aload_3        
        //    56: athrow         
        //    57: astore          4
        //    59: aload_0        
        //    60: monitorexit    
        //    61: aload           4
        //    63: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      9      52     57     Any
        //  9      18     57     64     Any
        //  18     20     52     57     Any
        //  21     34     57     64     Any
        //  34     51     52     57     Any
        //  53     55     52     57     Any
        //  59     61     57     64     Any
        //  61     64     52     57     Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void writeData(final int n, final boolean b, final Buffer buffer, long n2) throws IOException {
        Label_0020: {
            if (n2 == 0L) {
                this.frameWriter.data(b, n, buffer, 0);
            }
            else {
                Label_0100: {
                    break Label_0100;
                Label_0087_Outer:
                    while (true) {
                        while (true) {
                            Label_0169: {
                                while (true) {
                                    try {
                                        while (true) {
                                            final int min = Math.min((int)Math.min(n2, this.bytesLeftInWriteWindow), this.frameWriter.maxDataLength());
                                            this.bytesLeftInWriteWindow -= min;
                                            // monitorexit(this)
                                            n2 -= min;
                                            final FrameWriter frameWriter = this.frameWriter;
                                            if (!b || n2 != 0L) {
                                                break Label_0169;
                                            }
                                            final boolean b2 = true;
                                            frameWriter.data(b2, n, buffer, min);
                                            if (n2 <= 0L) {
                                                break Label_0020;
                                            }
                                            // monitorenter(this)
                                            try {
                                                if (this.bytesLeftInWriteWindow > 0L) {
                                                    continue Label_0087_Outer;
                                                }
                                                if (!this.streams.containsKey(n)) {
                                                    throw new IOException("stream closed");
                                                }
                                                break;
                                            }
                                            catch (InterruptedException ex) {
                                                throw new InterruptedIOException();
                                            }
                                        }
                                    }
                                    finally {
                                    }
                                    // monitorexit(this)
                                    this.wait();
                                    continue;
                                }
                            }
                            final boolean b2 = false;
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    void writeSynReply(final int n, final boolean b, final List<Header> list) throws IOException {
        this.frameWriter.synReply(b, n, list);
    }
    
    void writeSynReset(final int n, final ErrorCode errorCode) throws IOException {
        this.frameWriter.rstStream(n, errorCode);
    }
    
    void writeSynResetLater(final int n, final ErrorCode errorCode) {
        FramedConnection.executor.submit(new NamedRunnable("OkHttp %s stream %d", new Object[] { this.hostname, n }) {
            public void execute() {
                try {
                    FramedConnection.this.writeSynReset(n, errorCode);
                }
                catch (IOException ex) {}
            }
        });
    }
    
    void writeWindowUpdateLater(final int n, final long n2) {
        FramedConnection.executor.execute(new NamedRunnable("OkHttp Window Update %s stream %d", new Object[] { this.hostname, n }) {
            public void execute() {
                try {
                    FramedConnection.this.frameWriter.windowUpdate(n, n2);
                }
                catch (IOException ex) {}
            }
        });
    }
    
    public static class Builder
    {
        private boolean client;
        private String hostname;
        private Listener listener;
        private Protocol protocol;
        private PushObserver pushObserver;
        private BufferedSink sink;
        private Socket socket;
        private BufferedSource source;
        
        public Builder(final boolean client) throws IOException {
            this.listener = Listener.REFUSE_INCOMING_STREAMS;
            this.protocol = Protocol.SPDY_3;
            this.pushObserver = PushObserver.CANCEL;
            this.client = client;
        }
        
        public FramedConnection build() throws IOException {
            return new FramedConnection(this, null);
        }
        
        public Builder listener(final Listener listener) {
            this.listener = listener;
            return this;
        }
        
        public Builder protocol(final Protocol protocol) {
            this.protocol = protocol;
            return this;
        }
        
        public Builder pushObserver(final PushObserver pushObserver) {
            this.pushObserver = pushObserver;
            return this;
        }
        
        public Builder socket(final Socket socket) throws IOException {
            return this.socket(socket, ((InetSocketAddress)socket.getRemoteSocketAddress()).getHostName(), Okio.buffer(Okio.source(socket)), Okio.buffer(Okio.sink(socket)));
        }
        
        public Builder socket(final Socket socket, final String hostname, final BufferedSource source, final BufferedSink sink) {
            this.socket = socket;
            this.hostname = hostname;
            this.source = source;
            this.sink = sink;
            return this;
        }
    }
    
    public abstract static class Listener
    {
        public static final Listener REFUSE_INCOMING_STREAMS;
        
        static {
            REFUSE_INCOMING_STREAMS = new Listener() {
                @Override
                public void onStream(final FramedStream framedStream) throws IOException {
                    framedStream.close(ErrorCode.REFUSED_STREAM);
                }
            };
        }
        
        public void onSettings(final FramedConnection framedConnection) {
        }
        
        public abstract void onStream(final FramedStream p0) throws IOException;
    }
    
    class Reader extends NamedRunnable implements Handler
    {
        final FrameReader frameReader;
        
        private Reader(final FrameReader frameReader) {
            super("OkHttp %s", new Object[] { FramedConnection.this.hostname });
            this.frameReader = frameReader;
        }
        
        private void ackSettingsLater(final Settings settings) {
            FramedConnection.executor.execute(new NamedRunnable("OkHttp %s ACK Settings", new Object[] { FramedConnection.this.hostname }) {
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
            if (FramedConnection.this.pushedStream(n)) {
                FramedConnection.this.pushDataLater(n, bufferedSource, n2, b);
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
                final FramedStream[] array = (FramedStream[])FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                FramedConnection.this.shutdown = true;
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
            if (FramedConnection.this.pushedStream(n)) {
                FramedConnection.this.pushHeadersLater(n, list, b2);
            }
            else {
                synchronized (FramedConnection.this) {
                    if (FramedConnection.this.shutdown) {
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
                    if (n <= FramedConnection.this.lastGoodStreamId) {
                        // monitorexit(framedConnection)
                        return;
                    }
                    if (n % 2 == FramedConnection.this.nextStreamId % 2) {
                        // monitorexit(framedConnection)
                        return;
                    }
                    final FramedStream framedStream = new FramedStream(n, FramedConnection.this, b, b2, list);
                    FramedConnection.this.lastGoodStreamId = n;
                    FramedConnection.this.streams.put(n, framedStream);
                    FramedConnection.executor.execute(new NamedRunnable("OkHttp %s stream %d", new Object[] { FramedConnection.this.hostname, n }) {
                        public void execute() {
                            try {
                                FramedConnection.this.listener.onStream(framedStream);
                            }
                            catch (IOException ex) {
                                Internal.logger.log(Level.INFO, "FramedConnection.Listener failure for " + FramedConnection.this.hostname, ex);
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
                final Ping access$2400 = FramedConnection.this.removePing(n);
                if (access$2400 != null) {
                    access$2400.receive();
                }
                return;
            }
            FramedConnection.this.writePingLater(true, n, n2, null);
        }
        
        @Override
        public void priority(final int n, final int n2, final int n3, final boolean b) {
        }
        
        @Override
        public void pushPromise(final int n, final int n2, final List<Header> list) {
            FramedConnection.this.pushRequestLater(n2, list);
        }
        
        @Override
        public void rstStream(final int n, final ErrorCode errorCode) {
            if (FramedConnection.this.pushedStream(n)) {
                FramedConnection.this.pushResetLater(n, errorCode);
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
                            if (!FramedConnection.this.receivedInitialPeerSettings) {
                                FramedConnection.this.addBytesToWriteWindow(n);
                                FramedConnection.this.receivedInitialPeerSettings = true;
                            }
                            final boolean empty = FramedConnection.this.streams.isEmpty();
                            array = null;
                            if (!empty) {
                                array = (FramedStream[])FramedConnection.this.streams.values().toArray(new FramedStream[FramedConnection.this.streams.size()]);
                            }
                        }
                    }
                    FramedConnection.executor.execute(new NamedRunnable("OkHttp %s settings", new Object[] { FramedConnection.this.hostname }) {
                        public void execute() {
                            FramedConnection.this.listener.onSettings(FramedConnection.this);
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
}
