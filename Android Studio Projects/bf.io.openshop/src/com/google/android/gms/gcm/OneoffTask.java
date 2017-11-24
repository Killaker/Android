package com.google.android.gms.gcm;

import android.os.*;

public class OneoffTask extends Task
{
    public static final Parcelable$Creator<OneoffTask> CREATOR;
    private final long zzaLW;
    private final long zzaLX;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<OneoffTask>() {
            public OneoffTask zzeI(final Parcel parcel) {
                return new OneoffTask(parcel, null);
            }
            
            public OneoffTask[] zzhf(final int n) {
                return new OneoffTask[n];
            }
        };
    }
    
    private OneoffTask(final Parcel parcel) {
        super(parcel);
        this.zzaLW = parcel.readLong();
        this.zzaLX = parcel.readLong();
    }
    
    private OneoffTask(final Builder builder) {
        super((Task.Builder)builder);
        this.zzaLW = builder.zzaLY;
        this.zzaLX = builder.zzaLZ;
    }
    
    public long getWindowEnd() {
        return this.zzaLX;
    }
    
    public long getWindowStart() {
        return this.zzaLW;
    }
    
    @Override
    public void toBundle(final Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzaLW);
        bundle.putLong("window_end", this.zzaLX);
    }
    
    @Override
    public String toString() {
        return super.toString() + " " + "windowStart=" + this.getWindowStart() + " " + "windowEnd=" + this.getWindowEnd();
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeLong(this.zzaLW);
        parcel.writeLong(this.zzaLX);
    }
    
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
}
