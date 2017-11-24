package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.common.internal.*;
import android.os.*;
import com.google.android.gms.common.*;
import android.support.annotation.*;

public class zzc implements ConnectionCallbacks, OnConnectionFailedListener
{
    public final Api<?> zzagT;
    private final int zzagU;
    private zzl zzagV;
    
    public zzc(final Api<?> zzagT, final int zzagU) {
        this.zzagT = zzagT;
        this.zzagU = zzagU;
    }
    
    private void zzpi() {
        zzx.zzb(this.zzagV, "Callbacks must be attached to a GoogleApiClient instance before connecting the client.");
    }
    
    @Override
    public void onConnected(@Nullable final Bundle bundle) {
        this.zzpi();
        this.zzagV.onConnected(bundle);
    }
    
    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zzpi();
        this.zzagV.zza(connectionResult, this.zzagT, this.zzagU);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzpi();
        this.zzagV.onConnectionSuspended(n);
    }
    
    public void zza(final zzl zzagV) {
        this.zzagV = zzagV;
    }
}
