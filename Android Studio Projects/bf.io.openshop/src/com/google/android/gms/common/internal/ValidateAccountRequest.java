package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.os.*;

@Deprecated
public class ValidateAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ValidateAccountRequest> CREATOR;
    final int mVersionCode;
    private final String zzVO;
    private final Scope[] zzafT;
    final IBinder zzakA;
    private final int zzamy;
    private final Bundle zzamz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    ValidateAccountRequest(final int mVersionCode, final int zzamy, final IBinder zzakA, final Scope[] zzafT, final Bundle zzamz, final String zzVO) {
        this.mVersionCode = mVersionCode;
        this.zzamy = zzamy;
        this.zzakA = zzakA;
        this.zzafT = zzafT;
        this.zzamz = zzamz;
        this.zzVO = zzVO;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getCallingPackage() {
        return this.zzVO;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzae.zza(this, parcel, n);
    }
    
    public Scope[] zzrd() {
        return this.zzafT;
    }
    
    public int zzre() {
        return this.zzamy;
    }
    
    public Bundle zzrf() {
        return this.zzamz;
    }
}
