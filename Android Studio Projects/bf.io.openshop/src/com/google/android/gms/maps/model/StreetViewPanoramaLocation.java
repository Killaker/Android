package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class StreetViewPanoramaLocation implements SafeParcelable
{
    public static final zzl CREATOR;
    public final StreetViewPanoramaLink[] links;
    private final int mVersionCode;
    public final String panoId;
    public final LatLng position;
    
    static {
        CREATOR = new zzl();
    }
    
    StreetViewPanoramaLocation(final int mVersionCode, final StreetViewPanoramaLink[] links, final LatLng position, final String panoId) {
        this.mVersionCode = mVersionCode;
        this.links = links;
        this.position = position;
        this.panoId = panoId;
    }
    
    public StreetViewPanoramaLocation(final StreetViewPanoramaLink[] array, final LatLng latLng, final String s) {
        this(1, array, latLng, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaLocation)) {
                return false;
            }
            final StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation)o;
            if (!this.panoId.equals(streetViewPanoramaLocation.panoId) || !this.position.equals(streetViewPanoramaLocation.position)) {
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
        return zzw.hashCode(this.position, this.panoId);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("panoId", this.panoId).zzg("position", this.position.toString()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzl.zza(this, parcel, n);
    }
}
