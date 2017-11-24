package com.google.android.gms.internal;

import java.io.*;

public static final class zza extends zzsu
{
    private static volatile zza[] zzaZR;
    public String name;
    public Boolean zzaZS;
    
    public zza() {
        this.zzDL();
    }
    
    public static zza[] zzDK() {
        Label_0027: {
            if (zza.zzaZR != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zza.zzaZR == null) {
                    zza.zzaZR = new zza[0];
                }
                return zza.zzaZR;
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
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            }
            else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.zzaZS == null) {
                if (zza.zzaZS != null) {
                    return false;
                }
            }
            else if (!this.zzaZS.equals(zza.zzaZS)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.name == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.name.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        final Boolean zzaZS = this.zzaZS;
        int hashCode2 = 0;
        if (zzaZS != null) {
            hashCode2 = this.zzaZS.hashCode();
        }
        return n2 + hashCode2;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.name != null) {
            zzsn.zzn(1, this.name);
        }
        if (this.zzaZS != null) {
            zzsn.zze(2, this.zzaZS);
        }
        super.writeTo(zzsn);
    }
    
    public zza zzDL() {
        this.name = null;
        this.zzaZS = null;
        this.zzbuu = -1;
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.name != null) {
            zzz += zzsn.zzo(1, this.name);
        }
        if (this.zzaZS != null) {
            zzz += zzsn.zzf(2, this.zzaZS);
        }
        return zzz;
    }
    
    public zza zzz(final zzsm zzsm) throws IOException {
    Label_0048:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0048;
                    }
                    continue;
                }
                case 0: {
                    break Label_0048;
                }
                case 10: {
                    this.name = zzsm.readString();
                    continue;
                }
                case 16: {
                    this.zzaZS = zzsm.zzJc();
                    continue;
                }
            }
        }
        return this;
    }
}
