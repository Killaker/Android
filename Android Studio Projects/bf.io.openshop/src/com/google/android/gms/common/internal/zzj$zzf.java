package com.google.android.gms.common.internal;

import com.google.android.gms.common.*;
import android.support.annotation.*;
import java.util.*;
import com.google.android.gms.common.api.*;

protected class zzf implements GoogleApiClient.zza
{
    @Override
    public void zza(@NonNull final ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            zzj.this.zza(null, zzj.zze(zzj.this));
        }
        else if (zzj.zzf(zzj.this) != null) {
            zzj.zzf(zzj.this).onConnectionFailed(connectionResult);
        }
    }
}
