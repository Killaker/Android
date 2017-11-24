package com.google.android.gms.common.internal;

import android.content.*;

private static final class zza
{
    private final String zzSU;
    private final ComponentName zzamc;
    
    public zza(final ComponentName componentName) {
        this.zzSU = null;
        this.zzamc = zzx.zzz(componentName);
    }
    
    public zza(final String s) {
        this.zzSU = zzx.zzcM(s);
        this.zzamc = null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zza)) {
                return false;
            }
            final zza zza = (zza)o;
            if (!zzw.equal(this.zzSU, zza.zzSU) || !zzw.equal(this.zzamc, zza.zzamc)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzSU, this.zzamc);
    }
    
    @Override
    public String toString() {
        if (this.zzSU == null) {
            return this.zzamc.flattenToString();
        }
        return this.zzSU;
    }
    
    public Intent zzqS() {
        if (this.zzSU != null) {
            return new Intent(this.zzSU).setPackage("com.google.android.gms");
        }
        return new Intent().setComponent(this.zzamc);
    }
}
