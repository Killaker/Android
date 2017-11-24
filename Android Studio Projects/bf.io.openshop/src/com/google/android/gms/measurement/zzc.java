package com.google.android.gms.measurement;

import com.google.android.gms.internal.*;
import java.util.*;
import com.google.android.gms.common.internal.*;

public final class zzc
{
    private final zzf zzaUi;
    private boolean zzaUj;
    private long zzaUk;
    private long zzaUl;
    private long zzaUm;
    private long zzaUn;
    private long zzaUo;
    private boolean zzaUp;
    private final Map<Class<? extends zze>, zze> zzaUq;
    private final List<zzi> zzaUr;
    private final zzmq zzqW;
    
    zzc(final zzc zzc) {
        this.zzaUi = zzc.zzaUi;
        this.zzqW = zzc.zzqW;
        this.zzaUk = zzc.zzaUk;
        this.zzaUl = zzc.zzaUl;
        this.zzaUm = zzc.zzaUm;
        this.zzaUn = zzc.zzaUn;
        this.zzaUo = zzc.zzaUo;
        this.zzaUr = new ArrayList<zzi>(zzc.zzaUr);
        this.zzaUq = new HashMap<Class<? extends zze>, zze>(zzc.zzaUq.size());
        for (final Map.Entry<Class<? extends zze>, zze> entry : zzc.zzaUq.entrySet()) {
            final zze zzg = zzg((Class<zze>)entry.getKey());
            entry.getValue().zza(zzg);
            this.zzaUq.put(entry.getKey(), zzg);
        }
    }
    
    zzc(final zzf zzaUi, final zzmq zzqW) {
        zzx.zzz(zzaUi);
        zzx.zzz(zzqW);
        this.zzaUi = zzaUi;
        this.zzqW = zzqW;
        this.zzaUn = 1800000L;
        this.zzaUo = 3024000000L;
        this.zzaUq = new HashMap<Class<? extends zze>, zze>();
        this.zzaUr = new ArrayList<zzi>();
    }
    
    private static <T extends zze> T zzg(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", ex2);
        }
    }
    
    void zzAA() {
        this.zzaUm = this.zzqW.elapsedRealtime();
        if (this.zzaUl != 0L) {
            this.zzaUk = this.zzaUl;
        }
        else {
            this.zzaUk = this.zzqW.currentTimeMillis();
        }
        this.zzaUj = true;
    }
    
    zzf zzAB() {
        return this.zzaUi;
    }
    
    zzg zzAC() {
        return this.zzaUi.zzAC();
    }
    
    boolean zzAD() {
        return this.zzaUp;
    }
    
    void zzAE() {
        this.zzaUp = true;
    }
    
    public zzc zzAu() {
        return new zzc(this);
    }
    
    public Collection<zze> zzAv() {
        return (Collection<zze>)this.zzaUq.values();
    }
    
    public List<zzi> zzAw() {
        return this.zzaUr;
    }
    
    public long zzAx() {
        return this.zzaUk;
    }
    
    public void zzAy() {
        this.zzAC().zze(this);
    }
    
    public boolean zzAz() {
        return this.zzaUj;
    }
    
    public void zzM(final long zzaUl) {
        this.zzaUl = zzaUl;
    }
    
    public void zzb(final zze zze) {
        zzx.zzz(zze);
        final Class<? extends zze> class1 = zze.getClass();
        if (class1.getSuperclass() != zze.class) {
            throw new IllegalArgumentException();
        }
        zze.zza(this.zzf(class1));
    }
    
    public <T extends zze> T zze(final Class<T> clazz) {
        return (T)this.zzaUq.get(clazz);
    }
    
    public <T extends zze> T zzf(final Class<T> clazz) {
        zze zzg = this.zzaUq.get(clazz);
        if (zzg == null) {
            zzg = zzg((Class<zze>)clazz);
            this.zzaUq.put(clazz, zzg);
        }
        return (T)zzg;
    }
}
