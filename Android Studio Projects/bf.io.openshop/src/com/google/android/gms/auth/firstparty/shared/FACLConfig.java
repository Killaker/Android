package com.google.android.gms.auth.firstparty.shared;

import com.google.android.gms.common.internal.safeparcel.*;
import android.text.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class FACLConfig implements SafeParcelable
{
    public static final zza CREATOR;
    final int version;
    boolean zzYm;
    String zzYn;
    boolean zzYo;
    boolean zzYp;
    boolean zzYq;
    boolean zzYr;
    
    static {
        CREATOR = new zza();
    }
    
    FACLConfig(final int version, final boolean zzYm, final String zzYn, final boolean zzYo, final boolean zzYp, final boolean zzYq, final boolean zzYr) {
        this.version = version;
        this.zzYm = zzYm;
        this.zzYn = zzYn;
        this.zzYo = zzYo;
        this.zzYp = zzYp;
        this.zzYq = zzYq;
        this.zzYr = zzYr;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof FACLConfig;
        boolean b2 = false;
        if (b) {
            final FACLConfig faclConfig = (FACLConfig)o;
            final boolean zzYm = this.zzYm;
            final boolean zzYm2 = faclConfig.zzYm;
            b2 = false;
            if (zzYm == zzYm2) {
                final boolean equals = TextUtils.equals((CharSequence)this.zzYn, (CharSequence)faclConfig.zzYn);
                b2 = false;
                if (equals) {
                    final boolean zzYo = this.zzYo;
                    final boolean zzYo2 = faclConfig.zzYo;
                    b2 = false;
                    if (zzYo == zzYo2) {
                        final boolean zzYp = this.zzYp;
                        final boolean zzYp2 = faclConfig.zzYp;
                        b2 = false;
                        if (zzYp == zzYp2) {
                            final boolean zzYq = this.zzYq;
                            final boolean zzYq2 = faclConfig.zzYq;
                            b2 = false;
                            if (zzYq == zzYq2) {
                                final boolean zzYr = this.zzYr;
                                final boolean zzYr2 = faclConfig.zzYr;
                                b2 = false;
                                if (zzYr == zzYr2) {
                                    b2 = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzYm, this.zzYn, this.zzYo, this.zzYp, this.zzYq, this.zzYr);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
