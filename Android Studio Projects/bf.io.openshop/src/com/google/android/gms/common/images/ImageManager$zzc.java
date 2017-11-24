package com.google.android.gms.common.images;

import android.net.*;
import android.os.*;

private final class zzc implements Runnable
{
    private final Uri mUri;
    private final ParcelFileDescriptor zzajL;
    
    public zzc(final Uri mUri, final ParcelFileDescriptor zzajL) {
        this.mUri = mUri;
        this.zzajL = zzajL;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "LoadBitmapFromDiskRunnable can't be executed in the main thread"
        //     2: invokestatic    com/google/android/gms/common/internal/zzb.zzcE:(Ljava/lang/String;)V
        //     5: aload_0        
        //     6: getfield        com/google/android/gms/common/images/ImageManager$zzc.zzajL:Landroid/os/ParcelFileDescriptor;
        //     9: astore_1       
        //    10: aconst_null    
        //    11: astore_2       
        //    12: iconst_0       
        //    13: istore_3       
        //    14: aload_1        
        //    15: ifnull          40
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/common/images/ImageManager$zzc.zzajL:Landroid/os/ParcelFileDescriptor;
        //    22: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    25: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
        //    28: astore          12
        //    30: aload           12
        //    32: astore_2       
        //    33: aload_0        
        //    34: getfield        com/google/android/gms/common/images/ImageManager$zzc.zzajL:Landroid/os/ParcelFileDescriptor;
        //    37: invokevirtual   android/os/ParcelFileDescriptor.close:()V
        //    40: new             Ljava/util/concurrent/CountDownLatch;
        //    43: dup            
        //    44: iconst_1       
        //    45: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
        //    48: astore          4
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/common/images/ImageManager$zzc.zzajK:Lcom/google/android/gms/common/images/ImageManager;
        //    54: invokestatic    com/google/android/gms/common/images/ImageManager.zzg:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
        //    57: new             Lcom/google/android/gms/common/images/ImageManager$zzf;
        //    60: dup            
        //    61: aload_0        
        //    62: getfield        com/google/android/gms/common/images/ImageManager$zzc.zzajK:Lcom/google/android/gms/common/images/ImageManager;
        //    65: aload_0        
        //    66: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
        //    69: aload_2        
        //    70: iload_3        
        //    71: aload           4
        //    73: invokespecial   com/google/android/gms/common/images/ImageManager$zzf.<init>:(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
        //    76: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //    79: pop            
        //    80: aload           4
        //    82: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
        //    85: return         
        //    86: astore          8
        //    88: ldc             "ImageManager"
        //    90: new             Ljava/lang/StringBuilder;
        //    93: dup            
        //    94: invokespecial   java/lang/StringBuilder.<init>:()V
        //    97: ldc             "OOM while loading bitmap for uri: "
        //    99: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   102: aload_0        
        //   103: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   109: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   112: aload           8
        //   114: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   117: pop            
        //   118: iconst_1       
        //   119: istore_3       
        //   120: aconst_null    
        //   121: astore_2       
        //   122: goto            33
        //   125: astore          10
        //   127: ldc             "ImageManager"
        //   129: ldc             "closed failed"
        //   131: aload           10
        //   133: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   136: pop            
        //   137: goto            40
        //   140: astore          6
        //   142: ldc             "ImageManager"
        //   144: new             Ljava/lang/StringBuilder;
        //   147: dup            
        //   148: invokespecial   java/lang/StringBuilder.<init>:()V
        //   151: ldc             "Latch interrupted while posting "
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: aload_0        
        //   157: getfield        com/google/android/gms/common/images/ImageManager$zzc.mUri:Landroid/net/Uri;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   166: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   169: pop            
        //   170: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  18     30     86     125    Ljava/lang/OutOfMemoryError;
        //  33     40     125    140    Ljava/io/IOException;
        //  80     85     140    171    Ljava/lang/InterruptedException;
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
