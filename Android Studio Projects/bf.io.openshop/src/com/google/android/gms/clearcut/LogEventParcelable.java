package com.google.android.gms.clearcut;

import com.google.android.gms.common.internal.safeparcel.*;
import com.google.android.gms.playlog.internal.*;
import com.google.android.gms.internal.*;
import java.util.*;
import com.google.android.gms.common.internal.*;
import android.os.*;

public class LogEventParcelable implements SafeParcelable
{
    public static final zzd CREATOR;
    public final int versionCode;
    public PlayLoggerContext zzafh;
    public byte[] zzafi;
    public int[] zzafj;
    public final zzsz.zzd zzafk;
    public final zzb.zzb zzafl;
    public final zzb.zzb zzafm;
    
    static {
        CREATOR = new zzd();
    }
    
    LogEventParcelable(final int versionCode, final PlayLoggerContext zzafh, final byte[] zzafi, final int[] zzafj) {
        this.versionCode = versionCode;
        this.zzafh = zzafh;
        this.zzafi = zzafi;
        this.zzafj = zzafj;
        this.zzafk = null;
        this.zzafl = null;
        this.zzafm = null;
    }
    
    public LogEventParcelable(final PlayLoggerContext zzafh, final zzsz.zzd zzafk, final zzb.zzb zzafl, final zzb.zzb zzafm, final int[] zzafj) {
        this.versionCode = 1;
        this.zzafh = zzafh;
        this.zzafk = zzafk;
        this.zzafl = zzafl;
        this.zzafm = zzafm;
        this.zzafj = zzafj;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LogEventParcelable)) {
                return false;
            }
            final LogEventParcelable logEventParcelable = (LogEventParcelable)o;
            if (this.versionCode != logEventParcelable.versionCode || !zzw.equal(this.zzafh, logEventParcelable.zzafh) || !Arrays.equals(this.zzafi, logEventParcelable.zzafi) || !Arrays.equals(this.zzafj, logEventParcelable.zzafj) || !zzw.equal(this.zzafk, logEventParcelable.zzafk) || !zzw.equal(this.zzafl, logEventParcelable.zzafl) || !zzw.equal(this.zzafm, logEventParcelable.zzafm)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.versionCode, this.zzafh, this.zzafi, this.zzafj, this.zzafk, this.zzafl, this.zzafm);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.versionCode);
        sb.append(", ");
        sb.append(this.zzafh);
        sb.append(", ");
        String s;
        if (this.zzafi == null) {
            s = null;
        }
        else {
            s = new String(this.zzafi);
        }
        sb.append(s);
        sb.append(", ");
        String zza;
        if (this.zzafj == null) {
            zza = null;
        }
        else {
            zza = zzv.zzcL(", ").zza(Arrays.asList(new int[][] { this.zzafj }));
        }
        sb.append(zza);
        sb.append(", ");
        sb.append(this.zzafk);
        sb.append(", ");
        sb.append(this.zzafl);
        sb.append(", ");
        sb.append(this.zzafm);
        sb.append("]");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
