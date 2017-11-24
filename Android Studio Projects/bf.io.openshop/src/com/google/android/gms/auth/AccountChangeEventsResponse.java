package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class AccountChangeEventsResponse implements SafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEventsResponse> CREATOR;
    final int mVersion;
    final List<AccountChangeEvent> zzpH;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    AccountChangeEventsResponse(final int mVersion, final List<AccountChangeEvent> list) {
        this.mVersion = mVersion;
        this.zzpH = zzx.zzz(list);
    }
    
    public AccountChangeEventsResponse(final List<AccountChangeEvent> list) {
        this.mVersion = 1;
        this.zzpH = zzx.zzz(list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public List<AccountChangeEvent> getEvents() {
        return this.zzpH;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
