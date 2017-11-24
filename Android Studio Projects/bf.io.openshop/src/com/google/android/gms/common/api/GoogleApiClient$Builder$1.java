package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.*;

class GoogleApiClient$Builder$1 implements Runnable {
    final /* synthetic */ GoogleApiClient zzabL;
    
    @Override
    public void run() {
        if (Builder.zza(Builder.this).isFinishing() || Builder.zza(Builder.this).getSupportFragmentManager().isDestroyed()) {
            return;
        }
        Builder.zza(Builder.this, zzw.zzb(Builder.zza(Builder.this)), this.zzabL);
    }
}