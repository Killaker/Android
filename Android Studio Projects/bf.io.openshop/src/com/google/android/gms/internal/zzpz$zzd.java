package com.google.android.gms.internal;

import java.io.*;

public static final class zzd extends zzsu
{
    public Integer zzaZF;
    public Boolean zzaZG;
    public String zzaZH;
    public String zzaZI;
    public String zzaZJ;
    
    public zzd() {
        this.zzDG();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzd)) {
                return false;
            }
            final zzd zzd = (zzd)o;
            if (this.zzaZF == null) {
                if (zzd.zzaZF != null) {
                    return false;
                }
            }
            else if (!this.zzaZF.equals(zzd.zzaZF)) {
                return false;
            }
            if (this.zzaZG == null) {
                if (zzd.zzaZG != null) {
                    return false;
                }
            }
            else if (!this.zzaZG.equals(zzd.zzaZG)) {
                return false;
            }
            if (this.zzaZH == null) {
                if (zzd.zzaZH != null) {
                    return false;
                }
            }
            else if (!this.zzaZH.equals(zzd.zzaZH)) {
                return false;
            }
            if (this.zzaZI == null) {
                if (zzd.zzaZI != null) {
                    return false;
                }
            }
            else if (!this.zzaZI.equals(zzd.zzaZI)) {
                return false;
            }
            if (this.zzaZJ == null) {
                if (zzd.zzaZJ != null) {
                    return false;
                }
            }
            else if (!this.zzaZJ.equals(zzd.zzaZJ)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int intValue;
        if (this.zzaZF == null) {
            intValue = 0;
        }
        else {
            intValue = this.zzaZF;
        }
        final int n2 = 31 * (intValue + n);
        int hashCode;
        if (this.zzaZG == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzaZG.hashCode();
        }
        final int n3 = 31 * (hashCode + n2);
        int hashCode2;
        if (this.zzaZH == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzaZH.hashCode();
        }
        final int n4 = 31 * (hashCode2 + n3);
        int hashCode3;
        if (this.zzaZI == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzaZI.hashCode();
        }
        final int n5 = 31 * (hashCode3 + n4);
        final String zzaZJ = this.zzaZJ;
        int hashCode4 = 0;
        if (zzaZJ != null) {
            hashCode4 = this.zzaZJ.hashCode();
        }
        return n5 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzaZF != null) {
            zzsn.zzA(1, this.zzaZF);
        }
        if (this.zzaZG != null) {
            zzsn.zze(2, this.zzaZG);
        }
        if (this.zzaZH != null) {
            zzsn.zzn(3, this.zzaZH);
        }
        if (this.zzaZI != null) {
            zzsn.zzn(4, this.zzaZI);
        }
        if (this.zzaZJ != null) {
            zzsn.zzn(5, this.zzaZJ);
        }
        super.writeTo(zzsn);
    }
    
    public zzd zzDG() {
        this.zzaZF = null;
        this.zzaZG = null;
        this.zzaZH = null;
        this.zzaZI = null;
        this.zzaZJ = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzd zzw(final zzsm zzsm) throws IOException {
    Label_0072:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0072;
                    }
                    continue;
                }
                case 0: {
                    break Label_0072;
                }
                case 8: {
                    final int zzJb = zzsm.zzJb();
                    switch (zzJb) {
                        default: {
                            continue;
                        }
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4: {
                            this.zzaZF = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 16: {
                    this.zzaZG = zzsm.zzJc();
                    continue;
                }
                case 26: {
                    this.zzaZH = zzsm.readString();
                    continue;
                }
                case 34: {
                    this.zzaZI = zzsm.readString();
                    continue;
                }
                case 42: {
                    this.zzaZJ = zzsm.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzaZF != null) {
            zzz += zzsn.zzC(1, this.zzaZF);
        }
        if (this.zzaZG != null) {
            zzz += zzsn.zzf(2, this.zzaZG);
        }
        if (this.zzaZH != null) {
            zzz += zzsn.zzo(3, this.zzaZH);
        }
        if (this.zzaZI != null) {
            zzz += zzsn.zzo(4, this.zzaZI);
        }
        if (this.zzaZJ != null) {
            zzz += zzsn.zzo(5, this.zzaZJ);
        }
        return zzz;
    }
}
