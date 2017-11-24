package com.google.android.gms.analytics.internal;

import android.os.*;

public class Command implements Parcelable
{
    @Deprecated
    public static final Parcelable$Creator<Command> CREATOR;
    private String mValue;
    private String zzRt;
    private String zzyv;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<Command>() {
            @Deprecated
            public Command[] zzag(final int n) {
                return new Command[n];
            }
            
            @Deprecated
            public Command zzq(final Parcel parcel) {
                return new Command(parcel);
            }
        };
    }
    
    public Command() {
    }
    
    Command(final Parcel parcel) {
        this.readFromParcel(parcel);
    }
    
    @Deprecated
    private void readFromParcel(final Parcel parcel) {
        this.zzyv = parcel.readString();
        this.zzRt = parcel.readString();
        this.mValue = parcel.readString();
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
    }
    
    public String getId() {
        return this.zzyv;
    }
    
    public String getValue() {
        return this.mValue;
    }
    
    @Deprecated
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.zzyv);
        parcel.writeString(this.zzRt);
        parcel.writeString(this.mValue);
    }
}
