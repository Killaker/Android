package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;

private final class zzb implements UncaughtExceptionHandler
{
    private final String zzaXR;
    
    public zzb(final String zzaXR) {
        zzx.zzz(zzaXR);
        this.zzaXR = zzaXR;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        synchronized (this) {
            zzv.this.zzAo().zzCE().zzj(this.zzaXR, t);
        }
    }
}
