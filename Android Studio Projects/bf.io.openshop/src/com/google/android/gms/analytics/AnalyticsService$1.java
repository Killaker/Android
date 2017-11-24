package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.*;

class AnalyticsService$1 implements zzw {
    final /* synthetic */ int zzOP;
    final /* synthetic */ zzf zzOQ;
    final /* synthetic */ zzaf zzOR;
    
    @Override
    public void zzc(final Throwable t) {
        AnalyticsService.zza(AnalyticsService.this).post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (AnalyticsService.this.stopSelfResult(zzw.this.zzOP)) {
                    if (!zzw.this.zzOQ.zzjn().zzkr()) {
                        zzw.this.zzOR.zzbd("Local AnalyticsService processed last dispatch request");
                        return;
                    }
                    zzw.this.zzOR.zzbd("Device AnalyticsService processed last dispatch request");
                }
            }
        });
    }
}