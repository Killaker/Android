package com.google.android.gms.internal;

import com.google.android.gms.signin.internal.*;
import android.content.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;

static final class zzrl$2 extends Api.zza<zzh, zzrl.zza> {
    public zzh zza(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final zzrl.zza zza, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzh(context, looper, false, zzf, zza.zzFF(), connectionCallbacks, onConnectionFailedListener);
    }
}