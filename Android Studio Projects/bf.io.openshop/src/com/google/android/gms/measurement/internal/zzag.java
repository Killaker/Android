package com.google.android.gms.measurement.internal;

import android.app.*;
import android.content.*;
import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.measurement.*;

public class zzag extends zzz
{
    private boolean zzRH;
    private final AlarmManager zzRI;
    
    protected zzag(final zzw zzw) {
        super(zzw);
        this.zzRI = (AlarmManager)this.getContext().getSystemService("alarm");
    }
    
    private PendingIntent zzld() {
        final Intent intent = new Intent(this.getContext(), (Class)AppMeasurementReceiver.class);
        intent.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(this.getContext(), 0, intent, 0);
    }
    
    public void cancel() {
        this.zzjv();
        this.zzRH = false;
        this.zzRI.cancel(this.zzld());
    }
    
    @Override
    protected void zziJ() {
        this.zzRI.cancel(this.zzld());
    }
    
    public void zzt(final long n) {
        this.zzjv();
        zzx.zzac(n > 0L);
        zzx.zza(AppMeasurementReceiver.zzY(this.getContext()), (Object)"Receiver not registered/enabled");
        zzx.zza(AppMeasurementService.zzZ(this.getContext()), (Object)"Service not registered/enabled");
        this.cancel();
        final long n2 = n + this.zzjl().elapsedRealtime();
        this.zzRH = true;
        this.zzRI.setInexactRepeating(2, n2, Math.max(this.zzCp().zzBZ(), n), this.zzld());
    }
}
