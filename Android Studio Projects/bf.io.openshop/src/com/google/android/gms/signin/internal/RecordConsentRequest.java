package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class RecordConsentRequest implements SafeParcelable
{
    public static final Parcelable$Creator<RecordConsentRequest> CREATOR;
    final int mVersionCode;
    private final Account zzTI;
    private final String zzXd;
    private final Scope[] zzbhh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    RecordConsentRequest(final int mVersionCode, final Account zzTI, final Scope[] zzbhh, final String zzXd) {
        this.mVersionCode = mVersionCode;
        this.zzTI = zzTI;
        this.zzbhh = zzbhh;
        this.zzXd = zzXd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzTI;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    public Scope[] zzFM() {
        return this.zzbhh;
    }
    
    public String zzmR() {
        return this.zzXd;
    }
}
