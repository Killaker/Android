package com.google.android.gms.internal;

import java.util.*;

public static class zzc
{
    private final String zzadc;
    private final List<zze> zzbmj;
    private final Map<String, List<zza>> zzbmk;
    private final int zzbml;
    
    private zzc(final List<zze> list, final Map<String, List<zza>> map, final String zzadc, final int zzbml) {
        this.zzbmj = Collections.unmodifiableList((List<? extends zze>)list);
        this.zzbmk = Collections.unmodifiableMap((Map<? extends String, ? extends List<zza>>)map);
        this.zzadc = zzadc;
        this.zzbml = zzbml;
    }
    
    public static zzd zzHK() {
        return new zzd();
    }
    
    public String getVersion() {
        return this.zzadc;
    }
    
    @Override
    public String toString() {
        return "Rules: " + this.zzHL() + "  Macros: " + this.zzbmk;
    }
    
    public List<zze> zzHL() {
        return this.zzbmj;
    }
    
    public Map<String, List<zza>> zzHM() {
        return this.zzbmk;
    }
}
