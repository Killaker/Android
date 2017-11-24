package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import java.lang.ref.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;
import android.os.*;
import com.google.android.gms.common.internal.*;

private static class zza implements GoogleApiClient.zza
{
    private final Api<?> zzagT;
    private final int zzagU;
    private final WeakReference<zzh> zzahD;
    
    public zza(final zzh zzh, final Api<?> zzagT, final int zzagU) {
        this.zzahD = new WeakReference<zzh>(zzh);
        this.zzagT = zzagT;
        this.zzagU = zzagU;
    }
    
    @Override
    public void zza(@NonNull final ConnectionResult connectionResult) {
        final zzh zzh = this.zzahD.get();
        if (zzh == null) {
            return;
        }
        final Looper myLooper = Looper.myLooper();
        final Looper looper = com.google.android.gms.common.api.internal.zzh.zzd(zzh).zzagW.getLooper();
        boolean b = false;
        if (myLooper == looper) {
            b = true;
        }
        zzx.zza(b, (Object)"onReportServiceBinding must be called on the GoogleApiClient handler thread");
        com.google.android.gms.common.api.internal.zzh.zzc(zzh).lock();
        try {
            if (!com.google.android.gms.common.api.internal.zzh.zza(zzh, 0)) {
                return;
            }
            if (!connectionResult.isSuccess()) {
                com.google.android.gms.common.api.internal.zzh.zza(zzh, connectionResult, this.zzagT, this.zzagU);
            }
            if (com.google.android.gms.common.api.internal.zzh.zzk(zzh)) {
                com.google.android.gms.common.api.internal.zzh.zzj(zzh);
            }
        }
        finally {
            com.google.android.gms.common.api.internal.zzh.zzc(zzh).unlock();
        }
    }
}
