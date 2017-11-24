package com.google.android.gms.measurement.internal;

import javax.security.auth.x500.*;
import android.content.*;
import java.io.*;
import java.security.cert.*;
import android.content.pm.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.internal.*;

public class zzn extends zzz
{
    private static final X500Principal zzaWz;
    private String zzSE;
    private String zzSF;
    private String zzaUa;
    private String zzaVd;
    private String zzaVi;
    private long zzaWA;
    
    static {
        zzaWz = new X500Principal("CN=Android Debug,O=Android,C=US");
    }
    
    zzn(final zzw zzw) {
        super(zzw);
    }
    
    String zzBk() {
        this.zzjv();
        return this.zzaVd;
    }
    
    String zzBo() {
        this.zzjv();
        return this.zzaVi;
    }
    
    long zzBp() {
        return this.zzCp().zzBp();
    }
    
    long zzBq() {
        this.zzjv();
        return this.zzaWA;
    }
    
    boolean zzCD() {
        try {
            final PackageInfo packageInfo = this.getContext().getPackageManager().getPackageInfo(this.getContext().getPackageName(), 64);
            if (packageInfo != null && packageInfo.signatures != null && packageInfo.signatures.length > 0) {
                return ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(packageInfo.signatures[0].toByteArray()))).getSubjectX500Principal().equals(zzn.zzaWz);
            }
            goto Label_0098;
        }
        catch (CertificateException ex) {
            this.zzAo().zzCE().zzj("Error obtaining certificate", ex);
        }
        catch (PackageManager$NameNotFoundException ex2) {
            this.zzAo().zzCE().zzj("Package name not found", ex2);
            goto Label_0098;
        }
    }
    
    protected void zzba(final Status status) {
        if (status == null) {
            this.zzAo().zzCE().zzfg("GoogleService failed to initialize (no status)");
            return;
        }
        this.zzAo().zzCE().zze("GoogleService failed to initialize, status", status.getStatusCode(), status.getStatusMessage());
    }
    
    AppMetadata zzfe(final String s) {
        return new AppMetadata(this.zzwK(), this.zzBk(), this.zzli(), this.zzBo(), this.zzBp(), this.zzBq(), s, this.zzCo().zzAr(), !this.zzCo().zzaXx);
    }
    
    @Override
    protected void zziJ() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "Unknown"
        //     2: astore_1       
        //     3: ldc             "Unknown"
        //     5: astore_2       
        //     6: aload_0        
        //     7: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //    10: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    13: astore_3       
        //    14: aload_0        
        //    15: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //    18: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    21: astore          4
        //    23: aload_3        
        //    24: aload           4
        //    26: invokevirtual   android/content/pm/PackageManager.getInstallerPackageName:(Ljava/lang/String;)Ljava/lang/String;
        //    29: astore          5
        //    31: aload           5
        //    33: ifnonnull       293
        //    36: ldc             "manual_install"
        //    38: astore          5
        //    40: aload_3        
        //    41: aload_0        
        //    42: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //    45: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    48: iconst_0       
        //    49: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    52: astore          15
        //    54: aload           15
        //    56: ifnull          92
        //    59: aload_3        
        //    60: aload           15
        //    62: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //    65: invokevirtual   android/content/pm/PackageManager.getApplicationLabel:(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
        //    68: astore          16
        //    70: aload           16
        //    72: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    75: ifne            86
        //    78: aload           16
        //    80: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //    85: astore_2       
        //    86: aload           15
        //    88: getfield        android/content/pm/PackageInfo.versionName:Ljava/lang/String;
        //    91: astore_1       
        //    92: aload_0        
        //    93: aload           4
        //    95: putfield        com/google/android/gms/measurement/internal/zzn.zzaUa:Ljava/lang/String;
        //    98: aload_0        
        //    99: aload           5
        //   101: putfield        com/google/android/gms/measurement/internal/zzn.zzaVi:Ljava/lang/String;
        //   104: aload_0        
        //   105: aload_1        
        //   106: putfield        com/google/android/gms/measurement/internal/zzn.zzSF:Ljava/lang/String;
        //   109: aload_0        
        //   110: aload_2        
        //   111: putfield        com/google/android/gms/measurement/internal/zzn.zzSE:Ljava/lang/String;
        //   114: ldc_w           "MD5"
        //   117: invokestatic    com/google/android/gms/measurement/internal/zzaj.zzbv:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   120: astore          7
        //   122: aload           7
        //   124: ifnonnull       331
        //   127: aload_0        
        //   128: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   131: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   134: ldc_w           "Could not get MD5 instance"
        //   137: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   140: aload_0        
        //   141: ldc2_w          -1
        //   144: putfield        com/google/android/gms/measurement/internal/zzn.zzaWA:J
        //   147: aload_0        
        //   148: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //   151: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzkr:()Z
        //   154: ifeq            419
        //   157: aload_0        
        //   158: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //   161: ldc_w           "-"
        //   164: iconst_1       
        //   165: invokestatic    com/google/android/gms/measurement/zza.zzb:(Landroid/content/Context;Ljava/lang/String;Z)Lcom/google/android/gms/common/api/Status;
        //   168: astore          9
        //   170: aload           9
        //   172: ifnull          431
        //   175: aload           9
        //   177: invokevirtual   com/google/android/gms/common/api/Status.isSuccess:()Z
        //   180: ifeq            431
        //   183: iconst_1       
        //   184: istore          10
        //   186: iload           10
        //   188: ifne            197
        //   191: aload_0        
        //   192: aload           9
        //   194: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzba:(Lcom/google/android/gms/common/api/Status;)V
        //   197: iload           10
        //   199: ifeq            453
        //   202: invokestatic    com/google/android/gms/measurement/zza.zzAr:()Z
        //   205: istore          11
        //   207: iload           11
        //   209: ifeq            437
        //   212: aload_0        
        //   213: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   216: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   219: ldc_w           "AppMeasurement enabled"
        //   222: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   225: aload_0        
        //   226: ldc_w           ""
        //   229: putfield        com/google/android/gms/measurement/internal/zzn.zzaVd:Ljava/lang/String;
        //   232: aload_0        
        //   233: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //   236: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzkr:()Z
        //   239: ifne            292
        //   242: invokestatic    com/google/android/gms/measurement/zza.zzAp:()Ljava/lang/String;
        //   245: astore          13
        //   247: aload           13
        //   249: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   252: ifeq            260
        //   255: ldc_w           ""
        //   258: astore          13
        //   260: aload_0        
        //   261: aload           13
        //   263: putfield        com/google/android/gms/measurement/internal/zzn.zzaVd:Ljava/lang/String;
        //   266: iload           11
        //   268: ifeq            292
        //   271: aload_0        
        //   272: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   275: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   278: ldc_w           "App package, google app id"
        //   281: aload_0        
        //   282: getfield        com/google/android/gms/measurement/internal/zzn.zzaUa:Ljava/lang/String;
        //   285: aload_0        
        //   286: getfield        com/google/android/gms/measurement/internal/zzn.zzaVd:Ljava/lang/String;
        //   289: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zze:(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
        //   292: return         
        //   293: ldc_w           "com.android.vending"
        //   296: aload           5
        //   298: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   301: ifeq            40
        //   304: ldc_w           ""
        //   307: astore          5
        //   309: goto            40
        //   312: astore          6
        //   314: aload_0        
        //   315: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   318: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   321: ldc_w           "Error retrieving package info: appName"
        //   324: aload_2        
        //   325: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   328: goto            92
        //   331: aload_0        
        //   332: lconst_0       
        //   333: putfield        com/google/android/gms/measurement/internal/zzn.zzaWA:J
        //   336: aload_0        
        //   337: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzCD:()Z
        //   340: ifne            147
        //   343: aload_3        
        //   344: aload_0        
        //   345: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //   348: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   351: bipush          64
        //   353: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   356: astore          14
        //   358: aload           14
        //   360: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //   363: ifnull          147
        //   366: aload           14
        //   368: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //   371: arraylength    
        //   372: ifle            147
        //   375: aload_0        
        //   376: aload           7
        //   378: aload           14
        //   380: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //   383: iconst_0       
        //   384: aaload         
        //   385: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //   388: invokevirtual   java/security/MessageDigest.digest:([B)[B
        //   391: invokestatic    com/google/android/gms/measurement/internal/zzaj.zzq:([B)J
        //   394: putfield        com/google/android/gms/measurement/internal/zzn.zzaWA:J
        //   397: goto            147
        //   400: astore          8
        //   402: aload_0        
        //   403: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   406: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   409: ldc             "Package name not found"
        //   411: aload           8
        //   413: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   416: goto            147
        //   419: aload_0        
        //   420: invokevirtual   com/google/android/gms/measurement/internal/zzn.getContext:()Landroid/content/Context;
        //   423: invokestatic    com/google/android/gms/measurement/zza.zzaR:(Landroid/content/Context;)Lcom/google/android/gms/common/api/Status;
        //   426: astore          9
        //   428: goto            170
        //   431: iconst_0       
        //   432: istore          10
        //   434: goto            186
        //   437: aload_0        
        //   438: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   441: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCI:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   444: ldc_w           "AppMeasurement disabled with google_app_measurement_enable=0"
        //   447: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   450: goto            225
        //   453: iconst_0       
        //   454: istore          11
        //   456: goto            225
        //   459: astore          12
        //   461: aload_0        
        //   462: invokevirtual   com/google/android/gms/measurement/internal/zzn.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   465: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   468: ldc_w           "getGoogleAppId or isMeasurementEnabled failed with exception"
        //   471: aload           12
        //   473: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   476: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  40     54     312    331    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  59     86     312    331    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  86     92     312    331    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  242    255    459    477    Ljava/lang/IllegalStateException;
        //  260    266    459    477    Ljava/lang/IllegalStateException;
        //  271    292    459    477    Ljava/lang/IllegalStateException;
        //  336    397    400    419    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    String zzli() {
        this.zzjv();
        return this.zzSF;
    }
    
    String zzwK() {
        this.zzjv();
        return this.zzaUa;
    }
}
