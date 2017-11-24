package com.google.android.gms.common.images;

import java.lang.ref.*;
import android.widget.*;
import android.net.*;
import android.graphics.drawable.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public static final class zzb extends zza
{
    private WeakReference<ImageView> zzajX;
    
    public zzb(final ImageView imageView, final int n) {
        super(null, n);
        com.google.android.gms.common.internal.zzb.zzv(imageView);
        this.zzajX = new WeakReference<ImageView>(imageView);
    }
    
    public zzb(final ImageView imageView, final Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzb.zzv(imageView);
        this.zzajX = new WeakReference<ImageView>(imageView);
    }
    
    private void zza(final ImageView imageView, final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        boolean b4;
        if (!b2 && !b3) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        Label_0057: {
            if (!b4 || !(imageView instanceof zzmc)) {
                break Label_0057;
            }
            final int zzqp = ((zzmc)imageView).zzqp();
            if (this.zzajQ == 0 || zzqp != this.zzajQ) {
                break Label_0057;
            }
            return;
        }
        final boolean zzb = this.zzb(b, b2);
        Drawable imageDrawable;
        if (this.zzajR && drawable != null) {
            imageDrawable = drawable.getConstantState().newDrawable();
        }
        else {
            imageDrawable = drawable;
        }
        if (zzb) {
            imageDrawable = this.zza(imageView.getDrawable(), imageDrawable);
        }
        imageView.setImageDrawable(imageDrawable);
        if (imageView instanceof zzmc) {
            final zzmc zzmc = (zzmc)imageView;
            Uri uri;
            if (b3) {
                uri = this.zzajO.uri;
            }
            else {
                uri = null;
            }
            zzmc.zzm(uri);
            int zzajQ;
            if (b4) {
                zzajQ = this.zzajQ;
            }
            else {
                zzajQ = 0;
            }
            zzmc.zzbO(zzajQ);
        }
        if (zzb) {
            ((zzma)imageDrawable).startTransition(250);
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof zzb)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        final zzb zzb = (zzb)o;
        final ImageView imageView = this.zzajX.get();
        final ImageView imageView2 = zzb.zzajX.get();
        return imageView2 != null && imageView != null && zzw.equal(imageView2, imageView);
    }
    
    @Override
    public int hashCode() {
        return 0;
    }
    
    @Override
    protected void zza(final Drawable drawable, final boolean b, final boolean b2, final boolean b3) {
        final ImageView imageView = this.zzajX.get();
        if (imageView != null) {
            this.zza(imageView, drawable, b, b2, b3);
        }
    }
}
