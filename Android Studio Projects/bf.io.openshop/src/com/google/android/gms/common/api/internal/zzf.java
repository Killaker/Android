package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.*;
import com.google.android.gms.common.data.*;

public abstract class zzf implements Releasable, Result
{
    protected final Status zzUX;
    protected final DataHolder zzahi;
    
    protected zzf(final DataHolder zzahi, final Status zzUX) {
        this.zzUX = zzUX;
        this.zzahi = zzahi;
    }
    
    @Override
    public Status getStatus() {
        return this.zzUX;
    }
    
    @Override
    public void release() {
        if (this.zzahi != null) {
            this.zzahi.close();
        }
    }
}
