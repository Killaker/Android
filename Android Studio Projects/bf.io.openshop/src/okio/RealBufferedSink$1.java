package okio;

import java.io.*;

class RealBufferedSink$1 extends OutputStream {
    @Override
    public void close() throws IOException {
        RealBufferedSink.this.close();
    }
    
    @Override
    public void flush() throws IOException {
        if (!RealBufferedSink.access$000(RealBufferedSink.this)) {
            RealBufferedSink.this.flush();
        }
    }
    
    @Override
    public String toString() {
        return RealBufferedSink.this + ".outputStream()";
    }
    
    @Override
    public void write(final int n) throws IOException {
        if (RealBufferedSink.access$000(RealBufferedSink.this)) {
            throw new IOException("closed");
        }
        RealBufferedSink.this.buffer.writeByte((int)(byte)n);
        RealBufferedSink.this.emitCompleteSegments();
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        if (RealBufferedSink.access$000(RealBufferedSink.this)) {
            throw new IOException("closed");
        }
        RealBufferedSink.this.buffer.write(array, n, n2);
        RealBufferedSink.this.emitCompleteSegments();
    }
}