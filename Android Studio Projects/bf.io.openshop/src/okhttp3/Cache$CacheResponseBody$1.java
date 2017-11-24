package okhttp3;

import okhttp3.internal.*;
import okio.*;
import java.io.*;

class Cache$CacheResponseBody$1 extends ForwardingSource {
    final /* synthetic */ DiskLruCache.Snapshot val$snapshot;
    
    @Override
    public void close() throws IOException {
        this.val$snapshot.close();
        super.close();
    }
}