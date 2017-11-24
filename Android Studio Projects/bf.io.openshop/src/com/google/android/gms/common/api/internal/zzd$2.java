package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.*;
import android.os.*;
import android.support.annotation.*;

class zzd$2 implements zza {
    @Override
    public void zzc(final int n, final boolean b) {
        zzd.zza(zzd.this).lock();
        try {
            if (zzd.zzc(zzd.this)) {
                zzd.zza(zzd.this, false);
                zzd.zza(zzd.this, n, b);
                return;
            }
            zzd.zza(zzd.this, true);
            zzd.zzf(zzd.this).onConnectionSuspended(n);
        }
        finally {
            zzd.zza(zzd.this).unlock();
        }
    }
    
    @Override
    public void zzd(@NonNull final ConnectionResult connectionResult) {
        zzd.zza(zzd.this).lock();
        try {
            zzd.zzb(zzd.this, connectionResult);
            zzd.zzb(zzd.this);
        }
        finally {
            zzd.zza(zzd.this).unlock();
        }
    }
    
    @Override
    public void zzi(@Nullable final Bundle bundle) {
        zzd.zza(zzd.this).lock();
        try {
            zzd.zzb(zzd.this, ConnectionResult.zzafB);
            zzd.zzb(zzd.this);
        }
        finally {
            zzd.zza(zzd.this).unlock();
        }
    }
}