package okhttp3.internal.http;

import java.util.concurrent.*;
import okhttp3.internal.*;
import java.io.*;
import okio.*;

class HttpEngine$2 implements Source {
    boolean cacheRequestClosed;
    final /* synthetic */ BufferedSink val$cacheBody;
    final /* synthetic */ CacheRequest val$cacheRequest;
    final /* synthetic */ BufferedSource val$source;
    
    @Override
    public void close() throws IOException {
        if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
            this.cacheRequestClosed = true;
            this.val$cacheRequest.abort();
        }
        this.val$source.close();
    }
    
    @Override
    public long read(final Buffer buffer, final long n) throws IOException {
        long read;
        try {
            read = this.val$source.read(buffer, n);
            if (read == -1L) {
                if (!this.cacheRequestClosed) {
                    this.cacheRequestClosed = true;
                    this.val$cacheBody.close();
                }
                return -1L;
            }
        }
        catch (IOException ex) {
            if (!this.cacheRequestClosed) {
                this.cacheRequestClosed = true;
                this.val$cacheRequest.abort();
            }
            throw ex;
        }
        buffer.copyTo(this.val$cacheBody.buffer(), buffer.size() - read, read);
        this.val$cacheBody.emitCompleteSegments();
        return read;
    }
    
    @Override
    public Timeout timeout() {
        return this.val$source.timeout();
    }
}