package okhttp3.internal.framed;

import java.util.*;
import java.io.*;
import okio.*;
import java.net.*;

public final class FramedStream
{
    long bytesLeftInWriteWindow;
    private final FramedConnection connection;
    private ErrorCode errorCode;
    private final int id;
    private final StreamTimeout readTimeout;
    private final List<Header> requestHeaders;
    private List<Header> responseHeaders;
    final FramedDataSink sink;
    private final FramedDataSource source;
    long unacknowledgedBytesRead;
    private final StreamTimeout writeTimeout;
    
    FramedStream(final int id, final FramedConnection connection, final boolean b, final boolean b2, final List<Header> requestHeaders) {
        this.unacknowledgedBytesRead = 0L;
        this.readTimeout = new StreamTimeout();
        this.writeTimeout = new StreamTimeout();
        this.errorCode = null;
        if (connection == null) {
            throw new NullPointerException("connection == null");
        }
        if (requestHeaders == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.id = id;
        this.connection = connection;
        this.bytesLeftInWriteWindow = connection.peerSettings.getInitialWindowSize(65536);
        this.source = new FramedDataSource((long)connection.okHttpSettings.getInitialWindowSize(65536));
        this.sink = new FramedDataSink();
        this.source.finished = b2;
        this.sink.finished = b;
        this.requestHeaders = requestHeaders;
    }
    
    private void cancelStreamIfNecessary() throws IOException {
        assert !Thread.holdsLock(this);
        while (true) {
            while (true) {
                Label_0112: {
                    final boolean open;
                    synchronized (this) {
                        if (!this.source.finished && this.source.closed && (this.sink.finished || this.sink.closed)) {
                            break Label_0112;
                        }
                        final int n = 0;
                        open = this.isOpen();
                        // monitorexit(this)
                        if (n != 0) {
                            this.close(ErrorCode.CANCEL);
                            return;
                        }
                    }
                    if (!open) {
                        break;
                    }
                    return;
                }
                final int n = 1;
                continue;
            }
        }
        this.connection.removeStream(this.id);
    }
    
    private void checkOutNotClosed() throws IOException {
        if (this.sink.closed) {
            throw new IOException("stream closed");
        }
        if (this.sink.finished) {
            throw new IOException("stream finished");
        }
        if (this.errorCode != null) {
            throw new IOException("stream was reset: " + this.errorCode);
        }
    }
    
    private boolean closeInternal(final ErrorCode errorCode) {
        assert !Thread.holdsLock(this);
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
        }
        this.errorCode = errorCode;
        this.notifyAll();
        // monitorexit(this)
        this.connection.removeStream(this.id);
        return true;
    }
    
    private void waitForIo() throws InterruptedIOException {
        try {
            this.wait();
        }
        catch (InterruptedException ex) {
            throw new InterruptedIOException();
        }
    }
    
    void addBytesToWriteWindow(final long n) {
        this.bytesLeftInWriteWindow += n;
        if (n > 0L) {
            this.notifyAll();
        }
    }
    
    public void close(final ErrorCode errorCode) throws IOException {
        if (!this.closeInternal(errorCode)) {
            return;
        }
        this.connection.writeSynReset(this.id, errorCode);
    }
    
    public void closeLater(final ErrorCode errorCode) {
        if (!this.closeInternal(errorCode)) {
            return;
        }
        this.connection.writeSynResetLater(this.id, errorCode);
    }
    
    public FramedConnection getConnection() {
        return this.connection;
    }
    
    public ErrorCode getErrorCode() {
        synchronized (this) {
            return this.errorCode;
        }
    }
    
    public int getId() {
        return this.id;
    }
    
    public List<Header> getRequestHeaders() {
        return this.requestHeaders;
    }
    
