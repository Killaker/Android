package com.squareup.picasso;

import java.io.*;

final class MarkableInputStream extends InputStream
{
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private long defaultMark;
    private final InputStream in;
    private long limit;
    private long offset;
    private long reset;
    
    public MarkableInputStream(final InputStream inputStream) {
        this(inputStream, 4096);
    }
    
    public MarkableInputStream(InputStream in, final int n) {
        this.defaultMark = -1L;
        if (!in.markSupported()) {
            in = new BufferedInputStream(in, n);
        }
        this.in = in;
    }
    
    private void setLimit(final long limit) {
        try {
            if (this.reset < this.offset && this.offset <= this.limit) {
                this.in.reset();
                this.in.mark((int)(limit - this.reset));
                this.skip(this.reset, this.offset);
            }
            else {
                this.reset = this.offset;
                this.in.mark((int)(limit - this.offset));
            }
            this.limit = limit;
        }
        catch (IOException ex) {
            throw new IllegalStateException("Unable to mark: " + ex);
        }
    }
    
    private void skip(long n, final long n2) throws IOException {
        while (n < n2) {
            long skip = this.in.skip(n2 - n);
            if (skip == 0L) {
                if (this.read() == -1) {
                    break;
                }
                skip = 1L;
            }
            n += skip;
        }
    }
    
    @Override
    public int available() throws IOException {
        return this.in.available();
    }
    
    @Override
    public void close() throws IOException {
        this.in.close();
    }
    
    @Override
    public void mark(final int n) {
        this.defaultMark = this.savePosition(n);
    }
    
    @Override
    public boolean markSupported() {
        return this.in.markSupported();
    }
    
    @Override
    public int read() throws IOException {
        final int read = this.in.read();
        if (read != -1) {
            ++this.offset;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array) throws IOException {
        final int read = this.in.read(array);
        if (read != -1) {
            this.offset += read;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.in.read(array, n, n2);
        if (read != -1) {
            this.offset += read;
        }
        return read;
    }
    
    @Override
    public void reset() throws IOException {
        this.reset(this.defaultMark);
    }
    
    public void reset(final long offset) throws IOException {
        if (this.offset > this.limit || offset < this.reset) {
            throw new IOException("Cannot reset");
        }
        this.in.reset();
        this.skip(this.reset, offset);
        this.offset = offset;
    }
    
    public long savePosition(final int n) {
        final long limit = this.offset + n;
        if (this.limit < limit) {
            this.setLimit(limit);
        }
        return this.offset;
    }
    
    @Override
    public long skip(final long n) throws IOException {
        final long skip = this.in.skip(n);
        this.offset += skip;
        return skip;
    }
}
