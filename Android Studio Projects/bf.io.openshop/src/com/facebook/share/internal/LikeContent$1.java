package com.facebook.share.internal;

import android.os.*;

static final class LikeContent$1 implements Parcelable$Creator<LikeContent> {
    public LikeContent createFromParcel(final Parcel parcel) {
        return new LikeContent(parcel);
    }
    
    public LikeContent[] newArray(final int n) {
        return new LikeContent[n];
    }
}