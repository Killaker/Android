package com.facebook.internal;

import java.util.*;

public interface Collection<T>
{
    Object get(final T p0);
    
    Iterator<T> keyIterator();
    
    void set(final T p0, final Object p1, final OnErrorListener p2);
}
