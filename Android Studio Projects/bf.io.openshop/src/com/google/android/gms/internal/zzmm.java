package com.google.android.gms.internal;

import java.util.*;
import android.support.v4.util.*;

public class zzmm<E> extends AbstractSet<E>
{
    private final ArrayMap<E, E> zzanZ;
    
    public zzmm() {
        this.zzanZ = new ArrayMap<E, E>();
    }
    
    public zzmm(final int n) {
        this.zzanZ = new ArrayMap<E, E>(n);
    }
    
    public zzmm(final Collection<E> collection) {
        this(collection.size());
        this.addAll((Collection<? extends E>)collection);
    }
    
    @Override
    public boolean add(final E e) {
        if (this.zzanZ.containsKey(e)) {
            return false;
        }
        this.zzanZ.put(e, e);
        return true;
    }
    
    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        if (collection instanceof zzmm) {
            return this.zza((zzmm<? extends E>)collection);
        }
        return super.addAll(collection);
    }
    
    @Override
    public void clear() {
        this.zzanZ.clear();
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.zzanZ.containsKey(o);
    }
    
    @Override
    public Iterator<E> iterator() {
        return this.zzanZ.keySet().iterator();
    }
    
    @Override
    public boolean remove(final Object o) {
        if (!this.zzanZ.containsKey(o)) {
            return false;
        }
        this.zzanZ.remove(o);
        return true;
    }
    
    @Override
    public int size() {
        return this.zzanZ.size();
    }
    
    public boolean zza(final zzmm<? extends E> zzmm) {
        final int size = this.size();
        this.zzanZ.putAll((SimpleArrayMap<?, ?>)zzmm.zzanZ);
        return this.size() > size;
    }
}
