package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class PlayLoggerContext implements SafeParcelable
{
    public static final zze CREATOR;
    public final String packageName;
    public final int versionCode;
    public final int zzbdL;
    public final int zzbdM;
    public final String zzbdN;
    public final String zzbdO;
    public final boolean zzbdP;
    public final String zzbdQ;
    public final boolean zzbdR;
    public final int zzbdS;
    
    static {
        CREATOR = new zze();
    }
    
    public PlayLoggerContext(final int versionCode, final String packageName, final int zzbdL, final int zzbdM, final String zzbdN, final String zzbdO, final boolean zzbdP, final String zzbdQ, final boolean zzbdR, final int zzbdS) {
        this.versionCode = versionCode;
        this.packageName = packageName;
        this.zzbdL = zzbdL;
        this.zzbdM = zzbdM;
        this.zzbdN = zzbdN;
        this.zzbdO = zzbdO;
        this.zzbdP = zzbdP;
        this.zzbdQ = zzbdQ;
        this.zzbdR = zzbdR;
        this.zzbdS = zzbdS;
    }
    
    public PlayLoggerContext(final String s, final int zzbdL, final int zzbdM, final String zzbdQ, final String zzbdN, final String zzbdO, final boolean zzbdR, final int zzbdS) {
        this.versionCode = 1;
        this.packageName = zzx.zzz(s);
        this.zzbdL = zzbdL;
        this.zzbdM = zzbdM;
        this.zzbdQ = zzbdQ;
        this.zzbdN = zzbdN;
        this.zzbdO = zzbdO;
        this.zzbdP = !zzbdR;
        this.zzbdR = zzbdR;
        this.zzbdS = zzbdS;
    }
    
    public PlayLoggerContext(final String s, final int zzbdL, final int zzbdM, final String zzbdN, final String zzbdO, final boolean zzbdP) {
        this.versionCode = 1;
        this.packageName = zzx.zzz(s);
        this.zzbdL = zzbdL;
        this.zzbdM = zzbdM;
        this.zzbdQ = null;
        this.zzbdN = zzbdN;
        this.zzbdO = zzbdO;
        this.zzbdP = zzbdP;
        this.zzbdR = false;
        this.zzbdS = 0;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof PlayLoggerContext)) {
                return false;
            }
            final PlayLoggerContext playLoggerContext = (PlayLoggerContext)o;
            if (this.versionCode != playLoggerContext.versionCode || !this.packageName.equals(playLoggerContext.packageName) || this.zzbdL != playLoggerContext.zzbdL || this.zzbdM != playLoggerContext.zzbdM || !zzw.equal(this.zzbdQ, playLoggerContext.zzbdQ) || !zzw.equal(this.zzbdN, playLoggerContext.zzbdN) || !zzw.equal(this.zzbdO, playLoggerContext.zzbdO) || this.zzbdP != playLoggerContext.zzbdP || this.zzbdR != playLoggerContext.zzbdR || this.zzbdS != playLoggerContext.zzbdS) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.versionCode, this.packageName, this.zzbdL, this.zzbdM, this.zzbdQ, this.zzbdN, this.zzbdO, this.zzbdP, this.zzbdR, this.zzbdS);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PlayLoggerContext[");
        sb.append("versionCode=").append(this.versionCode).append(',');
        sb.append("package=").append(this.packageName).append(',');
        sb.append("packageVersionCode=").append(this.zzbdL).append(',');
        sb.append("logSource=").append(this.zzbdM).append(',');
        sb.append("logSourceName=").append(this.zzbdQ).append(',');
        sb.append("uploadAccount=").append(this.zzbdN).append(',');
        sb.append("loggingId=").append(this.zzbdO).append(',');
        sb.append("logAndroidId=").append(this.zzbdP).append(',');
        sb.append("isAnonymous=").append(this.zzbdR).append(',');
        sb.append("qosTier=").append(this.zzbdS);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
}
