package com.facebook.share.internal;

import android.os.*;

static final class ShareFeedContent$1 implements Parcelable$Creator<ShareFeedContent> {
    public ShareFeedContent createFromParcel(final Parcel parcel) {
        return new ShareFeedContent(parcel);
    }
    
    public ShareFeedContent[] newArray(final int n) {
        return new ShareFeedContent[n];
    }
}