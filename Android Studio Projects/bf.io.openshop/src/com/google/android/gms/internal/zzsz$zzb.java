package com.google.android.gms.internal;

import java.io.*;

public static final class zzb extends zzso<zzb>
{
    public String version;
    public int zzbuM;
    public String zzbuN;
    
    public zzb() {
        this.zzJD();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzb;
            b = false;
            if (b2) {
                final zzb zzb = (zzb)o;
                final int zzbuM = this.zzbuM;
                final int zzbuM2 = zzb.zzbuM;
                b = false;
                if (zzbuM == zzbuM2) {
                    if (this.zzbuN == null) {
                        final String zzbuN = zzb.zzbuN;
                        b = false;
                        if (zzbuN != null) {
                            return b;
                        }
                    }
                    else if (!this.zzbuN.equals(zzb.zzbuN)) {
                        return false;
                    }
                    if (this.version == null) {
                        final String version = zzb.version;
                        b = false;
                        if (version != null) {
                            return b;
                        }
                    }
                    else if (!this.version.equals(zzb.version)) {
                        return false;
                    }
                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                        if (zzb.zzbuj != null) {
                            final boolean empty = zzb.zzbuj.isEmpty();
                            b = false;
                            if (!empty) {
                                return b;
                            }
                        }
                        return true;
                    }
                    return this.zzbuj.equals(zzb.zzbuj);
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + this.zzbuM);
        int hashCode;
        if (this.zzbuN == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbuN.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.version == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.version.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        final zzsq zzbuj = this.zzbuj;
        int hashCode3 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode3 = 0;
            if (!empty) {
                hashCode3 = this.zzbuj.hashCode();
            }
        }
        return n3 + hashCode3;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbuM != 0) {
            zzsn.zzA(1, this.zzbuM);
        }
        if (!this.zzbuN.equals("")) {
            zzsn.zzn(2, this.zzbuN);
        }
        if (!this.version.equals("")) {
            zzsn.zzn(3, this.version);
        }
        super.writeTo(zzsn);
    }
    
    public zzb zzJD() {
        this.zzbuM = 0;
        this.zzbuN = "";
        this.version = "";
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzb zzT(final zzsm zzsm) throws IOException {
    Label_0057:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0057;
                    }
                    continue;
                }
                case 0: {
                    break Label_0057;
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
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 26: {
                            this.zzbuM = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 18: {
                    this.zzbuN = zzsm.readString();
                    continue;
                }
                case 26: {
                    this.version = zzsm.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzbuM != 0) {
            zzz += zzsn.zzC(1, this.zzbuM);
        }
        if (!this.zzbuN.equals("")) {
            zzz += zzsn.zzo(2, this.zzbuN);
        }
        if (!this.version.equals("")) {
            zzz += zzsn.zzo(3, this.version);
        }
        return zzz;
    }
}
