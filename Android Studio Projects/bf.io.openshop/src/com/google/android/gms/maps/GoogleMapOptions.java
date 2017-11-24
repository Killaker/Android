package com.google.android.gms.maps;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.maps.model.*;
import android.content.*;
import android.util.*;
import com.google.android.gms.*;
import android.content.res.*;
import android.os.*;

public final class GoogleMapOptions implements SafeParcelable
{
    public static final zza CREATOR;
    private final int mVersionCode;
    private Boolean zzaRP;
    private Boolean zzaRQ;
    private int zzaRR;
    private CameraPosition zzaRS;
    private Boolean zzaRT;
    private Boolean zzaRU;
    private Boolean zzaRV;
    private Boolean zzaRW;
    private Boolean zzaRX;
    private Boolean zzaRY;
    private Boolean zzaRZ;
    private Boolean zzaSa;
    private Boolean zzaSb;
    
    static {
        CREATOR = new zza();
    }
    
    public GoogleMapOptions() {
        this.zzaRR = -1;
        this.mVersionCode = 1;
    }
    
    GoogleMapOptions(final int mVersionCode, final byte b, final byte b2, final int zzaRR, final CameraPosition zzaRS, final byte b3, final byte b4, final byte b5, final byte b6, final byte b7, final byte b8, final byte b9, final byte b10, final byte b11) {
        this.zzaRR = -1;
        this.mVersionCode = mVersionCode;
        this.zzaRP = com.google.android.gms.maps.internal.zza.zza(b);
        this.zzaRQ = com.google.android.gms.maps.internal.zza.zza(b2);
        this.zzaRR = zzaRR;
        this.zzaRS = zzaRS;
        this.zzaRT = com.google.android.gms.maps.internal.zza.zza(b3);
        this.zzaRU = com.google.android.gms.maps.internal.zza.zza(b4);
        this.zzaRV = com.google.android.gms.maps.internal.zza.zza(b5);
        this.zzaRW = com.google.android.gms.maps.internal.zza.zza(b6);
        this.zzaRX = com.google.android.gms.maps.internal.zza.zza(b7);
        this.zzaRY = com.google.android.gms.maps.internal.zza.zza(b8);
        this.zzaRZ = com.google.android.gms.maps.internal.zza.zza(b9);
        this.zzaSa = com.google.android.gms.maps.internal.zza.zza(b10);
        this.zzaSb = com.google.android.gms.maps.internal.zza.zza(b11);
    }
    
    public static GoogleMapOptions createFromAttributes(final Context context, final AttributeSet set) {
        if (set == null) {
            return null;
        }
        final TypedArray obtainAttributes = context.getResources().obtainAttributes(set, R.styleable.MapAttrs);
        final GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, set));
        obtainAttributes.recycle();
        return googleMapOptions;
    }
    
    public GoogleMapOptions ambientEnabled(final boolean b) {
        this.zzaSb = b;
        return this;
    }
    
    public GoogleMapOptions camera(final CameraPosition zzaRS) {
        this.zzaRS = zzaRS;
        return this;
    }
    
    public GoogleMapOptions compassEnabled(final boolean b) {
        this.zzaRU = b;
        return this;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Boolean getAmbientEnabled() {
        return this.zzaSb;
    }
    
    public CameraPosition getCamera() {
        return this.zzaRS;
    }
    
    public Boolean getCompassEnabled() {
        return this.zzaRU;
    }
    
    public Boolean getLiteMode() {
        return this.zzaRZ;
    }
    
    public Boolean getMapToolbarEnabled() {
        return this.zzaSa;
    }
    
    public int getMapType() {
        return this.zzaRR;
    }
    
    public Boolean getRotateGesturesEnabled() {
        return this.zzaRY;
    }
    
    public Boolean getScrollGesturesEnabled() {
        return this.zzaRV;
    }
    
    public Boolean getTiltGesturesEnabled() {
        return this.zzaRX;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.zzaRQ;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public Boolean getZOrderOnTop() {
        return this.zzaRP;
    }
    
    public Boolean getZoomControlsEnabled() {
        return this.zzaRT;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.zzaRW;
    }
    
    public GoogleMapOptions liteMode(final boolean b) {
        this.zzaRZ = b;
        return this;
    }
    
    public GoogleMapOptions mapToolbarEnabled(final boolean b) {
        this.zzaSa = b;
        return this;
    }
    
    public GoogleMapOptions mapType(final int zzaRR) {
        this.zzaRR = zzaRR;
        return this;
    }
    
    public GoogleMapOptions rotateGesturesEnabled(final boolean b) {
        this.zzaRY = b;
        return this;
    }
    
    public GoogleMapOptions scrollGesturesEnabled(final boolean b) {
        this.zzaRV = b;
        return this;
    }
    
    public GoogleMapOptions tiltGesturesEnabled(final boolean b) {
        this.zzaRX = b;
        return this;
    }
    
    public GoogleMapOptions useViewLifecycleInFragment(final boolean b) {
        this.zzaRQ = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public GoogleMapOptions zOrderOnTop(final boolean b) {
        this.zzaRP = b;
        return this;
    }
    
    public GoogleMapOptions zoomControlsEnabled(final boolean b) {
        this.zzaRT = b;
        return this;
    }
    
    public GoogleMapOptions zoomGesturesEnabled(final boolean b) {
        this.zzaRW = b;
        return this;
    }
    
    byte zzzK() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRP);
    }
    
    byte zzzL() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRQ);
    }
    
    byte zzzM() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRT);
    }
    
    byte zzzN() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRU);
    }
    
    byte zzzO() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRV);
    }
    
    byte zzzP() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRW);
    }
    
    byte zzzQ() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRX);
    }
    
    byte zzzR() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRY);
    }
    
    byte zzzS() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaRZ);
    }
    
    byte zzzT() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaSa);
    }
    
    byte zzzU() {
        return com.google.android.gms.maps.internal.zza.zze(this.zzaSb);
    }
}
