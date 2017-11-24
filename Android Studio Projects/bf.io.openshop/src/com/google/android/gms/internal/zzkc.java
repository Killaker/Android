package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import java.util.*;

public final class zzkc extends zze<zzkc>
{
    private Map<Integer, Double> zzPM;
    
    public zzkc() {
        this.zzPM = new HashMap<Integer, Double>(4);
    }
    
    @Override
    public String toString() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<Integer, Double> entry : this.zzPM.entrySet()) {
            hashMap.put("metric" + entry.getKey(), entry.getValue());
        }
        return zze.zzF(hashMap);
    }
    
    @Override
    public void zza(final zzkc zzkc) {
        zzkc.zzPM.putAll(this.zzPM);
    }
    
    public Map<Integer, Double> zziQ() {
        return Collections.unmodifiableMap((Map<? extends Integer, ? extends Double>)this.zzPM);
    }
}
