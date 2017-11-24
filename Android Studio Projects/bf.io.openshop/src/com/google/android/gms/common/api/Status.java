package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import android.content.*;
import android.os.*;

public final class Status implements Result, SafeParcelable
{
    public static final Parcelable$Creator<Status> CREATOR;
    public static final Status zzagC;
    public static final Status zzagD;
    public static final Status zzagE;
    public static final Status zzagF;
    public static final Status zzagG;
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int zzade;
    private final String zzafC;
    
    static {
        zzagC = new Status(0);
        zzagD = new Status(14);
        zzagE = new Status(8);
        zzagF = new Status(15);
        zzagG = new Status(16);
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    public Status(final int n) {
        this(n, null);
    }
    
    Status(final int mVersionCode, final int zzade, final String zzafC, final PendingIntent mPendingIntent) {
        this.mVersionCode = mVersionCode;
        this.zzade = zzade;
        this.zzafC = zzafC;
        this.mPendingIntent = mPendingIntent;
    }
    
    public Status(final int n, final String s) {
        this(1, n, s, null);
    }
    
    public Status(final int n, final String s, final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    private String zzpd() {
        if (this.zzafC != null) {
            return this.zzafC;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzade);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.mVersionCode == status.mVersionCode && this.zzade == status.zzade && zzw.equal(this.zzafC, status.zzafC) && zzw.equal(this.mPendingIntent, status.mPendingIntent)) {
                return true;
            }
        }
        return false;
    }
    
    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }
    
    @Override
    public Status getStatus() {
        return this;
    }
    
    public int getStatusCode() {
        return this.zzade;
    }
    
    public String getStatusMessage() {
        return this.zzafC;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mVersionCode, this.zzade, this.zzafC, this.mPendingIntent);
    }
    
    public boolean isCanceled() {
        return this.zzade == 16;
    }
    
    public boolean isInterrupted() {
        return this.zzade == 14;
    }
    
    public boolean isSuccess() {
        return this.zzade <= 0;
    }
    
    public void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public String toString() {
        return zzw.zzy(this).zzg("statusCode", this.zzpd()).zzg("resolution", this.mPendingIntent).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
    
    PendingIntent zzpc() {
        return this.mPendingIntent;
    }
}
