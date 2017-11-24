package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class UserAttributeParcel implements SafeParcelable
{
    public static final zzah CREATOR;
    public final String name;
    public final int versionCode;
    public final String zzaVW;
    public final long zzaZm;
    public final Long zzaZn;
    public final Float zzaZo;
    public final String zzamJ;
    
    static {
        CREATOR = new zzah();
    }
    
    UserAttributeParcel(final int versionCode, final String name, final long zzaZm, final Long zzaZn, final Float zzaZo, final String zzamJ, final String zzaVW) {
        this.versionCode = versionCode;
        this.name = name;
        this.zzaZm = zzaZm;
        this.zzaZn = zzaZn;
        this.zzaZo = zzaZo;
        this.zzamJ = zzamJ;
        this.zzaVW = zzaVW;
    }
    
    UserAttributeParcel(final zzai zzai) {
        this(zzai.mName, zzai.zzaZp, zzai.zzNc, zzai.zzaUa);
    }
    
    UserAttributeParcel(final String name, final long zzaZm, final Object o, final String zzaVW) {
        zzx.zzcM(name);
        this.versionCode = 1;
        this.name = name;
        this.zzaZm = zzaZm;
        this.zzaVW = zzaVW;
        if (o == null) {
            this.zzaZn = null;
            this.zzaZo = null;
            this.zzamJ = null;
            return;
        }
        if (o instanceof Long) {
            this.zzaZn = (Long)o;
            this.zzaZo = null;
            this.zzamJ = null;
            return;
        }
        if (o instanceof Float) {
            this.zzaZn = null;
            this.zzaZo = (Float)o;
            this.zzamJ = null;
            return;
        }
        if (o instanceof String) {
            this.zzaZn = null;
            this.zzaZo = null;
            this.zzamJ = (String)o;
            return;
        }
        throw new IllegalArgumentException("User attribute given of un-supported type");
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Object getValue() {
        if (this.zzaZn != null) {
            return this.zzaZn;
        }
        if (this.zzaZo != null) {
            return this.zzaZo;
        }
        if (this.zzamJ != null) {
            return this.zzamJ;
        }
        return null;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzah.zza(this, parcel, n);
    }
}
