package com.facebook;

import android.os.*;

static final class Profile$2 implements Parcelable$Creator {
    public Profile createFromParcel(final Parcel parcel) {
        return new Profile(parcel, null);
    }
    
    public Profile[] newArray(final int n) {
        return new Profile[n];
    }
}