package com.google.android.gms.internal;

import android.graphics.drawable.*;
import android.graphics.*;

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
