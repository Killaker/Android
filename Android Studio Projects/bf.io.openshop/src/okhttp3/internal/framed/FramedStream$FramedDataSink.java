package okhttp3.internal.framed;

import java.io.*;
import okio.*;

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
            FramedStream.access$1100(FramedStream.this).enter();
            try {
                while (FramedStream.this.bytesLeftInWriteWindow <= 0L && !this.finished && !this.closed && FramedStream.access$800(FramedStream.this) == null) {
                    FramedStream.access$900(FramedStream.this);
                }
            }
            finally {
                FramedStream.access$1100(FramedStream.this).exitAndThrowIfTimedOut();
            }
        }
        FramedStream.access$1100(FramedStream.this).exitAndThrowIfTimedOut();
        FramedStream.access$1200(FramedStream.this);
        final long min = Math.min(FramedStream.this.bytesLeftInWriteWindow, this.sendBuffer.size());
        final FramedStream this$0 = FramedStream.this;
        this$0.bytesLeftInWriteWindow -= min;
        // monitorexit(framedStream)
        FramedStream.access$1100(FramedStream.this).enter();
        try {
            FramedStream.access$500(FramedStream.this).writeData(FramedStream.access$600(FramedStream.this), b && min == this.sendBuffer.size(), this.sendBuffer, min);
        }
        finally {
            FramedStream.access$1100(FramedStream.this).exitAndThrowIfTimedOut();
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
            FramedStream.access$500(FramedStream.this).writeData(FramedStream.access$600(FramedStream.this), true, null, 0L);
        }
        synchronized (FramedStream.this) {
            this.closed = true;
            // monitorexit(this.this$0)
            FramedStream.access$500(FramedStream.this).flush();
            FramedStream.access$1000(FramedStream.this);
        }
    }
    
    @Override
    public void flush() throws IOException {
        assert !Thread.holdsLock(FramedStream.this);
        synchronized (FramedStream.this) {
            FramedStream.access$1200(FramedStream.this);
            // monitorexit(this.this$0)
            while (this.sendBuffer.size() > 0L) {
                this.emitDataFrame(false);
                FramedStream.access$500(FramedStream.this).flush();
            }
        }
    }
    
    @Override
    public Timeout timeout() {
        return FramedStream.access$1100(FramedStream.this);
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
