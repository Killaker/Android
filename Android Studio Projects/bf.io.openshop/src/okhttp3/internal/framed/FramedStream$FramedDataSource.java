package okhttp3.internal.framed;

import java.io.*;
import okio.*;

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
        if (FramedStream.access$800(FramedStream.this) != null) {
            throw new IOException("stream was reset: " + FramedStream.access$800(FramedStream.this));
        }
    }
    
    private void waitUntilReadable() throws IOException {
        FramedStream.access$700(FramedStream.this).enter();
        try {
            while (this.readBuffer.size() == 0L && !this.finished && !this.closed && FramedStream.access$800(FramedStream.this) == null) {
                FramedStream.access$900(FramedStream.this);
            }
        }
        finally {
            FramedStream.access$700(FramedStream.this).exitAndThrowIfTimedOut();
        }
        FramedStream.access$700(FramedStream.this).exitAndThrowIfTimedOut();
    }
    
    @Override
    public void close() throws IOException {
        synchronized (FramedStream.this) {
            this.closed = true;
            this.readBuffer.clear();
            FramedStream.this.notifyAll();
            // monitorexit(this.this$0)
            FramedStream.access$1000(FramedStream.this);
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
            if (FramedStream.this.unacknowledgedBytesRead >= FramedStream.access$500(FramedStream.this).okHttpSettings.getInitialWindowSize(65536) / 2) {
                FramedStream.access$500(FramedStream.this).writeWindowUpdateLater(FramedStream.access$600(FramedStream.this), FramedStream.this.unacknowledgedBytesRead);
                FramedStream.this.unacknowledgedBytesRead = 0L;
            }
            // monitorexit(this.this$0)
            synchronized (FramedStream.access$500(FramedStream.this)) {
                final FramedConnection access$500 = FramedStream.access$500(FramedStream.this);
                access$500.unacknowledgedBytesRead += read;
                if (FramedStream.access$500(FramedStream.this).unacknowledgedBytesRead >= FramedStream.access$500(FramedStream.this).okHttpSettings.getInitialWindowSize(65536) / 2) {
                    FramedStream.access$500(FramedStream.this).writeWindowUpdateLater(0, FramedStream.access$500(FramedStream.this).unacknowledgedBytesRead);
                    FramedStream.access$500(FramedStream.this).unacknowledgedBytesRead = 0L;
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
        return FramedStream.access$700(FramedStream.this);
    }
}
