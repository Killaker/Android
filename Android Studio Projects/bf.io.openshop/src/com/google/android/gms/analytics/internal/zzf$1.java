package com.google.android.gms.analytics.internal;

class zzf$1 implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        final zzaf zzjy = zzf.this.zzjy();
        if (zzjy != null) {
            zzjy.zze("Job execution failed", t);
        }
    }
}