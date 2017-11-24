package com.android.volley.toolbox;

import java.io.*;

private static class CountingInputStream extends FilterInputStream
{
    private int bytesRead;
    
    private CountingInputStream(final InputStream inputStream) {
        super(inputStream);
        this.bytesRead = 0;
    }
    
    @Override
    public int read() throws IOException {
        final int read = super.read();
        if (read != -1) {
            ++this.bytesRead;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = super.read(array, n, n2);
        if (read != -1) {
            this.bytesRead += read;
        }
        return read;
    }
}
