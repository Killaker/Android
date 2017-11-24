package com.google.android.gms.common;

import android.util.*;
import android.content.pm.*;

public class zzf
{
    private static final zzf zzafS;
    
    static {
        zzafS = new zzf();
    }
    
    public static zzf zzoO() {
        return zzf.zzafS;
    }
    
    zzd.zza zza(final PackageInfo packageInfo, final zzd.zza... array) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        final zzd.zzb zzb = new zzd.zzb(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(zzb)) {
                return array[i];
            }
        }
        if (Log.isLoggable("GoogleSignatureVerifier", 2)) {
            Log.v("GoogleSignatureVerifier", "Signature not valid.  Found: \n" + Base64.encodeToString(((zzd.zza)zzb).getBytes(), 0));
        }
        return null;
    }
    
    public boolean zza(final PackageInfo packageInfo, final boolean b) {
        if (packageInfo != null && packageInfo.signatures != null) {
            zzd.zza zza;
            if (b) {
                zza = this.zza(packageInfo, zzd.zzd.zzafK);
            }
            else {
                zza = this.zza(packageInfo, zzd.zzd.zzafK[0]);
            }
            if (zza != null) {
                return true;
            }
        }
        return false;
    }
    
    public boolean zza(final PackageManager packageManager, final PackageInfo packageInfo) {
        boolean zza = false;
        if (packageInfo != null) {
            if (zze.zzc(packageManager)) {
                return this.zza(packageInfo, true);
            }
            zza = this.zza(packageInfo, false);
            if (!zza && this.zza(packageInfo, true)) {
                Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
                return zza;
            }
        }
        return zza;
    }
}
