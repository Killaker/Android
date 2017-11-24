package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.*;

class zzbe implements zzcd
{
    private final long zzSP;
    private final int zzSQ;
    private double zzSR;
    private long zzSS;
    private final Object zzST;
    private final String zzSU;
    private final long zzbjt;
    private final zzmq zzqW;
    
    public zzbe(final int zzSQ, final long zzSP, final long zzbjt, final String zzSU, final zzmq zzqW) {
        this.zzST = new Object();
        this.zzSQ = zzSQ;
        this.zzSR = this.zzSQ;
        this.zzSP = zzSP;
        this.zzbjt = zzbjt;
        this.zzSU = zzSU;
        this.zzqW = zzqW;
    }
    
    @Override
    public boolean zzlw() {
        synchronized (this.zzST) {
            final long currentTimeMillis = this.zzqW.currentTimeMillis();
            if (currentTimeMillis - this.zzSS < this.zzbjt) {
                zzbg.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
                return false;
            }
            if (this.zzSR < this.zzSQ) {
                final double n = (currentTimeMillis - this.zzSS) / this.zzSP;
                if (n > 0.0) {
                    this.zzSR = Math.min(this.zzSQ, n + this.zzSR);
                }
            }
            this.zzSS = currentTimeMillis;
            if (this.zzSR >= 1.0) {
                --this.zzSR;
                return true;
            }
        }
        zzbg.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
        // monitorexit(o)
        return false;
    }
}
