package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.api.*;
import android.os.*;

public class SignInButtonConfig implements SafeParcelable
{
    public static final Parcelable$Creator<SignInButtonConfig> CREATOR;
    final int mVersionCode;
    private final Scope[] zzafT;
    private final int zzamu;
    private final int zzamv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzaa();
    }
    
    SignInButtonConfig(final int mVersionCode, final int zzamu, final int zzamv, final Scope[] zzafT) {
        this.mVersionCode = mVersionCode;
        this.zzamu = zzamu;
        this.zzamv = zzamv;
        this.zzafT = zzafT;
    }
    
    public SignInButtonConfig(final int n, final int n2, final Scope[] array) {
        this(1, n, n2, array);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzaa.zza(this, parcel, n);
    }
    
    public int zzrb() {
        return this.zzamu;
    }
    
    public int zzrc() {
        return this.zzamv;
    }
    
    public Scope[] zzrd() {
        return this.zzafT;
    }
}
