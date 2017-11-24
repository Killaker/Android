package com.google.android.gms.internal;

import java.util.*;
import java.io.*;

public static final class zzd extends zzso<zzd>
{
    public String tag;
    public long zzbuR;
    public long zzbuS;
    public long zzbuT;
    public int zzbuU;
    public boolean zzbuV;
    public zze[] zzbuW;
    public zzb zzbuX;
    public byte[] zzbuY;
    public byte[] zzbuZ;
    public byte[] zzbva;
    public zza zzbvb;
    public String zzbvc;
    public long zzbvd;
    public zzc zzbve;
    public byte[] zzbvf;
    public int zzbvg;
    public int[] zzbvh;
    public long zzbvi;
    public int zzob;
    
    public zzd() {
        this.zzJF();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzd;
            b = false;
            if (b2) {
                final zzd zzd = (zzd)o;
                final long n = lcmp(this.zzbuR, zzd.zzbuR);
                b = false;
                if (n == 0) {
                    final long n2 = lcmp(this.zzbuS, zzd.zzbuS);
                    b = false;
                    if (n2 == 0) {
                        final long n3 = lcmp(this.zzbuT, zzd.zzbuT);
                        b = false;
                        if (n3 == 0) {
                            if (this.tag == null) {
                                final String tag = zzd.tag;
                                b = false;
                                if (tag != null) {
                                    return b;
                                }
                            }
                            else if (!this.tag.equals(zzd.tag)) {
                                return false;
                            }
                            final int zzbuU = this.zzbuU;
                            final int zzbuU2 = zzd.zzbuU;
                            b = false;
                            if (zzbuU == zzbuU2) {
                                final int zzob = this.zzob;
                                final int zzob2 = zzd.zzob;
                                b = false;
                                if (zzob == zzob2) {
                                    final boolean zzbuV = this.zzbuV;
                                    final boolean zzbuV2 = zzd.zzbuV;
                                    b = false;
                                    if (zzbuV == zzbuV2) {
                                        final boolean equals = zzss.equals(this.zzbuW, zzd.zzbuW);
                                        b = false;
                                        if (equals) {
                                            if (this.zzbuX == null) {
                                                final zzb zzbuX = zzd.zzbuX;
                                                b = false;
                                                if (zzbuX != null) {
                                                    return b;
                                                }
                                            }
                                            else if (!this.zzbuX.equals(zzd.zzbuX)) {
                                                return false;
                                            }
                                            final boolean equals2 = Arrays.equals(this.zzbuY, zzd.zzbuY);
                                            b = false;
                                            if (equals2) {
                                                final boolean equals3 = Arrays.equals(this.zzbuZ, zzd.zzbuZ);
                                                b = false;
                                                if (equals3) {
                                                    final boolean equals4 = Arrays.equals(this.zzbva, zzd.zzbva);
                                                    b = false;
                                                    if (equals4) {
                                                        if (this.zzbvb == null) {
                                                            final zza zzbvb = zzd.zzbvb;
                                                            b = false;
                                                            if (zzbvb != null) {
                                                                return b;
                                                            }
                                                        }
                                                        else if (!this.zzbvb.equals(zzd.zzbvb)) {
                                                            return false;
                                                        }
                                                        if (this.zzbvc == null) {
                                                            final String zzbvc = zzd.zzbvc;
                                                            b = false;
                                                            if (zzbvc != null) {
                                                                return b;
                                                            }
                                                        }
                                                        else if (!this.zzbvc.equals(zzd.zzbvc)) {
                                                            return false;
                                                        }
                                                        final long n4 = lcmp(this.zzbvd, zzd.zzbvd);
                                                        b = false;
                                                        if (n4 == 0) {
                                                            if (this.zzbve == null) {
                                                                final zzc zzbve = zzd.zzbve;
                                                                b = false;
                                                                if (zzbve != null) {
                                                                    return b;
                                                                }
                                                            }
                                                            else if (!this.zzbve.equals(zzd.zzbve)) {
                                                                return false;
                                                            }
                                                            final boolean equals5 = Arrays.equals(this.zzbvf, zzd.zzbvf);
                                                            b = false;
                                                            if (equals5) {
                                                                final int zzbvg = this.zzbvg;
                                                                final int zzbvg2 = zzd.zzbvg;
                                                                b = false;
                                                                if (zzbvg == zzbvg2) {
                                                                    final boolean equals6 = zzss.equals(this.zzbvh, zzd.zzbvh);
                                                                    b = false;
                                                                    if (equals6) {
                                                                        final long n5 = lcmp(this.zzbvi, zzd.zzbvi);
                                                                        b = false;
                                                                        if (n5 == 0) {
                                                                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                                                if (zzd.zzbuj != null) {
                                                                                    final boolean empty = zzd.zzbuj.isEmpty();
                                                                                    b = false;
                                                                                    if (!empty) {
                                                                                        return b;
                                                                                    }
                                                                                }
                                                                                return true;
                                                                            }
                                                                            return this.zzbuj.equals(zzd.zzbuj);
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
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + (int)(this.zzbuR ^ this.zzbuR >>> 32)) + (int)(this.zzbuS ^ this.zzbuS >>> 32)) + (int)(this.zzbuT ^ this.zzbuT >>> 32));
        int hashCode;
        if (this.tag == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.tag.hashCode();
        }
        final int n2 = 31 * (31 * (31 * (hashCode + n) + this.zzbuU) + this.zzob);
        int n3;
        if (this.zzbuV) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        final int n4 = 31 * (31 * (n3 + n2) + zzss.hashCode(this.zzbuW));
        int hashCode2;
        if (this.zzbuX == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzbuX.hashCode();
        }
        final int n5 = 31 * (31 * (31 * (31 * (hashCode2 + n4) + Arrays.hashCode(this.zzbuY)) + Arrays.hashCode(this.zzbuZ)) + Arrays.hashCode(this.zzbva));
        int hashCode3;
        if (this.zzbvb == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzbvb.hashCode();
        }
        final int n6 = 31 * (hashCode3 + n5);
        int hashCode4;
        if (this.zzbvc == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.zzbvc.hashCode();
        }
        final int n7 = 31 * (31 * (hashCode4 + n6) + (int)(this.zzbvd ^ this.zzbvd >>> 32));
        int hashCode5;
        if (this.zzbve == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.zzbve.hashCode();
        }
        final int n8 = 31 * (31 * (31 * (31 * (31 * (hashCode5 + n7) + Arrays.hashCode(this.zzbvf)) + this.zzbvg) + zzss.hashCode(this.zzbvh)) + (int)(this.zzbvi ^ this.zzbvi >>> 32));
        final zzsq zzbuj = this.zzbuj;
        int hashCode6 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode6 = 0;
            if (!empty) {
                hashCode6 = this.zzbuj.hashCode();
            }
        }
        return n8 + hashCode6;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbuR != 0L) {
            zzsn.zzb(1, this.zzbuR);
        }
        if (!this.tag.equals("")) {
            zzsn.zzn(2, this.tag);
        }
        if (this.zzbuW != null && this.zzbuW.length > 0) {
            for (int i = 0; i < this.zzbuW.length; ++i) {
                final zze zze = this.zzbuW[i];
                if (zze != null) {
                    zzsn.zza(3, zze);
                }
            }
        }
        if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
            zzsn.zza(6, this.zzbuY);
        }
        if (this.zzbvb != null) {
            zzsn.zza(7, this.zzbvb);
        }
        if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
            zzsn.zza(8, this.zzbuZ);
        }
        if (this.zzbuX != null) {
            zzsn.zza(9, this.zzbuX);
        }
        if (this.zzbuV) {
            zzsn.zze(10, this.zzbuV);
        }
        if (this.zzbuU != 0) {
            zzsn.zzA(11, this.zzbuU);
        }
        if (this.zzob != 0) {
            zzsn.zzA(12, this.zzob);
        }
        if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
            zzsn.zza(13, this.zzbva);
        }
        if (!this.zzbvc.equals("")) {
            zzsn.zzn(14, this.zzbvc);
        }
        if (this.zzbvd != 180000L) {
            zzsn.zzc(15, this.zzbvd);
        }
        if (this.zzbve != null) {
            zzsn.zza(16, this.zzbve);
        }
        if (this.zzbuS != 0L) {
            zzsn.zzb(17, this.zzbuS);
        }
        if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
            zzsn.zza(18, this.zzbvf);
        }
        if (this.zzbvg != 0) {
            zzsn.zzA(19, this.zzbvg);
        }
        if (this.zzbvh != null) {
            final int length = this.zzbvh.length;
            int j = 0;
            if (length > 0) {
                while (j < this.zzbvh.length) {
                    zzsn.zzA(20, this.zzbvh[j]);
                    ++j;
                }
            }
        }
        if (this.zzbuT != 0L) {
            zzsn.zzb(21, this.zzbuT);
        }
        if (this.zzbvi != 0L) {
            zzsn.zzb(22, this.zzbvi);
        }
        super.writeTo(zzsn);
    }
    
    public zzd zzJF() {
        this.zzbuR = 0L;
        this.zzbuS = 0L;
        this.zzbuT = 0L;
        this.tag = "";
        this.zzbuU = 0;
        this.zzob = 0;
        this.zzbuV = false;
        this.zzbuW = zze.zzJG();
        this.zzbuX = null;
        this.zzbuY = zzsx.zzbuD;
        this.zzbuZ = zzsx.zzbuD;
        this.zzbva = zzsx.zzbuD;
        this.zzbvb = null;
        this.zzbvc = "";
        this.zzbvd = 180000L;
        this.zzbve = null;
        this.zzbvf = zzsx.zzbuD;
        this.zzbvg = 0;
        this.zzbvh = zzsx.zzbuw;
        this.zzbvi = 0L;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzd zzV(final zzsm zzsm) throws IOException {
    Label_0201:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0201;
                    }
                    continue;
                }
                case 0: {
                    break Label_0201;
                }
                case 8: {
                    this.zzbuR = zzsm.zzJa();
                    continue;
                }
                case 18: {
                    this.tag = zzsm.readString();
                    continue;
                }
                case 26: {
                    final int zzc = zzsx.zzc(zzsm, 26);
                    int i;
                    if (this.zzbuW == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzbuW.length;
                    }
                    final zze[] zzbuW = new zze[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzbuW, 0, zzbuW, 0, i);
                    }
                    while (i < -1 + zzbuW.length) {
                        zzsm.zza(zzbuW[i] = new zze());
                        zzsm.zzIX();
                        ++i;
                    }
                    zzsm.zza(zzbuW[i] = new zze());
                    this.zzbuW = zzbuW;
                    continue;
                }
                case 50: {
                    this.zzbuY = zzsm.readBytes();
                    continue;
                }
                case 58: {
                    if (this.zzbvb == null) {
                        this.zzbvb = new zza();
                    }
                    zzsm.zza(this.zzbvb);
                    continue;
                }
                case 66: {
                    this.zzbuZ = zzsm.readBytes();
                    continue;
                }
                case 74: {
                    if (this.zzbuX == null) {
                        this.zzbuX = new zzb();
                    }
                    zzsm.zza(this.zzbuX);
                    continue;
                }
                case 80: {
                    this.zzbuV = zzsm.zzJc();
                    continue;
                }
                case 88: {
                    this.zzbuU = zzsm.zzJb();
                    continue;
                }
                case 96: {
                    this.zzob = zzsm.zzJb();
                    continue;
                }
                case 106: {
                    this.zzbva = zzsm.readBytes();
                    continue;
                }
                case 114: {
                    this.zzbvc = zzsm.readString();
                    continue;
                }
                case 120: {
                    this.zzbvd = zzsm.zzJe();
                    continue;
                }
                case 130: {
                    if (this.zzbve == null) {
                        this.zzbve = new zzc();
                    }
                    zzsm.zza(this.zzbve);
                    continue;
                }
                case 136: {
                    this.zzbuS = zzsm.zzJa();
                    continue;
                }
                case 146: {
                    this.zzbvf = zzsm.readBytes();
                    continue;
                }
                case 152: {
                    final int zzJb = zzsm.zzJb();
                    switch (zzJb) {
                        default: {
                            continue;
                        }
                        case 0:
                        case 1:
                        case 2: {
                            this.zzbvg = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 160: {
                    final int zzc2 = zzsx.zzc(zzsm, 160);
                    int j;
                    if (this.zzbvh == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzbvh.length;
                    }
                    final int[] zzbvh = new int[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zzbvh, 0, zzbvh, 0, j);
                    }
                    while (j < -1 + zzbvh.length) {
                        zzbvh[j] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++j;
                    }
                    zzbvh[j] = zzsm.zzJb();
                    this.zzbvh = zzbvh;
                    continue;
                }
                case 162: {
                    final int zzmq = zzsm.zzmq(zzsm.zzJf());
                    final int position = zzsm.getPosition();
                    int n = 0;
                    while (zzsm.zzJk() > 0) {
                        zzsm.zzJb();
                        ++n;
                    }
                    zzsm.zzms(position);
                    int k;
                    if (this.zzbvh == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzbvh.length;
                    }
                    final int[] zzbvh2 = new int[n + k];
                    if (k != 0) {
                        System.arraycopy(this.zzbvh, 0, zzbvh2, 0, k);
                    }
                    while (k < zzbvh2.length) {
                        zzbvh2[k] = zzsm.zzJb();
                        ++k;
                    }
                    this.zzbvh = zzbvh2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 168: {
                    this.zzbuT = zzsm.zzJa();
                    continue;
                }
                case 176: {
                    this.zzbvi = zzsm.zzJa();
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
        if (this.zzbuR != 0L) {
            zzz += zzsn.zzd(1, this.zzbuR);
        }
        if (!this.tag.equals("")) {
            zzz += zzsn.zzo(2, this.tag);
        }
        if (this.zzbuW != null && this.zzbuW.length > 0) {
            int n = zzz;
            for (int j = 0; j < this.zzbuW.length; ++j) {
                final zze zze = this.zzbuW[j];
                if (zze != null) {
                    n += zzsn.zzc(3, zze);
                }
            }
            zzz = n;
        }
        if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
            zzz += zzsn.zzb(6, this.zzbuY);
        }
        if (this.zzbvb != null) {
            zzz += zzsn.zzc(7, this.zzbvb);
        }
        if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
            zzz += zzsn.zzb(8, this.zzbuZ);
        }
        if (this.zzbuX != null) {
            zzz += zzsn.zzc(9, this.zzbuX);
        }
        if (this.zzbuV) {
            zzz += zzsn.zzf(10, this.zzbuV);
        }
        if (this.zzbuU != 0) {
            zzz += zzsn.zzC(11, this.zzbuU);
        }
        if (this.zzob != 0) {
            zzz += zzsn.zzC(12, this.zzob);
        }
        if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
            zzz += zzsn.zzb(13, this.zzbva);
        }
        if (!this.zzbvc.equals("")) {
            zzz += zzsn.zzo(14, this.zzbvc);
        }
        if (this.zzbvd != 180000L) {
            zzz += zzsn.zze(15, this.zzbvd);
        }
        if (this.zzbve != null) {
            zzz += zzsn.zzc(16, this.zzbve);
        }
        if (this.zzbuS != 0L) {
            zzz += zzsn.zzd(17, this.zzbuS);
        }
        if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
            zzz += zzsn.zzb(18, this.zzbvf);
        }
        if (this.zzbvg != 0) {
            zzz += zzsn.zzC(19, this.zzbvg);
        }
        if (this.zzbvh != null && this.zzbvh.length > 0) {
            int n2 = 0;
            while (i < this.zzbvh.length) {
                n2 += zzsn.zzmx(this.zzbvh[i]);
                ++i;
            }
            zzz = zzz + n2 + 2 * this.zzbvh.length;
        }
        if (this.zzbuT != 0L) {
            zzz += zzsn.zzd(21, this.zzbuT);
        }
        if (this.zzbvi != 0L) {
            zzz += zzsn.zzd(22, this.zzbvi);
        }
        return zzz;
    }
}
