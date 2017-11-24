package com.google.android.gms.analytics.internal;

import android.os.*;

class zzt$1 implements Runnable {
    @Override
    public void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzt.zza(zzt.this).zzjo().zzf(this);
        }
        else {
            final boolean zzbw = zzt.this.zzbw();
            zzt.zza(zzt.this, 0L);
            if (zzbw && !zzt.zzb(zzt.this)) {
                zzt.this.run();
            }
        }
    }
}