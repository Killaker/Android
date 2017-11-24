package com.google.android.gms.measurement;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.measurement.internal.*;
import android.content.*;
import android.support.annotation.*;

public final class AppMeasurementReceiver extends BroadcastReceiver
{
    static zzrp zzOM;
    static Boolean zzON;
    static final Object zzqy;
    
    static {
        zzqy = new Object();
    }
    
    public static boolean zzY(final Context context) {
        zzx.zzz(context);
        if (AppMeasurementReceiver.zzON != null) {
            return AppMeasurementReceiver.zzON;
        }
        final boolean zza = zzaj.zza(context, AppMeasurementReceiver.class, false);
        AppMeasurementReceiver.zzON = zza;
        return zza;
    }
    
    @MainThread
    public void onReceive(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/measurement/internal/zzw.zzaT:(Landroid/content/Context;)Lcom/google/android/gms/measurement/internal/zzw;
        //     4: astore_3       
        //     5: aload_3        
        //     6: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //     9: astore          4
        //    11: aload_2        
        //    12: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //    15: astore          5
        //    17: aload_3        
        //    18: invokevirtual   com/google/android/gms/measurement/internal/zzw.zzCp:()Lcom/google/android/gms/measurement/internal/zzd;
        //    21: invokevirtual   com/google/android/gms/measurement/internal/zzd.zzkr:()Z
        //    24: ifeq            99
        //    27: aload           4
        //    29: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //    32: ldc             "Device AppMeasurementReceiver got"
        //    34: aload           5
        //    36: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //    39: ldc             "com.google.android.gms.measurement.UPLOAD"
        //    41: aload           5
        //    43: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    46: ifeq            98
        //    49: aload_1        
        //    50: invokestatic    com/google/android/gms/measurement/AppMeasurementService.zzZ:(Landroid/content/Context;)Z
        //    53: istore          6
        //    55: new             Landroid/content/Intent;
        //    58: dup            
        //    59: aload_1        
        //    60: ldc             Lcom/google/android/gms/measurement/AppMeasurementService;.class
        //    62: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //    65: astore          7
        //    67: aload           7
        //    69: ldc             "com.google.android.gms.measurement.UPLOAD"
        //    71: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    74: pop            
        //    75: getstatic       com/google/android/gms/measurement/AppMeasurementReceiver.zzqy:Ljava/lang/Object;
        //    78: astore          9
        //    80: aload           9
        //    82: monitorenter   
        //    83: aload_1        
        //    84: aload           7
        //    86: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //    89: pop            
        //    90: iload           6
        //    92: ifne            114
        //    95: aload           9
        //    97: monitorexit    
        //    98: return         
        //    99: aload           4
        //   101: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   104: ldc             "Local AppMeasurementReceiver got"
        //   106: aload           5
        //   108: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   111: goto            39
        //   114: getstatic       com/google/android/gms/measurement/AppMeasurementReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   117: ifnonnull       141
        //   120: new             Lcom/google/android/gms/internal/zzrp;
        //   123: dup            
        //   124: aload_1        
        //   125: iconst_1       
        //   126: ldc             "AppMeasurement WakeLock"
        //   128: invokespecial   com/google/android/gms/internal/zzrp.<init>:(Landroid/content/Context;ILjava/lang/String;)V
        //   131: putstatic       com/google/android/gms/measurement/AppMeasurementReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   134: getstatic       com/google/android/gms/measurement/AppMeasurementReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   137: iconst_0       
        //   138: invokevirtual   com/google/android/gms/internal/zzrp.setReferenceCounted:(Z)V
        //   141: getstatic       com/google/android/gms/measurement/AppMeasurementReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   144: ldc2_w          1000
        //   147: invokevirtual   com/google/android/gms/internal/zzrp.acquire:(J)V
        //   150: aload           9
        //   152: monitorexit    
        //   153: return         
        //   154: astore          10
        //   156: aload           9
        //   158: monitorexit    
        //   159: aload           10
        //   161: athrow         
        //   162: astore          12
        //   164: aload           4
        //   166: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCF:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   169: ldc             "AppMeasurementService at risk of not starting. For more reliable app measurements, add the WAKE_LOCK permission to your manifest."
        //   171: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzfg:(Ljava/lang/String;)V
        //   174: goto            150
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  83     90     154    162    Any
        //  95     98     154    162    Any
        //  114    141    162    177    Ljava/lang/SecurityException;
        //  114    141    154    162    Any
        //  141    150    162    177    Ljava/lang/SecurityException;
        //  141    150    154    162    Any
        //  150    153    154    162    Any
        //  156    159    154    162    Any
        //  164    174    154    162    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
