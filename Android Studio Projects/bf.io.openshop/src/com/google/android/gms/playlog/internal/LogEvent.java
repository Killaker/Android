package com.google.android.gms.playlog.internal;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.os.*;

public class LogEvent implements SafeParcelable
{
    public static final zzc CREATOR;
    public final String tag;
    public final int versionCode;
    public final long zzbdA;
    public final long zzbdB;
    public final byte[] zzbdC;
    public final Bundle zzbdD;
    
    static {
        CREATOR = new zzc();
    }
    
    LogEvent(final int versionCode, final long zzbdA, final long zzbdB, final String tag, final byte[] zzbdC, final Bundle zzbdD) {
        this.versionCode = versionCode;
        this.zzbdA = zzbdA;
        this.zzbdB = zzbdB;
        this.tag = tag;
        this.zzbdC = zzbdC;
        this.zzbdD = zzbdD;
    }
    
    public LogEvent(final long zzbdA, final long zzbdB, final String tag, final byte[] zzbdC, final String... array) {
        this.versionCode = 1;
        this.zzbdA = zzbdA;
        this.zzbdB = zzbdB;
        this.tag = tag;
        this.zzbdC = zzbdC;
        this.zzbdD = zzd(array);
    }
    
    private static Bundle zzd(final String... array) {
        Bundle bundle = null;
        if (array != null) {
            if (array.length % 2 != 0) {
                throw new IllegalArgumentException("extras must have an even number of elements");
            }
            final int n = array.length / 2;
            bundle = null;
            if (n != 0) {
                bundle = new Bundle(n);
                for (int i = 0; i < n; ++i) {
                    bundle.putString(array[i * 2], array[1 + i * 2]);
                }
            }
        }
        return bundle;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("tag=").append(this.tag).append(",");
        sb.append("eventTime=").append(this.zzbdA).append(",");
        sb.append("eventUptime=").append(this.zzbdB).append(",");
        if (this.zzbdD != null && !this.zzbdD.isEmpty()) {
            sb.append("keyValues=");
            for (final String s : this.zzbdD.keySet()) {
                sb.append("(").append(s).append(",");
                sb.append(this.zzbdD.getString(s)).append(")");
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
