package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;
import java.util.*;

private static class zzc
{
    private final Map<zzrs.zze, List<zzrs.zza>> zzbkJ;
    private final Map<zzrs.zze, List<zzrs.zza>> zzbkK;
    private final Map<zzrs.zze, List<String>> zzbkL;
    private final Map<zzrs.zze, List<String>> zzbkM;
    private zzrs.zza zzbkN;
    private final Set<zzrs.zze> zzbky;
    
    public zzc() {
        this.zzbky = new HashSet<zzrs.zze>();
        this.zzbkJ = new HashMap<zzrs.zze, List<zzrs.zza>>();
        this.zzbkL = new HashMap<zzrs.zze, List<String>>();
        this.zzbkK = new HashMap<zzrs.zze, List<zzrs.zza>>();
        this.zzbkM = new HashMap<zzrs.zze, List<String>>();
    }
    
    public Set<zzrs.zze> zzHi() {
        return this.zzbky;
    }
    
    public Map<zzrs.zze, List<zzrs.zza>> zzHj() {
        return this.zzbkJ;
    }
    
    public Map<zzrs.zze, List<String>> zzHk() {
        return this.zzbkL;
    }
    
    public Map<zzrs.zze, List<String>> zzHl() {
        return this.zzbkM;
    }
    
    public Map<zzrs.zze, List<zzrs.zza>> zzHm() {
        return this.zzbkK;
    }
    
    public zzrs.zza zzHn() {
        return this.zzbkN;
    }
    
    public void zza(final zzrs.zze zze) {
        this.zzbky.add(zze);
    }
    
    public void zza(final zzrs.zze zze, final zzrs.zza zza) {
        List<zzrs.zza> list = this.zzbkJ.get(zze);
        if (list == null) {
            list = new ArrayList<zzrs.zza>();
            this.zzbkJ.put(zze, list);
        }
        list.add(zza);
    }
    
    public void zza(final zzrs.zze zze, final String s) {
        List<String> list = this.zzbkL.get(zze);
        if (list == null) {
            list = new ArrayList<String>();
            this.zzbkL.put(zze, list);
        }
        list.add(s);
    }
    
    public void zzb(final zzrs.zza zzbkN) {
        this.zzbkN = zzbkN;
    }
    
    public void zzb(final zzrs.zze zze, final zzrs.zza zza) {
        List<zzrs.zza> list = this.zzbkK.get(zze);
        if (list == null) {
            list = new ArrayList<zzrs.zza>();
            this.zzbkK.put(zze, list);
        }
        list.add(zza);
    }
    
    public void zzb(final zzrs.zze zze, final String s) {
        List<String> list = this.zzbkM.get(zze);
        if (list == null) {
            list = new ArrayList<String>();
            this.zzbkM.put(zze, list);
        }
        list.add(s);
    }
}
