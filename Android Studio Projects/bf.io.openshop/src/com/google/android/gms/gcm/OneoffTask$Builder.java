package com.google.android.gms.gcm;

import android.os.*;

public static class Builder extends Task.Builder
{
    private long zzaLY;
    private long zzaLZ;
    
    public Builder() {
        this.zzaLY = -1L;
        this.zzaLZ = -1L;
        this.isPersisted = false;
    }
    
    public OneoffTask build() {
        this.checkConditions();
        return new OneoffTask(this, null);
    }
    
    @Override
    protected void checkConditions() {
        super.checkConditions();
        if (this.zzaLY == -1L || this.zzaLZ == -1L) {
            throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
        }
        if (this.zzaLY >= this.zzaLZ) {
            throw new IllegalArgumentException("Window start must be shorter than window end.");
        }
    }
    
    public Builder setExecutionWindow(final long zzaLY, final long zzaLZ) {
        this.zzaLY = zzaLY;
        this.zzaLZ = zzaLZ;
        return this;
    }
    
    public Builder setExtras(final Bundle extras) {
        this.extras = extras;
        return this;
    }
    
    public Builder setPersisted(final boolean isPersisted) {
        this.isPersisted = isPersisted;
        return this;
    }
    
    public Builder setRequiredNetwork(final int requiredNetworkState) {
        this.requiredNetworkState = requiredNetworkState;
        return this;
    }
    
    public Builder setRequiresCharging(final boolean requiresCharging) {
        this.requiresCharging = requiresCharging;
        return this;
    }
    
    public Builder setService(final Class<? extends GcmTaskService> clazz) {
        this.gcmTaskService = clazz.getName();
        return this;
    }
    
    public Builder setTag(final String tag) {
        this.tag = tag;
        return this;
    }
    
    public Builder setUpdateCurrent(final boolean updateCurrent) {
        this.updateCurrent = updateCurrent;
        return this;
    }
}
