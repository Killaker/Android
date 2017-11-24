package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.*;

public static final class Builder
{
    private double zzaTu;
    private double zzaTv;
    private double zzaTw;
    private double zzaTx;
    
    public Builder() {
        this.zzaTu = Double.POSITIVE_INFINITY;
        this.zzaTv = Double.NEGATIVE_INFINITY;
        this.zzaTw = Double.NaN;
        this.zzaTx = Double.NaN;
    }
    
    private boolean zzj(final double n) {
        if (this.zzaTw <= this.zzaTx) {
            return this.zzaTw <= n && n <= this.zzaTx;
        }
        if (this.zzaTw > n) {
            final double n2 = dcmpg(n, this.zzaTx);
            final boolean b = false;
            if (n2 > 0) {
                return b;
            }
        }
        return true;
    }
    
    public LatLngBounds build() {
        zzx.zza(!Double.isNaN(this.zzaTw), (Object)"no included points");
        return new LatLngBounds(new LatLng(this.zzaTu, this.zzaTw), new LatLng(this.zzaTv, this.zzaTx));
    }
    
    public Builder include(final LatLng latLng) {
        this.zzaTu = Math.min(this.zzaTu, latLng.latitude);
        this.zzaTv = Math.max(this.zzaTv, latLng.latitude);
        final double longitude = latLng.longitude;
        if (Double.isNaN(this.zzaTw)) {
            this.zzaTw = longitude;
            this.zzaTx = longitude;
        }
        else if (!this.zzj(longitude)) {
            if (LatLngBounds.zzd(this.zzaTw, longitude) < LatLngBounds.zze(this.zzaTx, longitude)) {
                this.zzaTw = longitude;
                return this;
            }
            this.zzaTx = longitude;
            return this;
        }
        return this;
    }
}
