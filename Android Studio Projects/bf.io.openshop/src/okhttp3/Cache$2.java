package okhttp3;

import okhttp3.internal.*;
import java.io.*;
import okio.*;
import java.util.*;

class Cache$2 implements Iterator<String> {
    boolean canRemove;
    final Iterator<DiskLruCache.Snapshot> delegate = Cache.access$600(Cache.this).snapshots();
    String nextUrl;
    
    @Override
    public boolean hasNext() {
        if (this.nextUrl != null) {
            return true;
        }
        this.canRemove = false;
        while (this.delegate.hasNext()) {
            final DiskLruCache.Snapshot snapshot = this.delegate.next();
            try {
                this.nextUrl = Okio.buffer(snapshot.getSource(0)).readUtf8LineStrict();
                return true;
            }
            catch (IOException ex) {
                continue;
            }
            finally {
                snapshot.close();
            }
            break;
        }
        return false;
    }
    
    @Override
    public String next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        final String nextUrl = this.nextUrl;
        this.nextUrl = null;
        this.canRemove = true;
        return nextUrl;
    }
    
    @Override
    public void remove() {
        if (!this.canRemove) {
            throw new IllegalStateException("remove() before next()");
        }
        this.delegate.remove();
    }
}