package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.location.*;
import com.google.android.gms.maps.model.*;
import android.os.*;

class GoogleMap$11 extends ILocationSourceDelegate.zza {
    final /* synthetic */ LocationSource zzaRE;
    
    public void activate(final zzk zzk) {
        this.zzaRE.activate((LocationSource.OnLocationChangedListener)new LocationSource.OnLocationChangedListener() {
            @Override
            public void onLocationChanged(final Location location) {
                try {
                    zzk.zzd(location);
                }
                catch (RemoteException ex) {
                    throw new RuntimeRemoteException(ex);
                }
            }
        });
    }
    
    public void deactivate() {
        this.zzaRE.deactivate();
    }
}