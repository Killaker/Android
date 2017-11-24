package com.google.android.gms.internal;

import java.io.*;

public static final class zza extends zzso<zza>
{
    private static volatile zza[] zzjw;
    public int type;
    public zza[] zzjA;
    public String zzjB;
    public String zzjC;
    public long zzjD;
    public boolean zzjE;
    public zza[] zzjF;
    public int[] zzjG;
    public boolean zzjH;
    public String zzjx;
    public zza[] zzjy;
    public zza[] zzjz;
    
    public zza() {
        this.zzR();
    }
    
    public static zza[] zzQ() {
        Label_0027: {
            if (zza.zzjw != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zza.zzjw == null) {
                    zza.zzjw = new zza[0];
                }
                return zza.zzjw;
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
            final boolean b2 = o instanceof zza;
            b = false;
            if (b2) {
                final zza zza = (zza)o;
                final int type = this.type;
                final int type2 = zza.type;
                b = false;
                if (type == type2) {
                    if (this.zzjx == null) {
                        final String zzjx = zza.zzjx;
                        b = false;
                        if (zzjx != null) {
                            return b;
                        }
                    }
                    else if (!this.zzjx.equals(zza.zzjx)) {
                        return false;
                    }
                    final boolean equals = zzss.equals(this.zzjy, zza.zzjy);
                    b = false;
                    if (equals) {
                        final boolean equals2 = zzss.equals(this.zzjz, zza.zzjz);
                        b = false;
                        if (equals2) {
                            final boolean equals3 = zzss.equals(this.zzjA, zza.zzjA);
                            b = false;
                            if (equals3) {
                                if (this.zzjB == null) {
                                    final String zzjB = zza.zzjB;
                                    b = false;
                                    if (zzjB != null) {
                                        return b;
                                    }
                                }
                                else if (!this.zzjB.equals(zza.zzjB)) {
                                    return false;
                                }
                                if (this.zzjC == null) {
                                    final String zzjC = zza.zzjC;
                                    b = false;
                                    if (zzjC != null) {
                                        return b;
                                    }
                                }
                                else if (!this.zzjC.equals(zza.zzjC)) {
                                    return false;
                                }
                                final long n = lcmp(this.zzjD, zza.zzjD);
                                b = false;
                                if (n == 0) {
                                    final boolean zzjE = this.zzjE;
                                    final boolean zzjE2 = zza.zzjE;
                                    b = false;
                                    if (zzjE == zzjE2) {
                                        final boolean equals4 = zzss.equals(this.zzjF, zza.zzjF);
                                        b = false;
                                        if (equals4) {
                                            final boolean equals5 = zzss.equals(this.zzjG, zza.zzjG);
                                            b = false;
                                            if (equals5) {
                                                final boolean zzjH = this.zzjH;
                                                final boolean zzjH2 = zza.zzjH;
                                                b = false;
                                                if (zzjH == zzjH2) {
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
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        final int n2 = 31 * (31 * (527 + this.getClass().getName().hashCode()) + this.type);
        int hashCode;
        if (this.zzjx == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzjx.hashCode();
        }
        final int n3 = 31 * (31 * (31 * (31 * (hashCode + n2) + zzss.hashCode(this.zzjy)) + zzss.hashCode(this.zzjz)) + zzss.hashCode(this.zzjA));
        int hashCode2;
        if (this.zzjB == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzjB.hashCode();
        }
        final int n4 = 31 * (hashCode2 + n3);
        int hashCode3;
        if (this.zzjC == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzjC.hashCode();
        }
        final int n5 = 31 * (31 * (hashCode3 + n4) + (int)(this.zzjD ^ this.zzjD >>> 32));
        int n6;
        if (this.zzjE) {
            n6 = n;
        }
        else {
            n6 = 1237;
        }
        final int n7 = 31 * (31 * (31 * (n6 + n5) + zzss.hashCode(this.zzjF)) + zzss.hashCode(this.zzjG));
        if (!this.zzjH) {
            n = 1237;
        }
        final int n8 = 31 * (n7 + n);
        final zzsq zzbuj = this.zzbuj;
        int hashCode4 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode4 = 0;
            if (!empty) {
                hashCode4 = this.zzbuj.hashCode();
            }
        }
        return n8 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        zzsn.zzA(1, this.type);
        if (!this.zzjx.equals("")) {
            zzsn.zzn(2, this.zzjx);
        }
        if (this.zzjy != null && this.zzjy.length > 0) {
            for (int i = 0; i < this.zzjy.length; ++i) {
                final zza zza = this.zzjy[i];
                if (zza != null) {
                    zzsn.zza(3, zza);
                }
            }
        }
        if (this.zzjz != null && this.zzjz.length > 0) {
            for (int j = 0; j < this.zzjz.length; ++j) {
                final zza zza2 = this.zzjz[j];
                if (zza2 != null) {
                    zzsn.zza(4, zza2);
                }
            }
        }
        if (this.zzjA != null && this.zzjA.length > 0) {
            for (int k = 0; k < this.zzjA.length; ++k) {
                final zza zza3 = this.zzjA[k];
                if (zza3 != null) {
                    zzsn.zza(5, zza3);
                }
            }
        }
        if (!this.zzjB.equals("")) {
            zzsn.zzn(6, this.zzjB);
        }
        if (!this.zzjC.equals("")) {
            zzsn.zzn(7, this.zzjC);
        }
        if (this.zzjD != 0L) {
            zzsn.zzb(8, this.zzjD);
        }
        if (this.zzjH) {
            zzsn.zze(9, this.zzjH);
        }
        if (this.zzjG != null && this.zzjG.length > 0) {
            for (int l = 0; l < this.zzjG.length; ++l) {
                zzsn.zzA(10, this.zzjG[l]);
            }
        }
        if (this.zzjF != null) {
            final int length = this.zzjF.length;
            int n = 0;
            if (length > 0) {
                while (n < this.zzjF.length) {
                    final zza zza4 = this.zzjF[n];
                    if (zza4 != null) {
                        zzsn.zza(11, zza4);
                    }
                    ++n;
                }
            }
        }
        if (this.zzjE) {
            zzsn.zze(12, this.zzjE);
        }
        super.writeTo(zzsn);
    }
    
    public zza zzR() {
        this.type = 1;
        this.zzjx = "";
        this.zzjy = zzQ();
        this.zzjz = zzQ();
        this.zzjA = zzQ();
        this.zzjB = "";
        this.zzjC = "";
        this.zzjD = 0L;
        this.zzjE = false;
        this.zzjF = zzQ();
        this.zzjG = zzsx.zzbuw;
        this.zzjH = false;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zza zzk(final zzsm zzsm) throws IOException {
    Label_0137:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0137;
                    }
                    continue;
                }
                case 0: {
                    break Label_0137;
                }
                case 8: {
                    final int zzJb = zzsm.zzJb();
                    switch (zzJb) {
                        default: {
                            continue;
                        }
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8: {
                            this.type = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 18: {
                    this.zzjx = zzsm.readString();
                    continue;
                }
                case 26: {
                    final int zzc = zzsx.zzc(zzsm, 26);
                    int i;
                    if (this.zzjy == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzjy.length;
                    }
                    final zza[] zzjy = new zza[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzjy, 0, zzjy, 0, i);
                    }
                    while (i < -1 + zzjy.length) {
                        zzsm.zza(zzjy[i] = new zza());
                        zzsm.zzIX();
                        ++i;
                    }
                    zzsm.zza(zzjy[i] = new zza());
                    this.zzjy = zzjy;
                    continue;
                }
                case 34: {
                    final int zzc2 = zzsx.zzc(zzsm, 34);
                    int j;
                    if (this.zzjz == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzjz.length;
                    }
                    final zza[] zzjz = new zza[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zzjz, 0, zzjz, 0, j);
                    }
                    while (j < -1 + zzjz.length) {
                        zzsm.zza(zzjz[j] = new zza());
                        zzsm.zzIX();
                        ++j;
                    }
                    zzsm.zza(zzjz[j] = new zza());
                    this.zzjz = zzjz;
                    continue;
                }
                case 42: {
                    final int zzc3 = zzsx.zzc(zzsm, 42);
                    int k;
                    if (this.zzjA == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzjA.length;
                    }
                    final zza[] zzjA = new zza[zzc3 + k];
                    if (k != 0) {
                        System.arraycopy(this.zzjA, 0, zzjA, 0, k);
                    }
                    while (k < -1 + zzjA.length) {
                        zzsm.zza(zzjA[k] = new zza());
                        zzsm.zzIX();
                        ++k;
                    }
                    zzsm.zza(zzjA[k] = new zza());
                    this.zzjA = zzjA;
                    continue;
                }
                case 50: {
                    this.zzjB = zzsm.readString();
                    continue;
                }
                case 58: {
                    this.zzjC = zzsm.readString();
                    continue;
                }
                case 64: {
                    this.zzjD = zzsm.zzJa();
                    continue;
                }
                case 72: {
                    this.zzjH = zzsm.zzJc();
                    continue;
                }
                case 80: {
                    final int zzc4 = zzsx.zzc(zzsm, 80);
                    final int[] zzjG = new int[zzc4];
                    int l = 0;
                    int n = 0;
                    while (l < zzc4) {
                        if (l != 0) {
                            zzsm.zzIX();
                        }
                        final int zzJb2 = zzsm.zzJb();
                        int n2 = 0;
                        switch (zzJb2) {
                            default: {
                                n2 = n;
                                break;
                            }
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
                            case 17: {
                                n2 = n + 1;
                                zzjG[n] = zzJb2;
                                break;
                            }
                        }
                        ++l;
                        n = n2;
                    }
                    if (n == 0) {
                        continue;
                    }
                    int length;
                    if (this.zzjG == null) {
                        length = 0;
                    }
                    else {
                        length = this.zzjG.length;
                    }
                    if (length == 0 && n == zzjG.length) {
                        this.zzjG = zzjG;
                        continue;
                    }
                    final int[] zzjG2 = new int[length + n];
                    if (length != 0) {
                        System.arraycopy(this.zzjG, 0, zzjG2, 0, length);
                    }
                    System.arraycopy(zzjG, 0, zzjG2, length, n);
                    this.zzjG = zzjG2;
                    continue;
                }
                case 82: {
                    final int zzmq = zzsm.zzmq(zzsm.zzJf());
                    final int position = zzsm.getPosition();
                    int n3 = 0;
                    while (zzsm.zzJk() > 0) {
                        switch (zzsm.zzJb()) {
                            default: {
                                continue;
                            }
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
                            case 17: {
                                ++n3;
                                continue;
                            }
                        }
                    }
                    if (n3 != 0) {
                        zzsm.zzms(position);
                        int length2;
                        if (this.zzjG == null) {
                            length2 = 0;
                        }
                        else {
                            length2 = this.zzjG.length;
                        }
                        final int[] zzjG3 = new int[n3 + length2];
                        if (length2 != 0) {
                            System.arraycopy(this.zzjG, 0, zzjG3, 0, length2);
                        }
                        while (zzsm.zzJk() > 0) {
                            final int zzJb3 = zzsm.zzJb();
                            switch (zzJb3) {
                                default: {
                                    continue;
                                }
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
                                case 17: {
                                    final int n4 = length2 + 1;
                                    zzjG3[length2] = zzJb3;
                                    length2 = n4;
                                    continue;
                                }
                            }
                        }
                        this.zzjG = zzjG3;
                    }
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 90: {
                    final int zzc5 = zzsx.zzc(zzsm, 90);
                    int length3;
                    if (this.zzjF == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zzjF.length;
                    }
                    final zza[] zzjF = new zza[zzc5 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zzjF, 0, zzjF, 0, length3);
                    }
                    while (length3 < -1 + zzjF.length) {
                        zzsm.zza(zzjF[length3] = new zza());
                        zzsm.zzIX();
                        ++length3;
                    }
                    zzsm.zza(zzjF[length3] = new zza());
                    this.zzjF = zzjF;
                    continue;
                }
                case 96: {
                    this.zzjE = zzsm.zzJc();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int n = super.zzz() + zzsn.zzC(1, this.type);
        if (!this.zzjx.equals("")) {
            n += zzsn.zzo(2, this.zzjx);
        }
        if (this.zzjy != null && this.zzjy.length > 0) {
            int n2 = n;
            for (int i = 0; i < this.zzjy.length; ++i) {
                final zza zza = this.zzjy[i];
                if (zza != null) {
                    n2 += zzsn.zzc(3, zza);
                }
            }
            n = n2;
        }
        if (this.zzjz != null && this.zzjz.length > 0) {
            int n3 = n;
            for (int j = 0; j < this.zzjz.length; ++j) {
                final zza zza2 = this.zzjz[j];
                if (zza2 != null) {
                    n3 += zzsn.zzc(4, zza2);
                }
            }
            n = n3;
        }
        if (this.zzjA != null && this.zzjA.length > 0) {
            int n4 = n;
            for (int k = 0; k < this.zzjA.length; ++k) {
                final zza zza3 = this.zzjA[k];
                if (zza3 != null) {
                    n4 += zzsn.zzc(5, zza3);
                }
            }
            n = n4;
        }
        if (!this.zzjB.equals("")) {
            n += zzsn.zzo(6, this.zzjB);
        }
        if (!this.zzjC.equals("")) {
            n += zzsn.zzo(7, this.zzjC);
        }
        if (this.zzjD != 0L) {
            n += zzsn.zzd(8, this.zzjD);
        }
        if (this.zzjH) {
            n += zzsn.zzf(9, this.zzjH);
        }
        if (this.zzjG != null && this.zzjG.length > 0) {
            int l = 0;
            int n5 = 0;
            while (l < this.zzjG.length) {
                n5 += zzsn.zzmx(this.zzjG[l]);
                ++l;
            }
            n = n + n5 + 1 * this.zzjG.length;
        }
        if (this.zzjF != null) {
            final int length = this.zzjF.length;
            int n6 = 0;
            if (length > 0) {
                while (n6 < this.zzjF.length) {
                    final zza zza4 = this.zzjF[n6];
                    if (zza4 != null) {
                        n += zzsn.zzc(11, zza4);
                    }
                    ++n6;
                }
            }
        }
        if (this.zzjE) {
            n += zzsn.zzf(12, this.zzjE);
        }
        return n;
    }
}
