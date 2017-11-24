package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.location.*;
import com.google.android.gms.maps.model.*;
import android.os.*;

class GoogleMap$11$1 implements OnLocationChangedListener {
    final /* synthetic */ zzk zzaRF;
    
    @Override
    public void onLocationChanged(final Location location) {
        try {
            this.zzaRF.zzd(location);
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}