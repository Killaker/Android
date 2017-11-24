package com.google.android.gms.measurement;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

public abstract class zzf<T extends zzf>
{
    private final zzg zzaUs;
    protected final zzc zzaUt;
    private final List<zzd> zzaUu;
    
    protected zzf(final zzg zzaUs, final zzmq zzmq) {
        zzx.zzz(zzaUs);
        this.zzaUs = zzaUs;
        this.zzaUu = new ArrayList<zzd>();
        final zzc zzaUt = new zzc(this, zzmq);
        zzaUt.zzAE();
        this.zzaUt = zzaUt;
    }
    
    protected zzg zzAC() {
        return this.zzaUs;
    }
    
    public zzc zzAF() {
        return this.zzaUt;
    }
    
    public List<zzi> zzAG() {
        return this.zzaUt.zzAw();
    }
    
    protected void zza(final zzc zzc) {
    }
    
    protected void zzd(final zzc zzc) {
        final Iterator<zzd> iterator = this.zzaUu.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this, zzc);
        }
    }
    
    public zzc zziy() {
        final zzc zzAu = this.zzaUt.zzAu();
        this.zzd(zzAu);
        return zzAu;
    }
}
