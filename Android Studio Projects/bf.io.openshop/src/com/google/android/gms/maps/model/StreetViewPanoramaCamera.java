package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class StreetViewPanoramaCamera implements SafeParcelable
{
    public static final zzj CREATOR;
    public final float bearing;
    private final int mVersionCode;
    public final float tilt;
    public final float zoom;
    private StreetViewPanoramaOrientation zzaTN;
    
    static {
        CREATOR = new zzj();
    }
    
    public StreetViewPanoramaCamera(final float n, final float n2, final float n3) {
        this(1, n, n2, n3);
    }
    
    StreetViewPanoramaCamera(final int mVersionCode, float zoom, final float n, final float n2) {
        zzx.zzb(-90.0f <= n && n <= 90.0f, (Object)"Tilt needs to be between -90 and 90 inclusive");
        this.mVersionCode = mVersionCode;
        if (zoom <= 0.0) {
            zoom = 0.0f;
        }
        this.zoom = zoom;
        this.tilt = n + 0.0f;
        float n3;
        if (n2 <= 0.0) {
            n3 = 360.0f + n2 % 360.0f;
        }
        else {
            n3 = n2;
        }
        this.bearing = n3 % 360.0f;
        this.zzaTN = new StreetViewPanoramaOrientation.Builder().tilt(n).bearing(n2).build();
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static Builder builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
        return new Builder(streetViewPanoramaCamera);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof StreetViewPanoramaCamera)) {
                return false;
            }
            final StreetViewPanoramaCamera streetViewPanoramaCamera = (StreetViewPanoramaCamera)o;
            if (Float.floatToIntBits(this.zoom) != Float.floatToIntBits(streetViewPanoramaCamera.zoom) || Float.floatToIntBits(this.tilt) != Float.floatToIntBits(streetViewPanoramaCamera.tilt) || Float.floatToIntBits(this.bearing) != Float.floatToIntBits(streetViewPanoramaCamera.bearing)) {
                return false;
            }
        }
        return true;
    }
    
    public StreetViewPanoramaOrientation getOrientation() {
        return this.zzaTN;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zoom, this.tilt, this.bearing);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("zoom", this.zoom).zzg("tilt", this.tilt).zzg("bearing", this.bearing).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzj.zza(this, parcel, n);
    }
    
    public static final class Builder
    {
        public float bearing;
        public float tilt;
        public float zoom;
        
        public Builder() {
        }
        
        public Builder(final StreetViewPanoramaCamera streetViewPanoramaCamera) {
            this.zoom = streetViewPanoramaCamera.zoom;
            this.bearing = streetViewPanoramaCamera.bearing;
            this.tilt = streetViewPanoramaCamera.tilt;
        }
        
        public Builder bearing(final float bearing) {
            this.bearing = bearing;
            return this;
        }
        
        public StreetViewPanoramaCamera build() {
            return new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
        }
        
        public Builder orientation(final StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.tilt = streetViewPanoramaOrientation.tilt;
            this.bearing = streetViewPanoramaOrientation.bearing;
            return this;
        }
        
        public Builder tilt(final float tilt) {
            this.tilt = tilt;
            return this;
        }
        
        public Builder zoom(final float zoom) {
            this.zoom = zoom;
            return this;
        }
    }
}
