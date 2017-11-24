package com.google.android.gms.measurement.internal;

import android.os.*;

class zzac$5 implements Runnable {
    final /* synthetic */ UserAttributeParcel zzaYB;
    
    @Override
    public void run() {
        final zzm zzc = zzac.zzc(zzac.this);
        if (zzc == null) {
            zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to set user attribute");
            return;
        }
        try {
            zzc.zza(this.zzaYB, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
            zzac.zzd(zzac.this);
        }
        catch (RemoteException ex) {
            zzac.this.zzAo().zzCE().zzj("Failed to send attribute to AppMeasurementService", ex);
        }
    }
}