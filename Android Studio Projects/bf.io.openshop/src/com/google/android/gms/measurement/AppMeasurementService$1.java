package com.google.android.gms.measurement;

import com.google.android.gms.measurement.internal.*;

class AppMeasurementService$1 implements Runnable {
    final /* synthetic */ int zzOP;
    final /* synthetic */ zzw zzaTW;
    final /* synthetic */ zzp zzaTX;
    
    @Override
    public void run() {
        this.zzaTW.zzDc();
        AppMeasurementService.zza(AppMeasurementService.this).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AppMeasurementService.this.stopSelfResult(Runnable.this.zzOP)) {
                    if (!Runnable.this.zzaTW.zzCp().zzkr()) {
                        Runnable.this.zzaTX.zzCK().zzfg("Local AppMeasurementService processed last upload request");
                        return;
                    }
                    Runnable.this.zzaTX.zzCK().zzfg("Device AppMeasurementService processed last upload request");
                }
            }
        });
    }
}