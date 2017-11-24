package com.google.android.gms.measurement.internal;

import android.support.annotation.*;
import android.os.*;
import android.content.*;
import com.google.android.gms.internal.*;

public class zzad extends zzz
{
    private Handler mHandler;
    private long zzaZa;
    private final Runnable zzaZb;
    private final zzf zzaZc;
    private final zzf zzaZd;
    
    zzad(final zzw zzw) {
        super(zzw);
        this.zzaZb = new Runnable() {
            @MainThread
            @Override
            public void run() {
                zzad.this.zzCn().zzg(new Runnable() {
                    @Override
                    public void run() {
                        zzad.this.zzDx();
                    }
                });
            }
        };
        this.zzaZc = new zzf(this.zzaTV) {
            @WorkerThread
            @Override
            public void run() {
                zzad.this.zzDy();
            }
        };
        this.zzaZd = new zzf(this.zzaTV) {
            @WorkerThread
            @Override
            public void run() {
                zzad.this.zzDz();
            }
        };
    }
    
    private void zzDv() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }
    
    @WorkerThread
    private void zzDy() {
        this.zzjk();
        this.zzAo().zzCK().zzj("Session started, time", this.zzjl().elapsedRealtime());
        this.zzCo().zzaXu.set(false);
        this.zzCf().zze("auto", "_s", new Bundle());
    }
    
    @WorkerThread
    private void zzDz() {
        this.zzjk();
        final long elapsedRealtime = this.zzjl().elapsedRealtime();
        if (this.zzaZa == 0L) {
            this.zzaZa = elapsedRealtime - 3600000L;
        }
        final long n = this.zzCo().zzaXw.get() + (elapsedRealtime - this.zzaZa);
        this.zzCo().zzaXw.set(n);
        this.zzAo().zzCK().zzj("Recording user engagement, ms", n);
        final Bundle bundle = new Bundle();
        bundle.putLong("_et", n);
        this.zzCf().zze("auto", "_e", bundle);
        this.zzCo().zzaXw.set(0L);
        this.zzaZa = elapsedRealtime;
        this.zzaZd.zzt(Math.max(0L, 3600000L - this.zzCo().zzaXw.get()));
    }
    
    @WorkerThread
    private void zzae(final long zzaZa) {
        this.zzjk();
        this.zzDv();
        this.zzaZc.cancel();
        this.zzaZd.cancel();
        this.zzAo().zzCK().zzj("Activity resumed, time", zzaZa);
        this.zzaZa = zzaZa;
        if (this.zzjl().currentTimeMillis() - this.zzCo().zzaXt.get() > this.zzCo().zzaXv.get()) {
            this.zzCo().zzaXu.set(true);
            this.zzCo().zzaXw.set(0L);
        }
        if (this.zzCo().zzaXu.get()) {
            this.zzaZc.zzt(Math.max(0L, this.zzCo().zzaXs.get() - this.zzCo().zzaXw.get()));
            return;
        }
        this.zzaZd.zzt(Math.max(0L, 3600000L - this.zzCo().zzaXw.get()));
    }
    
    @WorkerThread
    private void zzaf(final long n) {
        this.zzjk();
        this.zzDv();
        this.zzaZc.cancel();
        this.zzaZd.cancel();
        this.zzAo().zzCK().zzj("Activity paused, time", n);
        if (this.zzaZa != 0L) {
            this.zzCo().zzaXw.set(this.zzCo().zzaXw.get() + (n - this.zzaZa));
        }
        this.zzCo().zzaXv.set(this.zzjl().currentTimeMillis());
        synchronized (this) {
            if (!this.zzCo().zzaXu.get()) {
                this.mHandler.postDelayed(this.zzaZb, 1000L);
            }
        }
    }
    
    @MainThread
    protected void zzDu() {
        synchronized (this) {
            this.zzDv();
            this.mHandler.removeCallbacks(this.zzaZb);
            // monitorexit(this)
            this.zzCn().zzg(new Runnable() {
                final /* synthetic */ long zzaZg = zzad.this.zzjl().elapsedRealtime();
                
                @Override
                public void run() {
                    zzad.this.zzae(this.zzaZg);
                }
            });
        }
    }
    
    @MainThread
    protected void zzDw() {
        this.zzCn().zzg(new Runnable() {
            final /* synthetic */ long zzaZg = zzad.this.zzjl().elapsedRealtime();
            
            @Override
            public void run() {
                zzad.this.zzaf(this.zzaZg);
            }
        });
    }
    
    @WorkerThread
    public void zzDx() {
        this.zzjk();
        this.zzAo().zzCJ().zzfg("Application backgrounded. Logging engagement");
        final long value = this.zzCo().zzaXw.get();
        if (value > 0L) {
            final Bundle bundle = new Bundle();
            bundle.putLong("_et", value);
            this.zzCf().zze("auto", "_e", bundle);
            this.zzCo().zzaXw.set(0L);
            return;
        }
        this.zzAo().zzCF().zzj("Not logging non-positive engagement time", value);
    }
    
    @Override
    protected void zziJ() {
    }
}
