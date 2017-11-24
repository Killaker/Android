package com.google.android.gms.measurement.internal;

import android.os.*;

class zzac$6 implements Runnable {
    @Override
    public void run() {
        final zzm zzc = zzac.zzc(zzac.this);
        if (zzc == null) {
            zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzc.zza(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
            zzac.zzd(zzac.this);
        }
        catch (RemoteException ex) {
            zzac.this.zzAo().zzCE().zzj("Failed to send app launch to AppMeasurementService", ex);
        }
    }
}