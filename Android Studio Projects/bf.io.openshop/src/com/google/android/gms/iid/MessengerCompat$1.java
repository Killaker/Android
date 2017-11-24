package com.google.android.gms.iid;

import android.os.*;

static final class MessengerCompat$1 implements Parcelable$Creator<MessengerCompat> {
    public MessengerCompat zzeO(final Parcel parcel) {
        final IBinder strongBinder = parcel.readStrongBinder();
        if (strongBinder != null) {
            return new MessengerCompat(strongBinder);
        }
        return null;
    }
    
    public MessengerCompat[] zzhm(final int n) {
        return new MessengerCompat[n];
    }
}