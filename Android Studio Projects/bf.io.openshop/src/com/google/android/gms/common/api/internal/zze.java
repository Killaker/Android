package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.data.*;

public abstract class zze<L> implements zzb<L>
{
    private final DataHolder zzahi;
    
    protected zze(final DataHolder zzahi) {
        this.zzahi = zzahi;
    }
    
    protected abstract void zza(final L p0, final DataHolder p1);
    
    @Override
    public void zzpr() {
        if (this.zzahi != null) {
            this.zzahi.close();
        }
    }
    
    @Override
    public final void zzt(final L l) {
        this.zza(l, this.zzahi);
    }
}
