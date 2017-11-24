package com.google.android.gms.gcm;

import android.os.*;
import com.google.android.gms.common.internal.*;

public abstract static class Builder
{
    protected Bundle extras;
    protected String gcmTaskService;
    protected boolean isPersisted;
    protected int requiredNetworkState;
    protected boolean requiresCharging;
    protected String tag;
    protected boolean updateCurrent;
    protected zzd zzaMn;
    
    public Builder() {
        this.zzaMn = zzd.zzaMc;
    }
    
    public abstract Task build();
    
    protected void checkConditions() {
        zzx.zzb(this.gcmTaskService != null, (Object)"Must provide an endpoint for this task by calling setService(ComponentName).");
        GcmNetworkManager.zzdT(this.tag);
        Task.zza(this.zzaMn);
        if (this.isPersisted) {
            Task.zzG(this.extras);
        }
    }
    
    public abstract Builder setExtras(final Bundle p0);
    
    public abstract Builder setPersisted(final boolean p0);
    
    public abstract Builder setRequiredNetwork(final int p0);
    
    public abstract Builder setRequiresCharging(final boolean p0);
    
    public abstract Builder setService(final Class<? extends GcmTaskService> p0);
    
    public abstract Builder setTag(final String p0);
    
    public abstract Builder setUpdateCurrent(final boolean p0);
}
