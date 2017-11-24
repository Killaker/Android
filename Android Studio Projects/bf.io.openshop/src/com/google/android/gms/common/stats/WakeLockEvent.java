package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.*;
import java.util.*;
import android.os.*;
import android.text.*;

public final class WakeLockEvent extends zzf implements SafeParcelable
{
    public static final Parcelable$Creator<WakeLockEvent> CREATOR;
    private final long mTimeout;
    final int mVersionCode;
    private final String zzanQ;
    private final int zzanR;
    private final List<String> zzanS;
    private final String zzanT;
    private int zzanU;
    private final String zzanV;
    private final String zzanW;
    private final float zzanX;
    private final long zzane;
    private int zzanf;
    private final long zzanm;
    private long zzano;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    WakeLockEvent(final int mVersionCode, final long zzane, final int zzanf, final String zzanQ, final int zzanR, final List<String> zzanS, final String zzanT, final long zzanm, final int zzanU, final String zzanV, final String zzanW, final float zzanX, final long mTimeout) {
        this.mVersionCode = mVersionCode;
        this.zzane = zzane;
        this.zzanf = zzanf;
        this.zzanQ = zzanQ;
        this.zzanV = zzanV;
        this.zzanR = zzanR;
        this.zzano = -1L;
        this.zzanS = zzanS;
        this.zzanT = zzanT;
        this.zzanm = zzanm;
        this.zzanU = zzanU;
        this.zzanW = zzanW;
        this.zzanX = zzanX;
        this.mTimeout = mTimeout;
    }
    
    public WakeLockEvent(final long n, final int n2, final String s, final int n3, final List<String> list, final String s2, final long n4, final int n5, final String s3, final String s4, final float n6, final long n7) {
        this(1, n, n2, s, n3, list, s2, n4, n5, s3, s4, n6, n7);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public int getEventType() {
        return this.zzanf;
    }
    
    @Override
    public long getTimeMillis() {
        return this.zzane;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public String zzrK() {
        return this.zzanT;
    }
    
    @Override
    public long zzrL() {
        return this.zzano;
    }
    
    public long zzrN() {
        return this.zzanm;
    }
    
    @Override
    public String zzrO() {
        final StringBuilder append = new StringBuilder().append("\t").append(this.zzrR()).append("\t").append(this.zzrT()).append("\t");
        String join;
        if (this.zzrU() == null) {
            join = "";
        }
        else {
            join = TextUtils.join((CharSequence)",", (Iterable)this.zzrU());
        }
        final StringBuilder append2 = append.append(join).append("\t").append(this.zzrV()).append("\t");
        String zzrS;
        if (this.zzrS() == null) {
            zzrS = "";
        }
        else {
            zzrS = this.zzrS();
        }
        final StringBuilder append3 = append2.append(zzrS).append("\t");
        String zzrW;
        if (this.zzrW() == null) {
            zzrW = "";
        }
        else {
            zzrW = this.zzrW();
        }
        return append3.append(zzrW).append("\t").append(this.zzrX()).toString();
    }
    
    public String zzrR() {
        return this.zzanQ;
    }
    
    public String zzrS() {
        return this.zzanV;
    }
    
    public int zzrT() {
        return this.zzanR;
    }
    
    public List<String> zzrU() {
        return this.zzanS;
    }
    
    public int zzrV() {
        return this.zzanU;
    }
    
    public String zzrW() {
        return this.zzanW;
    }
    
    public float zzrX() {
        return this.zzanX;
    }
    
    public long zzrY() {
        return this.mTimeout;
    }
}
