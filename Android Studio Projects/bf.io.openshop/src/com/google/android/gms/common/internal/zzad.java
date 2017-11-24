package com.google.android.gms.common.internal;

import android.content.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class zzad<T extends IInterface> extends zzj<T>
{
    private final Api.zzd<T> zzamx;
    
    public zzad(final Context context, final Looper looper, final int n, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final com.google.android.gms.common.internal.zzf zzf, final Api.zzd zzamx) {
        super(context, looper, n, zzf, connectionCallbacks, onConnectionFailedListener);
        this.zzamx = (Api.zzd<T>)zzamx;
    }
    
    @Override
    protected T zzW(final IBinder binder) {
        return this.zzamx.zzW(binder);
    }
    
    protected void zzc(final int n, final T t) {
        this.zzamx.zza(n, t);
    }
    
    @Override
    protected String zzgu() {
        return this.zzamx.zzgu();
    }
    
    @Override
    protected String zzgv() {
        return this.zzamx.zzgv();
    }
}
