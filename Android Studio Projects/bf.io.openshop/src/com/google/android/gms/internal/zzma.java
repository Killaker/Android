package com.google.android.gms.internal;

import android.os.*;
import android.graphics.drawable.*;
import android.annotation.*;
import android.graphics.*;

public final class zzma extends Drawable implements Drawable$Callback
{
    private int mFrom;
    private long zzRD;
    private boolean zzajT;
    private int zzaka;
    private int zzakb;
    private int zzakc;
    private int zzakd;
    private int zzake;
    private boolean zzakf;
    private zzb zzakg;
    private Drawable zzakh;
    private Drawable zzaki;
    private boolean zzakj;
    private boolean zzakk;
    private boolean zzakl;
    private int zzakm;
    
    public zzma(Drawable zzqo, Drawable zzqo2) {
        this(null);
        if (zzqo == null) {
            zzqo = zza.zzakn;
        }
        (this.zzakh = zzqo).setCallback((Drawable$Callback)this);
        final zzb zzakg = this.zzakg;
        zzakg.zzakq |= zzqo.getChangingConfigurations();
        if (zzqo2 == null) {
            zzqo2 = zza.zzakn;
        }
        (this.zzaki = zzqo2).setCallback((Drawable$Callback)this);
        final zzb zzakg2 = this.zzakg;
        zzakg2.zzakq |= zzqo2.getChangingConfigurations();
    }
    
    zzma(final zzb zzb) {
        this.zzaka = 0;
        this.zzakc = 255;
        this.zzake = 0;
        this.zzajT = true;
        this.zzakg = new zzb(zzb);
    }
    
    public boolean canConstantState() {
        if (!this.zzakj) {
            this.zzakk = (this.zzakh.getConstantState() != null && this.zzaki.getConstantState() != null);
            this.zzakj = true;
        }
        return this.zzakk;
    }
    
    public void draw(final Canvas canvas) {
        boolean b = true;
        int n = 0;
        Label_0031: {
            switch (this.zzaka) {
                case 1: {
                    this.zzRD = SystemClock.uptimeMillis();
                    this.zzaka = 2;
                    n = 0;
                    break Label_0031;
                }
                case 2: {
                    if (this.zzRD >= 0L) {
                        final float n2 = (SystemClock.uptimeMillis() - this.zzRD) / this.zzakd;
                        if (n2 < 1.0f) {
                            b = false;
                        }
                        if (b) {
                            this.zzaka = 0;
                        }
                        this.zzake = (int)(this.mFrom + Math.min(n2, 1.0f) * (this.zzakb - this.mFrom));
                        break;
                    }
                    break;
                }
            }
            n = (b ? 1 : 0);
        }
        final int zzake = this.zzake;
        final boolean zzajT = this.zzajT;
        final Drawable zzakh = this.zzakh;
        final Drawable zzaki = this.zzaki;
        if (n != 0) {
            if (!zzajT || zzake == 0) {
                zzakh.draw(canvas);
            }
            if (zzake == this.zzakc) {
                zzaki.setAlpha(this.zzakc);
                zzaki.draw(canvas);
            }
            return;
        }
        if (zzajT) {
            zzakh.setAlpha(this.zzakc - zzake);
        }
        zzakh.draw(canvas);
        if (zzajT) {
            zzakh.setAlpha(this.zzakc);
        }
        if (zzake > 0) {
            zzaki.setAlpha(zzake);
            zzaki.draw(canvas);
            zzaki.setAlpha(this.zzakc);
        }
        this.invalidateSelf();
    }
    
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzakg.zzakp | this.zzakg.zzakq;
    }
    
    public Drawable$ConstantState getConstantState() {
        if (this.canConstantState()) {
            this.zzakg.zzakp = this.getChangingConfigurations();
            return this.zzakg;
        }
        return null;
    }
    
    public int getIntrinsicHeight() {
        return Math.max(this.zzakh.getIntrinsicHeight(), this.zzaki.getIntrinsicHeight());
    }
    
    public int getIntrinsicWidth() {
        return Math.max(this.zzakh.getIntrinsicWidth(), this.zzaki.getIntrinsicWidth());
    }
    
    public int getOpacity() {
        if (!this.zzakl) {
            this.zzakm = Drawable.resolveOpacity(this.zzakh.getOpacity(), this.zzaki.getOpacity());
            this.zzakl = true;
        }
        return this.zzakm;
    }
    
    @TargetApi(11)
    public void invalidateDrawable(final Drawable drawable) {
        if (zzne.zzsd()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.invalidateDrawable((Drawable)this);
            }
        }
    }
    
    public Drawable mutate() {
        if (!this.zzakf && super.mutate() == this) {
            if (!this.canConstantState()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzakh.mutate();
            this.zzaki.mutate();
            this.zzakf = true;
        }
        return this;
    }
    
    protected void onBoundsChange(final Rect rect) {
        this.zzakh.setBounds(rect);
        this.zzaki.setBounds(rect);
    }
    
    @TargetApi(11)
    public void scheduleDrawable(final Drawable drawable, final Runnable runnable, final long n) {
        if (zzne.zzsd()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.scheduleDrawable((Drawable)this, runnable, n);
            }
        }
    }
    
    public void setAlpha(final int n) {
        if (this.zzake == this.zzakc) {
            this.zzake = n;
        }
        this.zzakc = n;
        this.invalidateSelf();
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        this.zzakh.setColorFilter(colorFilter);
        this.zzaki.setColorFilter(colorFilter);
    }
    
    public void startTransition(final int zzakd) {
        this.mFrom = 0;
        this.zzakb = this.zzakc;
        this.zzake = 0;
        this.zzakd = zzakd;
        this.zzaka = 1;
        this.invalidateSelf();
    }
    
    @TargetApi(11)
    public void unscheduleDrawable(final Drawable drawable, final Runnable runnable) {
        if (zzne.zzsd()) {
            final Drawable$Callback callback = this.getCallback();
            if (callback != null) {
                callback.unscheduleDrawable((Drawable)this, runnable);
            }
        }
    }
    
    public Drawable zzqn() {
        return this.zzaki;
    }
    
    private static final class zza extends Drawable
    {
        private static final zzma.zza zzakn;
        private static final zzma.zza.zza zzako;
        
        static {
            zzakn = new zzma.zza();
            zzako = new zzma.zza.zza();
        }
        
        public void draw(final Canvas canvas) {
        }
        
        public Drawable$ConstantState getConstantState() {
            return zzma.zza.zzako;
        }
        
        public int getOpacity() {
            return -2;
        }
        
        public void setAlpha(final int n) {
        }
        
        public void setColorFilter(final ColorFilter colorFilter) {
        }
        
        private static final class zza extends Drawable$ConstantState
        {
            public int getChangingConfigurations() {
                return 0;
            }
            
            public Drawable newDrawable() {
                return zzma.zza.zzakn;
            }
        }
    }
    
    static final class zzb extends Drawable$ConstantState
    {
        int zzakp;
        int zzakq;
        
        zzb(final zzb zzb) {
            if (zzb != null) {
                this.zzakp = zzb.zzakp;
                this.zzakq = zzb.zzakq;
            }
        }
        
        public int getChangingConfigurations() {
            return this.zzakp;
        }
        
        public Drawable newDrawable() {
            return new zzma(this);
        }
    }
}
