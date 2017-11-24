package com.facebook.share.model;

import android.os.*;

static final class ShareVideo$1 implements Parcelable$Creator<ShareVideo> {
    public ShareVideo createFromParcel(final Parcel parcel) {
        return new ShareVideo(parcel);
    }
    
    public ShareVideo[] newArray(final int n) {
        return new ShareVideo[n];
    }
}