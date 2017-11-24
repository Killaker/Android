package com.google.android.gms.common.server;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class FavaDiagnosticsEntity implements SafeParcelable
{
    public static final zza CREATOR;
    final int mVersionCode;
    public final String zzamD;
    public final int zzamE;
    
    static {
        CREATOR = new zza();
    }
    
    public FavaDiagnosticsEntity(final int mVersionCode, final String zzamD, final int zzamE) {
        this.mVersionCode = mVersionCode;
        this.zzamD = zzamD;
        this.zzamE = zzamE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
