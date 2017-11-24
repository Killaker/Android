package com.google.android.gms.measurement.internal;

import android.os.*;

class zzac$3 implements Runnable {
    @Override
    public void run() {
        final zzm zzc = zzac.zzc(zzac.this);
        if (zzc == null) {
            zzac.this.zzAo().zzCE().zzfg("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzc.zzb(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
            zzac.zzd(zzac.this);
        }
        catch (RemoteException ex) {
            zzac.this.zzAo().zzCE().zzj("Failed to send measurementEnabled to AppMeasurementService", ex);
        }
    }
}