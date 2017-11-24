package com.google.android.gms.measurement;

import java.util.concurrent.*;
import android.util.*;

private class zza extends ThreadPoolExecutor
{
    public zza() {
        super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        this.setThreadFactory(new zzb());
    }
    
    @Override
    protected <T> RunnableFuture<T> newTaskFor(final Runnable runnable, final T t) {
        return new FutureTask<T>(runnable, t) {
            @Override
            protected void setException(final Throwable exception) {
                final Thread.UncaughtExceptionHandler zzb = zzg.zzb(zzg.this);
                if (zzb != null) {
                    zzb.uncaughtException(Thread.currentThread(), exception);
                }
                else if (Log.isLoggable("GAv4", 6)) {
                    Log.e("GAv4", "MeasurementExecutor: job failed with " + exception);
                }
                super.setException(exception);
            }
        };
    }
}
