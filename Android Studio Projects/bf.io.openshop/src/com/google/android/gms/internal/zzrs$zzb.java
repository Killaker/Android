package com.google.android.gms.internal;

import java.util.*;

public static class zzb
{
    private zzag.zza zzbkI;
    private final Map<String, zzag.zza> zzbmi;
    
    private zzb() {
        this.zzbmi = new HashMap<String, zzag.zza>();
    }
    
    public zza zzHJ() {
        return new zza((Map)this.zzbmi, this.zzbkI);
    }
    
    public zzb zzb(final String s, final zzag.zza zza) {
        this.zzbmi.put(s, zza);
        return this;
    }
    
    public zzb zzq(final zzag.zza zzbkI) {
        this.zzbkI = zzbkI;
        return this;
    }
}
