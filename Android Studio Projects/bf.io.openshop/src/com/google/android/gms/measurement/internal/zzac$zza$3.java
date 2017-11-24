package com.google.android.gms.measurement.internal;

class zzac$zza$3 implements Runnable {
    final /* synthetic */ zzm zzaYZ;
    
    @Override
    public void run() {
        synchronized (zza.this) {
            zza.zza(zza.this, false);
            if (!zza.this.zzaYU.isConnected()) {
                zza.this.zzaYU.zzAo().zzCJ().zzfg("Connected to remote service");
                zzac.zza(zza.this.zzaYU, this.zzaYZ);
            }
        }
    }
}