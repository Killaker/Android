package com.google.android.gms.analytics.internal;

class zzi$zza$1 implements Runnable {
    final /* synthetic */ zzac zzQO;
    
    @Override
    public void run() {
        if (!zza.this.zzQL.isConnected()) {
            zza.this.zzQL.zzbe("Connected to service after a timeout");
            zzi.zza(zza.this.zzQL, this.zzQO);
        }
    }
}