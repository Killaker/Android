package com.google.android.gms.measurement;

import java.util.*;

class zzg$1 implements Runnable {
    final /* synthetic */ com.google.android.gms.measurement.zzc zzaUA;
    
    @Override
    public void run() {
        this.zzaUA.zzAB().zza(this.zzaUA);
        final Iterator<zzh> iterator = zzg.zza(zzg.this).iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this.zzaUA);
        }
        zzg.zza(zzg.this, this.zzaUA);
    }
}