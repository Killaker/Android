package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.*;
import android.os.*;

@KeepName
public final class BinderWrapper implements Parcelable
{
    public static final Parcelable$Creator<BinderWrapper> CREATOR;
    private IBinder zzakD;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<BinderWrapper>() {
            public BinderWrapper zzan(final Parcel parcel) {
                return new BinderWrapper(parcel, null);
            }
            
            public BinderWrapper[] zzbQ(final int n) {
                return new BinderWrapper[n];
            }
        };
    }
    
    public BinderWrapper() {
        this.zzakD = null;
    }
    
    public BinderWrapper(final IBinder zzakD) {
        this.zzakD = null;
        this.zzakD = zzakD;
    }
    
    private BinderWrapper(final Parcel parcel) {
        this.zzakD = null;
        this.zzakD = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzakD);
    }
}
