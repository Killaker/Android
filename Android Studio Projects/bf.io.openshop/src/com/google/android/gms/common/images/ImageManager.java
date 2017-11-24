package com.google.android.gms.common.images;

import android.net.*;
import com.google.android.gms.internal.*;
import android.graphics.*;
import android.annotation.*;
import android.widget.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.annotation.*;
import java.util.*;
import android.graphics.drawable.*;
import android.app.*;
import android.support.v4.util.*;
import android.os.*;
import android.content.*;
import android.content.res.*;
import java.util.concurrent.*;

public final class ImageManager
{
    private static HashSet<Uri> zzajA;
    private static ImageManager zzajB;
    private static ImageManager zzajC;
    private static final Object zzajz;
    private final Context mContext;
    private final Handler mHandler;
    private final ExecutorService zzajD;
    private final zzb zzajE;
    private final zzmd zzajF;
    private final Map<com.google.android.gms.common.images.zza, ImageReceiver> zzajG;
    private final Map<Uri, ImageReceiver> zzajH;
    private final Map<Uri, Long> zzajI;
    
    static {
        zzajz = new Object();
        ImageManager.zzajA = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.zzajD = Executors.newFixedThreadPool(4);
        if (b) {
            this.zzajE = new zzb(this.mContext);
            if (zzne.zzsg()) {
                this.zzqj();
            }
        }
        else {
            this.zzajE = null;
        }
        this.zzajF = new zzmd();
        this.zzajG = new HashMap<com.google.android.gms.common.images.zza, ImageReceiver>();
        this.zzajH = new HashMap<Uri, ImageReceiver>();
        this.zzajI = new HashMap<Uri, Long>();
    }
    
    public static ImageManager create(final Context context) {
        return zzc(context, false);
    }
    
    private Bitmap zza(final com.google.android.gms.common.images.zza.zza zza) {
        if (this.zzajE == null) {
            return null;
        }
        return this.zzajE.get(zza);
    }
    
    public static ImageManager zzc(final Context context, final boolean b) {
        if (b) {
            if (ImageManager.zzajC == null) {
                ImageManager.zzajC = new ImageManager(context, true);
            }
            return ImageManager.zzajC;
        }
        if (ImageManager.zzajB == null) {
            ImageManager.zzajB = new ImageManager(context, false);
        }
        return ImageManager.zzajB;
    }
    
    @TargetApi(14)
    private void zzqj() {
        this.mContext.registerComponentCallbacks((ComponentCallbacks)new zze(this.zzajE));
    }
    
    public void loadImage(final ImageView imageView, final int n) {
        this.zza(new com.google.android.gms.common.images.zza.zzb(imageView, n));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri) {
        this.zza(new com.google.android.gms.common.images.zza.zzb(imageView, uri));
    }
    
