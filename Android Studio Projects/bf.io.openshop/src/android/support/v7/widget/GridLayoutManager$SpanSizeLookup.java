package android.support.v7.widget;

import android.util.*;

public abstract static class SpanSizeLookup
{
    private boolean mCacheSpanIndices;
    final SparseIntArray mSpanIndexCache;
    
    public SpanSizeLookup() {
        this.mSpanIndexCache = new SparseIntArray();
        this.mCacheSpanIndices = false;
    }
    
    int findReferenceIndexFromCache(final int n) {
        int i = 0;
        int n2 = -1 + this.mSpanIndexCache.size();
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            if (this.mSpanIndexCache.keyAt(n3) < n) {
                i = n3 + 1;
            }
            else {
                n2 = n3 - 1;
            }
        }
        final int n4 = i - 1;
        if (n4 >= 0 && n4 < this.mSpanIndexCache.size()) {
            return this.mSpanIndexCache.keyAt(n4);
        }
        return -1;
    }
    
    int getCachedSpanIndex(final int n, final int n2) {
        int n3;
        if (!this.mCacheSpanIndices) {
            n3 = this.getSpanIndex(n, n2);
        }
        else {
            n3 = this.mSpanIndexCache.get(n, -1);
            if (n3 == -1) {
                final int spanIndex = this.getSpanIndex(n, n2);
                this.mSpanIndexCache.put(n, spanIndex);
                return spanIndex;
            }
        }
        return n3;
    }
    
    public int getSpanGroupIndex(final int n, final int n2) {
        int n3 = 0;
        int n4 = 0;
        final int spanSize = this.getSpanSize(n);
        for (int i = 0; i < n; ++i) {
            final int spanSize2 = this.getSpanSize(i);
            n3 += spanSize2;
            if (n3 == n2) {
                n3 = 0;
                ++n4;
            }
            else if (n3 > n2) {
                n3 = spanSize2;
                ++n4;
            }
        }
        if (n3 + spanSize > n2) {
            ++n4;
        }
        return n4;
    }
    
    public int getSpanIndex(final int n, final int n2) {
        final int spanSize = this.getSpanSize(n);
        int n3;
        if (spanSize == n2) {
            n3 = 0;
        }
        else {
            final boolean mCacheSpanIndices = this.mCacheSpanIndices;
            n3 = 0;
            int n4 = 0;
            if (mCacheSpanIndices) {
                final int size = this.mSpanIndexCache.size();
                n3 = 0;
                n4 = 0;
                if (size > 0) {
                    final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                    n3 = 0;
                    n4 = 0;
                    if (referenceIndexFromCache >= 0) {
                        n3 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                        n4 = referenceIndexFromCache + 1;
                    }
                }
            }
            for (int i = n4; i < n; ++i) {
                final int spanSize2 = this.getSpanSize(i);
                n3 += spanSize2;
                if (n3 == n2) {
                    n3 = 0;
                }
                else if (n3 > n2) {
                    n3 = spanSize2;
                }
            }
            if (n3 + spanSize > n2) {
                return 0;
            }
        }
        return n3;
    }
    
    public abstract int getSpanSize(final int p0);
    
    public void invalidateSpanIndexCache() {
        this.mSpanIndexCache.clear();
    }
    
    public boolean isSpanIndexCacheEnabled() {
        return this.mCacheSpanIndices;
    }
    
    public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
        this.mCacheSpanIndices = mCacheSpanIndices;
    }
}
