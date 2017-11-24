package com.google.android.gms.auth.api.signin.internal;

public class zze
{
    static int zzXy;
    private int zzXz;
    
    static {
        zze.zzXy = 31;
    }
    
    public zze() {
        this.zzXz = 1;
    }
    
    public zze zzP(final boolean b) {
        final int n = zze.zzXy * this.zzXz;
        int n2;
        if (b) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        this.zzXz = n2 + n;
        return this;
    }
    
    public int zzne() {
        return this.zzXz;
    }
    
    public zze zzp(final Object o) {
        final int n = zze.zzXy * this.zzXz;
        int hashCode;
        if (o == null) {
            hashCode = 0;
        }
        else {
            hashCode = o.hashCode();
        }
        this.zzXz = hashCode + n;
        return this;
    }
}
