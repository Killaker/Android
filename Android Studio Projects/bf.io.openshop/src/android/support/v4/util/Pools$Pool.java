package android.support.v4.util;

public interface Pool<T>
{
    T acquire();
    
    boolean release(final T p0);
}
