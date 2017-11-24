package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import java.util.concurrent.*;

private final class zza<V> extends FutureTask<V>
{
    private final String zzaXR;
    
    zza(final Runnable runnable, final String zzaXR) {
        super(runnable, null);
        zzx.zzz(zzaXR);
        this.zzaXR = zzaXR;
    }
    
    zza(final Callable<V> callable, final String zzaXR) {
        super(callable);
        zzx.zzz(zzaXR);
        this.zzaXR = zzaXR;
    }
    
    @Override
    protected void setException(final Throwable exception) {
        zzv.this.zzAo().zzCE().zzj(this.zzaXR, exception);
        super.setException(exception);
    }
}
