package com.google.android.gms.tagmanager;

import android.annotation.*;
import android.util.*;

@TargetApi(12)
class zzba<K, V> implements zzl<K, V>
{
    private LruCache<K, V> zzbjq;
    
    zzba(final int n, final zzm.zza<K, V> zza) {
        this.zzbjq = new LruCache<K, V>(n) {
            protected int sizeOf(final K k, final V v) {
                return zza.sizeOf(k, v);
            }
        };
    }
    
    @Override
    public V get(final K k) {
        return (V)this.zzbjq.get((Object)k);
    }
    
    @Override
    public void zzh(final K k, final V v) {
        this.zzbjq.put((Object)k, (Object)v);
    }
}
