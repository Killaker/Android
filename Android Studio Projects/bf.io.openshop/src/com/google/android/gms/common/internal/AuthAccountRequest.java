package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class AuthAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<AuthAccountRequest> CREATOR;
    final int mVersionCode;
    final Scope[] zzafT;
    final IBinder zzakA;
    Integer zzakB;
    Integer zzakC;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    AuthAccountRequest(final int mVersionCode, final IBinder zzakA, final Scope[] zzafT, final Integer zzakB, final Integer zzakC) {
        this.mVersionCode = mVersionCode;
        this.zzakA = zzakA;
        this.zzafT = zzafT;
        this.zzakB = zzakB;
        this.zzakC = zzakC;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
