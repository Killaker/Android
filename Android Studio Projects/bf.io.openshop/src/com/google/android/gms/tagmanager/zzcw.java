package com.google.android.gms.tagmanager;

import java.util.*;

class zzcw<K, V> implements zzl<K, V>
{
    private final Map<K, V> zzbld;
    private final int zzble;
    private final zzm.zza<K, V> zzblf;
    private int zzblg;
    
    zzcw(final int zzble, final zzm.zza<K, V> zzblf) {
        this.zzbld = new HashMap<K, V>();
        this.zzble = zzble;
        this.zzblf = zzblf;
    }
    
    @Override
    public V get(final K k) {
        synchronized (this) {
            return this.zzbld.get(k);
        }
    }
    
    @Override
    public void zzh(final K k, final V v) {
        // monitorenter(this)
        Label_0025: {
            if (k != null) {
                if (v != null) {
                    break Label_0025;
                }
            }
            try {
                throw new NullPointerException("key == null || value == null");
            }
            finally {
            }
            // monitorexit(this)
        }
        this.zzblg += this.zzblf.sizeOf(k, v);
        if (this.zzblg > this.zzble) {
            final Iterator<Map.Entry<K, V>> iterator = this.zzbld.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, V> entry = iterator.next();
                this.zzblg -= this.zzblf.sizeOf(entry.getKey(), entry.getValue());
                iterator.remove();
                if (this.zzblg <= this.zzble) {
                    break;
                }
            }
        }
        this.zzbld.put(k, v);
    }
    // monitorexit(this)
}
