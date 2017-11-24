package com.google.android.gms.tagmanager;

import android.os.*;

class zzm<K, V>
{
    final zza<K, V> zzbhK;
    
    public zzm() {
        this.zzbhK = (zza<K, V>)new zza<K, V>() {
            @Override
            public int sizeOf(final K k, final V v) {
                return 1;
            }
        };
    }
    
    int zzFY() {
        return Build$VERSION.SDK_INT;
    }
    
    public zzl<K, V> zza(final int n, final zza<K, V> zza) {
        if (n <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (this.zzFY() < 12) {
            return new zzcw<K, V>(n, zza);
        }
        return new zzba<K, V>(n, zza);
    }
    
    public interface zza<K, V>
    {
        int sizeOf(final K p0, final V p1);
    }
}
