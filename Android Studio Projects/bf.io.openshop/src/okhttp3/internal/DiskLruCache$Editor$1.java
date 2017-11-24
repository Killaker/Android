package okhttp3.internal;

import okio.*;
import java.io.*;

class DiskLruCache$Editor$1 extends FaultHidingSink {
    @Override
    protected void onException(final IOException ex) {
        synchronized (Editor.this.this$0) {
            Editor.access$1902(Editor.this, true);
        }
    }
}