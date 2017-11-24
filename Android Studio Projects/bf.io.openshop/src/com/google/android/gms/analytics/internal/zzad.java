package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.*;

public class zzad
{
    private final long zzSP;
    private final int zzSQ;
    private double zzSR;
    private long zzSS;
    private final Object zzST;
    private final String zzSU;
    private final zzmq zzqW;
    
    public zzad(final int zzSQ, final long zzSP, final String zzSU, final zzmq zzqW) {
        this.zzST = new Object();
        this.zzSQ = zzSQ;
        this.zzSR = this.zzSQ;
        this.zzSP = zzSP;
        this.zzSU = zzSU;
        this.zzqW = zzqW;
    }
    
    public zzad(final String s, final zzmq zzmq) {
        this(60, 2000L, s, zzmq);
    }
    
    public boolean zzlw() {
        synchronized (this.zzST) {
            final long currentTimeMillis = this.zzqW.currentTimeMillis();
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
            zzae.zzaK("Excessive " + this.zzSU + " detected; call ignored.");
            return false;
        }
    }
}
