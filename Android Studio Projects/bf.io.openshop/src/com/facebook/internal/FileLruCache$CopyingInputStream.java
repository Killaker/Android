package com.facebook.internal;

import java.io.*;

private static final class CopyingInputStream extends InputStream
{
    final InputStream input;
    final OutputStream output;
    
    CopyingInputStream(final InputStream input, final OutputStream output) {
        this.input = input;
        this.output = output;
    }
    
    @Override
    public int available() throws IOException {
        return this.input.available();
    }
    
    @Override
    public void close() throws IOException {
        try {
            this.input.close();
        }
        finally {
            this.output.close();
        }
    }
    
    @Override
    public void mark(final int n) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean markSupported() {
        return false;
    }
    
    @Override
    public int read() throws IOException {
        final int read = this.input.read();
        if (read >= 0) {
            this.output.write(read);
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        final int read = this.input.read(array);
        if (read > 0) {
            this.output.write(array, 0, read);
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.input.read(array, n, n2);
        if (read > 0) {
            this.output.write(array, n, read);
        }
        return read;
    }
    
    @Override
    public void reset() {
        synchronized (this) {
            throw new UnsupportedOperationException();
        }
    }
    
    @Override
    public long skip(final long n) throws IOException {
        final byte[] array = new byte[1024];
        long n2;
        int read;
        for (n2 = 0L; n2 < n; n2 += read) {
            read = this.read(array, 0, (int)Math.min(n - n2, array.length));
            if (read < 0) {
                break;
            }
        }
        return n2;
    }
}
