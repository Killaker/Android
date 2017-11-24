package com.facebook.login;

import android.os.*;

static final class LoginClient$Request$1 implements Parcelable$Creator {
    public Request createFromParcel(final Parcel parcel) {
        return new Request(parcel);
    }
    
    public Request[] newArray(final int n) {
        return new Request[n];
    }
}