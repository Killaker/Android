package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class AccountChangeEvent implements SafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEvent> CREATOR;
    final int mVersion;
    final long zzUZ;
    final String zzVa;
    final int zzVb;
    final int zzVc;
    final String zzVd;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    AccountChangeEvent(final int mVersion, final long zzUZ, final String s, final int zzVb, final int zzVc, final String zzVd) {
        this.mVersion = mVersion;
        this.zzUZ = zzUZ;
        this.zzVa = zzx.zzz(s);
        this.zzVb = zzVb;
        this.zzVc = zzVc;
        this.zzVd = zzVd;
    }
    
    public AccountChangeEvent(final long zzUZ, final String s, final int zzVb, final int zzVc, final String zzVd) {
        this.mVersion = 1;
        this.zzUZ = zzUZ;
        this.zzVa = zzx.zzz(s);
        this.zzVb = zzVb;
        this.zzVc = zzVc;
        this.zzVd = zzVd;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AccountChangeEvent)) {
                return false;
            }
            final AccountChangeEvent accountChangeEvent = (AccountChangeEvent)o;
            if (this.mVersion != accountChangeEvent.mVersion || this.zzUZ != accountChangeEvent.zzUZ || !zzw.equal(this.zzVa, accountChangeEvent.zzVa) || this.zzVb != accountChangeEvent.zzVb || this.zzVc != accountChangeEvent.zzVc || !zzw.equal(this.zzVd, accountChangeEvent.zzVd)) {
                return false;
            }
        }
        return true;
    }
    
    public String getAccountName() {
        return this.zzVa;
    }
    
    public String getChangeData() {
        return this.zzVd;
    }
    
    public int getChangeType() {
        return this.zzVb;
    }
    
    public int getEventIndex() {
        return this.zzVc;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mVersion, this.zzUZ, this.zzVa, this.zzVb, this.zzVc, this.zzVd);
    }
    
    @Override
    public String toString() {
        String s = "UNKNOWN";
        switch (this.zzVb) {
            case 1: {
                s = "ADDED";
                break;
            }
            case 2: {
                s = "REMOVED";
                break;
            }
            case 4: {
                s = "RENAMED_TO";
                break;
            }
            case 3: {
                s = "RENAMED_FROM";
                break;
            }
        }
        return "AccountChangeEvent {accountName = " + this.zzVa + ", changeType = " + s + ", changeData = " + this.zzVd + ", eventIndex = " + this.zzVc + "}";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
}
