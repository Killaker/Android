package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public final class PointOfInterest implements SafeParcelable
{
    public static final zzg CREATOR;
    private final int mVersionCode;
    public final String name;
    public final LatLng zzaTG;
    public final String zzaTH;
    
    static {
        CREATOR = new zzg();
    }
    
    PointOfInterest(final int mVersionCode, final LatLng zzaTG, final String zzaTH, final String name) {
        this.mVersionCode = mVersionCode;
        this.zzaTG = zzaTG;
        this.zzaTH = zzaTH;
        this.name = name;
    }
    
    public int describeContents() {
        return 0;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
}
