package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.os.*;

public final class PolygonOptions implements SafeParcelable
{
    public static final zzh CREATOR;
    private final int mVersionCode;
    private final List<LatLng> zzaTJ;
    private final List<List<LatLng>> zzaTK;
    private boolean zzaTL;
    private float zzaTe;
    private int zzaTf;
    private int zzaTg;
    private float zzaTh;
    private boolean zzaTi;
    private boolean zzaTr;
    
    static {
        CREATOR = new zzh();
    }
    
    public PolygonOptions() {
        this.zzaTe = 10.0f;
        this.zzaTf = -16777216;
        this.zzaTg = 0;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.zzaTL = false;
        this.zzaTr = false;
        this.mVersionCode = 1;
        this.zzaTJ = new ArrayList<LatLng>();
        this.zzaTK = new ArrayList<List<LatLng>>();
    }
    
    PolygonOptions(final int mVersionCode, final List<LatLng> zzaTJ, final List zzaTK, final float zzaTe, final int zzaTf, final int zzaTg, final float zzaTh, final boolean zzaTi, final boolean zzaTL, final boolean zzaTr) {
        this.zzaTe = 10.0f;
        this.zzaTf = -16777216;
        this.zzaTg = 0;
        this.zzaTh = 0.0f;
        this.zzaTi = true;
        this.zzaTL = false;
        this.zzaTr = false;
        this.mVersionCode = mVersionCode;
        this.zzaTJ = zzaTJ;
        this.zzaTK = (List<List<LatLng>>)zzaTK;
        this.zzaTe = zzaTe;
        this.zzaTf = zzaTf;
        this.zzaTg = zzaTg;
        this.zzaTh = zzaTh;
        this.zzaTi = zzaTi;
        this.zzaTL = zzaTL;
        this.zzaTr = zzaTr;
    }
    
    public PolygonOptions add(final LatLng latLng) {
        this.zzaTJ.add(latLng);
        return this;
    }
    
    public PolygonOptions add(final LatLng... array) {
        this.zzaTJ.addAll(Arrays.asList(array));
        return this;
    }
    
    public PolygonOptions addAll(final Iterable<LatLng> iterable) {
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.zzaTJ.add(iterator.next());
        }
        return this;
    }
    
    public PolygonOptions addHole(final Iterable<LatLng> iterable) {
        final ArrayList<LatLng> list = new ArrayList<LatLng>();
        final Iterator<LatLng> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        this.zzaTK.add(list);
        return this;
    }
    
    public PolygonOptions clickable(final boolean zzaTr) {
        this.zzaTr = zzaTr;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public PolygonOptions fillColor(final int zzaTg) {
        this.zzaTg = zzaTg;
        return this;
    }
    
    public PolygonOptions geodesic(final boolean zzaTL) {
        this.zzaTL = zzaTL;
        return this;
    }
    
    public int getFillColor() {
        return this.zzaTg;
    }
    
    public List<List<LatLng>> getHoles() {
        return this.zzaTK;
    }
    
    public List<LatLng> getPoints() {
        return this.zzaTJ;
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
    
    public boolean isClickable() {
        return this.zzaTr;
    }
    
    public boolean isGeodesic() {
        return this.zzaTL;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public PolygonOptions strokeColor(final int zzaTf) {
        this.zzaTf = zzaTf;
        return this;
    }
    
    public PolygonOptions strokeWidth(final float zzaTe) {
        this.zzaTe = zzaTe;
        return this;
    }
    
    public PolygonOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public PolygonOptions zIndex(final float zzaTh) {
        this.zzaTh = zzaTh;
        return this;
    }
    
    List zzAl() {
        return this.zzaTK;
    }
}
