package com.google.android.gms.gcm;

import android.os.*;
import android.util.*;
import java.util.*;
import com.google.android.gms.common.internal.*;

public abstract class Task implements Parcelable
{
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1L;
    private final Bundle mExtras;
    private final String mTag;
    private final String zzaMh;
    private final boolean zzaMi;
    private final boolean zzaMj;
    private final int zzaMk;
    private final boolean zzaMl;
    private final zzd zzaMm;
    
    Task(final Parcel parcel) {
        boolean zzaMj = true;
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zzaMh = parcel.readString();
        this.mTag = parcel.readString();
        this.zzaMi = (parcel.readInt() == (zzaMj ? 1 : 0) && zzaMj);
        if (parcel.readInt() != (zzaMj ? 1 : 0)) {
            zzaMj = false;
        }
        this.zzaMj = zzaMj;
        this.zzaMk = 2;
        this.zzaMl = false;
        this.zzaMm = zzd.zzaMc;
        this.mExtras = null;
    }
    
    Task(final Builder builder) {
        this.zzaMh = builder.gcmTaskService;
        this.mTag = builder.tag;
        this.zzaMi = builder.updateCurrent;
        this.zzaMj = builder.isPersisted;
        this.zzaMk = builder.requiredNetworkState;
        this.zzaMl = builder.requiresCharging;
        this.zzaMm = builder.zzaMn;
        this.mExtras = builder.extras;
    }
    
    private static boolean zzD(final Object o) {
        return o instanceof Integer || o instanceof Long || o instanceof Double || o instanceof String || o instanceof Boolean;
    }
    
    public static void zzG(final Bundle bundle) {
        if (bundle != null) {
            final Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            final int dataSize = obtain.dataSize();
            if (dataSize > 10240) {
                obtain.recycle();
                throw new IllegalArgumentException("Extras exceeding maximum size(10240 bytes): " + dataSize);
            }
            obtain.recycle();
            final Iterator<String> iterator = bundle.keySet().iterator();
            while (iterator.hasNext()) {
                if (!zzD(bundle.get((String)iterator.next()))) {
                    throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean. ");
                }
            }
        }
    }
    
    public static void zza(final zzd zzd) {
        if (zzd != null) {
            final int zzym = zzd.zzym();
            if (zzym != 1 && zzym != 0) {
                throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + zzym);
            }
            final int zzyn = zzd.zzyn();
            final int zzyo = zzd.zzyo();
            if (zzym == 0 && zzyn < 0) {
                throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + zzyn);
            }
            if (zzym == 1 && zzyn < 10) {
                throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
            }
            if (zzyo < zzyn) {
                throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + zzd.zzyo());
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public int getRequiredNetwork() {
        return this.zzaMk;
    }
    
    public boolean getRequiresCharging() {
        return this.zzaMl;
    }
    
    public String getServiceName() {
        return this.zzaMh;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    public boolean isPersisted() {
        return this.zzaMj;
    }
    
    public boolean isUpdateCurrent() {
        return this.zzaMi;
    }
    
    public void toBundle(final Bundle bundle) {
        bundle.putString("tag", this.mTag);
        bundle.putBoolean("update_current", this.zzaMi);
        bundle.putBoolean("persisted", this.zzaMj);
        bundle.putString("service", this.zzaMh);
        bundle.putInt("requiredNetwork", this.zzaMk);
        bundle.putBoolean("requiresCharging", this.zzaMl);
        bundle.putBundle("retryStrategy", this.zzaMm.zzF(new Bundle()));
        bundle.putBundle("extras", this.mExtras);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        int n2 = 1;
        parcel.writeString(this.zzaMh);
        parcel.writeString(this.mTag);
        int n3;
        if (this.zzaMi) {
            n3 = n2;
        }
        else {
            n3 = 0;
        }
        parcel.writeInt(n3);
        if (!this.zzaMj) {
            n2 = 0;
        }
        parcel.writeInt(n2);
    }
    
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
}
