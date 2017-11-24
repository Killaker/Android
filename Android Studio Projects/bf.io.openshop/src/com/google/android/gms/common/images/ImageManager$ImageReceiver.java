package com.google.android.gms.common.images;

import com.google.android.gms.common.annotation.*;
import android.net.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import android.content.*;
import android.os.*;

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
        ImageManager.zzf(ImageManager.this).execute(new zzc(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
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
        ImageManager.zzb(ImageManager.this).sendBroadcast(intent);
    }
}
