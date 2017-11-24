package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.os.*;

abstract class zzf
{
    private static volatile Handler zzRC;
    private volatile long zzRD;
    private final zzw zzaTV;
    private boolean zzaVI;
    private final Runnable zzx;
    
    zzf(final zzw zzaTV) {
        com.google.android.gms.common.internal.zzx.zzz(zzaTV);
        this.zzaTV = zzaTV;
        this.zzaVI = true;
        this.zzx = new Runnable() {
            @Override
            public void run() {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    zzf.this.zzaTV.zzCn().zzg(this);
                }
                else {
                    final boolean zzbw = zzf.this.zzbw();
                    zzf.this.zzRD = 0L;
                    if (zzbw && zzf.this.zzaVI) {
                        zzf.this.run();
                    }
                }
            }
        };
    }
    
    private Handler getHandler() {
        if (zzf.zzRC != null) {
            return zzf.zzRC;
        }
        synchronized (zzf.class) {
            if (zzf.zzRC == null) {
                zzf.zzRC = new Handler(this.zzaTV.getContext().getMainLooper());
            }
            return zzf.zzRC;
        }
    }
    
    public void cancel() {
        this.zzRD = 0L;
        this.getHandler().removeCallbacks(this.zzx);
    }
    
    public abstract void run();
    
    public boolean zzbw() {
        return this.zzRD != 0L;
    }
    
    public void zzt(final long n) {
        this.cancel();
        if (n >= 0L) {
            this.zzRD = this.zzaTV.zzjl().currentTimeMillis();
            if (!this.getHandler().postDelayed(this.zzx, n)) {
                this.zzaTV.zzAo().zzCE().zzj("Failed to schedule delayed post. time", n);
            }
        }
    }
}
