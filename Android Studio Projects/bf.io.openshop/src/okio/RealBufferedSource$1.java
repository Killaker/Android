package okio;

import java.io.*;

class RealBufferedSource$1 extends InputStream {
    @Override
    public int available() throws IOException {
        if (RealBufferedSource.access$000(RealBufferedSource.this)) {
            throw new IOException("closed");
        }
        return (int)Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
    }
    
    @Override
    public void close() throws IOException {
        RealBufferedSource.this.close();
    }
    
    @Override
    public int read() throws IOException {
        if (RealBufferedSource.access$000(RealBufferedSource.this)) {
            throw new IOException("closed");
        }
        if (RealBufferedSource.this.buffer.size == 0L && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L) {
            return -1;
        }
        return 0xFF & RealBufferedSource.this.buffer.readByte();
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (RealBufferedSource.access$000(RealBufferedSource.this)) {
            throw new IOException("closed");
        }
        Util.checkOffsetAndCount(array.length, n, n2);
        if (RealBufferedSource.this.buffer.size == 0L && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 2048L) == -1L) {
            return -1;
        }
        return RealBufferedSource.this.buffer.read(array, n, n2);
    }
    
    @Override
    public String toString() {
        return RealBufferedSource.this + ".inputStream()";
    }
}