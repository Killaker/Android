package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.os.*;

class StreetViewPanoramaView$zza$1 extends zzaa.zza {
    final /* synthetic */ OnStreetViewPanoramaReadyCallback zzaSA;
    
    public void zza(final IStreetViewPanoramaDelegate streetViewPanoramaDelegate) throws RemoteException {
        this.zzaSA.onStreetViewPanoramaReady(new StreetViewPanorama(streetViewPanoramaDelegate));
    }
}