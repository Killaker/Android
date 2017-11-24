package com.google.android.gms.gcm;

import android.os.*;

public class PendingCallback implements Parcelable
{
    public static final Parcelable$Creator<PendingCallback> CREATOR;
    final IBinder zzakD;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<PendingCallback>() {
            public PendingCallback zzeJ(final Parcel parcel) {
                return new PendingCallback(parcel);
            }
            
            public PendingCallback[] zzhg(final int n) {
                return new PendingCallback[n];
            }
        };
    }
    
    public PendingCallback(final Parcel parcel) {
        this.zzakD = parcel.readStrongBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    public IBinder getIBinder() {
        return this.zzakD;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStrongBinder(this.zzakD);
    }
}
