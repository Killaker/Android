package com.google.android.gms.tagmanager;

class zzcs implements zzcd
{
    private final long zzSP;
    private final int zzSQ;
    private double zzSR;
    private final Object zzST;
    private long zzbkO;
    
    public zzcs() {
        this(60, 2000L);
    }
    
    public zzcs(final int zzSQ, final long zzSP) {
        this.zzST = new Object();
        this.zzSQ = zzSQ;
        this.zzSR = this.zzSQ;
        this.zzSP = zzSP;
    }
    
    @Override
    public boolean zzlw() {
        synchronized (this.zzST) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.zzSR < this.zzSQ) {
                final double n = (currentTimeMillis - this.zzbkO) / this.zzSP;
                if (n > 0.0) {
                    this.zzSR = Math.min(this.zzSQ, n + this.zzSR);
                }
            }
            this.zzbkO = currentTimeMillis;
            if (this.zzSR >= 1.0) {
                --this.zzSR;
                return true;
            }
            zzbg.zzaK("No more tokens available.");
            return false;
        }
    }
}
