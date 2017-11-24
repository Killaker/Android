package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.dynamic.*;

class GoogleMap$3 extends zzd.zza {
    final /* synthetic */ InfoWindowAdapter zzaRw;
    
    public com.google.android.gms.dynamic.zzd zzb(final zzf zzf) {
        return zze.zzC(this.zzaRw.getInfoWindow(new Marker(zzf)));
    }
    
    public com.google.android.gms.dynamic.zzd zzc(final zzf zzf) {
        return zze.zzC(this.zzaRw.getInfoContents(new Marker(zzf)));
    }
}