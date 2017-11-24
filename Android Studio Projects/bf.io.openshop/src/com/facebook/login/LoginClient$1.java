package com.facebook.login;

import android.os.*;

static final class LoginClient$1 implements Parcelable$Creator {
    public LoginClient createFromParcel(final Parcel parcel) {
        return new LoginClient(parcel);
    }
    
    public LoginClient[] newArray(final int n) {
        return new LoginClient[n];
    }
}