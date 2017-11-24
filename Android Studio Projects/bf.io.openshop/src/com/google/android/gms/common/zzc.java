package com.google.android.gms.common;

import android.support.annotation.*;
import android.text.*;
import android.content.pm.*;
import android.app.*;
import android.content.*;
import com.google.android.gms.common.internal.*;

public class zzc
{
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zzc zzafF;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        zzafF = new zzc();
    }
    
    private String zzj(@Nullable final Context context, @Nullable final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("gcore_");
        sb.append(zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        sb.append("-");
        if (!TextUtils.isEmpty((CharSequence)s)) {
            sb.append(s);
        }
        sb.append("-");
        if (context != null) {
            sb.append(context.getPackageName());
        }
        sb.append("-");
        Label_0094: {
            if (context == null) {
                break Label_0094;
            }
            try {
                sb.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                return sb.toString();
            }
            catch (PackageManager$NameNotFoundException ex) {
                return sb.toString();
            }
        }
    }
    
    public static zzc zzoK() {
        return zzc.zzafF;
    }
    
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(final Context context, final int n, final int n2) {
        return this.zza(context, n, n2, null);
    }
    
    public String getErrorString(final int n) {
        return zze.getErrorString(n);
    }
    
    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(final Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }
    
    public int isGooglePlayServicesAvailable(final Context context) {
        int googlePlayServicesAvailable = zze.isGooglePlayServicesAvailable(context);
        if (zze.zzd(context, googlePlayServicesAvailable)) {
            googlePlayServicesAvailable = 18;
        }
        return googlePlayServicesAvailable;
    }
    
    public boolean isUserResolvableError(final int n) {
        return zze.isUserRecoverableError(n);
    }
    
    @Nullable
    public PendingIntent zza(final Context context, final int n, final int n2, @Nullable final String s) {
        final Intent zza = this.zza(context, n, s);
        if (zza == null) {
            return null;
        }
        return PendingIntent.getActivity(context, n2, zza, 268435456);
    }
    
    @Nullable
    public Intent zza(final Context context, final int n, @Nullable final String s) {
        switch (n) {
            default: {
                return null;
            }
            case 1:
            case 2: {
                return zzn.zzx("com.google.android.gms", this.zzj(context, s));
            }
            case 42: {
                return zzn.zzqU();
            }
            case 3: {
                return zzn.zzcJ("com.google.android.gms");
            }
        }
    }
    
    public int zzaj(final Context context) {
        return zze.zzaj(context);
    }
    
    public void zzak(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zze.zzad(context);
    }
    
    public void zzal(final Context context) {
        zze.zzal(context);
    }
    
    @Deprecated
    @Nullable
    public Intent zzbu(final int n) {
        return this.zza(null, n, null);
    }
    
    public boolean zzd(final Context context, final int n) {
        return zze.zzd(context, n);
    }
    
    public boolean zzi(final Context context, final String s) {
        return zze.zzi(context, s);
    }
}
