package com.google.android.gms.measurement.internal;

import android.support.annotation.*;
import java.util.*;
import java.net.*;
import com.google.android.gms.common.internal.*;

@WorkerThread
private class zzc implements Runnable
{
    private final String zzTJ;
    private final byte[] zzaWS;
    private final zza zzaWT;
    private final Map<String, String> zzaWU;
    private final URL zzzq;
    
    public zzc(final String zzTJ, final URL zzzq, final byte[] zzaWS, final Map<String, String> zzaWU, final zza zzaWT) {
        zzx.zzcM(zzTJ);
        zzx.zzz(zzzq);
        zzx.zzz(zzaWT);
        this.zzzq = zzzq;
        this.zzaWS = zzaWS;
        this.zzaWT = zzaWT;
        this.zzTJ = zzTJ;
        this.zzaWU = zzaWU;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //     4: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzCd:()V
        //     7: iconst_0       
        //     8: istore_1       
        //     9: aload_0        
        //    10: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //    13: aload_0        
        //    14: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzzq:Ljava/net/URL;
        //    17: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzc:(Ljava/net/URL;)Ljava/net/HttpURLConnection;
        //    20: astore          11
        //    22: aload           11
        //    24: astore          9
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWU:Ljava/util/Map;
        //    30: astore          13
        //    32: iconst_0       
        //    33: istore_1       
        //    34: aload           13
        //    36: ifnull          170
        //    39: aload_0        
        //    40: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWU:Ljava/util/Map;
        //    43: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    48: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    53: astore          14
        //    55: aload           14
        //    57: invokeinterface java/util/Iterator.hasNext:()Z
        //    62: istore          15
        //    64: iconst_0       
        //    65: istore_1       
        //    66: iload           15
        //    68: ifeq            170
        //    71: aload           14
        //    73: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    78: checkcast       Ljava/util/Map$Entry;
        //    81: astore          16
        //    83: aload           9
        //    85: aload           16
        //    87: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //    92: checkcast       Ljava/lang/String;
        //    95: aload           16
        //    97: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   102: checkcast       Ljava/lang/String;
        //   105: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   108: goto            55
        //   111: astore_2       
        //   112: iload_1        
        //   113: istore          5
        //   115: aconst_null    
        //   116: astore_3       
        //   117: aload           9
        //   119: astore          4
        //   121: aload_3        
        //   122: ifnull          129
        //   125: aload_3        
        //   126: invokevirtual   java/io/OutputStream.close:()V
        //   129: aload           4
        //   131: ifnull          139
        //   134: aload           4
        //   136: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   139: aload_0        
        //   140: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   143: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzCn:()Lcom/google/android/gms/measurement/internal/zzv;
        //   146: new             Lcom/google/android/gms/measurement/internal/zzq$zzb;
        //   149: dup            
        //   150: aload_0        
        //   151: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzTJ:Ljava/lang/String;
        //   154: aload_0        
        //   155: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWT:Lcom/google/android/gms/measurement/internal/zzq$zza;
        //   158: iload           5
        //   160: aload_2        
        //   161: aconst_null    
        //   162: aconst_null    
        //   163: invokespecial   com/google/android/gms/measurement/internal/zzq$zzb.<init>:(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
        //   166: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzg:(Ljava/lang/Runnable;)V
        //   169: return         
        //   170: aload_0        
        //   171: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWS:[B
        //   174: astore          17
        //   176: iconst_0       
        //   177: istore_1       
        //   178: aload           17
        //   180: ifnull          506
        //   183: aload_0        
        //   184: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   187: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzCk:()Lcom/google/android/gms/measurement/internal/zzaj;
        //   190: aload_0        
        //   191: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWS:[B
        //   194: invokevirtual   com/google/android/gms/measurement/internal/zzaj.zzg:([B)[B
        //   197: astore          18
        //   199: aload_0        
        //   200: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   203: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   206: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCK:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   209: ldc             "Uploading data. size"
        //   211: aload           18
        //   213: arraylength    
        //   214: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   217: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   220: aload           9
        //   222: iconst_1       
        //   223: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   226: aload           9
        //   228: ldc             "Content-Encoding"
        //   230: ldc             "gzip"
        //   232: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   235: aload           9
        //   237: aload           18
        //   239: arraylength    
        //   240: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   243: aload           9
        //   245: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   248: aload           9
        //   250: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   253: astore          19
        //   255: aload           19
        //   257: astore_3       
        //   258: aload_3        
        //   259: aload           18
        //   261: invokevirtual   java/io/OutputStream.write:([B)V
        //   264: aload_3        
        //   265: invokevirtual   java/io/OutputStream.close:()V
        //   268: aload           9
        //   270: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   273: istore_1       
        //   274: aload_0        
        //   275: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   278: aload           9
        //   280: invokestatic    com/google/android/gms/measurement/internal/zzq.zza:(Lcom/google/android/gms/measurement/internal/zzq;Ljava/net/HttpURLConnection;)[B
        //   283: astore          21
        //   285: iconst_0       
        //   286: ifeq            293
        //   289: aconst_null    
        //   290: invokevirtual   java/io/OutputStream.close:()V
        //   293: aload           9
        //   295: ifnull          303
        //   298: aload           9
        //   300: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   303: aload_0        
        //   304: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   307: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzCn:()Lcom/google/android/gms/measurement/internal/zzv;
        //   310: new             Lcom/google/android/gms/measurement/internal/zzq$zzb;
        //   313: dup            
        //   314: aload_0        
        //   315: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzTJ:Ljava/lang/String;
        //   318: aload_0        
        //   319: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWT:Lcom/google/android/gms/measurement/internal/zzq$zza;
        //   322: iload_1        
        //   323: aconst_null    
        //   324: aload           21
        //   326: aconst_null    
        //   327: invokespecial   com/google/android/gms/measurement/internal/zzq$zzb.<init>:(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
        //   330: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzg:(Ljava/lang/Runnable;)V
        //   333: return         
        //   334: astore          22
        //   336: aload_0        
        //   337: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   340: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   343: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   346: ldc             "Error closing HTTP compressed POST connection output stream"
        //   348: aload           22
        //   350: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   353: goto            293
        //   356: astore          6
        //   358: aload_0        
        //   359: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   362: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   365: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   368: ldc             "Error closing HTTP compressed POST connection output stream"
        //   370: aload           6
        //   372: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   375: goto            129
        //   378: astore          7
        //   380: aload           7
        //   382: astore          8
        //   384: aconst_null    
        //   385: astore          9
        //   387: aconst_null    
        //   388: astore_3       
        //   389: aload_3        
        //   390: ifnull          397
        //   393: aload_3        
        //   394: invokevirtual   java/io/OutputStream.close:()V
        //   397: aload           9
        //   399: ifnull          407
        //   402: aload           9
        //   404: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   407: aload_0        
        //   408: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   411: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzCn:()Lcom/google/android/gms/measurement/internal/zzv;
        //   414: new             Lcom/google/android/gms/measurement/internal/zzq$zzb;
        //   417: dup            
        //   418: aload_0        
        //   419: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzTJ:Ljava/lang/String;
        //   422: aload_0        
        //   423: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWT:Lcom/google/android/gms/measurement/internal/zzq$zza;
        //   426: iload_1        
        //   427: aconst_null    
        //   428: aconst_null    
        //   429: aconst_null    
        //   430: invokespecial   com/google/android/gms/measurement/internal/zzq$zzb.<init>:(Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzq$zza;ILjava/lang/Throwable;[BLcom/google/android/gms/measurement/internal/zzq$1;)V
        //   433: invokevirtual   com/google/android/gms/measurement/internal/zzv.zzg:(Ljava/lang/Runnable;)V
        //   436: aload           8
        //   438: athrow         
        //   439: astore          10
        //   441: aload_0        
        //   442: getfield        com/google/android/gms/measurement/internal/zzq$zzc.zzaWV:Lcom/google/android/gms/measurement/internal/zzq;
        //   445: invokevirtual   com/google/android/gms/measurement/internal/zzq.zzAo:()Lcom/google/android/gms/measurement/internal/zzp;
        //   448: invokevirtual   com/google/android/gms/measurement/internal/zzp.zzCE:()Lcom/google/android/gms/measurement/internal/zzp$zza;
        //   451: ldc             "Error closing HTTP compressed POST connection output stream"
        //   453: aload           10
        //   455: invokevirtual   com/google/android/gms/measurement/internal/zzp$zza.zzj:(Ljava/lang/String;Ljava/lang/Object;)V
        //   458: goto            397
        //   461: astore          12
        //   463: aload           12
        //   465: astore          8
        //   467: aconst_null    
        //   468: astore_3       
        //   469: goto            389
        //   472: astore          20
        //   474: aload           20
        //   476: astore          8
        //   478: iconst_0       
        //   479: istore_1       
        //   480: goto            389
        //   483: astore_2       
        //   484: aconst_null    
        //   485: astore_3       
        //   486: aconst_null    
        //   487: astore          4
        //   489: iconst_0       
        //   490: istore          5
        //   492: goto            121
        //   495: astore_2       
        //   496: aload           9
        //   498: astore          4
        //   500: iconst_0       
        //   501: istore          5
        //   503: goto            121
        //   506: goto            268
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      22     483    495    Ljava/io/IOException;
        //  9      22     378    389    Any
        //  26     32     111    121    Ljava/io/IOException;
        //  26     32     461    472    Any
        //  39     55     111    121    Ljava/io/IOException;
        //  39     55     461    472    Any
        //  55     64     111    121    Ljava/io/IOException;
        //  55     64     461    472    Any
        //  71     108    111    121    Ljava/io/IOException;
        //  71     108    461    472    Any
        //  125    129    356    378    Ljava/io/IOException;
        //  170    176    111    121    Ljava/io/IOException;
        //  170    176    461    472    Any
        //  183    255    111    121    Ljava/io/IOException;
        //  183    255    461    472    Any
        //  258    268    495    506    Ljava/io/IOException;
        //  258    268    472    483    Any
        //  268    285    111    121    Ljava/io/IOException;
        //  268    285    461    472    Any
        //  289    293    334    356    Ljava/io/IOException;
        //  393    397    439    461    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
