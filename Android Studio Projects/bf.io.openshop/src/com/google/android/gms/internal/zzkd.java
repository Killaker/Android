package com.google.android.gms.internal;

import com.google.android.gms.measurement.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

public final class zzkd extends zze<zzkd>
{
    private final Map<String, Object> zzxA;
    
    public zzkd() {
        this.zzxA = new HashMap<String, Object>();
    }
    
    private String zzaW(String substring) {
        zzx.zzcM(substring);
        if (substring != null && substring.startsWith("&")) {
            substring = substring.substring(1);
        }
        zzx.zzh(substring, "Name can not be empty or \"&\"");
        return substring;
    }
    
    public void set(final String s, final String s2) {
        this.zzxA.put(this.zzaW(s), s2);
    }
    
    @Override
    public String toString() {
        return zze.zzF(this.zzxA);
    }
    
    @Override
    public void zza(final zzkd zzkd) {
        zzx.zzz(zzkd);
        zzkd.zzxA.putAll(this.zzxA);
    }
    
    public Map<String, Object> zziR() {
        return Collections.unmodifiableMap((Map<? extends String, ?>)this.zzxA);
    }
}
