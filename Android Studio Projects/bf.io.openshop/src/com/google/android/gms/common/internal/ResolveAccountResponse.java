package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.*;
import android.os.*;

public class ResolveAccountResponse implements SafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountResponse> CREATOR;
    final int mVersionCode;
    private boolean zzahx;
    IBinder zzakA;
    private ConnectionResult zzams;
    private boolean zzamt;
    
    static {
        CREATOR = (Parcelable$Creator)new zzz();
    }
    
    ResolveAccountResponse(final int mVersionCode, final IBinder zzakA, final ConnectionResult zzams, final boolean zzahx, final boolean zzamt) {
        this.mVersionCode = mVersionCode;
        this.zzakA = zzakA;
        this.zzams = zzams;
        this.zzahx = zzahx;
        this.zzamt = zzamt;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ResolveAccountResponse)) {
                return false;
            }
            final ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse)o;
            if (!this.zzams.equals(resolveAccountResponse.zzams) || !this.zzqX().equals(resolveAccountResponse.zzqX())) {
                return false;
            }
        }
        return true;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzz.zza(this, parcel, n);
    }
    
    public zzp zzqX() {
        return zzp.zza.zzaP(this.zzakA);
    }
    
    public ConnectionResult zzqY() {
        return this.zzams;
    }
    
    public boolean zzqZ() {
        return this.zzahx;
    }
    
    public boolean zzra() {
        return this.zzamt;
    }
}
