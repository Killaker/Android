package okhttp3.internal;

import java.io.*;
import okio.*;

static final class DiskLruCache$4 implements Sink {
    @Override
    public void close() throws IOException {
    }
    
    @Override
    public void flush() throws IOException {
    }
    
    @Override
    public Timeout timeout() {
        return Timeout.NONE;
    }
    
    @Override
    public void write(final Buffer buffer, final long n) throws IOException {
        buffer.skip(n);
    }
}