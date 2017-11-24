package com.google.android.gms.common.api.internal;

import java.lang.ref.*;
import com.google.android.gms.signin.internal.*;
import android.support.annotation.*;

private static class zzd extends zzb
{
    private final WeakReference<zzh> zzahD;
    
    zzd(final zzh zzh) {
        this.zzahD = new WeakReference<zzh>(zzh);
    }
    
    @BinderThread
    @Override
    public void zzb(final SignInResponse signInResponse) {
        final zzh zzh = this.zzahD.get();
        if (zzh == null) {
            return;
        }
        com.google.android.gms.common.api.internal.zzh.zzd(zzh).zza((zzl.zza)new zzl.zza(zzh) {
            public void zzpt() {
                zzh.zza(zzh, signInResponse);
            }
        });
    }
}
