package com.google.android.gms.common.data;

import java.util.*;
import android.os.*;

public abstract class AbstractDataBuffer<T> implements DataBuffer<T>
{
    protected final DataHolder zzahi;
    
    protected AbstractDataBuffer(final DataHolder zzahi) {
        this.zzahi = zzahi;
        if (this.zzahi != null) {
            this.zzahi.zzu(this);
        }
    }
    
    @Deprecated
    @Override
    public final void close() {
        this.release();
    }
    
    @Override
    public abstract T get(final int p0);
    
    @Override
    public int getCount() {
        if (this.zzahi == null) {
            return 0;
        }
        return this.zzahi.getCount();
    }
    
    @Deprecated
    @Override
    public boolean isClosed() {
        return this.zzahi == null || this.zzahi.isClosed();
    }
    
    @Override
    public Iterator<T> iterator() {
        return new zzb<T>(this);
    }
    
    @Override
    public void release() {
        if (this.zzahi != null) {
            this.zzahi.close();
        }
    }
    
    @Override
    public Iterator<T> singleRefIterator() {
        return new zzg<T>(this);
    }
    
    @Override
    public Bundle zzpZ() {
        return this.zzahi.zzpZ();
    }
}
