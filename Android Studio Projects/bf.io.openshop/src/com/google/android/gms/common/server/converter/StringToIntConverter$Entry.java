package com.google.android.gms.common.server.converter;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public static final class Entry implements SafeParcelable
{
    public static final zzc CREATOR;
    final int versionCode;
    final String zzamJ;
    final int zzamK;
    
    static {
        CREATOR = new zzc();
    }
    
    Entry(final int versionCode, final String zzamJ, final int zzamK) {
        this.versionCode = versionCode;
        this.zzamJ = zzamJ;
        this.zzamK = zzamK;
    }
    
    Entry(final String zzamJ, final int zzamK) {
        this.versionCode = 1;
        this.zzamJ = zzamJ;
        this.zzamK = zzamK;
    }
    
    public int describeContents() {
        final zzc creator = Entry.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzc creator = Entry.CREATOR;
        zzc.zza(this, parcel, n);
    }
}
