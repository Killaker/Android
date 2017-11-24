package com.google.android.gms.auth.firstparty.shared;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.os.*;

public class ScopeDetail implements SafeParcelable
{
    public static final zzc CREATOR;
    String description;
    final int version;
    List<String> zzYA;
    public FACLData zzYB;
    String zzYw;
    String zzYx;
    String zzYy;
    String zzYz;
    
    static {
        CREATOR = new zzc();
    }
    
    ScopeDetail(final int version, final String description, final String zzYw, final String zzYx, final String zzYy, final String zzYz, final List<String> zzYA, final FACLData zzYB) {
        this.version = version;
        this.description = description;
        this.zzYw = zzYw;
        this.zzYx = zzYx;
        this.zzYy = zzYy;
        this.zzYz = zzYz;
        this.zzYA = zzYA;
        this.zzYB = zzYB;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
