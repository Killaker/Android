package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import com.google.android.gms.maps.model.internal.*;
import com.google.android.gms.maps.model.*;

class GoogleMap$1 extends zzg.zza {
    final /* synthetic */ OnIndoorStateChangeListener zzaRt;
    
    public void onIndoorBuildingFocused() {
        this.zzaRt.onIndoorBuildingFocused();
    }
    
    public void zza(final zzd zzd) {
        this.zzaRt.onIndoorLevelActivated(new IndoorBuilding(zzd));
    }
}