package com.google.android.gms.tagmanager;

import com.google.android.gms.analytics.*;

public final class InstallReferrerReceiver extends CampaignTrackingReceiver
{
    @Override
    protected void zzaV(final String s) {
        zzax.zzgh(s);
    }
    
    @Override
    protected Class<? extends CampaignTrackingService> zziB() {
        return InstallReferrerService.class;
    }
}