    public void loadImage(final ImageView imageView, final Uri uri, final int n) {
        final com.google.android.gms.common.images.zza.zzb zzb = new com.google.android.gms.common.images.zza.zzb(imageView, uri);
        zzb.zzbM(n);
        this.zza(zzb);
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        this.zza(new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri));
    }
    
    public void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int n) {
        final com.google.android.gms.common.images.zza.zzc zzc = new com.google.android.gms.common.images.zza.zzc(onImageLoadedListener, uri);
        zzc.zzbM(n);
        this.zza(zzc);
    }
    
    public void zza(final com.google.android.gms.common.images.zza zza) {
        com.google.android.gms.common.internal.zzb.zzcD("ImageManager.loadImage() must be called in the main thread");
        new zzd(zza).run();
    }
    
    @KeepName
    private final class ImageReceiver extends ResultReceiver
    {
        private final Uri mUri;
        private final ArrayList<com.google.android.gms.common.images.zza> zzajJ;
        
        ImageReceiver(final Uri mUri) {
            super(new Handler(Looper.getMainLooper()));
            this.mUri = mUri;
            this.zzajJ = new ArrayList<com.google.android.gms.common.images.zza>();
        }
        
        public void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.zzajD.execute(new zzc(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
        
        public void zzb(final com.google.android.gms.common.images.zza zza) {
            com.google.android.gms.common.internal.zzb.zzcD("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zzajJ.add(zza);
        }
        
        public void zzc(final com.google.android.gms.common.images.zza zza) {
            com.google.android.gms.common.internal.zzb.zzcD("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zzajJ.remove(zza);
        }
        
        public void zzqm() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }
    
    public interface OnImageLoadedListener
    {
        void onImageLoaded(final Uri p0, final Drawable p1, final boolean p2);
    }
    
    @TargetApi(11)
    private static final class zza
    {
        static int zza(final ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }
    }
    
    private static final class zzb extends LruCache<com.google.android.gms.common.images.zza.zza, Bitmap>
    {
        public zzb(final Context context) {
            super(zzas(context));
        }
        
        @TargetApi(11)
        private static int zzas(final Context context) {
            final ActivityManager activityManager = (ActivityManager)context.getSystemService("activity");
            boolean b;
            if ((0x100000 & context.getApplicationInfo().flags) != 0x0) {
                b = true;
            }
            else {
                b = false;
            }
            int n;
            if (b && zzne.zzsd()) {
                n = zza.zza(activityManager);
            }
            else {
                n = activityManager.getMemoryClass();
            }
            return (int)(0.33f * (n * 1048576));
        }
        
        protected int zza(final com.google.android.gms.common.images.zza.zza zza, final Bitmap bitmap) {
            return bitmap.getHeight() * bitmap.getRowBytes();
        }
        
        protected void zza(final boolean b, final com.google.android.gms.common.images.zza.zza zza, final Bitmap bitmap, final Bitmap bitmap2) {
            super.entryRemoved(b, zza, bitmap, bitmap2);
        }
    }
    
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
    
    private final class zzd implements Runnable
    {
        private final com.google.android.gms.common.images.zza zzajM;
        
        public zzd(final com.google.android.gms.common.images.zza zzajM) {
            this.zzajM = zzajM;
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.zzb.zzcD("LoadImageRunnable must be executed on the main thread");
            final ImageReceiver imageReceiver = ImageManager.this.zzajG.get(this.zzajM);
            if (imageReceiver != null) {
                ImageManager.this.zzajG.remove(this.zzajM);
                imageReceiver.zzc(this.zzajM);
            }
            final com.google.android.gms.common.images.zza.zza zzajO = this.zzajM.zzajO;
            if (zzajO.uri == null) {
                this.zzajM.zza(ImageManager.this.mContext, ImageManager.this.zzajF, true);
                return;
            }
            final Bitmap zza = ImageManager.this.zza(zzajO);
            if (zza != null) {
                this.zzajM.zza(ImageManager.this.mContext, zza, true);
                return;
            }
            final Long n = ImageManager.this.zzajI.get(zzajO.uri);
            if (n != null) {
                if (SystemClock.elapsedRealtime() - n < 3600000L) {
                    this.zzajM.zza(ImageManager.this.mContext, ImageManager.this.zzajF, true);
                    return;
                }
                ImageManager.this.zzajI.remove(zzajO.uri);
            }
            this.zzajM.zza(ImageManager.this.mContext, ImageManager.this.zzajF);
            ResultReceiver resultReceiver = ImageManager.this.zzajH.get(zzajO.uri);
            if (resultReceiver == null) {
                resultReceiver = new ImageReceiver(zzajO.uri);
                ImageManager.this.zzajH.put(zzajO.uri, resultReceiver);
            }
            ((ImageReceiver)resultReceiver).zzb(this.zzajM);
            if (!(this.zzajM instanceof com.google.android.gms.common.images.zza.zzc)) {
                ImageManager.this.zzajG.put(this.zzajM, resultReceiver);
            }
            synchronized (ImageManager.zzajz) {
                if (!ImageManager.zzajA.contains(zzajO.uri)) {
                    ImageManager.zzajA.add(zzajO.uri);
                    ((ImageReceiver)resultReceiver).zzqm();
                }
            }
        }
    }
    
    @TargetApi(14)
    private static final class zze implements ComponentCallbacks2
    {
        private final zzb zzajE;
        
        public zze(final zzb zzajE) {
            this.zzajE = zzajE;
        }
        
        public void onConfigurationChanged(final Configuration configuration) {
        }
        
        public void onLowMemory() {
            this.zzajE.evictAll();
        }
        
        public void onTrimMemory(final int n) {
            if (n >= 60) {
                this.zzajE.evictAll();
            }
            else if (n >= 20) {
                this.zzajE.trimToSize(this.zzajE.size() / 2);
            }
        }
    }
    
    private final class zzf implements Runnable
    {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private boolean zzajN;
        private final CountDownLatch zzpJ;
        
        public zzf(final Uri mUri, final Bitmap mBitmap, final boolean zzajN, final CountDownLatch zzpJ) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.zzajN = zzajN;
            this.zzpJ = zzpJ;
        }
        
        private void zza(final ImageReceiver imageReceiver, final boolean b) {
            final ArrayList zza = imageReceiver.zzajJ;
            for (int size = zza.size(), i = 0; i < size; ++i) {
                final com.google.android.gms.common.images.zza zza2 = zza.get(i);
                if (b) {
                    zza2.zza(ImageManager.this.mContext, this.mBitmap, false);
                }
                else {
                    ImageManager.this.zzajI.put(this.mUri, SystemClock.elapsedRealtime());
                    zza2.zza(ImageManager.this.mContext, ImageManager.this.zzajF, false);
                }
                if (!(zza2 instanceof com.google.android.gms.common.images.zza.zzc)) {
                    ImageManager.this.zzajG.remove(zza2);
                }
            }
        }
        
        @Override
        public void run() {
            com.google.android.gms.common.internal.zzb.zzcD("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            if (ImageManager.this.zzajE != null) {
                if (this.zzajN) {
                    ImageManager.this.zzajE.evictAll();
                    System.gc();
                    this.zzajN = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                    return;
                }
                if (b) {
                    ImageManager.this.zzajE.put(new com.google.android.gms.common.images.zza.zza(this.mUri), this.mBitmap);
                }
            }
            final ImageReceiver imageReceiver = ImageManager.this.zzajH.remove(this.mUri);
            if (imageReceiver != null) {
                this.zza(imageReceiver, b);
            }
            this.zzpJ.countDown();
            synchronized (ImageManager.zzajz) {
                ImageManager.zzajA.remove(this.mUri);
            }
        }
    }
}
