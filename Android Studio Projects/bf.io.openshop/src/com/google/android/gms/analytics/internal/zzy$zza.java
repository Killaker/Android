package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public static final class zza<V>
{
    private final V zzSA;
    private final zzlz<V> zzSB;
    private V zzSC;
    
    private zza(final zzlz<V> zzSB, final V zzSA) {
        zzx.zzz(zzSB);
        this.zzSB = zzSB;
        this.zzSA = zzSA;
    }
    
    static zza<Float> zza(final String s, final float n) {
        return zza(s, n, n);
    }
    
    static zza<Float> zza(final String s, final float n, final float n2) {
        return new zza<Float>(zzlz.zza(s, Float.valueOf(n2)), n);
    }
    
    static zza<Integer> zza(final String s, final int n, final int n2) {
        return new zza<Integer>(zzlz.zza(s, Integer.valueOf(n2)), n);
    }
    
    static zza<Long> zza(final String s, final long n, final long n2) {
        return new zza<Long>(zzlz.zza(s, Long.valueOf(n2)), n);
    }
    
    static zza<Boolean> zza(final String s, final boolean b, final boolean b2) {
        return new zza<Boolean>(zzlz.zzk(s, b2), b);
    }
    
    static zza<Long> zzb(final String s, final long n) {
        return zza(s, n, n);
    }
    
    static zza<Integer> zzd(final String s, final int n) {
        return zza(s, n, n);
    }
    
    static zza<String> zze(final String s, final String s2, final String s3) {
        return new zza<String>(zzlz.zzv(s, s3), s2);
    }
    
    static zza<Boolean> zzg(final String s, final boolean b) {
        return zza(s, b, b);
    }
    
    static zza<String> zzl(final String s, final String s2) {
        return zze(s, s2, s2);
    }
    
    public V get() {
        if (this.zzSC != null) {
            return this.zzSC;
        }
        if (zzd.zzakE && zzlz.isInitialized()) {
            return this.zzSB.zzpX();
        }
        return this.zzSA;
    }
}
