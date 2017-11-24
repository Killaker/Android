package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class CheckServerAuthResult implements SafeParcelable
{
    public static final Parcelable$Creator<CheckServerAuthResult> CREATOR;
    final int mVersionCode;
    final boolean zzbhf;
    final List<Scope> zzbhg;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    CheckServerAuthResult(final int mVersionCode, final boolean zzbhf, final List<Scope> zzbhg) {
        this.mVersionCode = mVersionCode;
        this.zzbhf = zzbhf;
        this.zzbhg = zzbhg;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
