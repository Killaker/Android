package com.google.android.gms.common.api.internal;

abstract static class zza
{
    private final zzk zzait;
    
    protected zza(final zzk zzait) {
        this.zzait = zzait;
    }
    
    public final void zzd(final zzl zzl) {
        zzl.zzb(zzl).lock();
        try {
            if (zzl.zzc(zzl) != this.zzait) {
                return;
            }
            this.zzpt();
        }
        finally {
            zzl.zzb(zzl).unlock();
        }
    }
    
    protected abstract void zzpt();
}
