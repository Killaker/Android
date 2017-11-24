package com.google.android.gms.internal;

import java.io.*;

public static final class zzh extends zzso<zzh>
{
    public static final zzsp<zzag.zza, zzh> zzjh;
    private static final zzh[] zzji;
    public int[] zzjj;
    public int[] zzjk;
    public int[] zzjl;
    public int zzjm;
    public int[] zzjn;
    public int zzjo;
    public int zzjp;
    
    static {
        zzjh = zzsp.zza(11, zzh.class, 810L);
        zzji = new zzh[0];
    }
    
    public zzh() {
        this.zzM();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzh;
            b = false;
            if (b2) {
                final zzh zzh = (zzh)o;
                final boolean equals = zzss.equals(this.zzjj, zzh.zzjj);
                b = false;
                if (equals) {
                    final boolean equals2 = zzss.equals(this.zzjk, zzh.zzjk);
                    b = false;
                    if (equals2) {
                        final boolean equals3 = zzss.equals(this.zzjl, zzh.zzjl);
                        b = false;
                        if (equals3) {
                            final int zzjm = this.zzjm;
                            final int zzjm2 = zzh.zzjm;
                            b = false;
                            if (zzjm == zzjm2) {
                                final boolean equals4 = zzss.equals(this.zzjn, zzh.zzjn);
                                b = false;
                                if (equals4) {
                                    final int zzjo = this.zzjo;
                                    final int zzjo2 = zzh.zzjo;
                                    b = false;
                                    if (zzjo == zzjo2) {
                                        final int zzjp = this.zzjp;
                                        final int zzjp2 = zzh.zzjp;
                                        b = false;
                                        if (zzjp == zzjp2) {
                                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                if (zzh.zzbuj != null) {
                                                    final boolean empty = zzh.zzbuj.isEmpty();
                                                    b = false;
                                                    if (!empty) {
                                                        return b;
                                                    }
                                                }
                                                return true;
                                            }
                                            return this.zzbuj.equals(zzh.zzbuj);
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
        final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzjj)) + zzss.hashCode(this.zzjk)) + zzss.hashCode(this.zzjl)) + this.zzjm) + zzss.hashCode(this.zzjn)) + this.zzjo) + this.zzjp);
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
        if (this.zzjj != null && this.zzjj.length > 0) {
            for (int i = 0; i < this.zzjj.length; ++i) {
                zzsn.zzA(1, this.zzjj[i]);
            }
        }
        if (this.zzjk != null && this.zzjk.length > 0) {
            for (int j = 0; j < this.zzjk.length; ++j) {
                zzsn.zzA(2, this.zzjk[j]);
            }
        }
        if (this.zzjl != null && this.zzjl.length > 0) {
            for (int k = 0; k < this.zzjl.length; ++k) {
                zzsn.zzA(3, this.zzjl[k]);
            }
        }
        if (this.zzjm != 0) {
            zzsn.zzA(4, this.zzjm);
        }
        if (this.zzjn != null) {
            final int length = this.zzjn.length;
            int l = 0;
            if (length > 0) {
                while (l < this.zzjn.length) {
                    zzsn.zzA(5, this.zzjn[l]);
                    ++l;
                }
            }
        }
        if (this.zzjo != 0) {
            zzsn.zzA(6, this.zzjo);
        }
        if (this.zzjp != 0) {
            zzsn.zzA(7, this.zzjp);
        }
        super.writeTo(zzsn);
    }
    
    public zzh zzM() {
        this.zzjj = zzsx.zzbuw;
        this.zzjk = zzsx.zzbuw;
        this.zzjl = zzsx.zzbuw;
        this.zzjm = 0;
        this.zzjn = zzsx.zzbuw;
        this.zzjo = 0;
        this.zzjp = 0;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzh zzh(final zzsm zzsm) throws IOException {
    Label_0121:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0121;
                    }
                    continue;
                }
                case 0: {
                    break Label_0121;
                }
                case 8: {
                    final int zzc = zzsx.zzc(zzsm, 8);
                    int i;
                    if (this.zzjj == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzjj.length;
                    }
                    final int[] zzjj = new int[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzjj, 0, zzjj, 0, i);
                    }
                    while (i < -1 + zzjj.length) {
                        zzjj[i] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzjj[i] = zzsm.zzJb();
                    this.zzjj = zzjj;
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
                    if (this.zzjj == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzjj.length;
                    }
                    final int[] zzjj2 = new int[n + j];
                    if (j != 0) {
                        System.arraycopy(this.zzjj, 0, zzjj2, 0, j);
                    }
                    while (j < zzjj2.length) {
                        zzjj2[j] = zzsm.zzJb();
                        ++j;
                    }
                    this.zzjj = zzjj2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 16: {
                    final int zzc2 = zzsx.zzc(zzsm, 16);
                    int k;
                    if (this.zzjk == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzjk.length;
                    }
                    final int[] zzjk = new int[zzc2 + k];
                    if (k != 0) {
                        System.arraycopy(this.zzjk, 0, zzjk, 0, k);
                    }
                    while (k < -1 + zzjk.length) {
                        zzjk[k] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++k;
                    }
                    zzjk[k] = zzsm.zzJb();
                    this.zzjk = zzjk;
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
                    if (this.zzjk == null) {
                        l = 0;
                    }
                    else {
                        l = this.zzjk.length;
                    }
                    final int[] zzjk2 = new int[n2 + l];
                    if (l != 0) {
                        System.arraycopy(this.zzjk, 0, zzjk2, 0, l);
                    }
                    while (l < zzjk2.length) {
                        zzjk2[l] = zzsm.zzJb();
                        ++l;
                    }
                    this.zzjk = zzjk2;
                    zzsm.zzmr(zzmq2);
                    continue;
                }
                case 24: {
                    final int zzc3 = zzsx.zzc(zzsm, 24);
                    int length;
                    if (this.zzjl == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzjl.length;
                    }
                    final int[] zzjl = new int[zzc3 + length];
                    if (length != 0) {
                        System.arraycopy(this.zzjl, 0, zzjl, 0, length);
                    }
                    while (length < -1 + zzjl.length) {
                        zzjl[length] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length;
                    }
                    zzjl[length] = zzsm.zzJb();
                    this.zzjl = zzjl;
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
                    if (this.zzjl == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zzjl.length;
                    }
                    final int[] zzjl2 = new int[n3 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zzjl, 0, zzjl2, 0, length2);
                    }
                    while (length2 < zzjl2.length) {
                        zzjl2[length2] = zzsm.zzJb();
                        ++length2;
                    }
                    this.zzjl = zzjl2;
                    zzsm.zzmr(zzmq3);
                    continue;
                }
                case 32: {
                    this.zzjm = zzsm.zzJb();
                    continue;
                }
                case 40: {
                    final int zzc4 = zzsx.zzc(zzsm, 40);
                    int length3;
                    if (this.zzjn == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zzjn.length;
                    }
                    final int[] zzjn = new int[zzc4 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzjn, 0, zzjn, 0, length3);
                    }
                    while (length3 < -1 + zzjn.length) {
                        zzjn[length3] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++length3;
                    }
                    zzjn[length3] = zzsm.zzJb();
                    this.zzjn = zzjn;
                    continue;
                }
                case 42: {
                    final int zzmq4 = zzsm.zzmq(zzsm.zzJf());
                    final int position4 = zzsm.getPosition();
                    int n4 = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n4;
                    }
                    zzsm.zzms(position4);
                    int length4;
                    if (this.zzjn == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.zzjn.length;
                    }
                    final int[] zzjn2 = new int[n4 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zzjn, 0, zzjn2, 0, length4);
                    }
                    while (length4 < zzjn2.length) {
                        zzjn2[length4] = zzsm.zzJb();
                        ++length4;
                    }
                    this.zzjn = zzjn2;
                    zzsm.zzmr(zzmq4);
                    continue;
                }
                case 48: {
                    this.zzjo = zzsm.zzJb();
                    continue;
                }
                case 56: {
                    this.zzjp = zzsm.zzJb();
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
        if (this.zzjj != null && this.zzjj.length > 0) {
            int j = 0;
            int n = 0;
            while (j < this.zzjj.length) {
                n += zzsn.zzmx(this.zzjj[j]);
                ++j;
            }
            n2 = zzz + n + 1 * this.zzjj.length;
        }
        else {
            n2 = zzz;
        }
        if (this.zzjk != null && this.zzjk.length > 0) {
            int k = 0;
            int n3 = 0;
            while (k < this.zzjk.length) {
                n3 += zzsn.zzmx(this.zzjk[k]);
                ++k;
            }
            n2 = n2 + n3 + 1 * this.zzjk.length;
        }
        if (this.zzjl != null && this.zzjl.length > 0) {
            int l = 0;
            int n4 = 0;
            while (l < this.zzjl.length) {
                n4 += zzsn.zzmx(this.zzjl[l]);
                ++l;
            }
            n2 = n2 + n4 + 1 * this.zzjl.length;
        }
        if (this.zzjm != 0) {
            n2 += zzsn.zzC(4, this.zzjm);
        }
        if (this.zzjn != null && this.zzjn.length > 0) {
            int n5 = 0;
            while (i < this.zzjn.length) {
                n5 += zzsn.zzmx(this.zzjn[i]);
                ++i;
            }
            n2 = n2 + n5 + 1 * this.zzjn.length;
        }
        if (this.zzjo != 0) {
            n2 += zzsn.zzC(6, this.zzjo);
        }
        if (this.zzjp != 0) {
            n2 += zzsn.zzC(7, this.zzjp);
        }
        return n2;
    }
}
