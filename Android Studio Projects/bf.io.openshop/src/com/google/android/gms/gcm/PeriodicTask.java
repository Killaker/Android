package com.google.android.gms.gcm;

import android.os.*;

public class PeriodicTask extends Task
{
    public static final Parcelable$Creator<PeriodicTask> CREATOR;
    protected long mFlexInSeconds;
    protected long mIntervalInSeconds;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<PeriodicTask>() {
            public PeriodicTask zzeK(final Parcel parcel) {
                return new PeriodicTask(parcel, null);
            }
            
            public PeriodicTask[] zzhh(final int n) {
                return new PeriodicTask[n];
            }
        };
    }
    
    private PeriodicTask(final Parcel parcel) {
        super(parcel);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = parcel.readLong();
        this.mFlexInSeconds = Math.min(parcel.readLong(), this.mIntervalInSeconds);
    }
    
    private PeriodicTask(final Builder builder) {
        super((Task.Builder)builder);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = builder.zzaMa;
        this.mFlexInSeconds = Math.min(builder.zzaMb, this.mIntervalInSeconds);
    }
    
    public long getFlex() {
        return this.mFlexInSeconds;
    }
    
    public long getPeriod() {
        return this.mIntervalInSeconds;
    }
    
    @Override
    public void toBundle(final Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("period", this.mIntervalInSeconds);
        bundle.putLong("period_flex", this.mFlexInSeconds);
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + "period=" + this.getPeriod() + " " + "flex=" + this.getFlex();
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeLong(this.mIntervalInSeconds);
        parcel.writeLong(this.mFlexInSeconds);
    }
    
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
}
