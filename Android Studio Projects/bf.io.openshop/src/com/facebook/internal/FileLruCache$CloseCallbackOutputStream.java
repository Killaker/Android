package com.facebook.internal;

import java.io.*;

private static class CloseCallbackOutputStream extends OutputStream
{
    final StreamCloseCallback callback;
    final OutputStream innerStream;
    
    CloseCallbackOutputStream(final OutputStream innerStream, final StreamCloseCallback callback) {
        this.innerStream = innerStream;
        this.callback = callback;
    }
    
    @Override
    public void close() throws IOException {
        try {
            this.innerStream.close();
        }
        finally {
            this.callback.onClose();
        }
    }
    
    @Override
    public void flush() throws IOException {
        this.innerStream.flush();
    }
    
    @Override
    public void write(final int n) throws IOException {
        this.innerStream.write(n);
    }
    
    @Override
    public void write(final byte[] array) throws IOException {
        this.innerStream.write(array);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.innerStream.write(array, n, n2);
    }
}
