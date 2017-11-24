package com.google.android.gms.common.server.response;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public static class FieldMapPair implements SafeParcelable
{
    public static final zzb CREATOR;
    final String key;
    final int versionCode;
    final FastJsonResponse.Field<?, ?> zzamZ;
    
    static {
        CREATOR = new zzb();
    }
    
    FieldMapPair(final int versionCode, final String key, final FastJsonResponse.Field<?, ?> zzamZ) {
        this.versionCode = versionCode;
        this.key = key;
        this.zzamZ = zzamZ;
    }
    
    FieldMapPair(final String key, final FastJsonResponse.Field<?, ?> zzamZ) {
        this.versionCode = 1;
        this.key = key;
        this.zzamZ = zzamZ;
    }
    
    public int describeContents() {
        final zzb creator = FieldMapPair.CREATOR;
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final zzb creator = FieldMapPair.CREATOR;
        zzb.zza(this, parcel, n);
    }
}
