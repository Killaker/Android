package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.maps.model.*;

class GoogleMap$16 extends zzq.zza {
    final /* synthetic */ OnMarkerDragListener zzaRL;
    
    public void zze(final zzf zzf) {
        this.zzaRL.onMarkerDragStart(new Marker(zzf));
    }
    
    public void zzf(final zzf zzf) {
        this.zzaRL.onMarkerDragEnd(new Marker(zzf));
    }
    
    public void zzg(final zzf zzf) {
        this.zzaRL.onMarkerDrag(new Marker(zzf));
    }
}