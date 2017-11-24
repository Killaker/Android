package com.google.android.gms.internal;

import com.google.android.gms.signin.internal.*;
import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;

static final class zzrl$1 extends Api.zza<zzh, zzro> {
    public zzh zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final zzro zzro, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzro zzbgV;
        if (zzro == null) {
            zzbgV = zzro.zzbgV;
        }
        else {
            zzbgV = zzro;
        }
        return new zzh(context, looper, true, zzf, zzbgV, connectionCallbacks, onConnectionFailedListener);
    }
}