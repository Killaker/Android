package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.*;
import android.support.annotation.*;
import com.google.android.gms.common.api.*;

class zzj$4 implements OnConnectionFailedListener {
    final /* synthetic */ zzv zzaif;
    
    @Override
    public void onConnectionFailed(@NonNull final ConnectionResult connectionResult) {
        this.zzaif.zza(new Status(8));
    }
}