package com.google.android.gms.measurement;

import java.util.concurrent.*;
import android.util.*;

class zzg$zza$1 extends FutureTask<T> {
    @Override
    protected void setException(final Throwable exception) {
        final Thread.UncaughtExceptionHandler zzb = zzg.zzb(zza.this.zzaUB);
        if (zzb != null) {
            zzb.uncaughtException(Thread.currentThread(), exception);
        }
        else if (Log.isLoggable("GAv4", 6)) {
            Log.e("GAv4", "MeasurementExecutor: job failed with " + exception);
        }
        super.setException(exception);
    }
}