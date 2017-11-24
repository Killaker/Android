package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class StreetViewPanoramaOrientation implements SafeParcelable
{
    public static final zzm CREATOR;
    public final float bearing;
    private final int mVersionCode;
    public final float tilt;
    
    static {
        CREATOR = new zzm();
    }
    
    public StreetViewPanoramaOrientation(final float n, final float n2) {
        this(1, n, n2);
    }
    
    StreetViewPanoramaOrientation(final int mVersionCode, final float n, float n2) {
        zzx.zzb(-90.0f <= n && n <= 90.0f, (Object)"Tilt needs to be between -90 and 90 inclusive");
        this.mVersionCode = mVersionCode;
        this.tilt = 0.0f + n;
        if (n2 <= 0.0) {
            n2 = 360.0f + n2 % 360.0f;
        }
        this.bearing = n2 % 360.0f;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return new Builder(streetViewPanoramaOrientation);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaOrientation)) {
                return false;
            }
            final StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation)o;
            if (Float.floatToIntBits(this.tilt) != Float.floatToIntBits(streetViewPanoramaOrientation.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaOrientation.bearing)) {
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
        return zzw.hashCode(this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzm.zza(this, parcel, n);
    }
    
    public static final class Builder
    {
        public float bearing;
        public float tilt;
        
        public Builder() {
        }
        
        public Builder(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.bearing = streetViewPanoramaOrientation.bearing;
            this.tilt = streetViewPanoramaOrientation.tilt;
        }
        
        public Builder bearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }
        
        public StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
        }
        
        public Builder tilt(final float tilt) {
            this.tilt = tilt;
            return this;
        }
    }
}
