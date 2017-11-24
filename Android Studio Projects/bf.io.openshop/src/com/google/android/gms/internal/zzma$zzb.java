package com.google.android.gms.internal;

import android.graphics.drawable.*;

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
