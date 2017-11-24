package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class LatLngBounds implements SafeParcelable
{
    public static final zzd CREATOR;
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;
    
    static {
        CREATOR = new zzd();
    }
    
    LatLngBounds(final int mVersionCode, final LatLng southwest, final LatLng northeast) {
        zzx.zzb(southwest, "null southwest");
        zzx.zzb(northeast, "null northeast");
        zzx.zzb(northeast.latitude >= southwest.latitude, "southern latitude exceeds northern latitude (%s > %s)", southwest.latitude, northeast.latitude);
        this.mVersionCode = mVersionCode;
        this.southwest = southwest;
        this.northeast = northeast;
    }
    
    public LatLngBounds(final LatLng latLng, final LatLng latLng2) {
        this(1, latLng, latLng2);
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    private static double zzb(final double n, final double n2) {
        return (360.0 + (n - n2)) % 360.0;
    }
    
    private static double zzc(final double n, final double n2) {
        return (360.0 + (n2 - n)) % 360.0;
    }
    
    private boolean zzi(final double n) {
        return this.southwest.latitude <= n && n <= this.northeast.latitude;
    }
    
    private boolean zzj(final double n) {
        if (this.southwest.longitude <= this.northeast.longitude) {
            return this.southwest.longitude <= n && n <= this.northeast.longitude;
        }
        if (this.southwest.longitude > n) {
            final double n2 = dcmpg(n, this.northeast.longitude);
            final boolean b = false;
            if (n2 > 0) {
                return b;
            }
        }
        return true;
    }
    
    public boolean contains(final LatLng latLng) {
        return this.zzi(latLng.latitude) && this.zzj(latLng.longitude);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LatLngBounds)) {
                return false;
            }
            final LatLngBounds latLngBounds = (LatLngBounds)o;
            if (!this.southwest.equals(latLngBounds.southwest) || !this.northeast.equals(latLngBounds.northeast)) {
                return false;
            }
        }
        return true;
    }
    
    public LatLng getCenter() {
        final double n = (this.southwest.latitude + this.northeast.latitude) / 2.0;
        final double longitude = this.northeast.longitude;
        final double longitude2 = this.southwest.longitude;
        double n2;
        if (longitude2 <= longitude) {
            n2 = (longitude + longitude2) / 2.0;
        }
        else {
            n2 = (longitude2 + (longitude + 360.0)) / 2.0;
        }
        return new LatLng(n, n2);
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.southwest, this.northeast);
    }
    
    public LatLngBounds including(final LatLng latLng) {
        final double min = Math.min(this.southwest.latitude, latLng.latitude);
        final double max = Math.max(this.northeast.latitude, latLng.latitude);
        final double longitude = this.northeast.longitude;
        final double longitude2 = this.southwest.longitude;
        double longitude3 = latLng.longitude;
        double n;
        if (!this.zzj(longitude3)) {
            if (zzb(longitude2, longitude3) < zzc(longitude, longitude3)) {
                n = longitude;
            }
            else {
                n = longitude3;
                longitude3 = longitude2;
            }
        }
        else {
            longitude3 = longitude2;
            n = longitude;
        }
        return new LatLngBounds(new LatLng(min, longitude3), new LatLng(max, n));
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("southwest", this.southwest).zzg("northeast", this.northeast).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
    
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
                if (zzb(this.zzaTw, longitude) < zzc(this.zzaTx, longitude)) {
                    this.zzaTw = longitude;
                    return this;
                }
                this.zzaTx = longitude;
                return this;
            }
            return this;
        }
    }
}
