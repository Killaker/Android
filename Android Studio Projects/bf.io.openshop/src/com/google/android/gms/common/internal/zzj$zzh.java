package com.google.android.gms.common.internal;

import android.os.*;
import com.google.android.gms.common.*;

protected final class zzh extends zza
{
    public zzh(final int n) {
        super(n, null);
    }
    
    @Override
    protected void zzj(final ConnectionResult connectionResult) {
        zzj.zzb(zzj.this).zza(connectionResult);
        zzj.this.onConnectionFailed(connectionResult);
    }
    
    @Override
    protected boolean zzqL() {
        zzj.zzb(zzj.this).zza(ConnectionResult.zzafB);
        return true;
    }
}
