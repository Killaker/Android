package com.google.android.gms.common.api.internal;

import java.lang.ref.*;

static class zzc extends zzn
{
    private WeakReference<zzj> zzail;
    
    zzc(final zzj zzj) {
        this.zzail = new WeakReference<zzj>(zzj);
    }
    
    public void zzpJ() {
        final zzj zzj = this.zzail.get();
        if (zzj == null) {
            return;
        }
        com.google.android.gms.common.api.internal.zzj.zzb(zzj);
    }
}
