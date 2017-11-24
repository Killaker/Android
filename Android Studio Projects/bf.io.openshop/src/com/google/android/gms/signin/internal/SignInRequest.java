package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class SignInRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SignInRequest> CREATOR;
    final int mVersionCode;
    final ResolveAccountRequest zzbhj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzi();
    }
    
    SignInRequest(final int mVersionCode, final ResolveAccountRequest zzbhj) {
        this.mVersionCode = mVersionCode;
        this.zzbhj = zzbhj;
    }
    
    public SignInRequest(final ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzi.zza(this, parcel, n);
    }
    
    public ResolveAccountRequest zzFO() {
        return this.zzbhj;
    }
}
