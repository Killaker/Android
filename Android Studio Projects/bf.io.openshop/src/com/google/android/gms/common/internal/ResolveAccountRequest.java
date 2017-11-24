package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import com.google.android.gms.auth.api.signin.*;
import android.os.*;
import android.support.annotation.*;

public class ResolveAccountRequest implements SafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountRequest> CREATOR;
    final int mVersionCode;
    private final Account zzTI;
    private final int zzamq;
    private final GoogleSignInAccount zzamr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzy();
    }
    
    ResolveAccountRequest(final int mVersionCode, final Account zzTI, final int zzamq, final GoogleSignInAccount zzamr) {
        this.mVersionCode = mVersionCode;
        this.zzTI = zzTI;
        this.zzamq = zzamq;
        this.zzamr = zzamr;
    }
    
    public ResolveAccountRequest(final Account account, final int n, final GoogleSignInAccount googleSignInAccount) {
        this(2, account, n, googleSignInAccount);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzTI;
    }
    
    public int getSessionId() {
        return this.zzamq;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzy.zza(this, parcel, n);
    }
    
    @Nullable
    public GoogleSignInAccount zzqW() {
        return this.zzamr;
    }
}
