package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.internal.*;
import android.os.*;
import android.graphics.*;
import com.google.android.gms.common.internal.*;

public final class BitmapDescriptorFactory
{
    public static final float HUE_AZURE = 210.0f;
    public static final float HUE_BLUE = 240.0f;
    public static final float HUE_CYAN = 180.0f;
    public static final float HUE_GREEN = 120.0f;
    public static final float HUE_MAGENTA = 300.0f;
    public static final float HUE_ORANGE = 30.0f;
    public static final float HUE_RED = 0.0f;
    public static final float HUE_ROSE = 330.0f;
    public static final float HUE_VIOLET = 270.0f;
    public static final float HUE_YELLOW = 60.0f;
    private static zza zzaSW;
    
    public static BitmapDescriptor defaultMarker() {
        try {
            return new BitmapDescriptor(zzAi().zzAn());
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor defaultMarker(final float n) {
        try {
            return new BitmapDescriptor(zzAi().zzh(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromAsset(final String s) {
        try {
            return new BitmapDescriptor(zzAi().zzer(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromBitmap(final Bitmap bitmap) {
        try {
            return new BitmapDescriptor(zzAi().zzc(bitmap));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromFile(final String s) {
        try {
            return new BitmapDescriptor(zzAi().zzes(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromPath(final String s) {
        try {
            return new BitmapDescriptor(zzAi().zzet(s));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    public static BitmapDescriptor fromResource(final int n) {
        try {
            return new BitmapDescriptor(zzAi().zziz(n));
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static zza zzAi() {
        return zzx.zzb(BitmapDescriptorFactory.zzaSW, "IBitmapDescriptorFactory is not initialized");
    }
    
    public static void zza(final zza zza) {
        if (BitmapDescriptorFactory.zzaSW != null) {
            return;
        }
        BitmapDescriptorFactory.zzaSW = zzx.zzz(zza);
    }
}
