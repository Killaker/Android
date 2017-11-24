package com.google.android.gms.analytics.internal;

import android.content.*;
import com.google.android.gms.common.internal.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.*;

public class zzn extends zzd
{
    private volatile String zzPO;
    private Future<String> zzRr;
    
    protected zzn(final zzf zzf) {
        super(zzf);
    }
    
    private boolean zzh(final Context context, final String s) {
        zzx.zzcM(s);
        zzx.zzcE("ClientId should be saved from worker thread");
        FileOutputStream openFileOutput = null;
        try {
            this.zza("Storing clientId", s);
            openFileOutput = context.openFileOutput("gaClientId", 0);
            openFileOutput.write(s.getBytes());
            final boolean b = true;
            if (openFileOutput == null) {
                return b;
            }
            try {
                openFileOutput.close();
                return b;
            }
            catch (IOException ex) {
                this.zze("Failed to close clientId writing stream", ex);
                return b;
            }
        }
        catch (FileNotFoundException ex2) {
            this.zze("Error creating clientId file", ex2);
            final boolean b = false;
            if (openFileOutput == null) {
                return b;
            }
            try {
                openFileOutput.close();
                return false;
            }
            catch (IOException ex3) {
                this.zze("Failed to close clientId writing stream", ex3);
                return false;
            }
        }
        catch (IOException ex4) {
            this.zze("Error writing to clientId file", ex4);
            final boolean b = false;
            if (openFileOutput == null) {
                return b;
            }
            try {
                openFileOutput.close();
                return false;
            }
            catch (IOException ex5) {
                this.zze("Failed to close clientId writing stream", ex5);
                return false;
            }
        }
        finally {
            Label_0153: {
                if (openFileOutput == null) {
                    break Label_0153;
                }
                try {
                    openFileOutput.close();
                }
                catch (IOException ex6) {
                    this.zze("Failed to close clientId writing stream", ex6);
                }
            }
        }
    }
    
    private String zzkn() {
        String zzko = this.zzko();
        try {
            if (!this.zzh(this.zzjo().getContext(), zzko)) {
                zzko = "0";
            }
            return zzko;
        }
        catch (Exception ex) {
            this.zze("Error saving clientId file", ex);
            return "0";
        }
    }
    
