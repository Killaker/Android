package com.facebook;

import android.os.*;

public static class ParcelableResourceWithMimeType<RESOURCE extends Parcelable> implements Parcelable
{
    public static final Parcelable$Creator<ParcelableResourceWithMimeType> CREATOR;
    private final String mimeType;
    private final RESOURCE resource;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ParcelableResourceWithMimeType>() {
            public ParcelableResourceWithMimeType createFromParcel(final Parcel parcel) {
                return new ParcelableResourceWithMimeType(parcel);
            }
            
            public ParcelableResourceWithMimeType[] newArray(final int n) {
                return new ParcelableResourceWithMimeType[n];
            }
        };
    }
    
    private ParcelableResourceWithMimeType(final Parcel parcel) {
        this.mimeType = parcel.readString();
        this.resource = (RESOURCE)parcel.readParcelable(FacebookSdk.getApplicationContext().getClassLoader());
    }
    
    public ParcelableResourceWithMimeType(final RESOURCE resource, final String mimeType) {
        this.mimeType = mimeType;
        this.resource = resource;
    }
    
    public int describeContents() {
        return 1;
    }
    
    public String getMimeType() {
        return this.mimeType;
    }
    
    public RESOURCE getResource() {
        return this.resource;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.mimeType);
        parcel.writeParcelable((Parcelable)this.resource, n);
    }
}
