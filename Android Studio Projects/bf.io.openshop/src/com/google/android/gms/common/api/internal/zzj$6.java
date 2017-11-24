package com.google.android.gms.common.api.internal;

import android.support.v4.app.*;

class zzj$6 implements Runnable {
    final /* synthetic */ FragmentActivity zzaih;
    
    @Override
    public void run() {
        if (this.zzaih.isFinishing() || this.zzaih.getSupportFragmentManager().isDestroyed()) {
            return;
        }
        zzw.zzb(this.zzaih).zzbD(zzj.zze(zzj.this));
    }
}