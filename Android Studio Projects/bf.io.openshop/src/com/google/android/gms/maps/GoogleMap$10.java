package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.graphics.*;
import android.os.*;
import com.google.android.gms.dynamic.*;

class GoogleMap$10 extends zzab.zza {
    final /* synthetic */ SnapshotReadyCallback zzaRD;
    
    public void onSnapshotReady(final Bitmap bitmap) throws RemoteException {
        this.zzaRD.onSnapshotReady(bitmap);
    }
    
    public void zzr(final zzd zzd) throws RemoteException {
        this.zzaRD.onSnapshotReady(zze.zzp(zzd));
    }
}