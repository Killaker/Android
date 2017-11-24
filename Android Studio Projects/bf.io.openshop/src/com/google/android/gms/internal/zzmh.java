package com.google.android.gms.internal;

import com.google.android.gms.common.api.*;
import android.os.*;
import com.google.android.gms.common.api.internal.*;

public final class zzmh implements zzmg
{
    @Override
    public PendingResult<Status> zzf(final GoogleApiClient googleApiClient) {
        return googleApiClient.zzb((PendingResult<Status>)new zzmi.zza(googleApiClient) {
            protected void zza(final zzmj zzmj) throws RemoteException {
                zzmj.zzqJ().zza(new zzmh.zza((zza.zzb<Status>)this));
            }
        });
    }
    
    private static class zza extends zzme
    {
        private final com.google.android.gms.common.api.internal.zza.zzb<Status> zzamC;
        
        public zza(final com.google.android.gms.common.api.internal.zza.zzb<Status> zzamC) {
            this.zzamC = zzamC;
        }
        
        @Override
        public void zzcb(final int n) throws RemoteException {
            this.zzamC.zzs(new Status(n));
        }
    }
}
