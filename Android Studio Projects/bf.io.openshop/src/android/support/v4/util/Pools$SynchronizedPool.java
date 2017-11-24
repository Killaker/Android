package android.support.v4.util;

public static class SynchronizedPool<T> extends SimplePool<T>
{
    private final Object mLock;
    
    public SynchronizedPool(final int n) {
        super(n);
        this.mLock = new Object();
    }
    
    @Override
    public T acquire() {
        synchronized (this.mLock) {
            return super.acquire();
        }
    }
    
    @Override
    public boolean release(final T t) {
        synchronized (this.mLock) {
            return super.release(t);
        }
    }
}
