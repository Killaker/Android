package com.google.android.gms.signin.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import android.content.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class AuthAccountResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<AuthAccountResult> CREATOR;
    final int mVersionCode;
    private int zzbhd;
    private Intent zzbhe;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public AuthAccountResult() {
        this(0, null);
    }
    
    AuthAccountResult(final int mVersionCode, final int zzbhd, final Intent zzbhe) {
        this.mVersionCode = mVersionCode;
        this.zzbhd = zzbhd;
        this.zzbhe = zzbhe;
    }
    
    public AuthAccountResult(final int n, final Intent intent) {
        this(2, n, intent);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public Status getStatus() {
        if (this.zzbhd == 0) {
            return Status.zzagC;
        }
        return Status.zzagG;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public int zzFK() {
        return this.zzbhd;
    }
    
    public Intent zzFL() {
        return this.zzbhe;
    }
}
