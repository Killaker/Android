package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.os.*;

public final class PolylineOptions implements SafeParcelable
{
    public static final zzi CREATOR;
    private int mColor;
    private final int mVersionCode;
    private final List<LatLng> zzaTJ;
    private boolean zzaTL;
    private float zzaTh;
    private boolean zzaTi;
    private float zzaTm;
    private boolean zzaTr;
    
    static {
        CREATOR = new zzi();
    }
    
    public PolylineOptions() {
        this.zzaTm = 10.0f;
        this.mColor = -16777216;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.zzaTL = false;
        this.zzaTr = false;
        this.mVersionCode = 1;
        this.zzaTJ = new ArrayList<LatLng>();
    }
    
    PolylineOptions(final int mVersionCode, final List zzaTJ, final float zzaTm, final int mColor, final float zzaTh, final boolean zzaTi, final boolean zzaTL, final boolean zzaTr) {
        this.zzaTm = 10.0f;
        this.mColor = -16777216;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.zzaTL = false;
        this.zzaTr = false;
        this.mVersionCode = mVersionCode;
        this.zzaTJ = (List<LatLng>)zzaTJ;
        this.zzaTm = zzaTm;
        this.mColor = mColor;
        this.zzaTh = zzaTh;
        this.zzaTi = zzaTi;
        this.zzaTL = zzaTL;
        this.zzaTr = zzaTr;
    }
    
    public PolylineOptions add(final LatLng latLng) {
        this.zzaTJ.add(latLng);
        return this;
    }
    
    public PolylineOptions add(final LatLng... array) {
        this.zzaTJ.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolylineOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.zzaTJ.add(iterator.next());
        }
        return this;
    }
    
    public PolylineOptions clickable(final boolean zzaTr) {
        this.zzaTr = zzaTr;
        return this;
    }
    
    public PolylineOptions color(final int mColor) {
        this.mColor = mColor;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolylineOptions geodesic(final boolean zzaTL) {
        this.zzaTL = zzaTL;
        return this;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public List<LatLng> getPoints() {
        return this.zzaTJ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public float getWidth() {
        return this.zzaTm;
    }
    
    public float getZIndex() {
        return this.zzaTh;
    }
    
    public boolean isClickable() {
        return this.zzaTr;
    }
    
    public boolean isGeodesic() {
        return this.zzaTL;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public PolylineOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public PolylineOptions width(final float zzaTm) {
        this.zzaTm = zzaTm;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public PolylineOptions zIndex(final float zzaTh) {
        this.zzaTh = zzaTh;
        return this;
    }
}
