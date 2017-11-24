package com.google.android.gms.gcm;

import com.google.android.gms.measurement.*;
import android.content.*;

class zza
{
    static AppMeasurement zzaLx;
    
    private static void zza(final Context p0, final String p1, final Intent p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Landroid/os/Bundle;
        //     3: dup            
        //     4: invokespecial   android/os/Bundle.<init>:()V
        //     7: astore_3       
        //     8: aload_2        
        //     9: ldc             "google.c.a.c_id"
        //    11: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    14: astore          4
        //    16: aload_2        
        //    17: ldc             "google.c.a.c_l"
        //    19: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    22: astore          5
        //    24: aload           4
        //    26: ifnull          50
        //    29: aload           5
        //    31: ifnull          50
        //    34: aload_3        
        //    35: ldc             "_nmid"
        //    37: aload           4
        //    39: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    42: aload_3        
        //    43: ldc             "_nmn"
        //    45: aload           5
        //    47: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    50: aload_2        
        //    51: ldc             "from"
        //    53: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    56: astore          6
        //    58: aload           6
        //    60: ifnull          196
        //    63: aload           6
        //    65: ldc             "/topics/"
        //    67: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    70: ifeq            196
        //    73: aload           6
        //    75: ifnull          86
        //    78: aload_3        
        //    79: ldc             "_nt"
        //    81: aload           6
        //    83: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    86: aload_3        
        //    87: ldc             "_nmt"
        //    89: aload_2        
        //    90: ldc             "google.c.a.ts"
        //    92: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    95: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //    98: invokevirtual   java/lang/Integer.intValue:()I
        //   101: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   104: aload_2        
        //   105: ldc             "google.c.a.udt"
        //   107: invokevirtual   android/content/Intent.hasExtra:(Ljava/lang/String;)Z
        //   110: ifeq            131
        //   113: aload_3        
        //   114: ldc             "_ndt"
        //   116: aload_2        
        //   117: ldc             "google.c.a.udt"
        //   119: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   122: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //   125: invokevirtual   java/lang/Integer.intValue:()I
        //   128: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   131: ldc             "GcmAnalytics"
        //   133: iconst_3       
        //   134: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   137: ifeq            174
        //   140: ldc             "GcmAnalytics"
        //   142: new             Ljava/lang/StringBuilder;
        //   145: dup            
        //   146: invokespecial   java/lang/StringBuilder.<init>:()V
        //   149: ldc             "Sending event="
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: aload_1        
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: ldc             " params="
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: aload_3        
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   167: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   170: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   173: pop            
        //   174: getstatic       com/google/android/gms/gcm/zza.zzaLx:Lcom/google/android/gms/measurement/AppMeasurement;
        //   177: ifnonnull       228
        //   180: aload_0        
        //   181: invokestatic    com/google/android/gms/measurement/AppMeasurement.getInstance:(Landroid/content/Context;)Lcom/google/android/gms/measurement/AppMeasurement;
        //   184: astore          11
        //   186: aload           11
        //   188: ldc             "gcm"
        //   190: aload_1        
        //   191: aload_3        
        //   192: invokevirtual   com/google/android/gms/measurement/AppMeasurement.zzd:(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V
        //   195: return         
        //   196: aconst_null    
        //   197: astore          6
        //   199: goto            73
        //   202: astore          7
        //   204: ldc             "GcmAnalytics"
        //   206: ldc             "Error while parsing timestamp in GCM event."
        //   208: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop            
        //   212: goto            104
        //   215: astore          13
        //   217: ldc             "GcmAnalytics"
        //   219: ldc             "Error while parsing use_device_time in GCM event."
        //   221: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   224: pop            
        //   225: goto            131
        //   228: getstatic       com/google/android/gms/gcm/zza.zzaLx:Lcom/google/android/gms/measurement/AppMeasurement;
        //   231: astore          11
        //   233: goto            186
        //   236: astore          9
        //   238: ldc             "GcmAnalytics"
        //   240: ldc             "Unable to log event, missing measurement library"
        //   242: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   245: pop            
        //   246: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  86     104    202    215    Ljava/lang/NumberFormatException;
        //  113    131    215    228    Ljava/lang/NumberFormatException;
        //  174    186    236    247    Ljava/lang/NoClassDefFoundError;
        //  186    195    236    247    Ljava/lang/NoClassDefFoundError;
        //  228    233    236    247    Ljava/lang/NoClassDefFoundError;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void zze(final Context context, final Intent intent) {
        zza(context, "_nr", intent);
    }
    
    public static void zzf(final Context context, final Intent intent) {
        zza(context, "_no", intent);
    }
    
    public static void zzg(final Context context, final Intent intent) {
        zza(context, "_nd", intent);
    }
    
    public static void zzh(final Context context, final Intent intent) {
        zza(context, "_nf", intent);
    }
}
