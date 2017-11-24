package com.google.android.gms.auth.firstparty.shared;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public class FACLData implements SafeParcelable
{
    public static final zzb CREATOR;
    final int version;
    FACLConfig zzYs;
    String zzYt;
    boolean zzYu;
    String zzYv;
    
    static {
        CREATOR = new zzb();
    }
    
    FACLData(final int version, final FACLConfig zzYs, final String zzYt, final boolean zzYu, final String zzYv) {
        this.version = version;
        this.zzYs = zzYs;
        this.zzYt = zzYt;
        this.zzYu = zzYu;
        this.zzYv = zzYv;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzb.zza(this, parcel, n);
    }
}
