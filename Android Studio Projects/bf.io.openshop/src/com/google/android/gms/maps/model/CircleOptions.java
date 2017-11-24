package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public final class CircleOptions implements SafeParcelable
{
    public static final zzb CREATOR;
    private final int mVersionCode;
    private LatLng zzaTc;
    private double zzaTd;
    private float zzaTe;
    private int zzaTf;
    private int zzaTg;
    private float zzaTh;
    private boolean zzaTi;
    
    static {
        CREATOR = new zzb();
    }
    
    public CircleOptions() {
        this.zzaTc = null;
        this.zzaTd = 0.0;
        this.zzaTe = 10.0f;
        this.zzaTf = -16777216;
        this.zzaTg = 0;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.mVersionCode = 1;
    }
    
    CircleOptions(final int mVersionCode, final LatLng zzaTc, final double zzaTd, final float zzaTe, final int zzaTf, final int zzaTg, final float zzaTh, final boolean zzaTi) {
        this.zzaTc = null;
        this.zzaTd = 0.0;
        this.zzaTe = 10.0f;
        this.zzaTf = -16777216;
        this.zzaTg = 0;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.mVersionCode = mVersionCode;
        this.zzaTc = zzaTc;
        this.zzaTd = zzaTd;
        this.zzaTe = zzaTe;
        this.zzaTf = zzaTf;
        this.zzaTg = zzaTg;
        this.zzaTh = zzaTh;
        this.zzaTi = zzaTi;
    }
    
    public CircleOptions center(final LatLng zzaTc) {
        this.zzaTc = zzaTc;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public CircleOptions fillColor(final int zzaTg) {
        this.zzaTg = zzaTg;
        return this;
    }
    
    public LatLng getCenter() {
        return this.zzaTc;
    }
    
    public int getFillColor() {
        return this.zzaTg;
    }
    
    public double getRadius() {
        return this.zzaTd;
    }
    
    public int getStrokeColor() {
        return this.zzaTf;
    }
    
    public float getStrokeWidth() {
        return this.zzaTe;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public float getZIndex() {
        return this.zzaTh;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public CircleOptions radius(final double zzaTd) {
        this.zzaTd = zzaTd;
        return this;
    }
    
    public CircleOptions strokeColor(final int zzaTf) {
        this.zzaTf = zzaTf;
        return this;
    }
    
    public CircleOptions strokeWidth(final float zzaTe) {
        this.zzaTe = zzaTe;
        return this;
    }
    
    public CircleOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public CircleOptions zIndex(final float zzaTh) {
        this.zzaTh = zzaTh;
        return this;
    }
}
