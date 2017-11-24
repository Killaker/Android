package com.google.android.gms.internal;

import java.io.*;

public static final class zzg extends zzso<zzg>
{
    private static volatile zzg[] zziW;
    public int[] zziX;
    public int[] zziY;
    public int[] zziZ;
    public int[] zzja;
    public int[] zzjb;
    public int[] zzjc;
    public int[] zzjd;
    public int[] zzje;
    public int[] zzjf;
    public int[] zzjg;
    
    public zzg() {
        this.zzL();
    }
    
    public static zzg[] zzK() {
        Label_0027: {
            if (zzg.zziW != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzg.zziW == null) {
                    zzg.zziW = new zzg[0];
                }
                return zzg.zziW;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzg;
            b = false;
            if (b2) {
                final zzg zzg = (zzg)o;
                final boolean equals = zzss.equals(this.zziX, zzg.zziX);
                b = false;
                if (equals) {
                    final boolean equals2 = zzss.equals(this.zziY, zzg.zziY);
                    b = false;
                    if (equals2) {
                        final boolean equals3 = zzss.equals(this.zziZ, zzg.zziZ);
                        b = false;
                        if (equals3) {
                            final boolean equals4 = zzss.equals(this.zzja, zzg.zzja);
                            b = false;
                            if (equals4) {
                                final boolean equals5 = zzss.equals(this.zzjb, zzg.zzjb);
                                b = false;
                                if (equals5) {
                                    final boolean equals6 = zzss.equals(this.zzjc, zzg.zzjc);
                                    b = false;
                                    if (equals6) {
                                        final boolean equals7 = zzss.equals(this.zzjd, zzg.zzjd);
                                        b = false;
                                        if (equals7) {
                                            final boolean equals8 = zzss.equals(this.zzje, zzg.zzje);
                                            b = false;
                                            if (equals8) {
                                                final boolean equals9 = zzss.equals(this.zzjf, zzg.zzjf);
                                                b = false;
                                                if (equals9) {
                                                    final boolean equals10 = zzss.equals(this.zzjg, zzg.zzjg);
                                                    b = false;
                                                    if (equals10) {
                                                        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                            if (zzg.zzbuj != null) {
                                                                final boolean empty = zzg.zzbuj.isEmpty();
                                                                b = false;
                                                                if (!empty) {
                                                                    return b;
                                                                }
                                                            }
                                                            return true;
                                                        }
                                                        return this.zzbuj.equals(zzg.zzbuj);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
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
        final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziX)) + zzss.hashCode(this.zziY)) + zzss.hashCode(this.zziZ)) + zzss.hashCode(this.zzja)) + zzss.hashCode(this.zzjb)) + zzss.hashCode(this.zzjc)) + zzss.hashCode(this.zzjd)) + zzss.hashCode(this.zzje)) + zzss.hashCode(this.zzjf)) + zzss.hashCode(this.zzjg));
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
        if (this.zziX != null && this.zziX.length > 0) {
            for (int i = 0; i < this.zziX.length; ++i) {
                zzsn.zzA(1, this.zziX[i]);
            }
        }
        if (this.zziY != null && this.zziY.length > 0) {
            for (int j = 0; j < this.zziY.length; ++j) {
                zzsn.zzA(2, this.zziY[j]);
            }
        }
        if (this.zziZ != null && this.zziZ.length > 0) {
            for (int k = 0; k < this.zziZ.length; ++k) {
                zzsn.zzA(3, this.zziZ[k]);
            }
        }
        if (this.zzja != null && this.zzja.length > 0) {
            for (int l = 0; l < this.zzja.length; ++l) {
                zzsn.zzA(4, this.zzja[l]);
            }
        }
        if (this.zzjb != null && this.zzjb.length > 0) {
            for (int n = 0; n < this.zzjb.length; ++n) {
                zzsn.zzA(5, this.zzjb[n]);
            }
        }
        if (this.zzjc != null && this.zzjc.length > 0) {
            for (int n2 = 0; n2 < this.zzjc.length; ++n2) {
                zzsn.zzA(6, this.zzjc[n2]);
            }
        }
        if (this.zzjd != null && this.zzjd.length > 0) {
            for (int n3 = 0; n3 < this.zzjd.length; ++n3) {
                zzsn.zzA(7, this.zzjd[n3]);
            }
        }
        if (this.zzje != null && this.zzje.length > 0) {
            for (int n4 = 0; n4 < this.zzje.length; ++n4) {
                zzsn.zzA(8, this.zzje[n4]);
            }
        }
        if (this.zzjf != null && this.zzjf.length > 0) {
            for (int n5 = 0; n5 < this.zzjf.length; ++n5) {
                zzsn.zzA(9, this.zzjf[n5]);
            }
        }
        if (this.zzjg != null) {
            final int length = this.zzjg.length;
            int n6 = 0;
            if (length > 0) {
                while (n6 < this.zzjg.length) {
                    zzsn.zzA(10, this.zzjg[n6]);
                    ++n6;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zzg zzL() {
        this.zziX = zzsx.zzbuw;
        this.zziY = zzsx.zzbuw;
        this.zziZ = zzsx.zzbuw;
        this.zzja = zzsx.zzbuw;
        this.zzjb = zzsx.zzbuw;
        this.zzjc = zzsx.zzbuw;
        this.zzjd = zzsx.zzbuw;
        this.zzje = zzsx.zzbuw;
        this.zzjf = zzsx.zzbuw;
        this.zzjg = zzsx.zzbuw;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzg zzg(final zzsm zzsm) throws IOException {
    Label_0193:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0193;
                    }
                    continue;
                }
                case 0: {
                    break Label_0193;
                }
                case 8: {
                    final int zzc = zzsx.zzc(zzsm, 8);
                    int i;
                    if (this.zziX == null) {
                        i = 0;
                    }
                    else {
                        i = this.zziX.length;
                    }
                    final int[] zziX = new int[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zziX, 0, zziX, 0, i);
                    }
                    while (i < -1 + zziX.length) {
                        zziX[i] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++i;
                    }
                    zziX[i] = zzsm.zzJb();
                    this.zziX = zziX;
                    continue;
                }
                case 10: {
                    final int zzmq = zzsm.zzmq(zzsm.zzJf());
                    final int position = zzsm.getPosition();
                    int n = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n;
                    }
                    zzsm.zzms(position);
                    int j;
                    if (this.zziX == null) {
                        j = 0;
                    }
                    else {
                        j = this.zziX.length;
                    }
                    final int[] zziX2 = new int[n + j];
                    if (j != 0) {
                        System.arraycopy(this.zziX, 0, zziX2, 0, j);
                    }
                    while (j < zziX2.length) {
                        zziX2[j] = zzsm.zzJb();
                        ++j;
                    }
                    this.zziX = zziX2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 16: {
                    final int zzc2 = zzsx.zzc(zzsm, 16);
                    int k;
                    if (this.zziY == null) {
                        k = 0;
                    }
                    else {
                        k = this.zziY.length;
                    }
                    final int[] zziY = new int[zzc2 + k];
                    if (k != 0) {
                        System.arraycopy(this.zziY, 0, zziY, 0, k);
                    }
                    while (k < -1 + zziY.length) {
                        zziY[k] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++k;
                    }
                    zziY[k] = zzsm.zzJb();
                    this.zziY = zziY;
                    continue;
                }
                case 18: {
                    final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                    final int position2 = zzsm.getPosition();
                    int n2 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n2;
                    }
                    zzsm.zzms(position2);
                    int l;
                    if (this.zziY == null) {
                        l = 0;
                    }
                    else {
                        l = this.zziY.length;
                    }
                    final int[] zziY2 = new int[n2 + l];
                    if (l != 0) {
                        System.arraycopy(this.zziY, 0, zziY2, 0, l);
                    }
                    while (l < zziY2.length) {
                        zziY2[l] = zzsm.zzJb();
                        ++l;
                    }
                    this.zziY = zziY2;
                    zzsm.zzmr(zzmq2);
                    continue;
                }
                case 24: {
                    final int zzc3 = zzsx.zzc(zzsm, 24);
                    int length;
                    if (this.zziZ == null) {
                        length = 0;
                    }
                    else {
                        length = this.zziZ.length;
                    }
                    final int[] zziZ = new int[zzc3 + length];
                    if (length != 0) {
                        System.arraycopy(this.zziZ, 0, zziZ, 0, length);
                    }
                    while (length < -1 + zziZ.length) {
                        zziZ[length] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length;
                    }
                    zziZ[length] = zzsm.zzJb();
                    this.zziZ = zziZ;
                    continue;
                }
                case 26: {
                    final int zzmq3 = zzsm.zzmq(zzsm.zzJf());
                    final int position3 = zzsm.getPosition();
                    int n3 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n3;
                    }
                    zzsm.zzms(position3);
                    int length2;
                    if (this.zziZ == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zziZ.length;
                    }
                    final int[] zziZ2 = new int[n3 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zziZ, 0, zziZ2, 0, length2);
                    }
                    while (length2 < zziZ2.length) {
                        zziZ2[length2] = zzsm.zzJb();
                        ++length2;
                    }
                    this.zziZ = zziZ2;
                    zzsm.zzmr(zzmq3);
                    continue;
                }
                case 32: {
                    final int zzc4 = zzsx.zzc(zzsm, 32);
                    int length3;
                    if (this.zzja == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zzja.length;
                    }
                    final int[] zzja = new int[zzc4 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzja, 0, zzja, 0, length3);
                    }
                    while (length3 < -1 + zzja.length) {
                        zzja[length3] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length3;
                    }
                    zzja[length3] = zzsm.zzJb();
                    this.zzja = zzja;
                    continue;
                }
                case 34: {
                    final int zzmq4 = zzsm.zzmq(zzsm.zzJf());
                    final int position4 = zzsm.getPosition();
                    int n4 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n4;
                    }
                    zzsm.zzms(position4);
                    int length4;
                    if (this.zzja == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.zzja.length;
                    }
                    final int[] zzja2 = new int[n4 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzja, 0, zzja2, 0, length4);
                    }
                    while (length4 < zzja2.length) {
                        zzja2[length4] = zzsm.zzJb();
                        ++length4;
                    }
                    this.zzja = zzja2;
                    zzsm.zzmr(zzmq4);
                    continue;
                }
                case 40: {
                    final int zzc5 = zzsx.zzc(zzsm, 40);
                    int length5;
                    if (this.zzjb == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.zzjb.length;
                    }
                    final int[] zzjb = new int[zzc5 + length5];
                    if (length5 != 0) {
                        System.arraycopy(this.zzjb, 0, zzjb, 0, length5);
                    }
                    while (length5 < -1 + zzjb.length) {
                        zzjb[length5] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length5;
                    }
                    zzjb[length5] = zzsm.zzJb();
                    this.zzjb = zzjb;
                    continue;
                }
                case 42: {
                    final int zzmq5 = zzsm.zzmq(zzsm.zzJf());
                    final int position5 = zzsm.getPosition();
                    int n5 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n5;
                    }
                    zzsm.zzms(position5);
                    int length6;
                    if (this.zzjb == null) {
                        length6 = 0;
                    }
                    else {
                        length6 = this.zzjb.length;
                    }
                    final int[] zzjb2 = new int[n5 + length6];
                    if (length6 != 0) {
                        System.arraycopy(this.zzjb, 0, zzjb2, 0, length6);
                    }
                    while (length6 < zzjb2.length) {
                        zzjb2[length6] = zzsm.zzJb();
                        ++length6;
                    }
                    this.zzjb = zzjb2;
                    zzsm.zzmr(zzmq5);
                    continue;
                }
                case 48: {
                    final int zzc6 = zzsx.zzc(zzsm, 48);
                    int length7;
                    if (this.zzjc == null) {
                        length7 = 0;
                    }
                    else {
                        length7 = this.zzjc.length;
                    }
                    final int[] zzjc = new int[zzc6 + length7];
                    if (length7 != 0) {
                        System.arraycopy(this.zzjc, 0, zzjc, 0, length7);
                    }
                    while (length7 < -1 + zzjc.length) {
                        zzjc[length7] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length7;
                    }
                    zzjc[length7] = zzsm.zzJb();
                    this.zzjc = zzjc;
                    continue;
                }
                case 50: {
                    final int zzmq6 = zzsm.zzmq(zzsm.zzJf());
                    final int position6 = zzsm.getPosition();
                    int n6 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n6;
                    }
                    zzsm.zzms(position6);
                    int length8;
                    if (this.zzjc == null) {
                        length8 = 0;
                    }
                    else {
                        length8 = this.zzjc.length;
                    }
                    final int[] zzjc2 = new int[n6 + length8];
                    if (length8 != 0) {
                        System.arraycopy(this.zzjc, 0, zzjc2, 0, length8);
                    }
                    while (length8 < zzjc2.length) {
                        zzjc2[length8] = zzsm.zzJb();
                        ++length8;
                    }
                    this.zzjc = zzjc2;
                    zzsm.zzmr(zzmq6);
                    continue;
                }
                case 56: {
                    final int zzc7 = zzsx.zzc(zzsm, 56);
                    int length9;
                    if (this.zzjd == null) {
                        length9 = 0;
                    }
                    else {
                        length9 = this.zzjd.length;
                    }
                    final int[] zzjd = new int[zzc7 + length9];
                    if (length9 != 0) {
                        System.arraycopy(this.zzjd, 0, zzjd, 0, length9);
                    }
                    while (length9 < -1 + zzjd.length) {
                        zzjd[length9] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length9;
                    }
                    zzjd[length9] = zzsm.zzJb();
                    this.zzjd = zzjd;
                    continue;
                }
                case 58: {
                    final int zzmq7 = zzsm.zzmq(zzsm.zzJf());
                    final int position7 = zzsm.getPosition();
                    int n7 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n7;
                    }
                    zzsm.zzms(position7);
                    int length10;
                    if (this.zzjd == null) {
                        length10 = 0;
                    }
                    else {
                        length10 = this.zzjd.length;
                    }
                    final int[] zzjd2 = new int[n7 + length10];
                    if (length10 != 0) {
                        System.arraycopy(this.zzjd, 0, zzjd2, 0, length10);
                    }
                    while (length10 < zzjd2.length) {
                        zzjd2[length10] = zzsm.zzJb();
                        ++length10;
                    }
                    this.zzjd = zzjd2;
                    zzsm.zzmr(zzmq7);
                    continue;
                }
                case 64: {
                    final int zzc8 = zzsx.zzc(zzsm, 64);
                    int length11;
                    if (this.zzje == null) {
                        length11 = 0;
                    }
                    else {
                        length11 = this.zzje.length;
                    }
                    final int[] zzje = new int[zzc8 + length11];
                    if (length11 != 0) {
                        System.arraycopy(this.zzje, 0, zzje, 0, length11);
                    }
                    while (length11 < -1 + zzje.length) {
                        zzje[length11] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length11;
                    }
                    zzje[length11] = zzsm.zzJb();
                    this.zzje = zzje;
                    continue;
                }
                case 66: {
                    final int zzmq8 = zzsm.zzmq(zzsm.zzJf());
                    final int position8 = zzsm.getPosition();
                    int n8 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n8;
                    }
                    zzsm.zzms(position8);
                    int length12;
                    if (this.zzje == null) {
                        length12 = 0;
                    }
                    else {
                        length12 = this.zzje.length;
                    }
                    final int[] zzje2 = new int[n8 + length12];
                    if (length12 != 0) {
                        System.arraycopy(this.zzje, 0, zzje2, 0, length12);
                    }
                    while (length12 < zzje2.length) {
                        zzje2[length12] = zzsm.zzJb();
                        ++length12;
                    }
                    this.zzje = zzje2;
                    zzsm.zzmr(zzmq8);
                    continue;
                }
                case 72: {
                    final int zzc9 = zzsx.zzc(zzsm, 72);
                    int length13;
                    if (this.zzjf == null) {
                        length13 = 0;
                    }
                    else {
                        length13 = this.zzjf.length;
                    }
                    final int[] zzjf = new int[zzc9 + length13];
                    if (length13 != 0) {
                        System.arraycopy(this.zzjf, 0, zzjf, 0, length13);
                    }
                    while (length13 < -1 + zzjf.length) {
                        zzjf[length13] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length13;
                    }
                    zzjf[length13] = zzsm.zzJb();
                    this.zzjf = zzjf;
                    continue;
                }
                case 74: {
                    final int zzmq9 = zzsm.zzmq(zzsm.zzJf());
                    final int position9 = zzsm.getPosition();
                    int n9 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n9;
                    }
                    zzsm.zzms(position9);
                    int length14;
                    if (this.zzjf == null) {
                        length14 = 0;
                    }
                    else {
                        length14 = this.zzjf.length;
                    }
                    final int[] zzjf2 = new int[n9 + length14];
                    if (length14 != 0) {
                        System.arraycopy(this.zzjf, 0, zzjf2, 0, length14);
                    }
                    while (length14 < zzjf2.length) {
                        zzjf2[length14] = zzsm.zzJb();
                        ++length14;
                    }
                    this.zzjf = zzjf2;
                    zzsm.zzmr(zzmq9);
                    continue;
                }
                case 80: {
                    final int zzc10 = zzsx.zzc(zzsm, 80);
                    int length15;
                    if (this.zzjg == null) {
                        length15 = 0;
                    }
                    else {
                        length15 = this.zzjg.length;
                    }
                    final int[] zzjg = new int[zzc10 + length15];
                    if (length15 != 0) {
                        System.arraycopy(this.zzjg, 0, zzjg, 0, length15);
                    }
                    while (length15 < -1 + zzjg.length) {
                        zzjg[length15] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length15;
                    }
                    zzjg[length15] = zzsm.zzJb();
                    this.zzjg = zzjg;
                    continue;
                }
                case 82: {
                    final int zzmq10 = zzsm.zzmq(zzsm.zzJf());
                    final int position10 = zzsm.getPosition();
                    int n10 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n10;
                    }
                    zzsm.zzms(position10);
                    int length16;
                    if (this.zzjg == null) {
                        length16 = 0;
                    }
                    else {
                        length16 = this.zzjg.length;
                    }
                    final int[] zzjg2 = new int[n10 + length16];
                    if (length16 != 0) {
                        System.arraycopy(this.zzjg, 0, zzjg2, 0, length16);
                    }
                    while (length16 < zzjg2.length) {
                        zzjg2[length16] = zzsm.zzJb();
                        ++length16;
                    }
                    this.zzjg = zzjg2;
                    zzsm.zzmr(zzmq10);
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
        if (this.zziX != null && this.zziX.length > 0) {
            int j = 0;
            int n = 0;
            while (j < this.zziX.length) {
                n += zzsn.zzmx(this.zziX[j]);
                ++j;
            }
            n2 = zzz + n + 1 * this.zziX.length;
        }
        else {
            n2 = zzz;
        }
        if (this.zziY != null && this.zziY.length > 0) {
            int k = 0;
            int n3 = 0;
            while (k < this.zziY.length) {
                n3 += zzsn.zzmx(this.zziY[k]);
                ++k;
            }
            n2 = n2 + n3 + 1 * this.zziY.length;
        }
        if (this.zziZ != null && this.zziZ.length > 0) {
            int l = 0;
            int n4 = 0;
            while (l < this.zziZ.length) {
                n4 += zzsn.zzmx(this.zziZ[l]);
                ++l;
            }
            n2 = n2 + n4 + 1 * this.zziZ.length;
        }
        if (this.zzja != null && this.zzja.length > 0) {
            int n5 = 0;
            int n6 = 0;
            while (n5 < this.zzja.length) {
                n6 += zzsn.zzmx(this.zzja[n5]);
                ++n5;
            }
            n2 = n2 + n6 + 1 * this.zzja.length;
        }
        if (this.zzjb != null && this.zzjb.length > 0) {
            int n7 = 0;
            int n8 = 0;
            while (n7 < this.zzjb.length) {
                n8 += zzsn.zzmx(this.zzjb[n7]);
                ++n7;
            }
            n2 = n2 + n8 + 1 * this.zzjb.length;
        }
        if (this.zzjc != null && this.zzjc.length > 0) {
            int n9 = 0;
            int n10 = 0;
            while (n9 < this.zzjc.length) {
                n10 += zzsn.zzmx(this.zzjc[n9]);
                ++n9;
            }
            n2 = n2 + n10 + 1 * this.zzjc.length;
        }
        if (this.zzjd != null && this.zzjd.length > 0) {
            int n11 = 0;
            int n12 = 0;
            while (n11 < this.zzjd.length) {
                n12 += zzsn.zzmx(this.zzjd[n11]);
                ++n11;
            }
            n2 = n2 + n12 + 1 * this.zzjd.length;
        }
        if (this.zzje != null && this.zzje.length > 0) {
            int n13 = 0;
            int n14 = 0;
            while (n13 < this.zzje.length) {
                n14 += zzsn.zzmx(this.zzje[n13]);
                ++n13;
            }
            n2 = n2 + n14 + 1 * this.zzje.length;
        }
        if (this.zzjf != null && this.zzjf.length > 0) {
            int n15 = 0;
            int n16 = 0;
            while (n15 < this.zzjf.length) {
                n16 += zzsn.zzmx(this.zzjf[n15]);
                ++n15;
            }
            n2 = n2 + n16 + 1 * this.zzjf.length;
        }
        if (this.zzjg != null && this.zzjg.length > 0) {
            int n17 = 0;
            while (i < this.zzjg.length) {
                n17 += zzsn.zzmx(this.zzjg[i]);
                ++i;
            }
            n2 = n2 + n17 + 1 * this.zzjg.length;
        }
        return n2;
    }
}
