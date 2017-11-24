package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

class zzaj
{
    private long zzCv;
    private final zzmq zzqW;
    
    public zzaj(final zzmq zzqW) {
        zzx.zzz(zzqW);
        this.zzqW = zzqW;
    }
    
    public zzaj(final zzmq zzqW, final long zzCv) {
        zzx.zzz(zzqW);
        this.zzqW = zzqW;
        this.zzCv = zzCv;
    }
    
    public void clear() {
        this.zzCv = 0L;
    }
    
    public void start() {
        this.zzCv = this.zzqW.elapsedRealtime();
    }
    
    public boolean zzv(final long n) {
        return this.zzCv == 0L || this.zzqW.elapsedRealtime() - this.zzCv > n;
    }
}
