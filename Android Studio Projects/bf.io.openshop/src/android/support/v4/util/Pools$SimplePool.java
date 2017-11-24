package android.support.v4.util;

public static class SimplePool<T> implements Pool<T>
{
    private final Object[] mPool;
    private int mPoolSize;
    
    public SimplePool(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The max pool size must be > 0");
        }
        this.mPool = new Object[n];
    }
    
    private boolean isInPool(final T t) {
        for (int i = 0; i < this.mPoolSize; ++i) {
            if (this.mPool[i] == t) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public T acquire() {
        if (this.mPoolSize > 0) {
            final int n = -1 + this.mPoolSize;
            final Object o = this.mPool[n];
            this.mPool[n] = null;
            --this.mPoolSize;
            return (T)o;
        }
        return null;
    }
    
    @Override
    public boolean release(final T t) {
        if (this.isInPool(t)) {
            throw new IllegalStateException("Already in the pool!");
        }
        if (this.mPoolSize < this.mPool.length) {
            this.mPool[this.mPoolSize] = t;
            ++this.mPoolSize;
            return true;
        }
        return false;
    }
}
