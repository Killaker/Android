package okio;

import java.io.*;

class Buffer$1 extends OutputStream {
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public String toString() {
        return this + ".outputStream()";
    }
    
    @Override
    public void write(final int n) {
        Buffer.this.writeByte((int)(byte)n);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        Buffer.this.write(array, n, n2);
    }
}