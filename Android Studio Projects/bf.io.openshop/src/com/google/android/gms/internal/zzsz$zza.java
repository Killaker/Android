package com.google.android.gms.internal;

import java.io.*;

public static final class zza extends zzso<zza>
{
    public String[] zzbuI;
    public String[] zzbuJ;
    public int[] zzbuK;
    public long[] zzbuL;
    
    public zza() {
        this.zzJC();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zza;
            b = false;
            if (b2) {
                final zza zza = (zza)o;
                final boolean equals = zzss.equals(this.zzbuI, zza.zzbuI);
                b = false;
                if (equals) {
                    final boolean equals2 = zzss.equals(this.zzbuJ, zza.zzbuJ);
                    b = false;
                    if (equals2) {
                        final boolean equals3 = zzss.equals(this.zzbuK, zza.zzbuK);
                        b = false;
                        if (equals3) {
                            final boolean equals4 = zzss.equals(this.zzbuL, zza.zzbuL);
                            b = false;
                            if (equals4) {
                                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                    if (zza.zzbuj != null) {
                                        final boolean empty = zza.zzbuj.isEmpty();
                                        b = false;
                                        if (!empty) {
                                            return b;
                                        }
                                    }
                                    return true;
                                }
                                return this.zzbuj.equals(zza.zzbuj);
                            }
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzbuI)) + zzss.hashCode(this.zzbuJ)) + zzss.hashCode(this.zzbuK)) + zzss.hashCode(this.zzbuL));
        int hashCode;
        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbuj.hashCode();
        }
        return hashCode + n;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbuI != null && this.zzbuI.length > 0) {
            for (int i = 0; i < this.zzbuI.length; ++i) {
                final String s = this.zzbuI[i];
                if (s != null) {
                    zzsn.zzn(1, s);
                }
            }
        }
        if (this.zzbuJ != null && this.zzbuJ.length > 0) {
            for (int j = 0; j < this.zzbuJ.length; ++j) {
                final String s2 = this.zzbuJ[j];
                if (s2 != null) {
                    zzsn.zzn(2, s2);
                }
            }
        }
        if (this.zzbuK != null && this.zzbuK.length > 0) {
            for (int k = 0; k < this.zzbuK.length; ++k) {
                zzsn.zzA(3, this.zzbuK[k]);
            }
        }
        if (this.zzbuL != null) {
            final int length = this.zzbuL.length;
            int l = 0;
            if (length > 0) {
                while (l < this.zzbuL.length) {
                    zzsn.zzb(4, this.zzbuL[l]);
                    ++l;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zza zzJC() {
        this.zzbuI = zzsx.zzbuB;
        this.zzbuJ = zzsx.zzbuB;
        this.zzbuK = zzsx.zzbuw;
        this.zzbuL = zzsx.zzbux;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zza zzS(final zzsm zzsm) throws IOException {
    Label_0081:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0081;
                    }
                    continue;
                }
                case 0: {
                    break Label_0081;
                }
                case 10: {
                    final int zzc = zzsx.zzc(zzsm, 10);
                    int i;
                    if (this.zzbuI == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzbuI.length;
                    }
                    final String[] zzbuI = new String[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzbuI, 0, zzbuI, 0, i);
                    }
                    while (i < -1 + zzbuI.length) {
                        zzbuI[i] = zzsm.readString();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzbuI[i] = zzsm.readString();
                    this.zzbuI = zzbuI;
                    continue;
                }
                case 18: {
                    final int zzc2 = zzsx.zzc(zzsm, 18);
                    int j;
                    if (this.zzbuJ == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzbuJ.length;
                    }
                    final String[] zzbuJ = new String[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zzbuJ, 0, zzbuJ, 0, j);
                    }
                    while (j < -1 + zzbuJ.length) {
                        zzbuJ[j] = zzsm.readString();
                        zzsm.zzIX();
                        ++j;
                    }
                    zzbuJ[j] = zzsm.readString();
                    this.zzbuJ = zzbuJ;
                    continue;
                }
                case 24: {
                    final int zzc3 = zzsx.zzc(zzsm, 24);
                    int k;
                    if (this.zzbuK == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzbuK.length;
                    }
                    final int[] zzbuK = new int[zzc3 + k];
                    if (k != 0) {
                        System.arraycopy(this.zzbuK, 0, zzbuK, 0, k);
                    }
                    while (k < -1 + zzbuK.length) {
                        zzbuK[k] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++k;
                    }
                    zzbuK[k] = zzsm.zzJb();
                    this.zzbuK = zzbuK;
                    continue;
                }
                case 26: {
                    final int zzmq = zzsm.zzmq(zzsm.zzJf());
                    final int position = zzsm.getPosition();
                    int n = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n;
                    }
                    zzsm.zzms(position);
                    int l;
                    if (this.zzbuK == null) {
                        l = 0;
                    }
                    else {
                        l = this.zzbuK.length;
                    }
                    final int[] zzbuK2 = new int[n + l];
                    if (l != 0) {
                        System.arraycopy(this.zzbuK, 0, zzbuK2, 0, l);
                    }
                    while (l < zzbuK2.length) {
                        zzbuK2[l] = zzsm.zzJb();
                        ++l;
                    }
                    this.zzbuK = zzbuK2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 32: {
                    final int zzc4 = zzsx.zzc(zzsm, 32);
                    int length;
                    if (this.zzbuL == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzbuL.length;
                    }
                    final long[] zzbuL = new long[zzc4 + length];
                    if (length != 0) {
                        System.arraycopy(this.zzbuL, 0, zzbuL, 0, length);
                    }
                    while (length < -1 + zzbuL.length) {
                        zzbuL[length] = zzsm.zzJa();
                        zzsm.zzIX();
                        ++length;
                    }
                    zzbuL[length] = zzsm.zzJa();
                    this.zzbuL = zzbuL;
                    continue;
                }
                case 34: {
                    final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                    final int position2 = zzsm.getPosition();
                    int n2 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJa();
                        ++n2;
                    }
                    zzsm.zzms(position2);
                    int length2;
                    if (this.zzbuL == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzbuL.length;
                    }
                    final long[] zzbuL2 = new long[n2 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzbuL, 0, zzbuL2, 0, length2);
                    }
                    while (length2 < zzbuL2.length) {
                        zzbuL2[length2] = zzsm.zzJa();
                        ++length2;
                    }
                    this.zzbuL = zzbuL2;
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
        int n3;
        if (this.zzbuI != null && this.zzbuI.length > 0) {
            int j = 0;
            int n = 0;
            int n2 = 0;
            while (j < this.zzbuI.length) {
                final String s = this.zzbuI[j];
                if (s != null) {
                    ++n2;
                    n += zzsn.zzgO(s);
                }
                ++j;
            }
            n3 = zzz + n + n2 * 1;
        }
        else {
            n3 = zzz;
        }
        if (this.zzbuJ != null && this.zzbuJ.length > 0) {
            int k = 0;
            int n4 = 0;
            int n5 = 0;
            while (k < this.zzbuJ.length) {
                final String s2 = this.zzbuJ[k];
                if (s2 != null) {
                    ++n5;
                    n4 += zzsn.zzgO(s2);
                }
                ++k;
            }
            n3 = n3 + n4 + n5 * 1;
        }
        if (this.zzbuK != null && this.zzbuK.length > 0) {
            int l = 0;
            int n6 = 0;
            while (l < this.zzbuK.length) {
                n6 += zzsn.zzmx(this.zzbuK[l]);
                ++l;
            }
            n3 = n3 + n6 + 1 * this.zzbuK.length;
        }
        if (this.zzbuL != null && this.zzbuL.length > 0) {
            int n7 = 0;
            while (i < this.zzbuL.length) {
                n7 += zzsn.zzas(this.zzbuL[i]);
                ++i;
            }
            n3 = n3 + n7 + 1 * this.zzbuL.length;
        }
        return n3;
    }
}
