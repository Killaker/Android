package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.*;
import android.os.*;

class TileOverlayOptions$1 implements TileProvider {
    private final zzi zzaTS = TileOverlayOptions.zza(TileOverlayOptions.this);
    
    @Override
    public Tile getTile(final int n, final int n2, final int n3) {
        try {
            return this.zzaTS.getTile(n, n2, n3);
        }
        catch (RemoteException ex) {
            return null;
        }
    }
}