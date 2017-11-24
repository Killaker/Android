package com.google.android.gms.internal;

import java.io.*;

public static final class zzf extends zzso<zzf>
{
    public String version;
    public String[] zziG;
    public String[] zziH;
    public zzag.zza[] zziI;
    public zze[] zziJ;
    public zzb[] zziK;
    public zzb[] zziL;
    public zzb[] zziM;
    public zzg[] zziN;
    public String zziO;
    public String zziP;
    public String zziQ;
    public zza zziR;
    public float zziS;
    public boolean zziT;
    public String[] zziU;
    public int zziV;
    
    public zzf() {
        this.zzJ();
    }
    
    public static zzf zzc(final byte[] array) throws zzst {
        return zzsu.mergeFrom(new zzf(), array);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzf;
            b = false;
            if (b2) {
                final zzf zzf = (zzf)o;
                final boolean equals = zzss.equals(this.zziG, zzf.zziG);
                b = false;
                if (equals) {
                    final boolean equals2 = zzss.equals(this.zziH, zzf.zziH);
                    b = false;
                    if (equals2) {
                        final boolean equals3 = zzss.equals(this.zziI, zzf.zziI);
                        b = false;
                        if (equals3) {
                            final boolean equals4 = zzss.equals(this.zziJ, zzf.zziJ);
                            b = false;
                            if (equals4) {
                                final boolean equals5 = zzss.equals(this.zziK, zzf.zziK);
                                b = false;
                                if (equals5) {
                                    final boolean equals6 = zzss.equals(this.zziL, zzf.zziL);
                                    b = false;
                                    if (equals6) {
                                        final boolean equals7 = zzss.equals(this.zziM, zzf.zziM);
                                        b = false;
                                        if (equals7) {
                                            final boolean equals8 = zzss.equals(this.zziN, zzf.zziN);
                                            b = false;
                                            if (equals8) {
                                                if (this.zziO == null) {
                                                    final String zziO = zzf.zziO;
                                                    b = false;
                                                    if (zziO != null) {
                                                        return b;
                                                    }
                                                }
                                                else if (!this.zziO.equals(zzf.zziO)) {
                                                    return false;
                                                }
                                                if (this.zziP == null) {
                                                    final String zziP = zzf.zziP;
                                                    b = false;
                                                    if (zziP != null) {
                                                        return b;
                                                    }
                                                }
                                                else if (!this.zziP.equals(zzf.zziP)) {
                                                    return false;
                                                }
                                                if (this.zziQ == null) {
                                                    final String zziQ = zzf.zziQ;
                                                    b = false;
                                                    if (zziQ != null) {
                                                        return b;
                                                    }
                                                }
                                                else if (!this.zziQ.equals(zzf.zziQ)) {
                                                    return false;
                                                }
                                                if (this.version == null) {
                                                    final String version = zzf.version;
                                                    b = false;
                                                    if (version != null) {
                                                        return b;
                                                    }
                                                }
                                                else if (!this.version.equals(zzf.version)) {
                                                    return false;
                                                }
                                                if (this.zziR == null) {
                                                    final zza zziR = zzf.zziR;
                                                    b = false;
                                                    if (zziR != null) {
                                                        return b;
                                                    }
                                                }
                                                else if (!this.zziR.equals(zzf.zziR)) {
                                                    return false;
                                                }
                                                final int floatToIntBits = Float.floatToIntBits(this.zziS);
                                                final int floatToIntBits2 = Float.floatToIntBits(zzf.zziS);
                                                b = false;
                                                if (floatToIntBits == floatToIntBits2) {
                                                    final boolean zziT = this.zziT;
                                                    final boolean zziT2 = zzf.zziT;
                                                    b = false;
                                                    if (zziT == zziT2) {
                                                        final boolean equals9 = zzss.equals(this.zziU, zzf.zziU);
                                                        b = false;
                                                        if (equals9) {
                                                            final int zziV = this.zziV;
                                                            final int zziV2 = zzf.zziV;
                                                            b = false;
                                                            if (zziV == zziV2) {
                                                                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                                                    if (zzf.zzbuj != null) {
                                                                        final boolean empty = zzf.zzbuj.isEmpty();
                                                                        b = false;
                                                                        if (!empty) {
                                                                            return b;
                                                                        }
                                                                    }
                                                                    return true;
                                                                }
                                                                return this.zzbuj.equals(zzf.zzbuj);
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
        final int n = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziG)) + zzss.hashCode(this.zziH)) + zzss.hashCode(this.zziI)) + zzss.hashCode(this.zziJ)) + zzss.hashCode(this.zziK)) + zzss.hashCode(this.zziL)) + zzss.hashCode(this.zziM)) + zzss.hashCode(this.zziN));
        int hashCode;
        if (this.zziO == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zziO.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.zziP == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zziP.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zziQ == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zziQ.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        int hashCode4;
        if (this.version == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.version.hashCode();
        }
        final int n5 = 31 * (hashCode4 + n4);
        int hashCode5;
        if (this.zziR == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.zziR.hashCode();
        }
        final int n6 = 31 * (31 * (hashCode5 + n5) + Float.floatToIntBits(this.zziS));
        int n7;
        if (this.zziT) {
            n7 = 1231;
        }
        else {
            n7 = 1237;
        }
        final int n8 = 31 * (31 * (31 * (n7 + n6) + zzss.hashCode(this.zziU)) + this.zziV);
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
        if (this.zziH != null && this.zziH.length > 0) {
            for (int i = 0; i < this.zziH.length; ++i) {
                final String s = this.zziH[i];
                if (s != null) {
                    zzsn.zzn(1, s);
                }
            }
        }
        if (this.zziI != null && this.zziI.length > 0) {
            for (int j = 0; j < this.zziI.length; ++j) {
                final zzag.zza zza = this.zziI[j];
                if (zza != null) {
                    zzsn.zza(2, zza);
                }
            }
        }
        if (this.zziJ != null && this.zziJ.length > 0) {
            for (int k = 0; k < this.zziJ.length; ++k) {
                final zze zze = this.zziJ[k];
                if (zze != null) {
                    zzsn.zza(3, zze);
                }
            }
        }
        if (this.zziK != null && this.zziK.length > 0) {
            for (int l = 0; l < this.zziK.length; ++l) {
                final zzb zzb = this.zziK[l];
                if (zzb != null) {
                    zzsn.zza(4, zzb);
                }
            }
        }
        if (this.zziL != null && this.zziL.length > 0) {
            for (int n = 0; n < this.zziL.length; ++n) {
                final zzb zzb2 = this.zziL[n];
                if (zzb2 != null) {
                    zzsn.zza(5, zzb2);
                }
            }
        }
        if (this.zziM != null && this.zziM.length > 0) {
            for (int n2 = 0; n2 < this.zziM.length; ++n2) {
                final zzb zzb3 = this.zziM[n2];
                if (zzb3 != null) {
                    zzsn.zza(6, zzb3);
                }
            }
        }
        if (this.zziN != null && this.zziN.length > 0) {
            for (int n3 = 0; n3 < this.zziN.length; ++n3) {
                final zzg zzg = this.zziN[n3];
                if (zzg != null) {
                    zzsn.zza(7, zzg);
                }
            }
        }
        if (!this.zziO.equals("")) {
            zzsn.zzn(9, this.zziO);
        }
        if (!this.zziP.equals("")) {
            zzsn.zzn(10, this.zziP);
        }
        if (!this.zziQ.equals("0")) {
            zzsn.zzn(12, this.zziQ);
        }
        if (!this.version.equals("")) {
            zzsn.zzn(13, this.version);
        }
        if (this.zziR != null) {
            zzsn.zza(14, this.zziR);
        }
        if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0f)) {
            zzsn.zzb(15, this.zziS);
        }
        if (this.zziU != null && this.zziU.length > 0) {
            for (int n4 = 0; n4 < this.zziU.length; ++n4) {
                final String s2 = this.zziU[n4];
                if (s2 != null) {
                    zzsn.zzn(16, s2);
                }
            }
        }
        if (this.zziV != 0) {
            zzsn.zzA(17, this.zziV);
        }
        if (this.zziT) {
            zzsn.zze(18, this.zziT);
        }
        if (this.zziG != null) {
            final int length = this.zziG.length;
            int n5 = 0;
            if (length > 0) {
                while (n5 < this.zziG.length) {
                    final String s3 = this.zziG[n5];
                    if (s3 != null) {
                        zzsn.zzn(19, s3);
                    }
                    ++n5;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zzf zzJ() {
        this.zziG = zzsx.zzbuB;
        this.zziH = zzsx.zzbuB;
        this.zziI = zzag.zza.zzQ();
        this.zziJ = zze.zzH();
        this.zziK = zzb.zzC();
        this.zziL = zzb.zzC();
        this.zziM = zzb.zzC();
        this.zziN = zzg.zzK();
        this.zziO = "";
        this.zziP = "";
        this.zziQ = "0";
        this.version = "";
        this.zziR = null;
        this.zziS = 0.0f;
        this.zziT = false;
        this.zziU = zzsx.zzbuB;
        this.zziV = 0;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzf zzf(final zzsm zzsm) throws IOException {
    Label_0169:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0169;
                    }
                    continue;
                }
                case 0: {
                    break Label_0169;
                }
                case 10: {
                    final int zzc = zzsx.zzc(zzsm, 10);
                    int i;
                    if (this.zziH == null) {
                        i = 0;
                    }
                    else {
                        i = this.zziH.length;
                    }
                    final String[] zziH = new String[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zziH, 0, zziH, 0, i);
                    }
                    while (i < -1 + zziH.length) {
                        zziH[i] = zzsm.readString();
                        zzsm.zzIX();
                        ++i;
                    }
                    zziH[i] = zzsm.readString();
                    this.zziH = zziH;
                    continue;
                }
                case 18: {
                    final int zzc2 = zzsx.zzc(zzsm, 18);
                    int j;
                    if (this.zziI == null) {
                        j = 0;
                    }
                    else {
                        j = this.zziI.length;
                    }
                    final zzag.zza[] zziI = new zzag.zza[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zziI, 0, zziI, 0, j);
                    }
                    while (j < -1 + zziI.length) {
                        zzsm.zza(zziI[j] = new zzag.zza());
                        zzsm.zzIX();
                        ++j;
                    }
                    zzsm.zza(zziI[j] = new zzag.zza());
                    this.zziI = zziI;
                    continue;
                }
                case 26: {
                    final int zzc3 = zzsx.zzc(zzsm, 26);
                    int k;
                    if (this.zziJ == null) {
                        k = 0;
                    }
                    else {
                        k = this.zziJ.length;
                    }
                    final zze[] zziJ = new zze[zzc3 + k];
                    if (k != 0) {
                        System.arraycopy(this.zziJ, 0, zziJ, 0, k);
                    }
                    while (k < -1 + zziJ.length) {
                        zzsm.zza(zziJ[k] = new zze());
                        zzsm.zzIX();
                        ++k;
                    }
                    zzsm.zza(zziJ[k] = new zze());
                    this.zziJ = zziJ;
                    continue;
                }
                case 34: {
                    final int zzc4 = zzsx.zzc(zzsm, 34);
                    int l;
                    if (this.zziK == null) {
                        l = 0;
                    }
                    else {
                        l = this.zziK.length;
                    }
                    final zzb[] zziK = new zzb[zzc4 + l];
                    if (l != 0) {
                        System.arraycopy(this.zziK, 0, zziK, 0, l);
                    }
                    while (l < -1 + zziK.length) {
                        zzsm.zza(zziK[l] = new zzb());
                        zzsm.zzIX();
                        ++l;
                    }
                    zzsm.zza(zziK[l] = new zzb());
                    this.zziK = zziK;
                    continue;
                }
                case 42: {
                    final int zzc5 = zzsx.zzc(zzsm, 42);
                    int length;
                    if (this.zziL == null) {
                        length = 0;
                    }
                    else {
                        length = this.zziL.length;
                    }
                    final zzb[] zziL = new zzb[zzc5 + length];
                    if (length != 0) {
                        System.arraycopy(this.zziL, 0, zziL, 0, length);
                    }
                    while (length < -1 + zziL.length) {
                        zzsm.zza(zziL[length] = new zzb());
                        zzsm.zzIX();
                        ++length;
                    }
                    zzsm.zza(zziL[length] = new zzb());
                    this.zziL = zziL;
                    continue;
                }
                case 50: {
                    final int zzc6 = zzsx.zzc(zzsm, 50);
                    int length2;
                    if (this.zziM == null) {
                        length2 = 0;
                    }
                    else {
                        length2 = this.zziM.length;
                    }
                    final zzb[] zziM = new zzb[zzc6 + length2];
                    if (length2 != 0) {
                        System.arraycopy(this.zziM, 0, zziM, 0, length2);
                    }
                    while (length2 < -1 + zziM.length) {
                        zzsm.zza(zziM[length2] = new zzb());
                        zzsm.zzIX();
                        ++length2;
                    }
                    zzsm.zza(zziM[length2] = new zzb());
                    this.zziM = zziM;
                    continue;
                }
                case 58: {
                    final int zzc7 = zzsx.zzc(zzsm, 58);
                    int length3;
                    if (this.zziN == null) {
                        length3 = 0;
                    }
                    else {
                        length3 = this.zziN.length;
                    }
                    final zzg[] zziN = new zzg[zzc7 + length3];
                    if (length3 != 0) {
                        System.arraycopy(this.zziN, 0, zziN, 0, length3);
                    }
                    while (length3 < -1 + zziN.length) {
                        zzsm.zza(zziN[length3] = new zzg());
                        zzsm.zzIX();
                        ++length3;
                    }
                    zzsm.zza(zziN[length3] = new zzg());
                    this.zziN = zziN;
                    continue;
                }
                case 74: {
                    this.zziO = zzsm.readString();
                    continue;
                }
                case 82: {
                    this.zziP = zzsm.readString();
                    continue;
                }
                case 98: {
                    this.zziQ = zzsm.readString();
                    continue;
                }
                case 106: {
                    this.version = zzsm.readString();
                    continue;
                }
                case 114: {
                    if (this.zziR == null) {
                        this.zziR = new zza();
                    }
                    zzsm.zza(this.zziR);
                    continue;
                }
                case 125: {
                    this.zziS = zzsm.readFloat();
                    continue;
                }
                case 130: {
                    final int zzc8 = zzsx.zzc(zzsm, 130);
                    int length4;
                    if (this.zziU == null) {
                        length4 = 0;
                    }
                    else {
                        length4 = this.zziU.length;
                    }
                    final String[] zziU = new String[zzc8 + length4];
                    if (length4 != 0) {
                        System.arraycopy(this.zziU, 0, zziU, 0, length4);
                    }
                    while (length4 < -1 + zziU.length) {
                        zziU[length4] = zzsm.readString();
                        zzsm.zzIX();
                        ++length4;
                    }
                    zziU[length4] = zzsm.readString();
                    this.zziU = zziU;
                    continue;
                }
                case 136: {
                    this.zziV = zzsm.zzJb();
                    continue;
                }
                case 144: {
                    this.zziT = zzsm.zzJc();
                    continue;
                }
                case 154: {
                    final int zzc9 = zzsx.zzc(zzsm, 154);
                    int length5;
                    if (this.zziG == null) {
                        length5 = 0;
                    }
                    else {
                        length5 = this.zziG.length;
                    }
                    final String[] zziG = new String[zzc9 + length5];
                    if (length5 != 0) {
                        System.arraycopy(this.zziG, 0, zziG, 0, length5);
                    }
                    while (length5 < -1 + zziG.length) {
                        zziG[length5] = zzsm.readString();
                        zzsm.zzIX();
                        ++length5;
                    }
                    zziG[length5] = zzsm.readString();
                    this.zziG = zziG;
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
        if (this.zziH != null && this.zziH.length > 0) {
            int j = 0;
            int n = 0;
            int n2 = 0;
            while (j < this.zziH.length) {
                final String s = this.zziH[j];
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
        if (this.zziI != null && this.zziI.length > 0) {
            int n4 = n3;
            for (int k = 0; k < this.zziI.length; ++k) {
                final zzag.zza zza = this.zziI[k];
                if (zza != null) {
                    n4 += zzsn.zzc(2, zza);
                }
            }
            n3 = n4;
        }
        if (this.zziJ != null && this.zziJ.length > 0) {
            int n5 = n3;
            for (int l = 0; l < this.zziJ.length; ++l) {
                final zze zze = this.zziJ[l];
                if (zze != null) {
                    n5 += zzsn.zzc(3, zze);
                }
            }
            n3 = n5;
        }
        if (this.zziK != null && this.zziK.length > 0) {
            int n6 = n3;
            for (int n7 = 0; n7 < this.zziK.length; ++n7) {
                final zzb zzb = this.zziK[n7];
                if (zzb != null) {
                    n6 += zzsn.zzc(4, zzb);
                }
            }
            n3 = n6;
        }
        if (this.zziL != null && this.zziL.length > 0) {
            int n8 = n3;
            for (int n9 = 0; n9 < this.zziL.length; ++n9) {
                final zzb zzb2 = this.zziL[n9];
                if (zzb2 != null) {
                    n8 += zzsn.zzc(5, zzb2);
                }
            }
            n3 = n8;
        }
        if (this.zziM != null && this.zziM.length > 0) {
            int n10 = n3;
            for (int n11 = 0; n11 < this.zziM.length; ++n11) {
                final zzb zzb3 = this.zziM[n11];
                if (zzb3 != null) {
                    n10 += zzsn.zzc(6, zzb3);
                }
            }
            n3 = n10;
        }
        if (this.zziN != null && this.zziN.length > 0) {
            int n12 = n3;
            for (int n13 = 0; n13 < this.zziN.length; ++n13) {
                final zzg zzg = this.zziN[n13];
                if (zzg != null) {
                    n12 += zzsn.zzc(7, zzg);
                }
            }
            n3 = n12;
        }
        if (!this.zziO.equals("")) {
            n3 += zzsn.zzo(9, this.zziO);
        }
        if (!this.zziP.equals("")) {
            n3 += zzsn.zzo(10, this.zziP);
        }
        if (!this.zziQ.equals("0")) {
            n3 += zzsn.zzo(12, this.zziQ);
        }
        if (!this.version.equals("")) {
            n3 += zzsn.zzo(13, this.version);
        }
        if (this.zziR != null) {
            n3 += zzsn.zzc(14, this.zziR);
        }
        if (Float.floatToIntBits(this.zziS) != Float.floatToIntBits(0.0f)) {
            n3 += zzsn.zzc(15, this.zziS);
        }
        if (this.zziU != null && this.zziU.length > 0) {
            int n14 = 0;
            int n15 = 0;
            int n16 = 0;
            while (n14 < this.zziU.length) {
                final String s2 = this.zziU[n14];
                if (s2 != null) {
                    ++n16;
                    n15 += zzsn.zzgO(s2);
                }
                ++n14;
            }
            n3 = n3 + n15 + n16 * 2;
        }
        if (this.zziV != 0) {
            n3 += zzsn.zzC(17, this.zziV);
        }
        if (this.zziT) {
            n3 += zzsn.zzf(18, this.zziT);
        }
        if (this.zziG != null && this.zziG.length > 0) {
            int n17 = 0;
            int n18 = 0;
            while (i < this.zziG.length) {
                final String s3 = this.zziG[i];
                if (s3 != null) {
                    ++n18;
                    n17 += zzsn.zzgO(s3);
                }
                ++i;
            }
            n3 = n3 + n17 + n18 * 2;
        }
        return n3;
    }
}
