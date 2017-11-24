package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.common.*;
import android.app.*;
import java.util.*;
import android.support.annotation.*;

private class zzb extends zzf
{
    private final Map<Api.zzb, GoogleApiClient.zza> zzahE;
    
    public zzb(final Map<Api.zzb, GoogleApiClient.zza> zzahE) {
        this.zzahE = zzahE;
    }
    
    @WorkerThread
    public void zzpt() {
        final int googlePlayServicesAvailable = zzh.zzb(zzh.this).isGooglePlayServicesAvailable(zzh.zza(zzh.this));
        if (googlePlayServicesAvailable != 0) {
            zzh.zzd(zzh.this).zza((zzl.zza)new zzl.zza(zzh.this) {
                final /* synthetic */ ConnectionResult zzahF = new ConnectionResult(googlePlayServicesAvailable, null);
                
                public void zzpt() {
                    zzh.zza(zzh.this, this.zzahF);
                }
            });
        }
        else {
            if (zzh.zze(zzh.this)) {
                zzh.zzf(zzh.this).connect();
            }
            for (final Api.zzb zzb : this.zzahE.keySet()) {
                zzb.zza(this.zzahE.get(zzb));
            }
        }
    }
}
