package com.google.android.gms.security;

import java.lang.reflect.*;
import com.google.android.gms.common.internal.*;
import android.util.*;
import com.google.android.gms.common.*;
import android.os.*;
import android.content.*;

public class ProviderInstaller
{
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final zzc zzbgP;
    private static Method zzbgQ;
    private static final Object zzqy;
    
    static {
        zzbgP = zzc.zzoK();
        zzqy = new Object();
        ProviderInstaller.zzbgQ = null;
    }
    
    public static void installIfNeeded(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zzx.zzb(context, "Context must not be null");
        ProviderInstaller.zzbgP.zzak(context);
        final Context remoteContext = zze.getRemoteContext(context);
        if (remoteContext == null) {
            Log.e("ProviderInstaller", "Failed to get remote context");
            throw new GooglePlayServicesNotAvailableException(8);
        }
        final Object zzqy = ProviderInstaller.zzqy;
        // monitorenter(zzqy)
        try {
            if (ProviderInstaller.zzbgQ == null) {
                zzaV(remoteContext);
            }
            ProviderInstaller.zzbgQ.invoke(null, remoteContext);
        }
        catch (Exception ex) {
            Log.e("ProviderInstaller", "Failed to install provider: " + ex.getMessage());
            throw new GooglePlayServicesNotAvailableException(8);
            try {}
            finally {
            }
            // monitorexit(zzqy)
        }
    }
    
    public static void installIfNeededAsync(final Context context, final ProviderInstallListener providerInstallListener) {
        zzx.zzb(context, "Context must not be null");
        zzx.zzb(providerInstallListener, "Listener must not be null");
        zzx.zzcD("Must be called on the UI thread");
        new AsyncTask<Void, Void, Integer>() {
            protected Integer zzc(final Void... array) {
                try {
                    ProviderInstaller.installIfNeeded(context);
                    return 0;
                }
                catch (GooglePlayServicesRepairableException ex) {
                    return ex.getConnectionStatusCode();
                }
                catch (GooglePlayServicesNotAvailableException ex2) {
                    return ex2.errorCode;
                }
            }
            
            protected void zze(final Integer n) {
                if (n == 0) {
                    providerInstallListener.onProviderInstalled();
                    return;
                }
                providerInstallListener.onProviderInstallFailed(n, ProviderInstaller.zzbgP.zza(context, n, "pi"));
            }
        }.execute((Object[])new Void[0]);
    }
    
    private static void zzaV(final Context context) throws ClassNotFoundException, NoSuchMethodException {
        ProviderInstaller.zzbgQ = context.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", Context.class);
    }
    
    public interface ProviderInstallListener
    {
        void onProviderInstallFailed(final int p0, final Intent p1);
        
        void onProviderInstalled();
    }
}
