package com.google.android.gms.gcm;

import android.os.*;

public static class Builder extends Task.Builder
{
    private long zzaMa;
    private long zzaMb;
    
    public Builder() {
        this.zzaMa = -1L;
        this.zzaMb = -1L;
        this.isPersisted = true;
    }
    
    public PeriodicTask build() {
        this.checkConditions();
        return new PeriodicTask(this, null);
    }
    
    @Override
    protected void checkConditions() {
        super.checkConditions();
        if (this.zzaMa == -1L) {
            throw new IllegalArgumentException("Must call setPeriod(long) to establish an execution interval for this periodic task.");
        }
        if (this.zzaMa <= 0L) {
            throw new IllegalArgumentException("Period set cannot be less or equal to 0: " + this.zzaMa);
        }
        if (this.zzaMb == -1L) {
            this.zzaMb = (long)(0.1f * this.zzaMa);
        }
        else if (this.zzaMb > this.zzaMa) {
            this.zzaMb = this.zzaMa;
        }
    }
    
    public Builder setExtras(final Bundle extras) {
        this.extras = extras;
        return this;
    }
    
    public Builder setFlex(final long zzaMb) {
        this.zzaMb = zzaMb;
        return this;
    }
    
    public Builder setPeriod(final long zzaMa) {
        this.zzaMa = zzaMa;
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
