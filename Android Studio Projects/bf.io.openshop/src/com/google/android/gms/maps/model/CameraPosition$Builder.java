package com.google.android.gms.maps.model;

public static final class Builder
{
    private LatLng zzaSX;
    private float zzaSY;
    private float zzaSZ;
    private float zzaTa;
    
    public Builder() {
    }
    
    public Builder(final CameraPosition cameraPosition) {
        this.zzaSX = cameraPosition.target;
        this.zzaSY = cameraPosition.zoom;
        this.zzaSZ = cameraPosition.tilt;
        this.zzaTa = cameraPosition.bearing;
    }
    
    public Builder bearing(final float zzaTa) {
        this.zzaTa = zzaTa;
        return this;
    }
    
    public CameraPosition build() {
        return new CameraPosition(this.zzaSX, this.zzaSY, this.zzaSZ, this.zzaTa);
    }
    
    public Builder target(final LatLng zzaSX) {
        this.zzaSX = zzaSX;
        return this;
    }
    
    public Builder tilt(final float zzaSZ) {
        this.zzaSZ = zzaSZ;
        return this;
    }
    
    public Builder zoom(final float zzaSY) {
        this.zzaSY = zzaSY;
        return this;
    }
}
