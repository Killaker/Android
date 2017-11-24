package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.*;
import java.util.*;

public class zzb<T> implements Iterator<T>
{
    protected final DataBuffer<T> zzajb;
    protected int zzajc;
    
    public zzb(final DataBuffer<T> dataBuffer) {
        this.zzajb = zzx.zzz(dataBuffer);
        this.zzajc = -1;
    }
    
    @Override
    public boolean hasNext() {
        return this.zzajc < -1 + this.zzajb.getCount();
    }
    
    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzajc);
        }
        final DataBuffer<T> zzajb = this.zzajb;
        final int zzajc = 1 + this.zzajc;
        this.zzajc = zzajc;
        return zzajb.get(zzajc);
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
