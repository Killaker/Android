package com.facebook.share.model;

import android.os.*;

static final class AppInviteContent$1 implements Parcelable$Creator<AppInviteContent> {
    public AppInviteContent createFromParcel(final Parcel parcel) {
        return new AppInviteContent(parcel);
    }
    
    public AppInviteContent[] newArray(final int n) {
        return new AppInviteContent[n];
    }
}