package com.google.android.gms.internal;

import java.util.*;

public static class zza
{
    private final zzag.zza zzbkI;
    private final Map<String, zzag.zza> zzbmi;
    
    private zza(final Map<String, zzag.zza> zzbmi, final zzag.zza zzbkI) {
        this.zzbmi = zzbmi;
        this.zzbkI = zzbkI;
    }
    
    public static zzb zzHH() {
        return new zzb();
    }
    
    @Override
    public String toString() {
        return "Properties: " + this.zzHI() + " pushAfterEvaluate: " + this.zzbkI;
    }
    
    public Map<String, zzag.zza> zzHI() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends zzag.zza>)this.zzbmi);
    }
    
    public zzag.zza zzHh() {
        return this.zzbkI;
    }
    
    public void zza(final String s, final zzag.zza zza) {
        this.zzbmi.put(s, zza);
    }
}
