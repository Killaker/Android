package okhttp3;

import okhttp3.internal.http.*;
import okio.*;
import okhttp3.internal.*;
import java.io.*;

private final class CacheRequestImpl implements CacheRequest
{
    private Sink body;
    private Sink cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;
    
    public CacheRequestImpl(final DiskLruCache.Editor editor) throws IOException {
        this.editor = editor;
        this.cacheOut = editor.newSink(1);
        this.body = new ForwardingSink(this.cacheOut) {
            @Override
            public void close() throws IOException {
                synchronized (Cache.this) {
                    if (CacheRequestImpl.this.done) {
                        return;
                    }
                    CacheRequestImpl.this.done = true;
                    Cache.access$808(Cache.this);
                    // monitorexit(this.this$1.this$0)
                    super.close();
                    editor.commit();
                }
            }
        };
    }
    
    @Override
    public void abort() {
        synchronized (Cache.this) {
            if (this.done) {
                return;
            }
            this.done = true;
            Cache.access$908(Cache.this);
            // monitorexit(this.this$0)
            Util.closeQuietly(this.cacheOut);
            try {
                this.editor.abort();
            }
            catch (IOException ex) {}
        }
    }
    
    @Override
    public Sink body() {
        return this.body;
    }
}
