package com.google.android.gms.common.images;

import java.lang.ref.*;
import android.net.*;
import com.google.android.gms.common.internal.*;
import android.graphics.drawable.*;

public static final class zzc extends zza
{
    private WeakReference<ImageManager.OnImageLoadedListener> zzajY;
    
    public zzc(final ImageManager.OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzb.zzv(onImageLoadedListener);
        this.zzajY = new WeakReference<ImageManager.OnImageLoadedListener>(onImageLoadedListener);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof zzc)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final zzc zzc = (zzc)o;
        final ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzajY.get();
        final ImageManager.OnImageLoadedListener onImageLoadedListener2 = zzc.zzajY.get();
        return onImageLoadedListener2 != null && onImageLoadedListener != null && zzw.equal(onImageLoadedListener2, onImageLoadedListener) && zzw.equal(zzc.zzajO, this.zzajO);
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzajO);
    }
    
    @Override
    protected void zza(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        if (!b2) {
            final ImageManager.OnImageLoadedListener onImageLoadedListener = this.zzajY.get();
            if (onImageLoadedListener != null) {
                onImageLoadedListener.onImageLoaded(this.zzajO.uri, drawable, b3);
            }
        }
    }
}