    public List<Header> getResponseHeaders() throws IOException {
        synchronized (this) {
            this.readTimeout.enter();
            try {
                while (this.responseHeaders == null && this.errorCode == null) {
                    this.waitForIo();
                }
            }
            finally {
                this.readTimeout.exitAndThrowIfTimedOut();
            }
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (this.responseHeaders != null) {
            // monitorexit(this)
            return this.responseHeaders;
        }
        throw new IOException("stream was reset: " + this.errorCode);
    }
    
    public Sink getSink() {
        synchronized (this) {
            if (this.responseHeaders == null && !this.isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        // monitorexit(this)
        return this.sink;
    }
    
    public Source getSource() {
        return this.source;
    }
    
    public boolean isLocallyInitiated() {
        return this.connection.client == ((0x1 & this.id) == 0x1);
    }
    
    public boolean isOpen() {
        synchronized (this) {
            final ErrorCode errorCode = this.errorCode;
            boolean b = false;
            if (errorCode == null) {
                if ((this.source.finished || this.source.closed) && (this.sink.finished || this.sink.closed)) {
                    final List<Header> responseHeaders = this.responseHeaders;
                    b = false;
                    if (responseHeaders != null) {
                        return b;
                    }
                }
                b = true;
            }
            return b;
        }
    }
    
    public Timeout readTimeout() {
        return this.readTimeout;
    }
    
    void receiveData(final BufferedSource bufferedSource, final int n) throws IOException {
        assert !Thread.holdsLock(this);
        this.source.receive(bufferedSource, n);
    }
    
    void receiveFin() {
        assert !Thread.holdsLock(this);
        synchronized (this) {
            this.source.finished = true;
            final boolean open = this.isOpen();
            this.notifyAll();
            // monitorexit(this)
            if (!open) {
                this.connection.removeStream(this.id);
            }
        }
    }
    
    void receiveHeaders(final List<Header> responseHeaders, final HeadersMode headersMode) {
        assert !Thread.holdsLock(this);
        while (true) {
            boolean open = true;
        Label_0142:
            while (true) {
                synchronized (this) {
                    if (this.responseHeaders == null) {
                        ErrorCode errorCode;
                        if (headersMode.failIfHeadersAbsent()) {
                            errorCode = ErrorCode.PROTOCOL_ERROR;
                        }
                        else {
                            this.responseHeaders = responseHeaders;
                            open = this.isOpen();
                            this.notifyAll();
                            errorCode = null;
                        }
                        // monitorexit(this)
                        if (errorCode != null) {
                            this.closeLater(errorCode);
                            return;
                        }
                        break Label_0142;
                    }
                }
                if (headersMode.failIfHeadersPresent()) {
                    final ErrorCode errorCode = ErrorCode.STREAM_IN_USE;
                    continue;
                }
                final ArrayList<Header> responseHeaders2 = new ArrayList<Header>();
                responseHeaders2.addAll((Collection<?>)this.responseHeaders);
                responseHeaders2.addAll((Collection<?>)responseHeaders);
                this.responseHeaders = responseHeaders2;
                ErrorCode errorCode = null;
                continue;
            }
            if (!open) {
                break;
            }
            return;
        }
        this.connection.removeStream(this.id);
    }
    
    void receiveRstStream(final ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode == null) {
                this.errorCode = errorCode;
                this.notifyAll();
            }
        }
    }
    
    public void reply(final List<Header> responseHeaders, final boolean b) throws IOException {
        assert !Thread.holdsLock(this);
        // monitorenter(this)
        if (responseHeaders == null) {
            try {
                throw new NullPointerException("responseHeaders == null");
            }
            finally {
            }
            // monitorexit(this)
        }
        if (this.responseHeaders != null) {
            throw new IllegalStateException("reply already sent");
        }
        this.responseHeaders = responseHeaders;
        boolean b2 = false;
        if (!b) {
            this.sink.finished = true;
            b2 = true;
        }
        // monitorexit(this)
        this.connection.writeSynReply(this.id, b2, responseHeaders);
        if (b2) {
            this.connection.flush();
        }
    }
    
    public Timeout writeTimeout() {
        return this.writeTimeout;
    }
    
    final class FramedDataSink implements Sink
    {
        private static final long EMIT_BUFFER_SIZE = 16384L;
        private boolean closed;
        private boolean finished;
        private final Buffer sendBuffer;
        
        FramedDataSink() {
            this.sendBuffer = new Buffer();
        }
        
        private void emitDataFrame(final boolean b) throws IOException {
            synchronized (FramedStream.this) {
                FramedStream.this.writeTimeout.enter();
                try {
                    while (FramedStream.this.bytesLeftInWriteWindow <= 0L && !this.finished && !this.closed && FramedStream.this.errorCode == null) {
                        FramedStream.this.waitForIo();
                    }
                }
                finally {
                    FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
                }
            }
            FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
            FramedStream.this.checkOutNotClosed();
            final long min = Math.min(FramedStream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
            final FramedStream this$0 = FramedStream.this;
            this$0.bytesLeftInWriteWindow -= min;
            // monitorexit(framedStream)
            FramedStream.this.writeTimeout.enter();
            try {
                FramedStream.this.connection.writeData(FramedStream.this.id, b && min == this.sendBuffer.size(), this.sendBuffer, min);
            }
            finally {
                FramedStream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }
        
        @Override
        public void close() throws IOException {
            assert !Thread.holdsLock(FramedStream.this);
            Label_0113: {
                synchronized (FramedStream.this) {
                    if (this.closed) {
                        return;
                    }
                    // monitorexit(this.this$0)
                    if (FramedStream.this.sink.finished) {
                        break Label_0113;
                    }
                    if (this.sendBuffer.size() > 0L) {
                        while (this.sendBuffer.size() > 0L) {
                            this.emitDataFrame(true);
                        }
                        break Label_0113;
                    }
                }
                FramedStream.this.connection.writeData(FramedStream.this.id, true, null, 0L);
            }
            synchronized (FramedStream.this) {
                this.closed = true;
                // monitorexit(this.this$0)
                FramedStream.this.connection.flush();
                FramedStream.this.cancelStreamIfNecessary();
            }
        }
        
        @Override
        public void flush() throws IOException {
            assert !Thread.holdsLock(FramedStream.this);
            synchronized (FramedStream.this) {
                FramedStream.this.checkOutNotClosed();
                // monitorexit(this.this$0)
                while (this.sendBuffer.size() > 0L) {
                    this.emitDataFrame(false);
                    FramedStream.this.connection.flush();
                }
            }
        }
        
        @Override
        public Timeout timeout() {
            return FramedStream.this.writeTimeout;
        }
        
        @Override
        public void write(final Buffer buffer, final long n) throws IOException {
            assert !Thread.holdsLock(FramedStream.this);
            this.sendBuffer.write(buffer, n);
            while (this.sendBuffer.size() >= 16384L) {
                this.emitDataFrame(false);
            }
        }
    }
    
    private final class FramedDataSource implements Source
    {
        private boolean closed;
        private boolean finished;
        private final long maxByteCount;
        private final Buffer readBuffer;
        private final Buffer receiveBuffer;
        
        private FramedDataSource(final long maxByteCount) {
            this.receiveBuffer = new Buffer();
            this.readBuffer = new Buffer();
            this.maxByteCount = maxByteCount;
        }
        
        private void checkNotClosed() throws IOException {
            if (this.closed) {
                throw new IOException("stream closed");
            }
            if (FramedStream.this.errorCode != null) {
                throw new IOException("stream was reset: " + FramedStream.this.errorCode);
            }
        }
        
        private void waitUntilReadable() throws IOException {
            FramedStream.this.readTimeout.enter();
            try {
                while (this.readBuffer.size() == 0L && !this.finished && !this.closed && FramedStream.this.errorCode == null) {
                    FramedStream.this.waitForIo();
                }
            }
            finally {
                FramedStream.this.readTimeout.exitAndThrowIfTimedOut();
            }
            FramedStream.this.readTimeout.exitAndThrowIfTimedOut();
        }
        
        @Override
        public void close() throws IOException {
            synchronized (FramedStream.this) {
                this.closed = true;
                this.readBuffer.clear();
                FramedStream.this.notifyAll();
                // monitorexit(this.this$0)
                FramedStream.this.cancelStreamIfNecessary();
            }
        }
        
        @Override
        public long read(final Buffer buffer, final long n) throws IOException {
            if (n < 0L) {
                throw new IllegalArgumentException("byteCount < 0: " + n);
            }
            synchronized (FramedStream.this) {
                this.waitUntilReadable();
                this.checkNotClosed();
                if (this.readBuffer.size() == 0L) {
                    return -1L;
                }
                final long read = this.readBuffer.read(buffer, Math.min(n, this.readBuffer.size()));
                final FramedStream this$0 = FramedStream.this;
                this$0.unacknowledgedBytesRead += read;
                if (FramedStream.this.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                    FramedStream.this.connection.writeWindowUpdateLater(FramedStream.this.id, FramedStream.this.unacknowledgedBytesRead);
                    FramedStream.this.unacknowledgedBytesRead = 0L;
                }
                // monitorexit(this.this$0)
                synchronized (FramedStream.this.connection) {
                    final FramedConnection access$500 = FramedStream.this.connection;
                    access$500.unacknowledgedBytesRead += read;
                    if (FramedStream.this.connection.unacknowledgedBytesRead >= FramedStream.this.connection.okHttpSettings.getInitialWindowSize(65536) / 2) {
                        FramedStream.this.connection.writeWindowUpdateLater(0, FramedStream.this.connection.unacknowledgedBytesRead);
                        FramedStream.this.connection.unacknowledgedBytesRead = 0L;
                    }
                    return read;
                }
            }
        }
        
        void receive(final BufferedSource bufferedSource, long n) throws IOException {
            assert !Thread.holdsLock(FramedStream.this);
            Label_0080: {
                break Label_0080;
                while (true) {
                    final long read;
                    n -= read;
                    synchronized (FramedStream.this) {
                        int n2;
                        if (this.readBuffer.size() == 0L) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                        this.readBuffer.writeAll(this.receiveBuffer);
                        if (n2 != 0) {
                            FramedStream.this.notifyAll();
                        }
                        // monitorexit(this.this$0)
                        if (n <= 0L) {
                            return;
                        }
                        final boolean finished;
                        synchronized (FramedStream.this) {
                            finished = this.finished;
                            int n3;
                            if (n + this.readBuffer.size() > this.maxByteCount) {
                                n3 = 1;
                            }
                            else {
                                n3 = 0;
                            }
                            // monitorexit(this.this$0)
                            if (n3 != 0) {
                                bufferedSource.skip(n);
                                FramedStream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                                return;
                            }
                        }
                        if (finished) {
                            bufferedSource.skip(n);
                            return;
                        }
                        read = bufferedSource.read(this.receiveBuffer, n);
                        if (read == -1L) {
                            throw new EOFException();
                        }
                        continue;
                    }
                }
            }
        }
        
        @Override
        public Timeout timeout() {
            return FramedStream.this.readTimeout;
        }
    }
    
    class StreamTimeout extends AsyncTimeout
    {
        public void exitAndThrowIfTimedOut() throws IOException {
            if (this.exit()) {
                throw this.newTimeoutException(null);
            }
        }
        
        @Override
        protected IOException newTimeoutException(final IOException ex) {
            final SocketTimeoutException ex2 = new SocketTimeoutException("timeout");
            if (ex != null) {
                ex2.initCause(ex);
            }
            return ex2;
        }
        
        @Override
        protected void timedOut() {
            FramedStream.this.closeLater(ErrorCode.CANCEL);
        }
    }
}
