package com.google.android.gms.internal;

import java.io.*;

public static final class zzf extends zzsu
{
    public long[] zzbaG;
    public long[] zzbaH;
    
    public zzf() {
        this.zzDY();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzf)) {
                return false;
            }
            final zzf zzf = (zzf)o;
            if (!zzss.equals(this.zzbaG, zzf.zzbaG)) {
                return false;
            }
            if (!zzss.equals(this.zzbaH, zzf.zzbaH)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzbaG)) + zzss.hashCode(this.zzbaH);
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbaG != null && this.zzbaG.length > 0) {
            for (int i = 0; i < this.zzbaG.length; ++i) {
                zzsn.zza(1, this.zzbaG[i]);
            }
        }
        if (this.zzbaH != null) {
            final int length = this.zzbaH.length;
            int j = 0;
            if (length > 0) {
                while (j < this.zzbaH.length) {
                    zzsn.zza(2, this.zzbaH[j]);
                    ++j;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zzf zzDY() {
        this.zzbaG = zzsx.zzbux;
        this.zzbaH = zzsx.zzbux;
        this.zzbuu = -1;
        return this;
    }
    
    public zzf zzH(final zzsm zzsm) throws IOException {
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
                    final int zzc = zzsx.zzc(zzsm, 8);
                    int i;
                    if (this.zzbaG == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzbaG.length;
                    }
                    final long[] zzbaG = new long[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzbaG, 0, zzbaG, 0, i);
                    }
                    while (i < -1 + zzbaG.length) {
                        zzbaG[i] = zzsm.zzIZ();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzbaG[i] = zzsm.zzIZ();
                    this.zzbaG = zzbaG;
                    continue;
                }
                case 10: {
                    final int zzmq = zzsm.zzmq(zzsm.zzJf());
                    final int position = zzsm.getPosition();
                    int n = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzIZ();
                        ++n;
                    }
                    zzsm.zzms(position);
                    int j;
                    if (this.zzbaG == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzbaG.length;
                    }
                    final long[] zzbaG2 = new long[n + j];
                    if (j != 0) {
                        System.arraycopy(this.zzbaG, 0, zzbaG2, 0, j);
                    }
                    while (j < zzbaG2.length) {
                        zzbaG2[j] = zzsm.zzIZ();
                        ++j;
                    }
                    this.zzbaG = zzbaG2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 16: {
                    final int zzc2 = zzsx.zzc(zzsm, 16);
                    int k;
                    if (this.zzbaH == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzbaH.length;
                    }
                    final long[] zzbaH = new long[zzc2 + k];
                    if (k != 0) {
                        System.arraycopy(this.zzbaH, 0, zzbaH, 0, k);
                    }
                    while (k < -1 + zzbaH.length) {
                        zzbaH[k] = zzsm.zzIZ();
                        zzsm.zzIX();
                        ++k;
                    }
                    zzbaH[k] = zzsm.zzIZ();
                    this.zzbaH = zzbaH;
                    continue;
                }
                case 18: {
                    final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                    final int position2 = zzsm.getPosition();
                    int n2 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzIZ();
                        ++n2;
                    }
                    zzsm.zzms(position2);
                    int l;
                    if (this.zzbaH == null) {
                        l = 0;
                    }
                    else {
                        l = this.zzbaH.length;
                    }
                    final long[] zzbaH2 = new long[n2 + l];
                    if (l != 0) {
                        System.arraycopy(this.zzbaH, 0, zzbaH2, 0, l);
                    }
                    while (l < zzbaH2.length) {
                        zzbaH2[l] = zzsm.zzIZ();
                        ++l;
                    }
                    this.zzbaH = zzbaH2;
                    zzsm.zzmr(zzmq2);
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int i = 0;
        final int zzz = super.zzz();
        int n2;
        if (this.zzbaG != null && this.zzbaG.length > 0) {
            int j = 0;
            int n = 0;
            while (j < this.zzbaG.length) {
                n += zzsn.zzar(this.zzbaG[j]);
                ++j;
            }
            n2 = zzz + n + 1 * this.zzbaG.length;
        }
        else {
            n2 = zzz;
        }
        if (this.zzbaH != null && this.zzbaH.length > 0) {
            int n3 = 0;
            while (i < this.zzbaH.length) {
                n3 += zzsn.zzar(this.zzbaH[i]);
                ++i;
            }
            n2 = n2 + n3 + 1 * this.zzbaH.length;
        }
        return n2;
    }
}
