package com.google.android.gms.common.images;

import android.graphics.*;
import android.net.*;
import java.util.concurrent.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.common.annotation.*;
import android.content.*;
import android.os.*;

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
                zza2.zza(ImageManager.zzb(ImageManager.this), this.mBitmap, false);
            }
            else {
                ImageManager.zzd(ImageManager.this).put(this.mUri, SystemClock.elapsedRealtime());
                zza2.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), false);
            }
            if (!(zza2 instanceof com.google.android.gms.common.images.zza.zzc)) {
                ImageManager.zza(ImageManager.this).remove(zza2);
            }
        }
    }
    
    @Override
    public void run() {
        com.google.android.gms.common.internal.zzb.zzcD("OnBitmapLoadedRunnable must be executed in the main thread");
        final boolean b = this.mBitmap != null;
        if (ImageManager.zzh(ImageManager.this) != null) {
            if (this.zzajN) {
                ImageManager.zzh(ImageManager.this).evictAll();
                System.gc();
                this.zzajN = false;
                ImageManager.zzg(ImageManager.this).post((Runnable)this);
                return;
            }
            if (b) {
                ImageManager.zzh(ImageManager.this).put(new com.google.android.gms.common.images.zza.zza(this.mUri), this.mBitmap);
            }
        }
        final ImageReceiver imageReceiver = ImageManager.zze(ImageManager.this).remove(this.mUri);
        if (imageReceiver != null) {
            this.zza(imageReceiver, b);
        }
        this.zzpJ.countDown();
        synchronized (ImageManager.zzqk()) {
            ImageManager.zzql().remove(this.mUri);
        }
    }
}
