package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.os.*;

abstract class zzt
{
    private static volatile Handler zzRC;
    private final zzf zzQj;
    private volatile long zzRD;
    private boolean zzRE;
    private final Runnable zzx;
    
    zzt(final zzf zzQj) {
        com.google.android.gms.common.internal.zzx.zzz(zzQj);
        this.zzQj = zzQj;
        this.zzx = new Runnable() {
            @Override
            public void run() {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    zzt.this.zzQj.zzjo().zzf(this);
                }
                else {
                    final boolean zzbw = zzt.this.zzbw();
                    zzt.this.zzRD = 0L;
                    if (zzbw && !zzt.this.zzRE) {
                        zzt.this.run();
                    }
                }
            }
        };
    }
    
    private Handler getHandler() {
        if (zzt.zzRC != null) {
            return zzt.zzRC;
        }
        synchronized (zzt.class) {
            if (zzt.zzRC == null) {
                zzt.zzRC = new Handler(this.zzQj.getContext().getMainLooper());
            }
            return zzt.zzRC;
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
    
    public long zzkY() {
        if (this.zzRD == 0L) {
            return 0L;
        }
        return Math.abs(this.zzQj.zzjl().currentTimeMillis() - this.zzRD);
    }
    
    public void zzt(final long n) {
        this.cancel();
        if (n >= 0L) {
            this.zzRD = this.zzQj.zzjl().currentTimeMillis();
            if (!this.getHandler().postDelayed(this.zzx, n)) {
                this.zzQj.zzjm().zze("Failed to schedule delayed post. time", n);
            }
        }
    }
    
    public void zzu(final long n) {
        long n2 = 0L;
        if (this.zzbw()) {
            if (n < n2) {
                this.cancel();
                return;
            }
            final long n3 = n - Math.abs(this.zzQj.zzjl().currentTimeMillis() - this.zzRD);
            if (n3 >= n2) {
                n2 = n3;
            }
            this.getHandler().removeCallbacks(this.zzx);
            if (!this.getHandler().postDelayed(this.zzx, n2)) {
                this.zzQj.zzjm().zze("Failed to adjust delayed post. time", n2);
            }
        }
    }
}
