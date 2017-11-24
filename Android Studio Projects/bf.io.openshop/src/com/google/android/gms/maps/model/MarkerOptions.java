package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.dynamic.*;
import android.os.*;

public final class MarkerOptions implements SafeParcelable
{
    public static final zzf CREATOR;
    private float mAlpha;
    private final int mVersionCode;
    private LatLng zzaSF;
    private BitmapDescriptor zzaTA;
    private boolean zzaTB;
    private boolean zzaTC;
    private float zzaTD;
    private float zzaTE;
    private float zzaTF;
    private boolean zzaTi;
    private float zzaTp;
    private float zzaTq;
    private String zzaTz;
    private String zzapg;
    
    static {
        CREATOR = new zzf();
    }
    
    public MarkerOptions() {
        this.zzaTp = 0.5f;
        this.zzaTq = 1.0f;
        this.zzaTi = true;
        this.zzaTC = false;
        this.zzaTD = 0.0f;
        this.zzaTE = 0.5f;
        this.zzaTF = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = 1;
    }
    
    MarkerOptions(final int mVersionCode, final LatLng zzaSF, final String zzapg, final String zzaTz, final IBinder binder, final float zzaTp, final float zzaTq, final boolean zzaTB, final boolean zzaTi, final boolean zzaTC, final float zzaTD, final float zzaTE, final float zzaTF, final float mAlpha) {
        this.zzaTp = 0.5f;
        this.zzaTq = 1.0f;
        this.zzaTi = true;
        this.zzaTC = false;
        this.zzaTD = 0.0f;
        this.zzaTE = 0.5f;
        this.zzaTF = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = mVersionCode;
        this.zzaSF = zzaSF;
        this.zzapg = zzapg;
        this.zzaTz = zzaTz;
        BitmapDescriptor zzaTA;
        if (binder == null) {
            zzaTA = null;
        }
        else {
            zzaTA = new BitmapDescriptor(zzd.zza.zzbs(binder));
        }
        this.zzaTA = zzaTA;
        this.zzaTp = zzaTp;
        this.zzaTq = zzaTq;
        this.zzaTB = zzaTB;
        this.zzaTi = zzaTi;
        this.zzaTC = zzaTC;
        this.zzaTD = zzaTD;
        this.zzaTE = zzaTE;
        this.zzaTF = zzaTF;
        this.mAlpha = mAlpha;
    }
    
    public MarkerOptions alpha(final float mAlpha) {
        this.mAlpha = mAlpha;
        return this;
    }
    
    public MarkerOptions anchor(final float zzaTp, final float zzaTq) {
        this.zzaTp = zzaTp;
        this.zzaTq = zzaTq;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MarkerOptions draggable(final boolean zzaTB) {
        this.zzaTB = zzaTB;
        return this;
    }
    
    public MarkerOptions flat(final boolean zzaTC) {
        this.zzaTC = zzaTC;
        return this;
    }
    
    public float getAlpha() {
        return this.mAlpha;
    }
    
    public float getAnchorU() {
        return this.zzaTp;
    }
    
    public float getAnchorV() {
        return this.zzaTq;
    }
    
    public BitmapDescriptor getIcon() {
        return this.zzaTA;
    }
    
    public float getInfoWindowAnchorU() {
        return this.zzaTE;
    }
    
    public float getInfoWindowAnchorV() {
        return this.zzaTF;
    }
    
    public LatLng getPosition() {
        return this.zzaSF;
    }
    
    public float getRotation() {
        return this.zzaTD;
    }
    
    public String getSnippet() {
        return this.zzaTz;
    }
    
    public String getTitle() {
        return this.zzapg;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public MarkerOptions icon(final BitmapDescriptor zzaTA) {
        this.zzaTA = zzaTA;
        return this;
    }
    
    public MarkerOptions infoWindowAnchor(final float zzaTE, final float zzaTF) {
        this.zzaTE = zzaTE;
        this.zzaTF = zzaTF;
        return this;
    }
    
    public boolean isDraggable() {
        return this.zzaTB;
    }
    
    public boolean isFlat() {
        return this.zzaTC;
    }
    
    public boolean isVisible() {
        return this.zzaTi;
    }
    
    public MarkerOptions position(final LatLng zzaSF) {
        this.zzaSF = zzaSF;
        return this;
    }
    
    public MarkerOptions rotation(final float zzaTD) {
        this.zzaTD = zzaTD;
        return this;
    }
    
    public MarkerOptions snippet(final String zzaTz) {
        this.zzaTz = zzaTz;
        return this;
    }
    
    public MarkerOptions title(final String zzapg) {
        this.zzapg = zzapg;
        return this;
    }
    
    public MarkerOptions visible(final boolean zzaTi) {
        this.zzaTi = zzaTi;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    IBinder zzAk() {
        if (this.zzaTA == null) {
            return null;
        }
        return this.zzaTA.zzzH().asBinder();
    }
}
