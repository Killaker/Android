package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.graphics.*;
import com.google.android.gms.dynamic.*;
import android.os.*;
import com.google.android.gms.maps.model.*;

public final class Projection
{
    private final IProjectionDelegate zzaSq;
    
    Projection(final IProjectionDelegate zzaSq) {
        this.zzaSq = zzaSq;
    }
    
    public LatLng fromScreenLocation(final Point point) {
        try {
            return this.zzaSq.fromScreenLocation(zze.zzC(point));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public VisibleRegion getVisibleRegion() {
        try {
            return this.zzaSq.getVisibleRegion();
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public Point toScreenLocation(final LatLng latLng) {
        try {
            return zze.zzp(this.zzaSq.toScreenLocation(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
}
