package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.maps.model.*;

class GoogleMap$9 extends zzv.zza {
    final /* synthetic */ OnPolylineClickListener zzaRC;
    
    public void zza(final IPolylineDelegate polylineDelegate) {
        this.zzaRC.onPolylineClick(new Polyline(polylineDelegate));
    }
}