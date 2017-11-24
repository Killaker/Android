package com.facebook.internal;

public interface Mapper<T, K>
{
    K apply(final T p0);
}
