package com.google.android.gms.analytics;

class AnalyticsService$1$1 implements Runnable {
    @Override
    public void run() {
        if (zzw.this.zzOS.stopSelfResult(zzw.this.zzOP)) {
            if (!zzw.this.zzOQ.zzjn().zzkr()) {
                zzw.this.zzOR.zzbd("Local AnalyticsService processed last dispatch request");
                return;
            }
            zzw.this.zzOR.zzbd("Device AnalyticsService processed last dispatch request");
        }
    }
}