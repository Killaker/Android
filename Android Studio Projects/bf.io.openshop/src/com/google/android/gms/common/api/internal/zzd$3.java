package com.google.android.gms.common.api.internal;

class zzd$3 implements Runnable {
    @Override
    public void run() {
        zzd.zza(zzd.this).lock();
        try {
            zzd.zzb(zzd.this);
        }
        finally {
            zzd.zza(zzd.this).unlock();
        }
    }
}