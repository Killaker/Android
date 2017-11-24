package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import java.util.*;

private class zza implements zzb
{
    zzqb.zze zzaYt;
    List<Long> zzaYu;
    long zzaYv;
    List<zzqb.zzb> zzpH;
    
    private long zza(final zzqb.zzb zzb) {
        return zzb.zzbaf / 1000L / 60L / 60L;
    }
    
    boolean isEmpty() {
        return this.zzpH == null || this.zzpH.isEmpty();
    }
    
    @Override
    public boolean zza(final long n, final zzqb.zzb zzb) {
        zzx.zzz(zzb);
        if (this.zzpH == null) {
            this.zzpH = new ArrayList<zzqb.zzb>();
        }
        if (this.zzaYu == null) {
            this.zzaYu = new ArrayList<Long>();
        }
        if (this.zzpH.size() > 0 && this.zza(this.zzpH.get(0)) != this.zza(zzb)) {
            return false;
        }
        final long zzaYv = this.zzaYv + zzb.getSerializedSize();
        if (zzaYv >= zzw.this.zzCp().zzBT()) {
            return false;
        }
        this.zzaYv = zzaYv;
        this.zzpH.add(zzb);
        this.zzaYu.add(n);
        return this.zzpH.size() < zzw.this.zzCp().zzBU();
    }
    
    @Override
    public void zzc(final zzqb.zze zzaYt) {
        zzx.zzz(zzaYt);
        this.zzaYt = zzaYt;
    }
}
