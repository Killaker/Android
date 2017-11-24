package com.google.android.gms.tagmanager;

class zzat$1 implements Runnable {
    final /* synthetic */ zzas zzbjd;
    final /* synthetic */ long zzbje;
    final /* synthetic */ String zzzP;
    
    @Override
    public void run() {
        if (zzat.zza(zzat.this) == null) {
            final zzcu zzHo = zzcu.zzHo();
            zzHo.zza(zzat.zzb(zzat.this), this.zzbjd);
            zzat.zza(zzat.this, zzHo.zzHr());
        }
        zzat.zza(zzat.this).zzg(this.zzbje, this.zzzP);
    }
}