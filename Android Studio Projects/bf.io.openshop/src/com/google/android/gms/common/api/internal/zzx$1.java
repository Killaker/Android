package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import android.support.annotation.*;

class zzx$1 implements Runnable {
    final /* synthetic */ Result zzaiT;
    
    @WorkerThread
    @Override
    public void run() {
        try {
            zzx.zzd(zzx.this).sendMessage(zzx.zzd(zzx.this).obtainMessage(0, (Object)zzx.zzc(zzx.this).onSuccess(this.zzaiT)));
        }
        catch (RuntimeException ex) {
            zzx.zzd(zzx.this).sendMessage(zzx.zzd(zzx.this).obtainMessage(1, (Object)ex));
        }
        finally {
            zzx.zza(zzx.this, this.zzaiT);
            final GoogleApiClient googleApiClient = (GoogleApiClient)zzx.zze(zzx.this).get();
            if (googleApiClient != null) {
                googleApiClient.zzb(zzx.this);
            }
        }
    }
}