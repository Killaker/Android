package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.os.*;

class SupportMapFragment$zza$1 extends zzo.zza {
    final /* synthetic */ OnMapReadyCallback zzaSf;
    
    public void zza(final IGoogleMapDelegate googleMapDelegate) throws RemoteException {
        this.zzaSf.onMapReady(new GoogleMap(googleMapDelegate));
    }
}