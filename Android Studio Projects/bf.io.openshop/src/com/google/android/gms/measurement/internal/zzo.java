package com.google.android.gms.measurement.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzo extends zzj<zzm>
{
    public zzo(final Context context, final Looper looper, final com.google.android.gms.common.internal.zzf zzf, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 93, zzf, connectionCallbacks, onConnectionFailedListener);
    }
    
    public zzm zzdo(final IBinder binder) {
        return zzm.zza.zzdn(binder);
    }
    
    @Override
    protected String zzgu() {
        return "com.google.android.gms.measurement.START";
    }
    
    @Override
    protected String zzgv() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }
}
