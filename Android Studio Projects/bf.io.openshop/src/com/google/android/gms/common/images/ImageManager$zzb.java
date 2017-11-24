package com.google.android.gms.common.images;

import android.support.v4.util.*;
import android.graphics.*;
import android.content.*;
import android.app.*;
import com.google.android.gms.internal.*;
import android.annotation.*;

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
