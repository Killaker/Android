package com.google.android.gms.internal;

import java.util.*;

public static class zze
{
    private final List<zza> zzbmm;
    private final List<zza> zzbmn;
    private final List<zza> zzbmo;
    private final List<zza> zzbmp;
    private final List<zza> zzbmq;
    private final List<zza> zzbmr;
    private final List<String> zzbms;
    private final List<String> zzbmt;
    private final List<String> zzbmu;
    private final List<String> zzbmv;
    
    private zze(final List<zza> list, final List<zza> list2, final List<zza> list3, final List<zza> list4, final List<zza> list5, final List<zza> list6, final List<String> list7, final List<String> list8, final List<String> list9, final List<String> list10) {
        this.zzbmm = Collections.unmodifiableList((List<? extends zza>)list);
        this.zzbmn = Collections.unmodifiableList((List<? extends zza>)list2);
        this.zzbmo = Collections.unmodifiableList((List<? extends zza>)list3);
        this.zzbmp = Collections.unmodifiableList((List<? extends zza>)list4);
        this.zzbmq = Collections.unmodifiableList((List<? extends zza>)list5);
        this.zzbmr = Collections.unmodifiableList((List<? extends zza>)list6);
        this.zzbms = Collections.unmodifiableList((List<? extends String>)list7);
        this.zzbmt = Collections.unmodifiableList((List<? extends String>)list8);
        this.zzbmu = Collections.unmodifiableList((List<? extends String>)list9);
        this.zzbmv = Collections.unmodifiableList((List<? extends String>)list10);
    }
    
    public static zzf zzHO() {
        return new zzf();
    }
    
    @Override
    public String toString() {
        return "Positive predicates: " + this.zzHP() + "  Negative predicates: " + this.zzHQ() + "  Add tags: " + this.zzHR() + "  Remove tags: " + this.zzHS() + "  Add macros: " + this.zzHT() + "  Remove macros: " + this.zzHY();
    }
    
    public List<zza> zzHP() {
        return this.zzbmm;
    }
    
    public List<zza> zzHQ() {
        return this.zzbmn;
    }
    
    public List<zza> zzHR() {
        return this.zzbmo;
    }
    
    public List<zza> zzHS() {
        return this.zzbmp;
    }
    
    public List<zza> zzHT() {
        return this.zzbmq;
    }
    
    public List<String> zzHU() {
        return this.zzbms;
    }
    
    public List<String> zzHV() {
        return this.zzbmt;
    }
    
    public List<String> zzHW() {
        return this.zzbmu;
    }
    
    public List<String> zzHX() {
        return this.zzbmv;
    }
    
    public List<zza> zzHY() {
        return this.zzbmr;
    }
}
