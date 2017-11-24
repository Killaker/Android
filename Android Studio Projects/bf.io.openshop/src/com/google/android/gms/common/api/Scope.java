package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public final class Scope implements SafeParcelable
{
    public static final Parcelable$Creator<Scope> CREATOR;
    final int mVersionCode;
    private final String zzagB;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    Scope(final int mVersionCode, final String zzagB) {
        zzx.zzh(zzagB, "scopeUri must not be null or empty");
        this.mVersionCode = mVersionCode;
        this.zzagB = zzagB;
    }
    
    public Scope(final String s) {
        this(1, s);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof Scope && this.zzagB.equals(((Scope)o).zzagB));
    }
    
    @Override
    public int hashCode() {
        return this.zzagB.hashCode();
    }
    
    @Override
    public String toString() {
        return this.zzagB;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
    
    public String zzpb() {
        return this.zzagB;
    }
}
