package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class StreetViewPanoramaLink implements SafeParcelable
{
    public static final zzk CREATOR;
    public final float bearing;
    private final int mVersionCode;
    public final String panoId;
    
    static {
        CREATOR = new zzk();
    }
    
    StreetViewPanoramaLink(final int mVersionCode, final String panoId, float n) {
        this.mVersionCode = mVersionCode;
        this.panoId = panoId;
        if (n <= 0.0) {
            n = 360.0f + n % 360.0f;
        }
        this.bearing = n % 360.0f;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaLink)) {
                return false;
            }
            final StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink)o;
            if (!this.panoId.equals(streetViewPanoramaLink.panoId) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaLink.bearing)) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.panoId, this.bearing);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("panoId", this.panoId).zzg("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzk.zza(this, parcel, n);
    }
}
