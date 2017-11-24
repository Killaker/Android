package com.google.android.gms.maps;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.internal.*;
import android.os.*;

public final class StreetViewPanoramaOptions implements SafeParcelable
{
    public static final zzb CREATOR;
    private final int mVersionCode;
    private Boolean zzaRQ;
    private Boolean zzaRW;
    private StreetViewPanoramaCamera zzaSD;
    private String zzaSE;
    private LatLng zzaSF;
    private Integer zzaSG;
    private Boolean zzaSH;
    private Boolean zzaSI;
    private Boolean zzaSJ;
    
    static {
        CREATOR = new zzb();
    }
    
    public StreetViewPanoramaOptions() {
        this.zzaSH = true;
        this.zzaRW = true;
        this.zzaSI = true;
        this.zzaSJ = true;
        this.mVersionCode = 1;
    }
    
    StreetViewPanoramaOptions(final int mVersionCode, final StreetViewPanoramaCamera zzaSD, final String zzaSE, final LatLng zzaSF, final Integer zzaSG, final byte b, final byte b2, final byte b3, final byte b4, final byte b5) {
        this.zzaSH = true;
        this.zzaRW = true;
        this.zzaSI = true;
        this.zzaSJ = true;
        this.mVersionCode = mVersionCode;
        this.zzaSD = zzaSD;
        this.zzaSF = zzaSF;
        this.zzaSG = zzaSG;
        this.zzaSE = zzaSE;
        this.zzaSH = zza.zza(b);
        this.zzaRW = zza.zza(b2);
        this.zzaSI = zza.zza(b3);
        this.zzaSJ = zza.zza(b4);
        this.zzaRQ = zza.zza(b5);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Boolean getPanningGesturesEnabled() {
        return this.zzaSI;
    }
    
    public String getPanoramaId() {
        return this.zzaSE;
    }
    
    public LatLng getPosition() {
        return this.zzaSF;
    }
    
    public Integer getRadius() {
        return this.zzaSG;
    }
    
    public Boolean getStreetNamesEnabled() {
        return this.zzaSJ;
    }
    
    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.zzaSD;
    }
    
    public Boolean getUseViewLifecycleInFragment() {
        return this.zzaRQ;
    }
    
    public Boolean getUserNavigationEnabled() {
        return this.zzaSH;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public Boolean getZoomGesturesEnabled() {
        return this.zzaRW;
    }
    
    public StreetViewPanoramaOptions panningGesturesEnabled(final boolean b) {
        this.zzaSI = b;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaCamera(final StreetViewPanoramaCamera zzaSD) {
        this.zzaSD = zzaSD;
        return this;
    }
    
    public StreetViewPanoramaOptions panoramaId(final String zzaSE) {
        this.zzaSE = zzaSE;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng zzaSF) {
        this.zzaSF = zzaSF;
        return this;
    }
    
    public StreetViewPanoramaOptions position(final LatLng zzaSF, final Integer zzaSG) {
        this.zzaSF = zzaSF;
        this.zzaSG = zzaSG;
        return this;
    }
    
    public StreetViewPanoramaOptions streetNamesEnabled(final boolean b) {
        this.zzaSJ = b;
        return this;
    }
    
    public StreetViewPanoramaOptions useViewLifecycleInFragment(final boolean b) {
        this.zzaRQ = b;
        return this;
    }
    
    public StreetViewPanoramaOptions userNavigationEnabled(final boolean b) {
        this.zzaSH = b;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public StreetViewPanoramaOptions zoomGesturesEnabled(final boolean b) {
        this.zzaRW = b;
        return this;
    }
    
    byte zzAa() {
        return zza.zze(this.zzaSH);
    }
    
    byte zzAb() {
        return zza.zze(this.zzaSI);
    }
    
    byte zzAc() {
        return zza.zze(this.zzaSJ);
    }
    
    byte zzzL() {
        return zza.zze(this.zzaRQ);
    }
    
    byte zzzP() {
        return zza.zze(this.zzaRW);
    }
}
