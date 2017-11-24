package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.text.*;
import android.os.*;

public class AppMetadata implements SafeParcelable
{
    public static final zzb CREATOR;
    public final String packageName;
    public final int versionCode;
    public final String zzaMV;
    public final String zzaVt;
    public final String zzaVu;
    public final long zzaVv;
    public final long zzaVw;
    public final String zzaVx;
    public final boolean zzaVy;
    public final boolean zzaVz;
    
    static {
        CREATOR = new zzb();
    }
    
    AppMetadata(final int versionCode, final String packageName, final String zzaVt, final String zzaMV, final String zzaVu, final long zzaVv, final long zzaVw, final String zzaVx, final boolean zzaVy, final boolean zzaVz) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.zzaVt = zzaVt;
        this.zzaMV = zzaMV;
        this.zzaVu = zzaVu;
        this.zzaVv = zzaVv;
        this.zzaVw = zzaVw;
        this.zzaVx = zzaVx;
        if (versionCode >= 3) {
            this.zzaVy = zzaVy;
        }
        else {
            this.zzaVy = true;
        }
        this.zzaVz = zzaVz;
    }
    
    AppMetadata(final String packageName, String zzaVt, final String zzaMV, final String zzaVu, final long zzaVv, final long zzaVw, final String zzaVx, final boolean zzaVy, final boolean zzaVz) {
        zzx.zzcM(packageName);
        this.versionCode = 4;
        this.packageName = packageName;
        if (TextUtils.isEmpty((CharSequence)zzaVt)) {
            zzaVt = null;
        }
        this.zzaVt = zzaVt;
        this.zzaMV = zzaMV;
        this.zzaVu = zzaVu;
        this.zzaVv = zzaVv;
        this.zzaVw = zzaVw;
        this.zzaVx = zzaVx;
        this.zzaVy = zzaVy;
        this.zzaVz = zzaVz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
