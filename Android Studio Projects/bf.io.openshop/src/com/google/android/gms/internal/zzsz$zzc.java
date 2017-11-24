package com.google.android.gms.internal;

import java.util.*;
import java.io.*;

public static final class zzc extends zzso<zzc>
{
    public byte[] zzbuO;
    public byte[][] zzbuP;
    public boolean zzbuQ;
    
    public zzc() {
        this.zzJE();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzc;
            b = false;
            if (b2) {
                final zzc zzc = (zzc)o;
                final boolean equals = Arrays.equals(this.zzbuO, zzc.zzbuO);
                b = false;
                if (equals) {
                    final boolean zza = zzss.zza(this.zzbuP, zzc.zzbuP);
                    b = false;
                    if (zza) {
                        final boolean zzbuQ = this.zzbuQ;
                        final boolean zzbuQ2 = zzc.zzbuQ;
                        b = false;
                        if (zzbuQ == zzbuQ2) {
                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                if (zzc.zzbuj != null) {
                                    final boolean empty = zzc.zzbuj.isEmpty();
                                    b = false;
                                    if (!empty) {
                                        return b;
                                    }
                                }
                                return true;
                            }
                            return this.zzbuj.equals(zzc.zzbuj);
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + Arrays.hashCode(this.zzbuO)) + zzss.zza(this.zzbuP));
        int n2;
        if (this.zzbuQ) {
            n2 = 1231;
        }
        else {
            n2 = 1237;
        }
        final int n3 = 31 * (n2 + n);
        int hashCode;
        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbuj.hashCode();
        }
        return hashCode + n3;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
            zzsn.zza(1, this.zzbuO);
        }
        if (this.zzbuP != null && this.zzbuP.length > 0) {
            for (int i = 0; i < this.zzbuP.length; ++i) {
                final byte[] array = this.zzbuP[i];
                if (array != null) {
                    zzsn.zza(2, array);
                }
            }
        }
        if (this.zzbuQ) {
            zzsn.zze(3, this.zzbuQ);
        }
        super.writeTo(zzsn);
    }
    
    public zzc zzJE() {
        this.zzbuO = zzsx.zzbuD;
        this.zzbuP = zzsx.zzbuC;
        this.zzbuQ = false;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzc zzU(final zzsm zzsm) throws IOException {
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
                case 10: {
                    this.zzbuO = zzsm.readBytes();
                    continue;
                }
                case 18: {
                    final int zzc = zzsx.zzc(zzsm, 18);
                    int i;
                    if (this.zzbuP == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzbuP.length;
                    }
                    final byte[][] zzbuP = new byte[zzc + i][];
                    if (i != 0) {
                        System.arraycopy(this.zzbuP, 0, zzbuP, 0, i);
                    }
                    while (i < -1 + zzbuP.length) {
                        zzbuP[i] = zzsm.readBytes();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzbuP[i] = zzsm.readBytes();
                    this.zzbuP = zzbuP;
                    continue;
                }
                case 24: {
                    this.zzbuQ = zzsm.zzJc();
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
        if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
            zzz += zzsn.zzb(1, this.zzbuO);
        }
        if (this.zzbuP != null && this.zzbuP.length > 0) {
            int n = 0;
            int n2 = 0;
            while (i < this.zzbuP.length) {
                final byte[] array = this.zzbuP[i];
                if (array != null) {
                    ++n2;
                    n += zzsn.zzG(array);
                }
                ++i;
            }
            zzz = zzz + n + n2 * 1;
        }
        if (this.zzbuQ) {
            zzz += zzsn.zzf(3, this.zzbuQ);
        }
        return zzz;
    }
}
