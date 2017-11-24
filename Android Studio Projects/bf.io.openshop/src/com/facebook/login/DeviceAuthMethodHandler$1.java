package com.facebook.login;

import android.os.*;

static final class DeviceAuthMethodHandler$1 implements Parcelable$Creator {
    public DeviceAuthMethodHandler createFromParcel(final Parcel parcel) {
        return new DeviceAuthMethodHandler(parcel);
    }
    
    public DeviceAuthMethodHandler[] newArray(final int n) {
        return new DeviceAuthMethodHandler[n];
    }
}