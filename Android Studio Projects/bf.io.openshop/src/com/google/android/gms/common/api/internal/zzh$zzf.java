package com.google.android.gms.common.api.internal;

import android.support.annotation.*;

private abstract class zzf implements Runnable
{
    @WorkerThread
    @Override
    public void run() {
        zzh.zzc(zzh.this).lock();
        try {
            if (Thread.interrupted()) {
                return;
            }
            this.zzpt();
        }
        catch (RuntimeException ex) {
            zzh.zzd(zzh.this).zza(ex);
        }
        finally {
            zzh.zzc(zzh.this).unlock();
        }
    }
    
    @WorkerThread
    protected abstract void zzpt();
}
