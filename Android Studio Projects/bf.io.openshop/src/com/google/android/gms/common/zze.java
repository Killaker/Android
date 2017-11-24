package com.google.android.gms.common;

import java.util.concurrent.atomic.*;
import android.net.*;
import java.io.*;
import android.content.res.*;
import android.util.*;
import android.content.*;
import android.text.*;
import com.google.android.gms.internal.*;
import android.annotation.*;
import android.app.*;
import android.content.pm.*;
import java.util.*;
import android.os.*;

public class zze
{
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 0;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean zzafL;
    public static boolean zzafM;
    static int zzafN;
    private static String zzafO;
    private static Integer zzafP;
    static final AtomicBoolean zzafQ;
    private static final AtomicBoolean zzafR;
    private static final Object zzqy;
    
    static {
        zze.zzafL = false;
        zze.zzafM = false;
        zze.zzafN = -1;
        zzqy = new Object();
        zze.zzafO = null;
        zze.zzafP = null;
        zzafQ = new AtomicBoolean();
        zzafR = new AtomicBoolean();
    }
    
    @Deprecated
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        return zzc.zzoK().getErrorResolutionPendingIntent(context, n, n2);
    }
    
    @Deprecated
    public static String getErrorString(final int n) {
        return ConnectionResult.getStatusString(n);
    }
    
    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        final Uri build = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        String next = null;
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream(build);
            try {
                next = new Scanner(openInputStream).useDelimiter("\\A").next();
                return next;
            }
            catch (NoSuchElementException ex) {}
            finally {
                if (openInputStream != null) {
                    openInputStream.close();
                }
            }
        }
        catch (Exception ex2) {
            next = null;
        }
        return next;
    }
    
    public static Context getRemoteContext(final Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static Resources getRemoteResource(final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/common/internal/zzd.zzakE:Z
        //     3: ifeq            8
        //     6: iconst_0       
        //     7: ireturn        
        //     8: aload_0        
        //     9: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    12: astore_1       
        //    13: aload_0        
        //    14: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    17: getstatic       com/google/android/gms/R$string.common_google_play_services_unknown_issue:I
        //    20: invokevirtual   android/content/res/Resources.getString:(I)Ljava/lang/String;
        //    23: pop            
        //    24: ldc             "com.google.android.gms"
        //    26: aload_0        
        //    27: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    30: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    33: ifne            40
        //    36: aload_0        
        //    37: invokestatic    com/google/android/gms/common/zze.zzan:(Landroid/content/Context;)V
        //    40: aload_1        
        //    41: ldc             "com.google.android.gms"
        //    43: bipush          64
        //    45: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    48: astore          6
        //    50: invokestatic    com/google/android/gms/common/zzf.zzoO:()Lcom/google/android/gms/common/zzf;
        //    53: astore          7
        //    55: aload_0        
        //    56: invokestatic    com/google/android/gms/internal/zzmu.zzaw:(Landroid/content/Context;)Z
        //    59: ifeq            110
        //    62: aload           7
        //    64: aload           6
        //    66: getstatic       com/google/android/gms/common/zzd$zzd.zzafK:[Lcom/google/android/gms/common/zzd$zza;
        //    69: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //    72: ifnonnull       188
        //    75: ldc             "GooglePlayServicesUtil"
        //    77: ldc             "Google Play services signature invalid."
        //    79: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    82: pop            
        //    83: bipush          9
        //    85: ireturn        
        //    86: astore_2       
        //    87: ldc             "GooglePlayServicesUtil"
        //    89: ldc             "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
        //    91: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    94: pop            
        //    95: goto            24
        //    98: astore          4
        //   100: ldc             "GooglePlayServicesUtil"
        //   102: ldc             "Google Play services is missing."
        //   104: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   107: pop            
        //   108: iconst_1       
        //   109: ireturn        
        //   110: aload           7
        //   112: aload_1        
        //   113: ldc             "com.android.vending"
        //   115: sipush          8256
        //   118: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   121: getstatic       com/google/android/gms/common/zzd$zzd.zzafK:[Lcom/google/android/gms/common/zzd$zza;
        //   124: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //   127: astore          10
        //   129: aload           10
        //   131: ifnonnull       158
        //   134: ldc             "GooglePlayServicesUtil"
        //   136: ldc             "Google Play Store signature invalid."
        //   138: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   141: pop            
        //   142: bipush          9
        //   144: ireturn        
        //   145: astore          8
        //   147: ldc             "GooglePlayServicesUtil"
        //   149: ldc             "Google Play Store is neither installed nor updating."
        //   151: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   154: pop            
        //   155: bipush          9
        //   157: ireturn        
        //   158: aload           7
        //   160: aload           6
        //   162: iconst_1       
        //   163: anewarray       Lcom/google/android/gms/common/zzd$zza;
        //   166: dup            
        //   167: iconst_0       
        //   168: aload           10
        //   170: aastore        
        //   171: invokevirtual   com/google/android/gms/common/zzf.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzd$zza;)Lcom/google/android/gms/common/zzd$zza;
        //   174: ifnonnull       188
        //   177: ldc             "GooglePlayServicesUtil"
        //   179: ldc             "Google Play services signature invalid."
        //   181: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   184: pop            
        //   185: bipush          9
        //   187: ireturn        
        //   188: getstatic       com/google/android/gms/common/zze.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   191: invokestatic    com/google/android/gms/internal/zzmx.zzco:(I)I
        //   194: istore          12
        //   196: aload           6
        //   198: getfield        android/content/pm/PackageInfo.versionCode:I
        //   201: invokestatic    com/google/android/gms/internal/zzmx.zzco:(I)I
        //   204: iload           12
        //   206: if_icmpge       253
        //   209: ldc             "GooglePlayServicesUtil"
        //   211: new             Ljava/lang/StringBuilder;
        //   214: dup            
        //   215: invokespecial   java/lang/StringBuilder.<init>:()V
        //   218: ldc_w           "Google Play services out of date.  Requires "
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: getstatic       com/google/android/gms/common/zze.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   227: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   230: ldc_w           " but found "
        //   233: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   236: aload           6
        //   238: getfield        android/content/pm/PackageInfo.versionCode:I
        //   241: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   244: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   247: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   250: pop            
        //   251: iconst_2       
        //   252: ireturn        
        //   253: aload           6
        //   255: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   258: astore          13
        //   260: aload           13
        //   262: ifnonnull       278
        //   265: aload_1        
        //   266: ldc             "com.google.android.gms"
        //   268: iconst_0       
        //   269: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   272: astore          16
        //   274: aload           16
        //   276: astore          13
        //   278: aload           13
        //   280: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   283: ifne            303
        //   286: iconst_3       
        //   287: ireturn        
        //   288: astore          14
        //   290: ldc             "GooglePlayServicesUtil"
        //   292: ldc_w           "Google Play services missing when getting application info."
        //   295: aload           14
        //   297: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   300: pop            
        //   301: iconst_1       
        //   302: ireturn        
        //   303: iconst_0       
        //   304: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  13     24     86     98     Ljava/lang/Throwable;
        //  40     50     98     110    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  110    129    145    158    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  134    142    145    158    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  158    185    145    158    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  265    274    288    303    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Deprecated
    public static boolean isUserRecoverableError(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3:
            case 9: {
                return true;
            }
        }
    }
    
    @Deprecated
    public static void zzad(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = zzc.zzoK().isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent zza = zzc.zzoK().zza(context, googlePlayServicesAvailable, "e");
        Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + googlePlayServicesAvailable);
        if (zza == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", zza);
    }
    
    @Deprecated
    public static int zzaj(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }
    
    @Deprecated
    public static void zzal(final Context context) {
        if (zze.zzafQ.getAndSet(true)) {
            return;
        }
        try {
            ((NotificationManager)context.getSystemService("notification")).cancel(10436);
        }
        catch (SecurityException ex) {}
    }
    
    private static void zzan(final Context context) {
        if (!zze.zzafR.get()) {
            while (true) {
                Integer zzafP = null;
            Label_0178:
                while (true) {
                    synchronized (zze.zzqy) {
                        if (zze.zzafO == null) {
                            zze.zzafO = context.getPackageName();
                            try {
                                final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                                if (metaData != null) {
                                    zze.zzafP = metaData.getInt("com.google.android.gms.version");
                                }
                                else {
                                    zze.zzafP = null;
                                }
                                zzafP = zze.zzafP;
                                // monitorexit(zze.zzqy)
                                if (zzafP == null) {
                                    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                                }
                                break Label_0178;
                            }
                            catch (PackageManager$NameNotFoundException ex) {
                                Log.wtf("GooglePlayServicesUtil", "This should never happen.", (Throwable)ex);
                                continue;
                            }
                            continue;
                        }
                    }
                    if (!zze.zzafO.equals(context.getPackageName())) {
                        throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + zze.zzafO + "' and this call used package '" + context.getPackageName() + "'.");
                    }
                    continue;
                }
                if (zzafP != zze.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + zze.GOOGLE_PLAY_SERVICES_VERSION_CODE + " but" + " found " + zzafP + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
                }
                break;
            }
        }
    }
    
    public static String zzao(final Context context) {
        String s = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            return s;
        }
        s = context.getPackageName();
        final PackageManager packageManager = context.getApplicationContext().getPackageManager();
        while (true) {
            try {
                final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
                if (applicationInfo != null) {
                    s = packageManager.getApplicationLabel(applicationInfo).toString();
                }
                return s;
            }
            catch (PackageManager$NameNotFoundException ex) {
                final ApplicationInfo applicationInfo = null;
                continue;
            }
            break;
        }
    }
    
    public static boolean zzap(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        return zzne.zzsm() && packageManager.hasSystemFeature("cn.google");
    }
    
    @TargetApi(18)
    public static boolean zzaq(final Context context) {
        if (zzne.zzsj()) {
            final Bundle applicationRestrictions = ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }
    
    @TargetApi(19)
    public static boolean zzb(final Context context, final int n, final String s) {
        Label_0031: {
            if (!zzne.zzsk()) {
                break Label_0031;
            }
            final AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService("appops");
            try {
                appOpsManager.checkPackage(n, s);
                boolean b = true;
                Label_0028: {
                    return b;
                }
                // iftrue(Label_0084:, !s.equals((Object)packagesForUid[n2]))
                // iftrue(Label_0028:, packagesForUid == null)
                // iftrue(Label_0028:, s == null)
            Block_4_Outer:
                while (true) {
                    String[] packagesForUid = null;
                    Label_0057: {
                        int n2;
                        while (true) {
                            while (true) {
                                n2 = 0;
                                break Label_0057;
                                return true;
                                b = false;
                                continue Block_4_Outer;
                            }
                            packagesForUid = context.getPackageManager().getPackagesForUid(n);
                            b = false;
                            continue;
                        }
                        Label_0084:
                        ++n2;
                    }
                    final int length = packagesForUid.length;
                    b = false;
                    continue;
                }
            }
            // iftrue(Label_0028:, n2 >= length)
            catch (SecurityException ex) {
                return false;
            }
        }
    }
    
    public static boolean zzb(final PackageManager packageManager) {
        while (true) {
            boolean b = true;
            synchronized (zze.zzqy) {
            Label_0065:
                while (true) {
                    if (zze.zzafN != -1) {
                        break Label_0065;
                    }
                    while (true) {
                        try {
                            if (zzf.zzoO().zza(packageManager.getPackageInfo("com.google.android.gms", 64), zzd.zzd.zzafK[1]) != null) {
                                zze.zzafN = 1;
                            }
                            else {
                                zze.zzafN = 0;
                            }
                            if (zze.zzafN != 0) {
                                return b;
                            }
                            break;
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            zze.zzafN = 0;
                            continue Label_0065;
                        }
                        continue Label_0065;
                    }
                    break;
                }
            }
            b = false;
            return b;
        }
    }
    
    @Deprecated
    public static Intent zzbv(final int n) {
        return zzc.zzoK().zza(null, n, null);
    }
    
    static boolean zzbw(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3:
            case 18:
            case 42: {
                return true;
            }
        }
    }
    
    public static boolean zzc(final PackageManager packageManager) {
        return zzb(packageManager) || !zzoN();
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        return n == 18 || (n == 1 && zzi(context, "com.google.android.gms"));
    }
    
    @Deprecated
    public static boolean zze(final Context context, final int n) {
        return n == 9 && zzi(context, "com.android.vending");
    }
    
    public static boolean zzf(final Context context, final int n) {
        if (zzb(context, n, "com.google.android.gms")) {
            final PackageManager packageManager = context.getPackageManager();
            try {
                return zzf.zzoO().zza(context.getPackageManager(), packageManager.getPackageInfo("com.google.android.gms", 64));
            }
            catch (PackageManager$NameNotFoundException ex) {
                if (Log.isLoggable("GooglePlayServicesUtil", 3)) {
                    Log.d("GooglePlayServicesUtil", "Package manager can't find google play services package, defaulting to false");
                    return false;
                }
            }
        }
        return false;
    }
    
    @TargetApi(21)
    static boolean zzi(final Context context, final String s) {
        if (zzne.zzsm()) {
            final Iterator<PackageInstaller$SessionInfo> iterator = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (iterator.hasNext()) {
                if (s.equals(iterator.next().getAppPackageName())) {
                    return true;
                }
            }
        }
        if (zzaq(context)) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(s, 8192).enabled;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    private static int zzoM() {
        return 8487000;
    }
    
    public static boolean zzoN() {
        if (zze.zzafL) {
            return zze.zzafM;
        }
        return "user".equals(Build.TYPE);
    }
}
