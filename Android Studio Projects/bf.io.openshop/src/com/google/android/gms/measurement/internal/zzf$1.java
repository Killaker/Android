package com.google.android.gms.measurement.internal;

import android.os.*;

class zzf$1 implements Runnable {
    @Override
    public void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzf.zza(zzf.this).zzCn().zzg(this);
        }
        else {
            final boolean zzbw = zzf.this.zzbw();
            zzf.zza(zzf.this, 0L);
            if (zzbw && zzf.zzb(zzf.this)) {
                zzf.this.run();
            }
        }
    }
}