package com.android.volley.toolbox;

import java.io.*;

public class PoolingByteArrayOutputStream extends ByteArrayOutputStream
{
    private static final int DEFAULT_SIZE = 256;
    private final ByteArrayPool mPool;
    
    public PoolingByteArrayOutputStream(final ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 256);
    }
    
    public PoolingByteArrayOutputStream(final ByteArrayPool mPool, final int n) {
        this.mPool = mPool;
        this.buf = this.mPool.getBuf(Math.max(n, 256));
    }
    
    private void expand(final int n) {
        if (n + this.count <= this.buf.length) {
            return;
        }
        final byte[] buf = this.mPool.getBuf(2 * (n + this.count));
        System.arraycopy(this.buf, 0, buf, 0, this.count);
        this.mPool.returnBuf(this.buf);
        this.buf = buf;
    }
    
    @Override
    public void close() throws IOException {
        this.mPool.returnBuf(this.buf);
        this.buf = null;
        super.close();
    }
    
    public void finalize() {
        this.mPool.returnBuf(this.buf);
    }
    
    @Override
    public void write(final int n) {
        synchronized (this) {
            this.expand(1);
            super.write(n);
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        synchronized (this) {
            this.expand(n2);
            super.write(array, n, n2);
        }
    }
}
