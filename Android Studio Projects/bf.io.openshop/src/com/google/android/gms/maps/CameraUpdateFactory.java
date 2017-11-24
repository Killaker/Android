package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.*;
import android.os.*;
import com.google.android.gms.maps.model.*;
import android.graphics.*;
import com.google.android.gms.common.internal.*;

public final class CameraUpdateFactory
{
    private static ICameraUpdateFactoryDelegate zzaRq;
    
    public static CameraUpdate newCameraPosition(final CameraPosition cameraPosition) {
        try {
            return new CameraUpdate(zzzI().newCameraPosition(cameraPosition));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLng(final LatLng latLng) {
        try {
            return new CameraUpdate(zzzI().newLatLng(latLng));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n) {
        try {
            return new CameraUpdate(zzzI().newLatLngBounds(latLngBounds, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngBounds(final LatLngBounds latLngBounds, final int n, final int n2, final int n3) {
        try {
            return new CameraUpdate(zzzI().newLatLngBoundsWithSize(latLngBounds, n, n2, n3));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate newLatLngZoom(final LatLng latLng, final float n) {
        try {
            return new CameraUpdate(zzzI().newLatLngZoom(latLng, n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate scrollBy(final float n, final float n2) {
        try {
            return new CameraUpdate(zzzI().scrollBy(n, n2));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n) {
        try {
            return new CameraUpdate(zzzI().zoomBy(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomBy(final float n, final Point point) {
        try {
            return new CameraUpdate(zzzI().zoomByWithFocus(n, point.x, point.y));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomIn() {
        try {
            return new CameraUpdate(zzzI().zoomIn());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomOut() {
        try {
            return new CameraUpdate(zzzI().zoomOut());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static CameraUpdate zoomTo(final float n) {
        try {
            return new CameraUpdate(zzzI().zoomTo(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static void zza(final ICameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        CameraUpdateFactory.zzaRq = zzx.zzz(cameraUpdateFactoryDelegate);
    }
    
    private static ICameraUpdateFactoryDelegate zzzI() {
        return zzx.zzb(CameraUpdateFactory.zzaRq, "CameraUpdateFactory is not initialized");
    }
}
