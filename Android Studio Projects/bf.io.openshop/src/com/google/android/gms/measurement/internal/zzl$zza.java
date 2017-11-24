package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.*;
import com.google.android.gms.common.internal.*;

public static final class zza<V>
{
    private final V zzSA;
    private final zzlz<V> zzSB;
    private V zzSC;
    private final String zzvs;
    
    private zza(final String zzvs, final zzlz<V> zzSB, final V zzSA) {
        zzx.zzz(zzSB);
        this.zzSB = zzSB;
        this.zzSA = zzSA;
        this.zzvs = zzvs;
    }
    
    static zza<Integer> zzD(final String s, final int n) {
        return zzo(s, n, n);
    }
    
    static zza<String> zzN(final String s, final String s2) {
        return zzl(s, s2, s2);
    }
    
    static zza<Long> zzb(final String s, final long n, final long n2) {
        return new zza<Long>(s, zzlz.zza(s, Long.valueOf(n2)), n);
    }
    
    static zza<Boolean> zzb(final String s, final boolean b, final boolean b2) {
        return new zza<Boolean>(s, zzlz.zzk(s, b2), b);
    }
    
    static zza<Long> zze(final String s, final long n) {
        return zzb(s, n, n);
    }
    
    static zza<String> zzl(final String s, final String s2, final String s3) {
        return new zza<String>(s, zzlz.zzv(s, s3), s2);
    }
    
    static zza<Boolean> zzm(final String s, final boolean b) {
        return zzb(s, b, b);
    }
    
    static zza<Integer> zzo(final String s, final int n, final int n2) {
        return new zza<Integer>(s, zzlz.zza(s, Integer.valueOf(n2)), n);
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
    
    public V get(V zzSC) {
        if (this.zzSC != null) {
            zzSC = this.zzSC;
        }
        else if (zzSC == null) {
            if (zzd.zzakE && zzlz.isInitialized()) {
                return this.zzSB.zzpX();
            }
            return this.zzSA;
        }
        return zzSC;
    }
    
    public String getKey() {
        return this.zzvs;
    }
}
