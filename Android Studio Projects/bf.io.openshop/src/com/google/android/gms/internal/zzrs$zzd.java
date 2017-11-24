package com.google.android.gms.internal;

import java.util.*;
import com.google.android.gms.tagmanager.*;

public static class zzd
{
    private String zzadc;
    private final List<zze> zzbmj;
    private final Map<String, List<zza>> zzbmk;
    private int zzbml;
    
    private zzd() {
        this.zzbmj = new ArrayList<zze>();
        this.zzbmk = new HashMap<String, List<zza>>();
        this.zzadc = "";
        this.zzbml = 0;
    }
    
    public zzc zzHN() {
        return new zzc((List)this.zzbmj, (Map)this.zzbmk, this.zzadc, this.zzbml);
    }
    
    public zzd zzb(final zze zze) {
        this.zzbmj.add(zze);
        return this;
    }
    
    public zzd zzc(final zza zza) {
        final String zzg = zzdf.zzg(zza.zzHI().get(zzae.zzfR.toString()));
        List<zza> list = this.zzbmk.get(zzg);
        if (list == null) {
            list = new ArrayList<zza>();
            this.zzbmk.put(zzg, list);
        }
        list.add(zza);
        return this;
    }
    
    public zzd zzgD(final String zzadc) {
        this.zzadc = zzadc;
        return this;
    }
    
    public zzd zzko(final int zzbml) {
        this.zzbml = zzbml;
        return this;
    }
}
