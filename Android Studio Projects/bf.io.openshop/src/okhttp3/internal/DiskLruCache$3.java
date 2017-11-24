package okhttp3.internal;

import java.util.*;
import java.io.*;
import okio.*;

class DiskLruCache$3 implements Iterator<Snapshot> {
    final Iterator<Entry> delegate = new ArrayList<Entry>(DiskLruCache.access$2000(DiskLruCache.this).values()).iterator();
    Snapshot nextSnapshot;
    Snapshot removeSnapshot;
    
    @Override
    public boolean hasNext() {
        if (this.nextSnapshot != null) {
            return true;
        }
        Label_0076: {
            synchronized (DiskLruCache.this) {
                if (DiskLruCache.access$100(DiskLruCache.this)) {
                    return false;
                }
                Snapshot snapshot = null;
                Block_6: {
                    while (this.delegate.hasNext()) {
                        snapshot = this.delegate.next().snapshot();
                        if (snapshot != null) {
                            break Block_6;
                        }
                    }
                    break Label_0076;
                }
                this.nextSnapshot = snapshot;
                return true;
            }
        }
        // monitorexit(diskLruCache)
        return false;
    }
    
    @Override
    public Snapshot next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.removeSnapshot = this.nextSnapshot;
        this.nextSnapshot = null;
        return this.removeSnapshot;
    }
    
    @Override
    public void remove() {
        if (this.removeSnapshot == null) {
            throw new IllegalStateException("remove() before next()");
        }
        try {
            DiskLruCache.this.remove(this.removeSnapshot.key);
        }
        catch (IOException ex) {}
        finally {
            this.removeSnapshot = null;
        }
    }
}