package com.google.android.gms.common.internal.safeparcel;

import android.os.*;

public static class zza extends RuntimeException
{
    public zza(final String s, final Parcel parcel) {
        super(s + " Parcel: pos=" + parcel.dataPosition() + " size=" + parcel.dataSize());
    }
}
