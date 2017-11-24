package com.facebook.share.model;

import android.os.*;

static final class AppGroupCreationContent$1 implements Parcelable$Creator<AppGroupCreationContent> {
    public AppGroupCreationContent createFromParcel(final Parcel parcel) {
        return new AppGroupCreationContent(parcel);
    }
    
    public AppGroupCreationContent[] newArray(final int n) {
        return new AppGroupCreationContent[n];
    }
}