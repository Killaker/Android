package com.google.android.gms.analytics.internal;

class zzb$3 implements Runnable {
    final /* synthetic */ String zzQf;
    final /* synthetic */ Runnable zzQg;
    
    @Override
    public void run() {
        zzb.zza(zzb.this).zzbl(this.zzQf);
        if (this.zzQg != null) {
            this.zzQg.run();
        }
    }
}