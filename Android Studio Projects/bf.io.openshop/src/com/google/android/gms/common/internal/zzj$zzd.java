package com.google.android.gms.common.internal;

import android.os.*;
import android.support.annotation.*;
import android.util.*;

public static final class zzd extends zzr.zza
{
    private zzj zzalN;
    private final int zzalO;
    
    public zzd(@NonNull final zzj zzalN, final int zzalO) {
        this.zzalN = zzalN;
        this.zzalO = zzalO;
    }
    
    private void zzqP() {
        this.zzalN = null;
    }
    
    @BinderThread
    public void zza(final int n, @NonNull final IBinder binder, @Nullable final Bundle bundle) {
        zzx.zzb(this.zzalN, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzalN.zza(n, binder, bundle, this.zzalO);
        this.zzqP();
    }
    
    @BinderThread
    public void zzb(final int n, @Nullable final Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", (Throwable)new Exception());
    }
}
