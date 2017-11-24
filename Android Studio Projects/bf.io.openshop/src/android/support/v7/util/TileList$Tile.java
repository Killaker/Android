package android.support.v7.util;

import java.lang.reflect.*;

public static class Tile<T>
{
    public int mItemCount;
    public final T[] mItems;
    Tile<T> mNext;
    public int mStartPosition;
    
    public Tile(final Class<T> clazz, final int n) {
        this.mItems = (Object[])Array.newInstance(clazz, n);
    }
    
    boolean containsPosition(final int n) {
        return this.mStartPosition <= n && n < this.mStartPosition + this.mItemCount;
    }
    
    T getByPosition(final int n) {
        return (T)this.mItems[n - this.mStartPosition];
    }
}
