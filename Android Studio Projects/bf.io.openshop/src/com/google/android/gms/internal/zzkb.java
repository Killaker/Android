package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;

public final class zzkb extends zze<zzkb>
{
    private Map<Integer, String> zzPL;
    
    public zzkb() {
        this.zzPL = new HashMap<Integer, String>(4);
    }
    
    @Override
    public String toString() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<Integer, String> entry : this.zzPL.entrySet()) {
            hashMap.put("dimension" + entry.getKey(), entry.getValue());
        }
        return zze.zzF(hashMap);
    }
    
    @Override
    public void zza(final zzkb zzkb) {
        zzkb.zzPL.putAll(this.zzPL);
    }
    
    public Map<Integer, String> zziP() {
        return Collections.unmodifiableMap((Map<? extends Integer, ? extends String>)this.zzPL);
    }
}
