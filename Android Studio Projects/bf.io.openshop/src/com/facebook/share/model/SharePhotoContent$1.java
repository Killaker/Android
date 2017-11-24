package com.facebook.share.model;

import android.os.*;

static final class SharePhotoContent$1 implements Parcelable$Creator<SharePhotoContent> {
    public SharePhotoContent createFromParcel(final Parcel parcel) {
        return new SharePhotoContent(parcel);
    }
    
    public SharePhotoContent[] newArray(final int n) {
        return new SharePhotoContent[n];
    }
}