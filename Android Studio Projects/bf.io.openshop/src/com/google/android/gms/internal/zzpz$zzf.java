package com.google.android.gms.internal;

import java.io.*;

public static final class zzf extends zzsu
{
    public Integer zzaZN;
    public String zzaZO;
    public Boolean zzaZP;
    public String[] zzaZQ;
    
    public zzf() {
        this.zzDJ();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzf)) {
                return false;
            }
            final zzf zzf = (zzf)o;
            if (this.zzaZN == null) {
                if (zzf.zzaZN != null) {
                    return false;
                }
            }
            else if (!this.zzaZN.equals(zzf.zzaZN)) {
                return false;
            }
            if (this.zzaZO == null) {
                if (zzf.zzaZO != null) {
                    return false;
                }
            }
            else if (!this.zzaZO.equals(zzf.zzaZO)) {
                return false;
            }
            if (this.zzaZP == null) {
                if (zzf.zzaZP != null) {
                    return false;
                }
            }
            else if (!this.zzaZP.equals(zzf.zzaZP)) {
                return false;
            }
            if (!zzss.equals(this.zzaZQ, zzf.zzaZQ)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int intValue;
        if (this.zzaZN == null) {
            intValue = 0;
        }
        else {
            intValue = this.zzaZN;
        }
        final int n2 = 31 * (intValue + n);
        int hashCode;
        if (this.zzaZO == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzaZO.hashCode();
        }
        final int n3 = 31 * (hashCode + n2);
        final Boolean zzaZP = this.zzaZP;
        int hashCode2 = 0;
        if (zzaZP != null) {
            hashCode2 = this.zzaZP.hashCode();
        }
        return 31 * (n3 + hashCode2) + zzss.hashCode(this.zzaZQ);
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzaZN != null) {
            zzsn.zzA(1, this.zzaZN);
        }
        if (this.zzaZO != null) {
            zzsn.zzn(2, this.zzaZO);
        }
        if (this.zzaZP != null) {
            zzsn.zze(3, this.zzaZP);
        }
        if (this.zzaZQ != null && this.zzaZQ.length > 0) {
            for (int i = 0; i < this.zzaZQ.length; ++i) {
                final String s = this.zzaZQ[i];
                if (s != null) {
                    zzsn.zzn(4, s);
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zzf zzDJ() {
        this.zzaZN = null;
        this.zzaZO = null;
        this.zzaZP = null;
        this.zzaZQ = zzsx.zzbuB;
        this.zzbuu = -1;
        return this;
    }
    
    public zzf zzy(final zzsm zzsm) throws IOException {
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
                    final int zzJb = zzsm.zzJb();
                    switch (zzJb) {
                        default: {
                            continue;
                        }
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6: {
                            this.zzaZN = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 18: {
                    this.zzaZO = zzsm.readString();
                    continue;
                }
                case 24: {
                    this.zzaZP = zzsm.zzJc();
                    continue;
                }
                case 34: {
                    final int zzc = zzsx.zzc(zzsm, 34);
                    int i;
                    if (this.zzaZQ == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzaZQ.length;
                    }
                    final String[] zzaZQ = new String[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzaZQ, 0, zzaZQ, 0, i);
                    }
                    while (i < -1 + zzaZQ.length) {
                        zzaZQ[i] = zzsm.readString();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzaZQ[i] = zzsm.readString();
                    this.zzaZQ = zzaZQ;
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int i = 0;
        int zzz = super.zzz();
        if (this.zzaZN != null) {
            zzz += zzsn.zzC(1, this.zzaZN);
        }
        if (this.zzaZO != null) {
            zzz += zzsn.zzo(2, this.zzaZO);
        }
        if (this.zzaZP != null) {
            zzz += zzsn.zzf(3, this.zzaZP);
        }
        if (this.zzaZQ != null && this.zzaZQ.length > 0) {
            int n = 0;
            int n2 = 0;
            while (i < this.zzaZQ.length) {
                final String s = this.zzaZQ[i];
                if (s != null) {
                    ++n2;
                    n += zzsn.zzgO(s);
                }
                ++i;
            }
            zzz = zzz + n + n2 * 1;
        }
        return zzz;
    }
}
