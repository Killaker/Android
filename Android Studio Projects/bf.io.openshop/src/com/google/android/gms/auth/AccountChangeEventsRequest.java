package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.*;
import android.accounts.*;
import android.text.*;
import android.os.*;

public class AccountChangeEventsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEventsRequest> CREATOR;
    final int mVersion;
    Account zzTI;
    @Deprecated
    String zzVa;
    int zzVc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    public AccountChangeEventsRequest() {
        this.mVersion = 1;
    }
    
    AccountChangeEventsRequest(final int mVersion, final int zzVc, final String zzVa, final Account zzTI) {
        this.mVersion = mVersion;
        this.zzVc = zzVc;
        this.zzVa = zzVa;
        if (zzTI == null && !TextUtils.isEmpty((CharSequence)zzVa)) {
            this.zzTI = new Account(zzVa, "com.google");
            return;
        }
        this.zzTI = zzTI;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Account getAccount() {
        return this.zzTI;
    }
    
    public String getAccountName() {
        return this.zzVa;
    }
    
    public int getEventIndex() {
        return this.zzVc;
    }
    
    public AccountChangeEventsRequest setAccount(final Account zzTI) {
        this.zzTI = zzTI;
        return this;
    }
    
    public AccountChangeEventsRequest setAccountName(final String zzVa) {
        this.zzVa = zzVa;
        return this;
    }
    
    public AccountChangeEventsRequest setEventIndex(final int zzVc) {
        this.zzVc = zzVc;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
