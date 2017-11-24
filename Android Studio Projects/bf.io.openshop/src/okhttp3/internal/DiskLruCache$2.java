package okhttp3.internal;

import okio.*;
import java.io.*;

class DiskLruCache$2 extends FaultHidingSink {
    @Override
    protected void onException(final IOException ex) {
        assert Thread.holdsLock(DiskLruCache.this);
        DiskLruCache.access$602(DiskLruCache.this, true);
    }
}