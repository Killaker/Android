package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.safeparcel.*;
import android.os.*;

public final class ConnectionEvent extends zzf implements SafeParcelable
{
    public static final Parcelable$Creator<ConnectionEvent> CREATOR;
    final int mVersionCode;
    private final long zzane;
    private int zzanf;
    private final String zzang;
    private final String zzanh;
    private final String zzani;
    private final String zzanj;
    private final String zzank;
    private final String zzanl;
    private final long zzanm;
    private final long zzann;
    private long zzano;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    ConnectionEvent(final int mVersionCode, final long zzane, final int zzanf, final String zzang, final String zzanh, final String zzani, final String zzanj, final String zzank, final String zzanl, final long zzanm, final long zzann) {
        this.mVersionCode = mVersionCode;
        this.zzane = zzane;
        this.zzanf = zzanf;
        this.zzang = zzang;
        this.zzanh = zzanh;
        this.zzani = zzani;
        this.zzanj = zzanj;
        this.zzano = -1L;
        this.zzank = zzank;
        this.zzanl = zzanl;
        this.zzanm = zzanm;
        this.zzann = zzann;
    }
    
    public ConnectionEvent(final long n, final int n2, final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final long n3, final long n4) {
        this(1, n, n2, s, s2, s3, s4, s5, s6, n3, n4);
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
        zza.zza(this, parcel, n);
    }
    
    public String zzrF() {
        return this.zzang;
    }
    
    public String zzrG() {
        return this.zzanh;
    }
    
    public String zzrH() {
        return this.zzani;
    }
    
    public String zzrI() {
        return this.zzanj;
    }
    
    public String zzrJ() {
        return this.zzank;
    }
    
    public String zzrK() {
        return this.zzanl;
    }
    
    @Override
    public long zzrL() {
        return this.zzano;
    }
    
    public long zzrM() {
        return this.zzann;
    }
    
    public long zzrN() {
        return this.zzanm;
    }
    
    @Override
    public String zzrO() {
        final StringBuilder append = new StringBuilder().append("\t").append(this.zzrF()).append("/").append(this.zzrG()).append("\t").append(this.zzrH()).append("/").append(this.zzrI()).append("\t");
        String zzank;
        if (this.zzank == null) {
            zzank = "";
        }
        else {
            zzank = this.zzank;
        }
        return append.append(zzank).append("\t").append(this.zzrM()).toString();
    }
}
