package com.google.android.gms.measurement.internal;

class zzp$1 implements Runnable {
    final /* synthetic */ String zzaWL;
    
    @Override
    public void run() {
        final zzt zzCo = zzp.this.zzaTV.zzCo();
        if (!zzCo.isInitialized() || zzCo.zzDi()) {
            zzp.this.zzl(6, "Persisted config not initialized . Not logging error/warn.");
            return;
        }
        zzCo.zzaXi.zzbq(this.zzaWL);
    }
}