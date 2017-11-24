package com.google.android.gms.internal;

import java.io.*;

public static final class zza extends zzsu
{
    private static volatile zza[] zzaZZ;
    public Integer zzaZr;
    public zzf zzbaa;
    public zzf zzbab;
    public Boolean zzbac;
    
    public zza() {
        this.zzDQ();
    }
    
    public static zza[] zzDP() {
        Label_0027: {
            if (zza.zzaZZ != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zza.zzaZZ == null) {
                    zza.zzaZZ = new zza[0];
                }
                return zza.zzaZZ;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zza)) {
                return false;
            }
            final zza zza = (zza)o;
            if (this.zzaZr == null) {
                if (zza.zzaZr != null) {
                    return false;
                }
            }
            else if (!this.zzaZr.equals(zza.zzaZr)) {
                return false;
            }
            if (this.zzbaa == null) {
                if (zza.zzbaa != null) {
                    return false;
                }
            }
            else if (!this.zzbaa.equals(zza.zzbaa)) {
                return false;
            }
            if (this.zzbab == null) {
                if (zza.zzbab != null) {
                    return false;
                }
            }
            else if (!this.zzbab.equals(zza.zzbab)) {
                return false;
            }
            if (this.zzbac == null) {
                if (zza.zzbac != null) {
                    return false;
                }
            }
            else if (!this.zzbac.equals(zza.zzbac)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.zzaZr == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzaZr.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.zzbaa == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzbaa.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzbab == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzbab.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        final Boolean zzbac = this.zzbac;
        int hashCode4 = 0;
        if (zzbac != null) {
            hashCode4 = this.zzbac.hashCode();
        }
        return n4 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzaZr != null) {
            zzsn.zzA(1, this.zzaZr);
        }
        if (this.zzbaa != null) {
            zzsn.zza(2, this.zzbaa);
        }
        if (this.zzbab != null) {
            zzsn.zza(3, this.zzbab);
        }
        if (this.zzbac != null) {
            zzsn.zze(4, this.zzbac);
        }
        super.writeTo(zzsn);
    }
    
    public zza zzC(final zzsm zzsm) throws IOException {
    Label_0064:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0064;
                    }
                    continue;
                }
                case 0: {
                    break Label_0064;
                }
                case 8: {
                    this.zzaZr = zzsm.zzJb();
                    continue;
                }
                case 18: {
                    if (this.zzbaa == null) {
                        this.zzbaa = new zzf();
                    }
                    zzsm.zza(this.zzbaa);
                    continue;
                }
                case 26: {
                    if (this.zzbab == null) {
                        this.zzbab = new zzf();
                    }
                    zzsm.zza(this.zzbab);
                    continue;
                }
                case 32: {
                    this.zzbac = zzsm.zzJc();
                    continue;
                }
            }
        }
        return this;
    }
    
    public zza zzDQ() {
        this.zzaZr = null;
        this.zzbaa = null;
        this.zzbab = null;
        this.zzbac = null;
        this.zzbuu = -1;
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzaZr != null) {
            zzz += zzsn.zzC(1, this.zzaZr);
        }
        if (this.zzbaa != null) {
            zzz += zzsn.zzc(2, this.zzbaa);
        }
        if (this.zzbab != null) {
            zzz += zzsn.zzc(3, this.zzbab);
        }
        if (this.zzbac != null) {
            zzz += zzsn.zzf(4, this.zzbac);
        }
        return zzz;
    }
}
