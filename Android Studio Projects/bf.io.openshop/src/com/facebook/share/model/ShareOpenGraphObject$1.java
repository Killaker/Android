package com.facebook.share.model;

import android.os.*;

static final class ShareOpenGraphObject$1 implements Parcelable$Creator<ShareOpenGraphObject> {
    public ShareOpenGraphObject createFromParcel(final Parcel parcel) {
        return new ShareOpenGraphObject(parcel);
    }
    
    public ShareOpenGraphObject[] newArray(final int n) {
        return new ShareOpenGraphObject[n];
    }
}