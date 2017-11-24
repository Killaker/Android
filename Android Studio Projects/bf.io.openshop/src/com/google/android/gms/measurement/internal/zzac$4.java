package com.google.android.gms.measurement.internal;

import android.text.*;
import android.os.*;

class zzac$4 implements Runnable {
    final /* synthetic */ String zzaHU;
    final /* synthetic */ EventParcel zzaYz;
    
    @Override
    public void run() {
        final zzm zzc = zzac.zzc(zzac.this);
        if (zzc == null) {
            zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send event to service");
            return;
        }
        while (true) {
            while (true) {
                try {
                    if (TextUtils.isEmpty((CharSequence)this.zzaHU)) {
                        zzc.zza(this.zzaYz, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
                        zzac.zzd(zzac.this);
                        return;
                    }
                }
                catch (RemoteException ex) {
                    zzac.this.zzAo().zzCE().zzj("Failed to send event to AppMeasurementService", ex);
                    return;
                }
                zzc.zza(this.zzaYz, this.zzaHU, zzac.this.zzAo().zzCL());
                continue;
            }
        }
    }
}