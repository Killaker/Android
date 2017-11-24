package com.google.android.gms.maps.model;

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
