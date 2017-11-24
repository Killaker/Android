package com.google.android.gms.analytics.internal;

import android.app.*;
import com.google.android.gms.analytics.*;
import android.content.*;
import android.content.pm.*;
import com.google.android.gms.common.internal.*;

public class zzv extends zzd
{
    private boolean zzRG;
    private boolean zzRH;
    private AlarmManager zzRI;
    
    protected zzv(final zzf zzf) {
        super(zzf);
        this.zzRI = (AlarmManager)this.getContext().getSystemService("alarm");
    }
    
    private PendingIntent zzld() {
        final Intent intent = new Intent(this.getContext(), (Class)AnalyticsReceiver.class);
        intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
        return PendingIntent.getBroadcast(this.getContext(), 0, intent, 0);
    }
    
    public void cancel() {
        this.zzjv();
        this.zzRH = false;
        this.zzRI.cancel(this.zzld());
    }
    
    public boolean zzbw() {
        return this.zzRH;
    }
    
    @Override
    protected void zziJ() {
        try {
            this.zzRI.cancel(this.zzld());
            if (this.zzjn().zzkA() > 0L) {
                final ActivityInfo receiverInfo = this.getContext().getPackageManager().getReceiverInfo(new ComponentName(this.getContext(), (Class)AnalyticsReceiver.class), 2);
                if (receiverInfo != null && receiverInfo.enabled) {
                    this.zzbd("Receiver registered. Using alarm for local dispatch.");
                    this.zzRG = true;
                }
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
    }
    
    public boolean zzlb() {
        return this.zzRG;
    }
    
    public void zzlc() {
        this.zzjv();
        zzx.zza(this.zzlb(), (Object)"Receiver not registered");
        final long zzkA = this.zzjn().zzkA();
        if (zzkA > 0L) {
            this.cancel();
            final long n = zzkA + this.zzjl().elapsedRealtime();
            this.zzRH = true;
            this.zzRI.setInexactRepeating(2, n, 0L, this.zzld());
        }
    }
}
