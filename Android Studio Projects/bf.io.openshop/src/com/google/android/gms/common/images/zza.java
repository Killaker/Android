package com.google.android.gms.common.images;

import android.net.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import com.google.android.gms.common.internal.*;
import java.lang.ref.*;
import android.widget.*;
import com.google.android.gms.internal.*;

public abstract class zza
{
    final zza zzajO;
    protected int zzajP;
    protected int zzajQ;
    protected boolean zzajR;
    protected ImageManager.OnImageLoadedListener zzajS;
    private boolean zzajT;
    private boolean zzajU;
    private boolean zzajV;
    protected int zzajW;
    
    public zza(final Uri uri, final int zzajQ) {
        this.zzajP = 0;
        this.zzajQ = 0;
        this.zzajR = false;
        this.zzajT = true;
        this.zzajU = false;
        this.zzajV = true;
        this.zzajO = new zza(uri);
        this.zzajQ = zzajQ;
    }
    
    private Drawable zza(final Context context, final zzmd zzmd, final int n) {
        final Resources resources = context.getResources();
        if (this.zzajW > 0) {
            final zzmd.zza zza = new zzmd.zza(n, this.zzajW);
            Drawable drawable = zzmd.get(zza);
            if (drawable == null) {
                drawable = resources.getDrawable(n);
                if ((0x1 & this.zzajW) != 0x0) {
                    drawable = this.zza(resources, drawable);
                }
                zzmd.put(zza, drawable);
            }
            return drawable;
        }
        return resources.getDrawable(n);
    }
    
    protected Drawable zza(final Resources resources, final Drawable drawable) {
        return zzmb.zza(resources, drawable);
    }
    
    protected zzma zza(Drawable zzqn, final Drawable drawable) {
        if (zzqn != null) {
            if (zzqn instanceof zzma) {
                zzqn = ((zzma)zzqn).zzqn();
            }
        }
        else {
            zzqn = null;
        }
        return new zzma(zzqn, drawable);
    }
    
    void zza(final Context context, Bitmap zzb, final boolean b) {
        zzb.zzv(zzb);
        if ((0x1 & this.zzajW) != 0x0) {
            zzb = zzmb.zzb(zzb);
        }
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), zzb);
        if (this.zzajS != null) {
            this.zzajS.onImageLoaded(this.zzajO.uri, (Drawable)bitmapDrawable, true);
        }
        this.zza((Drawable)bitmapDrawable, b, false, true);
    }
    
    void zza(final Context context, final zzmd zzmd) {
        if (this.zzajV) {
            final int zzajP = this.zzajP;
            Drawable zza = null;
            if (zzajP != 0) {
                zza = this.zza(context, zzmd, this.zzajP);
            }
            this.zza(zza, false, true, false);
        }
    }
    
    void zza(final Context context, final zzmd zzmd, final boolean b) {
        final int zzajQ = this.zzajQ;
        Drawable zza = null;
        if (zzajQ != 0) {
            zza = this.zza(context, zzmd, this.zzajQ);
        }
        if (this.zzajS != null) {
            this.zzajS.onImageLoaded(this.zzajO.uri, zza, false);
        }
        this.zza(zza, b, false, false);
    }
    
    protected abstract void zza(final Drawable p0, final boolean p1, final boolean p2, final boolean p3);
    
    protected boolean zzb(final boolean b, final boolean b2) {
        return this.zzajT && !b2 && (!b || this.zzajU);
    }
    
    public void zzbM(final int zzajQ) {
        this.zzajQ = zzajQ;
    }
    
    static final class zza
    {
        public final Uri uri;
        
        public zza(final Uri uri) {
            this.uri = uri;
        }
        
        @Override
        public boolean equals(final Object o) {
            return o instanceof zza && (this == o || zzw.equal(((zza)o).uri, this.uri));
        }
        
        @Override
        public int hashCode() {
            return zzw.hashCode(this.uri);
        }
    }
    
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
}
