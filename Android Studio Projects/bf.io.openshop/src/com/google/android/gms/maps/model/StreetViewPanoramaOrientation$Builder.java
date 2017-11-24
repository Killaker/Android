package com.google.android.gms.maps.model;

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