    protected String zzac(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "ClientId should be loaded from worker thread"
        //     2: invokestatic    com/google/android/gms/common/internal/zzx.zzcE:(Ljava/lang/String;)V
        //     5: aload_1        
        //     6: ldc             "gaClientId"
        //     8: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    11: astore          12
        //    13: aload           12
        //    15: astore_3       
        //    16: bipush          36
        //    18: newarray        B
        //    20: astore          14
        //    22: aload_3        
        //    23: aload           14
        //    25: iconst_0       
        //    26: aload           14
        //    28: arraylength    
        //    29: invokevirtual   java/io/FileInputStream.read:([BII)I
        //    32: istore          15
        //    34: aload_3        
        //    35: invokevirtual   java/io/FileInputStream.available:()I
        //    38: ifle            80
        //    41: aload_0        
        //    42: ldc             "clientId file seems corrupted, deleting it."
        //    44: invokevirtual   com/google/android/gms/analytics/internal/zzn.zzbg:(Ljava/lang/String;)V
        //    47: aload_3        
        //    48: invokevirtual   java/io/FileInputStream.close:()V
        //    51: aload_1        
        //    52: ldc             "gaClientId"
        //    54: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    57: pop            
        //    58: aload_3        
        //    59: ifnull          66
        //    62: aload_3        
        //    63: invokevirtual   java/io/FileInputStream.close:()V
        //    66: aconst_null    
        //    67: areturn        
        //    68: astore          21
        //    70: aload_0        
        //    71: ldc             "Failed to close client id reading stream"
        //    73: aload           21
        //    75: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //    78: aconst_null    
        //    79: areturn        
        //    80: iload           15
        //    82: bipush          14
        //    84: if_icmpge       126
        //    87: aload_0        
        //    88: ldc             "clientId file is empty, deleting it."
        //    90: invokevirtual   com/google/android/gms/analytics/internal/zzn.zzbg:(Ljava/lang/String;)V
        //    93: aload_3        
        //    94: invokevirtual   java/io/FileInputStream.close:()V
        //    97: aload_1        
        //    98: ldc             "gaClientId"
        //   100: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   103: pop            
        //   104: aload_3        
        //   105: ifnull          66
        //   108: aload_3        
        //   109: invokevirtual   java/io/FileInputStream.close:()V
        //   112: aconst_null    
        //   113: areturn        
        //   114: astore          19
        //   116: aload_0        
        //   117: ldc             "Failed to close client id reading stream"
        //   119: aload           19
        //   121: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   124: aconst_null    
        //   125: areturn        
        //   126: aload_3        
        //   127: invokevirtual   java/io/FileInputStream.close:()V
        //   130: new             Ljava/lang/String;
        //   133: dup            
        //   134: aload           14
        //   136: iconst_0       
        //   137: iload           15
        //   139: invokespecial   java/lang/String.<init>:([BII)V
        //   142: astore          16
        //   144: aload_0        
        //   145: ldc             "Read client id from disk"
        //   147: aload           16
        //   149: invokevirtual   com/google/android/gms/analytics/internal/zzn.zza:(Ljava/lang/String;Ljava/lang/Object;)V
        //   152: aload_3        
        //   153: ifnull          160
        //   156: aload_3        
        //   157: invokevirtual   java/io/FileInputStream.close:()V
        //   160: aload           16
        //   162: areturn        
        //   163: astore          17
        //   165: aload_0        
        //   166: ldc             "Failed to close client id reading stream"
        //   168: aload           17
        //   170: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   173: goto            160
        //   176: astore          9
        //   178: aconst_null    
        //   179: astore          10
        //   181: aload           10
        //   183: ifnull          66
        //   186: aload           10
        //   188: invokevirtual   java/io/FileInputStream.close:()V
        //   191: aconst_null    
        //   192: areturn        
        //   193: astore          11
        //   195: aload_0        
        //   196: ldc             "Failed to close client id reading stream"
        //   198: aload           11
        //   200: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   203: aconst_null    
        //   204: areturn        
        //   205: astore          6
        //   207: aconst_null    
        //   208: astore_3       
        //   209: aload_0        
        //   210: ldc             "Error reading client id file, deleting it"
        //   212: aload           6
        //   214: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   217: aload_1        
        //   218: ldc             "gaClientId"
        //   220: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //   223: pop            
        //   224: aload_3        
        //   225: ifnull          66
        //   228: aload_3        
        //   229: invokevirtual   java/io/FileInputStream.close:()V
        //   232: aconst_null    
        //   233: areturn        
        //   234: astore          8
        //   236: aload_0        
        //   237: ldc             "Failed to close client id reading stream"
        //   239: aload           8
        //   241: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   244: aconst_null    
        //   245: areturn        
        //   246: astore_2       
        //   247: aconst_null    
        //   248: astore_3       
        //   249: aload_2        
        //   250: astore          4
        //   252: aload_3        
        //   253: ifnull          260
        //   256: aload_3        
        //   257: invokevirtual   java/io/FileInputStream.close:()V
        //   260: aload           4
        //   262: athrow         
        //   263: astore          5
        //   265: aload_0        
        //   266: ldc             "Failed to close client id reading stream"
        //   268: aload           5
        //   270: invokevirtual   com/google/android/gms/analytics/internal/zzn.zze:(Ljava/lang/String;Ljava/lang/Object;)V
        //   273: goto            260
        //   276: astore          4
        //   278: goto            252
        //   281: astore          6
        //   283: goto            209
        //   286: astore          13
        //   288: aload_3        
        //   289: astore          10
        //   291: goto            181
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  5      13     176    181    Ljava/io/FileNotFoundException;
        //  5      13     205    209    Ljava/io/IOException;
        //  5      13     246    252    Any
        //  16     58     286    294    Ljava/io/FileNotFoundException;
        //  16     58     281    286    Ljava/io/IOException;
        //  16     58     276    281    Any
        //  62     66     68     80     Ljava/io/IOException;
        //  87     104    286    294    Ljava/io/FileNotFoundException;
        //  87     104    281    286    Ljava/io/IOException;
        //  87     104    276    281    Any
        //  108    112    114    126    Ljava/io/IOException;
        //  126    152    286    294    Ljava/io/FileNotFoundException;
        //  126    152    281    286    Ljava/io/IOException;
        //  126    152    276    281    Any
        //  156    160    163    176    Ljava/io/IOException;
        //  186    191    193    205    Ljava/io/IOException;
        //  209    224    276    281    Any
        //  228    232    234    246    Ljava/io/IOException;
        //  256    260    263    276    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected void zziJ() {
    }
    
    public String zzkk() {
        this.zzjv();
        // monitorenter(this)
        try {
            if (this.zzPO == null) {
                this.zzRr = this.zzjo().zzc((Callable<String>)new Callable<String>() {
                    public String zzkp() throws Exception {
                        return zzn.this.zzkm();
                    }
                });
            }
            Label_0085: {
                if (this.zzRr == null) {
                    break Label_0085;
                }
                try {
                    this.zzPO = this.zzRr.get();
                    if (this.zzPO == null) {
                        this.zzPO = "0";
                    }
                    this.zza("Loaded clientId", this.zzPO);
                    this.zzRr = null;
                    return this.zzPO;
                }
                catch (InterruptedException ex) {
                    this.zzd("ClientId loading or generation was interrupted", ex);
                    this.zzPO = "0";
                }
                catch (ExecutionException ex2) {
                    this.zze("Failed to load or generate client id", ex2);
                    this.zzPO = "0";
                }
            }
        }
        finally {}
    }
    
    String zzkl() {
        synchronized (this) {
            this.zzPO = null;
            this.zzRr = this.zzjo().zzc((Callable<String>)new Callable<String>() {
                public String zzkp() throws Exception {
                    return zzn.this.zzkn();
                }
            });
            return this.zzkk();
        }
    }
    
    String zzkm() {
        String s = this.zzac(this.zzjo().getContext());
        if (s == null) {
            s = this.zzkn();
        }
        return s;
    }
    
    protected String zzko() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
