package com.facebook.share.model;

import android.os.*;

static final class ShareOpenGraphContent$1 implements Parcelable$Creator<ShareOpenGraphContent> {
    public ShareOpenGraphContent createFromParcel(final Parcel parcel) {
        return new ShareOpenGraphContent(parcel);
    }
    
    public ShareOpenGraphContent[] newArray(final int n) {
        return new ShareOpenGraphContent[n];
    }
}