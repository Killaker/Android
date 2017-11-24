package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import java.util.concurrent.atomic.*;
import android.os.*;

class zzj$3 implements ConnectionCallbacks {
    final /* synthetic */ AtomicReference zzaie;
    final /* synthetic */ zzv zzaif;
    
    @Override
    public void onConnected(final Bundle bundle) {
        zzj.zza(zzj.this, this.zzaie.get(), this.zzaif, true);
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
    }
}