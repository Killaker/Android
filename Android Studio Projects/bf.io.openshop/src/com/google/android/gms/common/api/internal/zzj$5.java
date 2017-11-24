package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import android.support.annotation.*;
import com.google.android.gms.auth.api.signin.internal.*;

class zzj$5 implements ResultCallback<Status> {
    final /* synthetic */ GoogleApiClient zzabL;
    final /* synthetic */ zzv zzaif;
    final /* synthetic */ boolean zzaig;
    
    public void zzp(@NonNull final Status status) {
        zzq.zzaf(zzj.zzd(zzj.this)).zznr();
        if (status.isSuccess() && zzj.this.isConnected()) {
            zzj.this.reconnect();
        }
        this.zzaif.zza(status);
        if (this.zzaig) {
            this.zzabL.disconnect();
        }
    }
}