package com.google.android.gms.internal;

import java.io.*;

public static final class zzc extends zzsu
{
    private static volatile zzc[] zzaZA;
    public zzf zzaZB;
    public zzd zzaZC;
    public Boolean zzaZD;
    public String zzaZE;
    
    public zzc() {
        this.zzDF();
    }
    
    public static zzc[] zzDE() {
        Label_0027: {
            if (zzc.zzaZA != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzc.zzaZA == null) {
                    zzc.zzaZA = new zzc[0];
                }
                return zzc.zzaZA;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzc)) {
                return false;
            }
            final zzc zzc = (zzc)o;
            if (this.zzaZB == null) {
                if (zzc.zzaZB != null) {
                    return false;
                }
            }
            else if (!this.zzaZB.equals(zzc.zzaZB)) {
                return false;
            }
            if (this.zzaZC == null) {
                if (zzc.zzaZC != null) {
                    return false;
                }
            }
            else if (!this.zzaZC.equals(zzc.zzaZC)) {
                return false;
            }
            if (this.zzaZD == null) {
                if (zzc.zzaZD != null) {
                    return false;
                }
            }
            else if (!this.zzaZD.equals(zzc.zzaZD)) {
                return false;
            }
            if (this.zzaZE == null) {
                if (zzc.zzaZE != null) {
                    return false;
                }
            }
            else if (!this.zzaZE.equals(zzc.zzaZE)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.zzaZB == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzaZB.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.zzaZC == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzaZC.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzaZD == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzaZD.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        final String zzaZE = this.zzaZE;
        int hashCode4 = 0;
        if (zzaZE != null) {
            hashCode4 = this.zzaZE.hashCode();
        }
        return n4 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzaZB != null) {
            zzsn.zza(1, this.zzaZB);
        }
        if (this.zzaZC != null) {
            zzsn.zza(2, this.zzaZC);
        }
        if (this.zzaZD != null) {
            zzsn.zze(3, this.zzaZD);
        }
        if (this.zzaZE != null) {
            zzsn.zzn(4, this.zzaZE);
        }
        super.writeTo(zzsn);
    }
    
    public zzc zzDF() {
        this.zzaZB = null;
        this.zzaZC = null;
        this.zzaZD = null;
        this.zzaZE = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzc zzv(final zzsm zzsm) throws IOException {
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
                case 10: {
                    if (this.zzaZB == null) {
                        this.zzaZB = new zzf();
                    }
                    zzsm.zza(this.zzaZB);
                    continue;
                }
                case 18: {
                    if (this.zzaZC == null) {
                        this.zzaZC = new zzd();
                    }
                    zzsm.zza(this.zzaZC);
                    continue;
                }
                case 24: {
                    this.zzaZD = zzsm.zzJc();
                    continue;
                }
                case 34: {
                    this.zzaZE = zzsm.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzaZB != null) {
            zzz += zzsn.zzc(1, this.zzaZB);
        }
        if (this.zzaZC != null) {
            zzz += zzsn.zzc(2, this.zzaZC);
        }
        if (this.zzaZD != null) {
            zzz += zzsn.zzf(3, this.zzaZD);
        }
        if (this.zzaZE != null) {
            zzz += zzsn.zzo(4, this.zzaZE);
        }
        return zzz;
    }
}
