package com.google.android.gms.tagmanager;

import java.util.*;

static final class zza
{
    public final Object zzNc;
    public final String zzvs;
    
    zza(final String zzvs, final Object zzNc) {
        this.zzvs = zzvs;
        this.zzNc = zzNc;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof zza) {
            final zza zza = (zza)o;
            if (this.zzvs.equals(zza.zzvs) && this.zzNc.equals(zza.zzNc)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(new Integer[] { this.zzvs.hashCode(), this.zzNc.hashCode() });
    }
    
    @Override
    public String toString() {
        return "Key: " + this.zzvs + " value: " + this.zzNc.toString();
    }
}
