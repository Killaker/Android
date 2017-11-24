package com.google.android.gms.common.data;

import com.google.android.gms.common.api.*;
import java.util.*;
import android.os.*;

public interface DataBuffer<T> extends Releasable, Iterable<T>
{
    @Deprecated
    void close();
    
    T get(final int p0);
    
    int getCount();
    
    @Deprecated
    boolean isClosed();
    
    Iterator<T> iterator();
    
    void release();
    
    Iterator<T> singleRefIterator();
    
    Bundle zzpZ();
}
