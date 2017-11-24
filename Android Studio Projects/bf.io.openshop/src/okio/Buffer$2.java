package okio;

import java.io.*;

class Buffer$2 extends InputStream {
    @Override
    public int available() {
        return (int)Math.min(Buffer.this.size, 2147483647L);
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public int read() {
        if (Buffer.this.size > 0L) {
            return 0xFF & Buffer.this.readByte();
        }
        return -1;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        return Buffer.this.read(array, n, n2);
    }
    
    @Override
    public String toString() {
        return Buffer.this + ".inputStream()";
    }
}