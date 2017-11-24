package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.os.*;

public class SignInResponse implements SafeParcelable
{
    public static final Parcelable$Creator<SignInResponse> CREATOR;
    final int mVersionCode;
    private final ConnectionResult zzams;
    private final ResolveAccountResponse zzbhk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzj();
    }
    
    public SignInResponse(final int n) {
        this(new ConnectionResult(n, null), null);
    }
    
    SignInResponse(final int mVersionCode, final ConnectionResult zzams, final ResolveAccountResponse zzbhk) {
        this.mVersionCode = mVersionCode;
        this.zzams = zzams;
        this.zzbhk = zzbhk;
    }
    
    public SignInResponse(final ConnectionResult connectionResult, final ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, resolveAccountResponse);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzj.zza(this, parcel, n);
    }
    
    public ResolveAccountResponse zzFP() {
        return this.zzbhk;
    }
    
    public ConnectionResult zzqY() {
        return this.zzams;
    }
}
