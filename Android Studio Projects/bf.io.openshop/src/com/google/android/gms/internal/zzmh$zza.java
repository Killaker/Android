package com.google.android.gms.internal;

import com.google.android.gms.common.api.*;
import android.os.*;

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
