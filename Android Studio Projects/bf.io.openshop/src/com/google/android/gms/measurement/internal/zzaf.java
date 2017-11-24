package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

class zzaf
{
    private long zzCv;
    private final zzmq zzqW;
    
    public zzaf(final zzmq zzqW) {
        zzx.zzz(zzqW);
        this.zzqW = zzqW;
    }
    
    public void clear() {
        this.zzCv = 0L;
    }
    
    public void start() {
        this.zzCv = this.zzqW.elapsedRealtime();
    }
    
    public boolean zzv(final long n) {
        return this.zzCv == 0L || this.zzqW.elapsedRealtime() - this.zzCv >= n;
    }
}
