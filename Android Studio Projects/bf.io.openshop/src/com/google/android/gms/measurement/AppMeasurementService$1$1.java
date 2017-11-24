package com.google.android.gms.measurement;

class AppMeasurementService$1$1 implements Runnable {
    @Override
    public void run() {
        if (Runnable.this.zzaTY.stopSelfResult(Runnable.this.zzOP)) {
            if (!Runnable.this.zzaTW.zzCp().zzkr()) {
                Runnable.this.zzaTX.zzCK().zzfg("Local AppMeasurementService processed last upload request");
                return;
            }
            Runnable.this.zzaTX.zzCK().zzfg("Device AppMeasurementService processed last upload request");
        }
    }
}