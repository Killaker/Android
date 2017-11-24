package com.google.android.gms.analytics;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.analytics.internal.*;
import android.content.*;
import android.support.annotation.*;

public final class AnalyticsReceiver extends BroadcastReceiver
{
    static zzrp zzOM;
    static Boolean zzON;
    static Object zzqy;
    
    static {
        AnalyticsReceiver.zzqy = new Object();
    }
    
    public static boolean zzY(final Context context) {
        zzx.zzz(context);
        if (AnalyticsReceiver.zzON != null) {
            return AnalyticsReceiver.zzON;
        }
        final boolean zza = zzam.zza(context, AnalyticsReceiver.class, false);
        AnalyticsReceiver.zzON = zza;
        return zza;
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public void onReceive(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/google/android/gms/analytics/internal/zzf.zzaa:(Landroid/content/Context;)Lcom/google/android/gms/analytics/internal/zzf;
        //     4: astore_3       
        //     5: aload_3        
        //     6: invokevirtual   com/google/android/gms/analytics/internal/zzf.zzjm:()Lcom/google/android/gms/analytics/internal/zzaf;
        //     9: astore          4
        //    11: aload_2        
        //    12: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //    15: astore          5
        //    17: aload_3        
        //    18: invokevirtual   com/google/android/gms/analytics/internal/zzf.zzjn:()Lcom/google/android/gms/analytics/internal/zzr;
        //    21: invokevirtual   com/google/android/gms/analytics/internal/zzr.zzkr:()Z
        //    24: ifeq            96
        //    27: aload           4
        //    29: ldc             "Device AnalyticsReceiver got"
        //    31: aload           5
        //    33: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //    36: ldc             "com.google.android.gms.analytics.ANALYTICS_DISPATCH"
        //    38: aload           5
        //    40: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    43: ifeq            95
        //    46: aload_1        
        //    47: invokestatic    com/google/android/gms/analytics/AnalyticsService.zzZ:(Landroid/content/Context;)Z
        //    50: istore          6
        //    52: new             Landroid/content/Intent;
        //    55: dup            
        //    56: aload_1        
        //    57: ldc             Lcom/google/android/gms/analytics/AnalyticsService;.class
        //    59: invokespecial   android/content/Intent.<init>:(Landroid/content/Context;Ljava/lang/Class;)V
        //    62: astore          7
        //    64: aload           7
        //    66: ldc             "com.google.android.gms.analytics.ANALYTICS_DISPATCH"
        //    68: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    71: pop            
        //    72: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzqy:Ljava/lang/Object;
        //    75: astore          9
        //    77: aload           9
        //    79: monitorenter   
        //    80: aload_1        
        //    81: aload           7
        //    83: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //    86: pop            
        //    87: iload           6
        //    89: ifne            108
        //    92: aload           9
        //    94: monitorexit    
        //    95: return         
        //    96: aload           4
        //    98: ldc             "Local AnalyticsReceiver got"
        //   100: aload           5
        //   102: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   105: goto            36
        //   108: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   111: ifnonnull       135
        //   114: new             Lcom/google/android/gms/internal/zzrp;
        //   117: dup            
        //   118: aload_1        
        //   119: iconst_1       
        //   120: ldc             "Analytics WakeLock"
        //   122: invokespecial   com/google/android/gms/internal/zzrp.<init>:(Landroid/content/Context;ILjava/lang/String;)V
        //   125: putstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   128: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   131: iconst_0       
        //   132: invokevirtual   com/google/android/gms/internal/zzrp.setReferenceCounted:(Z)V
        //   135: getstatic       com/google/android/gms/analytics/AnalyticsReceiver.zzOM:Lcom/google/android/gms/internal/zzrp;
        //   138: ldc2_w          1000
        //   141: invokevirtual   com/google/android/gms/internal/zzrp.acquire:(J)V
        //   144: aload           9
        //   146: monitorexit    
        //   147: return         
        //   148: astore          10
        //   150: aload           9
        //   152: monitorexit    
        //   153: aload           10
        //   155: athrow         
        //   156: astore          12
        //   158: aload           4
        //   160: ldc             "Analytics service at risk of not starting. For more reliable analytics, add the WAKE_LOCK permission to your manifest. See http://goo.gl/8Rd3yj for instructions."
        //   162: invokevirtual   com/google/android/gms/analytics/internal/zzaf.zzbg:(Ljava/lang/String;)V
        //   165: goto            144
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  80     87     148    156    Any
        //  92     95     148    156    Any
        //  108    135    156    168    Ljava/lang/SecurityException;
        //  108    135    148    156    Any
        //  135    144    156    168    Ljava/lang/SecurityException;
        //  135    144    148    156    Any
        //  144    147    148    156    Any
        //  150    153    148    156    Any
        //  158    165    148    156    Any
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
