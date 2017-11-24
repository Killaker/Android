package android.support.v7.util;

import android.support.annotation.*;

public abstract static class DataCallback<T>
{
    @WorkerThread
    public abstract void fillData(final T[] p0, final int p1, final int p2);
    
    @WorkerThread
    public int getMaxCachedTiles() {
        return 10;
    }
    
    @WorkerThread
    public void recycleData(final T[] array, final int n) {
    }
    
    @WorkerThread
    public abstract int refreshData();
}
