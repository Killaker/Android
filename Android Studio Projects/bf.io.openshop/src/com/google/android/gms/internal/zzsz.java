package com.google.android.gms.internal;

import java.io.*;
import java.util.*;

public interface zzsz
{
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
    
    public static final class zze extends zzso<zze>
    {
        private static volatile zze[] zzbvj;
        public String key;
        public String value;
        
        public zze() {
            this.zzJH();
        }
        
        public static zze[] zzJG() {
            Label_0027: {
                if (zze.zzbvj != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zze.zzbvj == null) {
                        zze.zzbvj = new zze[0];
                    }
                    return zze.zzbvj;
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
                final boolean b2 = o instanceof zze;
                b = false;
                if (b2) {
                    final zze zze = (zze)o;
                    if (this.key == null) {
                        final String key = zze.key;
                        b = false;
                        if (key != null) {
                            return b;
                        }
                    }
                    else if (!this.key.equals(zze.key)) {
                        return false;
                    }
                    if (this.value == null) {
                        final String value = zze.value;
                        b = false;
                        if (value != null) {
                            return b;
                        }
                    }
                    else if (!this.value.equals(zze.value)) {
                        return false;
                    }
                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                        if (zze.zzbuj != null) {
                            final boolean empty = zze.zzbuj.isEmpty();
                            b = false;
                            if (!empty) {
                                return b;
                            }
                        }
                        return true;
                    }
                    return this.zzbuj.equals(zze.zzbuj);
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.key == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.key.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.value == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.value.hashCode();
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
            if (!this.key.equals("")) {
                zzsn.zzn(1, this.key);
            }
            if (!this.value.equals("")) {
                zzsn.zzn(2, this.value);
            }
            super.writeTo(zzsn);
        }
        
        public zze zzJH() {
            this.key = "";
            this.value = "";
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zze zzW(final zzsm zzsm) throws IOException {
        Label_0049:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!this.zza(zzsm, zzIX)) {
                            break Label_0049;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0049;
                    }
                    case 10: {
                        this.key = zzsm.readString();
                        continue;
                    }
                    case 18: {
                        this.value = zzsm.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (!this.key.equals("")) {
                zzz += zzsn.zzo(1, this.key);
            }
            if (!this.value.equals("")) {
                zzz += zzsn.zzo(2, this.value);
            }
            return zzz;
        }
    }
}
