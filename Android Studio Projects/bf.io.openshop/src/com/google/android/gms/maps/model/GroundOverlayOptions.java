package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.dynamic.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class GroundOverlayOptions implements SafeParcelable
{
    public static final zzc CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    private final int mVersionCode;
    private LatLngBounds zzaRk;
    private float zzaTa;
    private float zzaTh;
    private boolean zzaTi;
    private BitmapDescriptor zzaTk;
    private LatLng zzaTl;
    private float zzaTm;
    private float zzaTn;
    private float zzaTo;
    private float zzaTp;
    private float zzaTq;
    private boolean zzaTr;
    
    static {
        CREATOR = new zzc();
    }
    
    public GroundOverlayOptions() {
        this.zzaTi = true;
        this.zzaTo = 0.0f;
        this.zzaTp = 0.5f;
        this.zzaTq = 0.5f;
        this.zzaTr = false;
        this.mVersionCode = 1;
    }
    
    GroundOverlayOptions(final int mVersionCode, final IBinder binder, final LatLng zzaTl, final float zzaTm, final float zzaTn, final LatLngBounds zzaRk, final float zzaTa, final float zzaTh, final boolean zzaTi, final float zzaTo, final float zzaTp, final float zzaTq, final boolean zzaTr) {
        this.zzaTi = true;
        this.zzaTo = 0.0f;
        this.zzaTp = 0.5f;
        this.zzaTq = 0.5f;
        this.zzaTr = false;
        this.mVersionCode = mVersionCode;
        this.zzaTk = new BitmapDescriptor(zzd.zza.zzbs(binder));
        this.zzaTl = zzaTl;
        this.zzaTm = zzaTm;
        this.zzaTn = zzaTn;
        this.zzaRk = zzaRk;
        this.zzaTa = zzaTa;
        this.zzaTh = zzaTh;
        this.zzaTi = zzaTi;
        this.zzaTo = zzaTo;
        this.zzaTp = zzaTp;
        this.zzaTq = zzaTq;
        this.zzaTr = zzaTr;
    }
    
    private GroundOverlayOptions zza(final LatLng zzaTl, final float zzaTm, final float zzaTn) {
        this.zzaTl = zzaTl;
        this.zzaTm = zzaTm;
        this.zzaTn = zzaTn;
        return this;
    }
    
    public GroundOverlayOptions anchor(final float zzaTp, final float zzaTq) {
        this.zzaTp = zzaTp;
        this.zzaTq = zzaTq;
        return this;
    }
    
    public GroundOverlayOptions bearing(final float n) {
        this.zzaTa = (360.0f + n % 360.0f) % 360.0f;
        return this;
    }
    
    public GroundOverlayOptions clickable(final boolean zzaTr) {
        this.zzaTr = zzaTr;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public float getAnchorU() {
        return this.zzaTp;
    }
    
    public float getAnchorV() {
        return this.zzaTq;
    }
    
    public float getBearing() {
        return this.zzaTa;
    }
    
    public LatLngBounds getBounds() {
        return this.zzaRk;
    }
    
    public float getHeight() {
        return this.zzaTn;
    }
    
    public BitmapDescriptor getImage() {
        return this.zzaTk;
    }
    
    public LatLng getLocation() {
        return this.zzaTl;
    }
    
    public float getTransparency() {
        return this.zzaTo;
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
    
    public GroundOverlayOptions image(final BitmapDescriptor zzaTk) {
        this.zzaTk = zzaTk;
        return this;
    }
    
    public boolean isClickable() {
        return this.zzaTr;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n) {
        boolean b = true;
        zzx.zza(this.zzaRk == null && b, (Object)"Position has already been set using positionFromBounds");
        zzx.zzb(latLng != null && b, (Object)"Location must be specified");
        if (n < 0.0f) {
            b = false;
        }
        zzx.zzb(b, (Object)"Width must be non-negative");
        return this.zza(latLng, n, -1.0f);
    }
    
    public GroundOverlayOptions position(final LatLng latLng, final float n, final float n2) {
        boolean b = true;
        zzx.zza(this.zzaRk == null && b, (Object)"Position has already been set using positionFromBounds");
        zzx.zzb(latLng != null && b, (Object)"Location must be specified");
        zzx.zzb(n >= 0.0f && b, (Object)"Width must be non-negative");
        if (n2 < 0.0f) {
            b = false;
        }
        zzx.zzb(b, (Object)"Height must be non-negative");
        return this.zza(latLng, n, n2);
    }
    
    public GroundOverlayOptions positionFromBounds(final LatLngBounds zzaRk) {
        zzx.zza(this.zzaTl == null, (Object)("Position has already been set using position: " + this.zzaTl));
        this.zzaRk = zzaRk;
        return this;
    }
    
    public GroundOverlayOptions transparency(final float zzaTo) {
        zzx.zzb(zzaTo >= 0.0f && zzaTo <= 1.0f, (Object)"Transparency must be in the range [0..1]");
        this.zzaTo = zzaTo;
        return this;
    }
    
    public GroundOverlayOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
    
    public GroundOverlayOptions zIndex(final float zzaTh) {
        this.zzaTh = zzaTh;
        return this;
    }
    
    IBinder zzAj() {
        return this.zzaTk.zzzH().asBinder();
    }
}
