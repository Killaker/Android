package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.*;
import android.os.*;
import com.google.android.gms.common.*;

public class zzd implements ConnectionCallbacks, OnConnectionFailedListener
{
    private final zzqu.zza zzbdJ;
    private boolean zzbdK;
    private zzf zzbdy;
    
    public zzd(final zzqu.zza zzbdJ) {
        this.zzbdJ = zzbdJ;
        this.zzbdy = null;
        this.zzbdK = true;
    }
    
    @Override
    public void onConnected(final Bundle bundle) {
        this.zzbdy.zzau(false);
        if (this.zzbdK && this.zzbdJ != null) {
            this.zzbdJ.zzES();
        }
        this.zzbdK = false;
    }
    
    @Override
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        this.zzbdy.zzau(true);
        if (this.zzbdK && this.zzbdJ != null) {
            if (connectionResult.hasResolution()) {
                this.zzbdJ.zzc(connectionResult.getResolution());
            }
            else {
                this.zzbdJ.zzET();
            }
        }
        this.zzbdK = false;
    }
    
    @Override
    public void onConnectionSuspended(final int n) {
        this.zzbdy.zzau(true);
    }
    
    public void zza(final zzf zzbdy) {
        this.zzbdy = zzbdy;
    }
    
    public void zzat(final boolean zzbdK) {
        this.zzbdK = zzbdK;
    }
}
