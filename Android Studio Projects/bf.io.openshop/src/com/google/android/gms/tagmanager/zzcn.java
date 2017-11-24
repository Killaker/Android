package com.google.android.gms.tagmanager;

import android.content.*;
import java.util.concurrent.*;
import org.json.*;
import com.google.android.gms.internal.*;
import android.content.res.*;
import java.io.*;

class zzcn implements zzf
{
    private final Context mContext;
    private final String zzbhM;
    private zzbf<zzrq.zza> zzbkg;
    private final ExecutorService zzbkn;
    
    zzcn(final Context mContext, final String zzbhM) {
        this.mContext = mContext;
        this.zzbhM = zzbhM;
        this.zzbkn = Executors.newSingleThreadExecutor();
    }
    
    private zzrs.zzc zza(final ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzaz.zzgi(byteArrayOutputStream.toString("UTF-8"));
        }
        catch (UnsupportedEncodingException ex) {
            zzbg.zzaI("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        }
        catch (JSONException ex2) {
            zzbg.zzaK("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }
    
    private void zzd(final zzrq.zza zza) throws IllegalArgumentException {
        if (zza.zzju == null && zza.zzbme == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }
    
    private zzrs.zzc zzx(final byte[] array) {
        try {
            final zzrs.zzc zzb = zzrs.zzb(zzaf.zzf.zzc(array));
            if (zzb != null) {
                zzbg.v("The container was successfully loaded from the resource (using binary file)");
            }
            return zzb;
        }
        catch (zzst zzst) {
            zzbg.e("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        }
        catch (zzrs.zzg zzg) {
            zzbg.zzaK("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }
    
    @Override
    public void release() {
        synchronized (this) {
            this.zzbkn.shutdown();
        }
    }
    
    @Override
    public void zzGl() {
        this.zzbkn.execute(new Runnable() {
            @Override
            public void run() {
                zzcn.this.zzHc();
            }
        });
    }
    
    void zzHc() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //     4: ifnonnull       17
        //     7: new             Ljava/lang/IllegalStateException;
        //    10: dup            
        //    11: ldc             "Callback must be set before execute"
        //    13: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //    21: invokeinterface com/google/android/gms/tagmanager/zzbf.zzGk:()V
        //    26: ldc             "Attempting to load resource from disk"
        //    28: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //    31: invokestatic    com/google/android/gms/tagmanager/zzcb.zzGU:()Lcom/google/android/gms/tagmanager/zzcb;
        //    34: invokevirtual   com/google/android/gms/tagmanager/zzcb.zzGV:()Lcom/google/android/gms/tagmanager/zzcb$zza;
        //    37: getstatic       com/google/android/gms/tagmanager/zzcb$zza.zzbjV:Lcom/google/android/gms/tagmanager/zzcb$zza;
        //    40: if_acmpeq       55
        //    43: invokestatic    com/google/android/gms/tagmanager/zzcb.zzGU:()Lcom/google/android/gms/tagmanager/zzcb;
        //    46: invokevirtual   com/google/android/gms/tagmanager/zzcb.zzGV:()Lcom/google/android/gms/tagmanager/zzcb$zza;
        //    49: getstatic       com/google/android/gms/tagmanager/zzcb$zza.zzbjW:Lcom/google/android/gms/tagmanager/zzcb$zza;
        //    52: if_acmpne       84
        //    55: aload_0        
        //    56: getfield        com/google/android/gms/tagmanager/zzcn.zzbhM:Ljava/lang/String;
        //    59: invokestatic    com/google/android/gms/tagmanager/zzcb.zzGU:()Lcom/google/android/gms/tagmanager/zzcb;
        //    62: invokevirtual   com/google/android/gms/tagmanager/zzcb.getContainerId:()Ljava/lang/String;
        //    65: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    68: ifeq            84
        //    71: aload_0        
        //    72: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //    75: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbju:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //    78: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //    83: return         
        //    84: new             Ljava/io/FileInputStream;
        //    87: dup            
        //    88: aload_0        
        //    89: invokevirtual   com/google/android/gms/tagmanager/zzcn.zzHd:()Ljava/io/File;
        //    92: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    95: astore_1       
        //    96: new             Ljava/io/ByteArrayOutputStream;
        //    99: dup            
        //   100: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   103: astore_2       
        //   104: aload_1        
        //   105: aload_2        
        //   106: invokestatic    com/google/android/gms/internal/zzrs.zzb:(Ljava/io/InputStream;Ljava/io/OutputStream;)V
        //   109: aload_2        
        //   110: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   113: invokestatic    com/google/android/gms/internal/zzrq$zza.zzy:([B)Lcom/google/android/gms/internal/zzrq$zza;
        //   116: astore          9
        //   118: aload_0        
        //   119: aload           9
        //   121: invokespecial   com/google/android/gms/tagmanager/zzcn.zzd:(Lcom/google/android/gms/internal/zzrq$zza;)V
        //   124: aload_0        
        //   125: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   128: aload           9
        //   130: invokeinterface com/google/android/gms/tagmanager/zzbf.zzI:(Ljava/lang/Object;)V
        //   135: aload_1        
        //   136: invokevirtual   java/io/FileInputStream.close:()V
        //   139: ldc             "The Disk resource was successfully read."
        //   141: invokestatic    com/google/android/gms/tagmanager/zzbg.v:(Ljava/lang/String;)V
        //   144: return         
        //   145: astore          11
        //   147: ldc             "Failed to find the resource in the disk"
        //   149: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaI:(Ljava/lang/String;)V
        //   152: aload_0        
        //   153: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   156: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbju:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   159: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   164: return         
        //   165: astore          10
        //   167: ldc             "Error closing stream for reading resource from disk"
        //   169: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   172: goto            139
        //   175: astore          7
        //   177: aload_0        
        //   178: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   181: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbjv:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   184: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   189: ldc             "Failed to read the resource from disk"
        //   191: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   194: aload_1        
        //   195: invokevirtual   java/io/FileInputStream.close:()V
        //   198: goto            139
        //   201: astore          8
        //   203: ldc             "Error closing stream for reading resource from disk"
        //   205: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   208: goto            139
        //   211: astore          5
        //   213: aload_0        
        //   214: getfield        com/google/android/gms/tagmanager/zzcn.zzbkg:Lcom/google/android/gms/tagmanager/zzbf;
        //   217: getstatic       com/google/android/gms/tagmanager/zzbf$zza.zzbjv:Lcom/google/android/gms/tagmanager/zzbf$zza;
        //   220: invokeinterface com/google/android/gms/tagmanager/zzbf.zza:(Lcom/google/android/gms/tagmanager/zzbf$zza;)V
        //   225: ldc             "Failed to read the resource from disk. The resource is inconsistent"
        //   227: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   230: aload_1        
        //   231: invokevirtual   java/io/FileInputStream.close:()V
        //   234: goto            139
        //   237: astore          6
        //   239: ldc             "Error closing stream for reading resource from disk"
        //   241: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   244: goto            139
        //   247: astore_3       
        //   248: aload_1        
        //   249: invokevirtual   java/io/FileInputStream.close:()V
        //   252: aload_3        
        //   253: athrow         
        //   254: astore          4
        //   256: ldc             "Error closing stream for reading resource from disk"
        //   258: invokestatic    com/google/android/gms/tagmanager/zzbg.zzaK:(Ljava/lang/String;)V
        //   261: goto            252
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  84     96     145    165    Ljava/io/FileNotFoundException;
        //  96     135    175    211    Ljava/io/IOException;
        //  96     135    211    247    Ljava/lang/IllegalArgumentException;
        //  96     135    247    264    Any
        //  135    139    165    175    Ljava/io/IOException;
        //  177    194    247    264    Any
        //  194    198    201    211    Ljava/io/IOException;
        //  213    230    247    264    Any
        //  230    234    237    247    Ljava/io/IOException;
        //  248    252    254    264    Ljava/io/IOException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    File zzHd() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.zzbhM);
    }
    
    @Override
    public void zza(final zzbf<zzrq.zza> zzbkg) {
        this.zzbkg = zzbkg;
    }
    
    @Override
    public void zzb(final zzrq.zza zza) {
        this.zzbkn.execute(new Runnable() {
            @Override
            public void run() {
                zzcn.this.zzc(zza);
            }
        });
    }
    
    boolean zzc(final zzrq.zza zza) {
        final File zzHd = this.zzHd();
        FileOutputStream fileOutputStream;
        try {
            final FileOutputStream fileOutputStream2;
            fileOutputStream = (fileOutputStream2 = new FileOutputStream(zzHd));
            final zzrq.zza zza2 = zza;
            final byte[] array = zzsu.toByteArray(zza2);
            fileOutputStream2.write(array);
            final FileOutputStream fileOutputStream3 = fileOutputStream;
            fileOutputStream3.close();
            return true;
        }
        catch (FileNotFoundException ex) {
            zzbg.e("Error opening resource file for writing");
            return false;
        }
        try {
            final FileOutputStream fileOutputStream2 = fileOutputStream;
            final zzrq.zza zza2 = zza;
            final byte[] array = zzsu.toByteArray(zza2);
            fileOutputStream2.write(array);
            try {
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                fileOutputStream3.close();
                return true;
            }
            catch (IOException ex2) {
                zzbg.zzaK("error closing stream for writing resource to disk");
                return true;
            }
        }
        catch (IOException ex3) {
            zzbg.zzaK("Error writing resource to disk. Removing resource from disk.");
            zzHd.delete();
            try {
                fileOutputStream.close();
                return false;
            }
            catch (IOException ex4) {
                zzbg.zzaK("error closing stream for writing resource to disk");
                return false;
            }
        }
        finally {
            try {
                fileOutputStream.close();
            }
            catch (IOException ex5) {
                zzbg.zzaK("error closing stream for writing resource to disk");
            }
        }
    }
    
    @Override
    public zzrs.zzc zzke(final int n) {
        Label_0124: {
            InputStream openRawResource;
            try {
                openRawResource = this.mContext.getResources().openRawResource(n);
                zzbg.v("Attempting to load a container from the resource ID " + n + " (" + this.mContext.getResources().getResourceName(n) + ")");
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = openRawResource;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                zzrs.zzb(inputStream, byteArrayOutputStream2);
                final zzcn zzcn = this;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final zzrs.zzc zzc = zzcn.zza(byteArrayOutputStream3);
                final zzrs.zzc zzc3;
                final zzrs.zzc zzc2 = zzc3 = zzc;
                if (zzc3 != null) {
                    final String s = "The container was successfully loaded from the resource (using JSON file format)";
                    zzbg.v(s);
                    return zzc2;
                }
                break Label_0124;
            }
            catch (Resources$NotFoundException ex) {
                zzbg.zzaK("Failed to load the container. No default container resource found with the resource ID " + n);
                return null;
            }
            try {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final InputStream inputStream = openRawResource;
                final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                zzrs.zzb(inputStream, byteArrayOutputStream2);
                final zzcn zzcn = this;
                final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                final zzrs.zzc zzc = zzcn.zza(byteArrayOutputStream3);
                final zzrs.zzc zzc3;
                final zzrs.zzc zzc2 = zzc3 = zzc;
                if (zzc3 != null) {
                    final String s = "The container was successfully loaded from the resource (using JSON file format)";
                    zzbg.v(s);
                    return zzc2;
                }
                return this.zzx(byteArrayOutputStream.toByteArray());
            }
            catch (IOException ex2) {
                zzbg.zzaK("Error reading the default container with resource ID " + n + " (" + this.mContext.getResources().getResourceName(n) + ")");
                return null;
            }
        }
    }
}
