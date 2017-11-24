package com.google.android.gms.maps.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.common.*;
import android.util.*;
import android.os.*;

public class zzad
{
    private static Context zzaSU;
    private static zzc zzaSV;
    
    private static Context getRemoteContext(final Context context) {
        if (zzad.zzaSU == null) {
            if (zzAg()) {
                zzad.zzaSU = context.getApplicationContext();
            }
            else {
                zzad.zzaSU = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return zzad.zzaSU;
    }
    
    public static boolean zzAg() {
        return false;
    }
    
    private static Class<?> zzAh() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static <T> T zza(final ClassLoader classLoader, final String s) {
        try {
            return zzd(zzx.zzz(classLoader).loadClass(s));
        }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Unable to find dynamic class " + s);
        }
    }
    
    public static zzc zzaO(final Context context) throws GooglePlayServicesNotAvailableException {
        zzx.zzz(context);
        if (zzad.zzaSV != null) {
            return zzad.zzaSV;
        }
        zzaP(context);
        zzad.zzaSV = zzaQ(context);
        try {
            zzad.zzaSV.zzd(zze.zzC(getRemoteContext(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return zzad.zzaSV;
        }
        catch (RemoteException ex) {
            throw new RuntimeRemoteException(ex);
        }
    }
    
    private static void zzaP(final Context context) throws GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (googlePlayServicesAvailable) {
            default: {
                throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
            }
            case 0: {}
        }
    }
    
    private static zzc zzaQ(final Context context) {
        if (zzAg()) {
            Log.i(zzad.class.getSimpleName(), "Making Creator statically");
            return zzd(zzAh());
        }
        Log.i(zzad.class.getSimpleName(), "Making Creator dynamically");
        return zzc.zza.zzcu(zza(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }
    
    private static <T> T zzd(final Class<?> clazz) {
        try {
            return (T)clazz.newInstance();
        }
        catch (InstantiationException ex) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + clazz.getName());
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalStateException("Unable to call the default constructor of " + clazz.getName());
        }
    }
}
