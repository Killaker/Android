package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import android.os.*;
import com.google.android.gms.signin.internal.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;

private class zze implements ConnectionCallbacks, OnConnectionFailedListener
{
    @Override
    public void onConnected(final Bundle bundle) {
        zzh.zzf(zzh.this).zza(new zzd(zzh.this));
    }
    
    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        zzh.zzc(zzh.this).lock();
        try {
            if (zzh.zzb(zzh.this, connectionResult)) {
                zzh.zzi(zzh.this);
                zzh.zzj(zzh.this);
            }
            else {
                zzh.zza(zzh.this, connectionResult);
            }
        }
        finally {
            zzh.zzc(zzh.this).unlock();
        }
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
}
