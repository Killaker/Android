package com.facebook.share.model;

import android.os.*;

static final class ShareVideoContent$1 implements Parcelable$Creator<ShareVideoContent> {
    public ShareVideoContent createFromParcel(final Parcel parcel) {
        return new ShareVideoContent(parcel);
    }
    
    public ShareVideoContent[] newArray(final int n) {
        return new ShareVideoContent[n];
    }
}