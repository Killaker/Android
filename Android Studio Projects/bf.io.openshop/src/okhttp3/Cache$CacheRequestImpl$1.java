package okhttp3;

import okhttp3.internal.*;
import okio.*;
import java.io.*;

class Cache$CacheRequestImpl$1 extends ForwardingSink {
    final /* synthetic */ DiskLruCache.Editor val$editor;
    
    @Override
    public void close() throws IOException {
        synchronized (CacheRequestImpl.this.this$0) {
            if (CacheRequestImpl.access$700(CacheRequestImpl.this)) {
                return;
            }
            CacheRequestImpl.access$702(CacheRequestImpl.this, true);
            Cache.access$808(CacheRequestImpl.this.this$0);
            // monitorexit(this.this$1.this$0)
            super.close();
            this.val$editor.commit();
        }
    }
}