package com.google.android.gms.common.api;

class Batch$1 implements PendingResult.zza {
    @Override
    public void zzu(final Status status) {
        while (true) {
        Label_0101:
            while (true) {
                synchronized (Batch.zza(Batch.this)) {
                    if (Batch.this.isCanceled()) {
                        return;
                    }
                    if (status.isCanceled()) {
                        Batch.zza(Batch.this, true);
                        Batch.zzb(Batch.this);
                        if (Batch.zzc(Batch.this) == 0) {
                            if (!Batch.zzd(Batch.this)) {
                                break Label_0101;
                            }
                            Batch.zze(Batch.this);
                        }
                        return;
                    }
                }
                if (!status.isSuccess()) {
                    Batch.zzb(Batch.this, true);
                    continue;
                }
                continue;
            }
            Status zzagC;
            if (Batch.zzf(Batch.this)) {
                zzagC = new Status(13);
            }
            else {
                zzagC = Status.zzagC;
            }
            Batch.this.zza(new BatchResult(zzagC, Batch.zzg(Batch.this)));
        }
    }
}