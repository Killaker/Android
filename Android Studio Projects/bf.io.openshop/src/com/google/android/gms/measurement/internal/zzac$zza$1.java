package com.google.android.gms.measurement.internal;

class zzac$zza$1 implements Runnable {
    final /* synthetic */ zzm zzaYX;
    
    @Override
    public void run() {
        synchronized (zza.this) {
            zza.zza(zza.this, false);
            if (!zza.this.zzaYU.isConnected()) {
                zza.this.zzaYU.zzAo().zzCK().zzfg("Connected to service");
                zzac.zza(zza.this.zzaYU, this.zzaYX);
            }
        }
    }
}