package com.google.android.gms.gcm;

import android.os.*;

public class zzd
{
    public static final zzd zzaMc;
    public static final zzd zzaMd;
    private final int zzaMe;
    private final int zzaMf;
    private final int zzaMg;
    
    static {
        zzaMc = new zzd(0, 30, 3600);
        zzaMd = new zzd(1, 30, 3600);
    }
    
    private zzd(final int zzaMe, final int zzaMf, final int zzaMg) {
        this.zzaMe = zzaMe;
        this.zzaMf = zzaMf;
        this.zzaMg = zzaMg;
    }
    
    public Bundle zzF(final Bundle bundle) {
        bundle.putInt("retry_policy", this.zzaMe);
        bundle.putInt("initial_backoff_seconds", this.zzaMf);
        bundle.putInt("maximum_backoff_seconds", this.zzaMg);
        return bundle;
    }
    
    public int zzym() {
        return this.zzaMe;
    }
    
    public int zzyn() {
        return this.zzaMf;
    }
    
    public int zzyo() {
        return this.zzaMg;
    }
}
