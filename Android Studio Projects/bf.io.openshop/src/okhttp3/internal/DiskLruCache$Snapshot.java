package okhttp3.internal;

import okio.*;
import java.io.*;

public final class Snapshot implements Closeable
{
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    private final Source[] sources;
    
    private Snapshot(final String key, final long sequenceNumber, final Source[] sources, final long[] lengths) {
        this.key = key;
        this.sequenceNumber = sequenceNumber;
        this.sources = sources;
        this.lengths = lengths;
    }
    
    @Override
    public void close() {
        final Source[] sources = this.sources;
        for (int length = sources.length, i = 0; i < length; ++i) {
            Util.closeQuietly(sources[i]);
        }
    }
    
    public Editor edit() throws IOException {
        return DiskLruCache.access$2200(DiskLruCache.this, this.key, this.sequenceNumber);
    }
    
    public long getLength(final int n) {
        return this.lengths[n];
    }
    
    public Source getSource(final int n) {
        return this.sources[n];
    }
    
    public String key() {
        return this.key;
    }
}
