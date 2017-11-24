package com.facebook.share.model;

import android.os.*;

static final class ShareLinkContent$1 implements Parcelable$Creator<ShareLinkContent> {
    public ShareLinkContent createFromParcel(final Parcel parcel) {
        return new ShareLinkContent(parcel);
    }
    
    public ShareLinkContent[] newArray(final int n) {
        return new ShareLinkContent[n];
    }
}