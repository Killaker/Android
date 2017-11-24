package com.facebook;

import android.os.*;

static final class AccessToken$2 implements Parcelable$Creator {
    public AccessToken createFromParcel(final Parcel parcel) {
        return new AccessToken(parcel);
    }
    
    public AccessToken[] newArray(final int n) {
        return new AccessToken[n];
    }
}