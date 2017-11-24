package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.*;

class CampaignTrackingService$3 implements Runnable {
    final /* synthetic */ int zzOP;
    final /* synthetic */ zzaf zzOR;
    
    @Override
    public void run() {
        final boolean stopSelfResult = CampaignTrackingService.this.stopSelfResult(this.zzOP);
        if (stopSelfResult) {
            this.zzOR.zza("Install campaign broadcast processed", stopSelfResult);
        }
    }
}