package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.*;
import android.os.*;

class CampaignTrackingService$1 implements Runnable {
    final /* synthetic */ int zzOP;
    final /* synthetic */ zzaf zzOR;
    final /* synthetic */ Handler zzt;
    
    @Override
    public void run() {
        CampaignTrackingService.this.zza(this.zzOR, this.zzt, this.zzOP);
    }
}