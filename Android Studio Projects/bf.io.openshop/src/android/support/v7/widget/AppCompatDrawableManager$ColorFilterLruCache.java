package android.support.v7.widget;

import android.support.v4.util.*;
import android.graphics.*;

private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter>
{
    public ColorFilterLruCache(final int n) {
        super(n);
    }
    
    private static int generateCacheKey(final int n, final PorterDuff$Mode porterDuff$Mode) {
        return 31 * (n + 31) + porterDuff$Mode.hashCode();
    }
    
    PorterDuffColorFilter get(final int n, final PorterDuff$Mode porterDuff$Mode) {
        return this.get(generateCacheKey(n, porterDuff$Mode));
    }
    
    PorterDuffColorFilter put(final int n, final PorterDuff$Mode porterDuff$Mode, final PorterDuffColorFilter porterDuffColorFilter) {
        return this.put(generateCacheKey(n, porterDuff$Mode), porterDuffColorFilter);
    }
}
