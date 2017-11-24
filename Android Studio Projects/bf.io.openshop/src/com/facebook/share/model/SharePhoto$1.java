package com.facebook.share.model;

import android.os.*;

static final class SharePhoto$1 implements Parcelable$Creator<SharePhoto> {
    public SharePhoto createFromParcel(final Parcel parcel) {
        return new SharePhoto(parcel);
    }
    
    public SharePhoto[] newArray(final int n) {
        return new SharePhoto[n];
    }
}