package com.google.android.gms.analytics.internal;

public abstract class zzd extends zzc
{
    private boolean zzQk;
    private boolean zzQl;
    
    protected zzd(final zzf zzf) {
        super(zzf);
    }
    
    public boolean isInitialized() {
        return this.zzQk && !this.zzQl;
    }
    
    public void zza() {
        this.zziJ();
        this.zzQk = true;
    }
    
    protected abstract void zziJ();
    
    protected void zzjv() {
        if (!this.isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
