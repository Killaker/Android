package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.*;
import android.graphics.*;
import android.os.*;

private final class zzd implements Runnable
{
    private final com.google.android.gms.common.images.zza zzajM;
    
    public zzd(final com.google.android.gms.common.images.zza zzajM) {
        this.zzajM = zzajM;
    }
    
    @Override
    public void run() {
        com.google.android.gms.common.internal.zzb.zzcD("LoadImageRunnable must be executed on the main thread");
        final ImageReceiver imageReceiver = ImageManager.zza(ImageManager.this).get(this.zzajM);
        if (imageReceiver != null) {
            ImageManager.zza(ImageManager.this).remove(this.zzajM);
            imageReceiver.zzc(this.zzajM);
        }
        final com.google.android.gms.common.images.zza.zza zzajO = this.zzajM.zzajO;
        if (zzajO.uri == null) {
            this.zzajM.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
            return;
        }
        final Bitmap zza = ImageManager.zza(ImageManager.this, zzajO);
        if (zza != null) {
            this.zzajM.zza(ImageManager.zzb(ImageManager.this), zza, true);
            return;
        }
        final Long n = ImageManager.zzd(ImageManager.this).get(zzajO.uri);
        if (n != null) {
            if (SystemClock.elapsedRealtime() - n < 3600000L) {
                this.zzajM.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
                return;
            }
            ImageManager.zzd(ImageManager.this).remove(zzajO.uri);
        }
        this.zzajM.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this));
        ResultReceiver resultReceiver = ImageManager.zze(ImageManager.this).get(zzajO.uri);
        if (resultReceiver == null) {
            resultReceiver = new ImageReceiver(zzajO.uri);
            ImageManager.zze(ImageManager.this).put(zzajO.uri, resultReceiver);
        }
        ((ImageReceiver)resultReceiver).zzb(this.zzajM);
        if (!(this.zzajM instanceof com.google.android.gms.common.images.zza.zzc)) {
            ImageManager.zza(ImageManager.this).put(this.zzajM, resultReceiver);
        }
        synchronized (ImageManager.zzqk()) {
            if (!ImageManager.zzql().contains(zzajO.uri)) {
                ImageManager.zzql().add(zzajO.uri);
                ((ImageReceiver)resultReceiver).zzqm();
            }
        }
    }
}
