package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public final class EventParcel implements SafeParcelable
{
    public static final zzk CREATOR;
    public final String name;
    public final int versionCode;
    public final EventParams zzaVV;
    public final String zzaVW;
    public final long zzaVX;
    
    static {
        CREATOR = new zzk();
    }
    
    EventParcel(final int versionCode, final String name, final EventParams zzaVV, final String zzaVW, final long zzaVX) {
        this.versionCode = versionCode;
        this.name = name;
        this.zzaVV = zzaVV;
        this.zzaVW = zzaVW;
        this.zzaVX = zzaVX;
    }
    
    public EventParcel(final String name, final EventParams zzaVV, final String zzaVW, final long zzaVX) {
        this.versionCode = 1;
        this.name = name;
        this.zzaVV = zzaVV;
        this.zzaVW = zzaVW;
        this.zzaVX = zzaVX;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "origin=" + this.zzaVW + ",name=" + this.name + ",params=" + this.zzaVV;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzk.zza(this, parcel, n);
    }
}
