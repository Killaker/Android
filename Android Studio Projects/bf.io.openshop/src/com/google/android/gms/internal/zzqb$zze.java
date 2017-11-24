package com.google.android.gms.internal;

import java.io.*;

public static final class zze extends zzsu
{
    private static volatile zze[] zzbak;
    public String appId;
    public String osVersion;
    public String zzaMV;
    public String zzaVt;
    public String zzaVu;
    public String zzaVx;
    public Boolean zzbaA;
    public String zzbaB;
    public Long zzbaC;
    public Integer zzbaD;
    public Boolean zzbaE;
    public zza[] zzbaF;
    public Integer zzbal;
    public zzb[] zzbam;
    public zzg[] zzban;
    public Long zzbao;
    public Long zzbap;
    public Long zzbaq;
    public Long zzbar;
    public Long zzbas;
    public String zzbat;
    public String zzbau;
    public String zzbav;
    public Integer zzbaw;
    public Long zzbax;
    public Long zzbay;
    public String zzbaz;
    
    public zze() {
        this.zzDX();
    }
    
    public static zze[] zzDW() {
        Label_0027: {
            if (zze.zzbak != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zze.zzbak == null) {
                    zze.zzbak = new zze[0];
                }
                return zze.zzbak;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zze)) {
                return false;
            }
            final zze zze = (zze)o;
            if (this.zzbal == null) {
                if (zze.zzbal != null) {
                    return false;
                }
            }
            else if (!this.zzbal.equals(zze.zzbal)) {
                return false;
            }
            if (!zzss.equals(this.zzbam, zze.zzbam)) {
                return false;
            }
            if (!zzss.equals(this.zzban, zze.zzban)) {
                return false;
            }
            if (this.zzbao == null) {
                if (zze.zzbao != null) {
                    return false;
                }
            }
            else if (!this.zzbao.equals(zze.zzbao)) {
                return false;
            }
            if (this.zzbap == null) {
                if (zze.zzbap != null) {
                    return false;
                }
            }
            else if (!this.zzbap.equals(zze.zzbap)) {
                return false;
            }
            if (this.zzbaq == null) {
                if (zze.zzbaq != null) {
                    return false;
                }
            }
            else if (!this.zzbaq.equals(zze.zzbaq)) {
                return false;
            }
            if (this.zzbar == null) {
                if (zze.zzbar != null) {
                    return false;
                }
            }
            else if (!this.zzbar.equals(zze.zzbar)) {
                return false;
            }
            if (this.zzbas == null) {
                if (zze.zzbas != null) {
                    return false;
                }
            }
            else if (!this.zzbas.equals(zze.zzbas)) {
                return false;
            }
            if (this.zzbat == null) {
                if (zze.zzbat != null) {
                    return false;
                }
            }
            else if (!this.zzbat.equals(zze.zzbat)) {
                return false;
            }
            if (this.osVersion == null) {
                if (zze.osVersion != null) {
                    return false;
                }
            }
            else if (!this.osVersion.equals(zze.osVersion)) {
                return false;
            }
            if (this.zzbau == null) {
                if (zze.zzbau != null) {
                    return false;
                }
            }
            else if (!this.zzbau.equals(zze.zzbau)) {
                return false;
            }
            if (this.zzbav == null) {
                if (zze.zzbav != null) {
                    return false;
                }
            }
            else if (!this.zzbav.equals(zze.zzbav)) {
                return false;
            }
            if (this.zzbaw == null) {
                if (zze.zzbaw != null) {
                    return false;
                }
            }
            else if (!this.zzbaw.equals(zze.zzbaw)) {
                return false;
            }
            if (this.zzaVu == null) {
                if (zze.zzaVu != null) {
                    return false;
                }
            }
            else if (!this.zzaVu.equals(zze.zzaVu)) {
                return false;
            }
            if (this.appId == null) {
                if (zze.appId != null) {
                    return false;
                }
            }
            else if (!this.appId.equals(zze.appId)) {
                return false;
            }
            if (this.zzaMV == null) {
                if (zze.zzaMV != null) {
                    return false;
                }
            }
            else if (!this.zzaMV.equals(zze.zzaMV)) {
                return false;
            }
            if (this.zzbax == null) {
                if (zze.zzbax != null) {
                    return false;
                }
            }
            else if (!this.zzbax.equals(zze.zzbax)) {
                return false;
            }
            if (this.zzbay == null) {
                if (zze.zzbay != null) {
                    return false;
                }
            }
            else if (!this.zzbay.equals(zze.zzbay)) {
                return false;
            }
            if (this.zzbaz == null) {
                if (zze.zzbaz != null) {
                    return false;
                }
            }
            else if (!this.zzbaz.equals(zze.zzbaz)) {
                return false;
            }
            if (this.zzbaA == null) {
                if (zze.zzbaA != null) {
                    return false;
                }
            }
            else if (!this.zzbaA.equals(zze.zzbaA)) {
                return false;
            }
            if (this.zzbaB == null) {
                if (zze.zzbaB != null) {
                    return false;
                }
            }
            else if (!this.zzbaB.equals(zze.zzbaB)) {
                return false;
            }
            if (this.zzbaC == null) {
                if (zze.zzbaC != null) {
                    return false;
                }
            }
            else if (!this.zzbaC.equals(zze.zzbaC)) {
                return false;
            }
            if (this.zzbaD == null) {
                if (zze.zzbaD != null) {
                    return false;
                }
            }
            else if (!this.zzbaD.equals(zze.zzbaD)) {
                return false;
            }
            if (this.zzaVx == null) {
                if (zze.zzaVx != null) {
                    return false;
                }
            }
            else if (!this.zzaVx.equals(zze.zzaVx)) {
                return false;
            }
            if (this.zzaVt == null) {
                if (zze.zzaVt != null) {
                    return false;
                }
            }
            else if (!this.zzaVt.equals(zze.zzaVt)) {
                return false;
            }
            if (this.zzbaE == null) {
                if (zze.zzbaE != null) {
                    return false;
                }
            }
            else if (!this.zzbaE.equals(zze.zzbaE)) {
                return false;
            }
            if (!zzss.equals(this.zzbaF, zze.zzbaF)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.zzbal == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbal.hashCode();
        }
        final int n2 = 31 * (31 * (31 * (hashCode + n) + zzss.hashCode(this.zzbam)) + zzss.hashCode(this.zzban));
        int hashCode2;
        if (this.zzbao == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzbao.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzbap == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzbap.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        int hashCode4;
        if (this.zzbaq == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.zzbaq.hashCode();
        }
        final int n5 = 31 * (hashCode4 + n4);
        int hashCode5;
        if (this.zzbar == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = this.zzbar.hashCode();
        }
        final int n6 = 31 * (hashCode5 + n5);
        int hashCode6;
        if (this.zzbas == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = this.zzbas.hashCode();
        }
        final int n7 = 31 * (hashCode6 + n6);
        int hashCode7;
        if (this.zzbat == null) {
            hashCode7 = 0;
        }
        else {
            hashCode7 = this.zzbat.hashCode();
        }
        final int n8 = 31 * (hashCode7 + n7);
        int hashCode8;
        if (this.osVersion == null) {
            hashCode8 = 0;
        }
        else {
            hashCode8 = this.osVersion.hashCode();
        }
        final int n9 = 31 * (hashCode8 + n8);
        int hashCode9;
        if (this.zzbau == null) {
            hashCode9 = 0;
        }
        else {
            hashCode9 = this.zzbau.hashCode();
        }
        final int n10 = 31 * (hashCode9 + n9);
        int hashCode10;
        if (this.zzbav == null) {
            hashCode10 = 0;
        }
        else {
            hashCode10 = this.zzbav.hashCode();
        }
        final int n11 = 31 * (hashCode10 + n10);
        int hashCode11;
        if (this.zzbaw == null) {
            hashCode11 = 0;
        }
        else {
            hashCode11 = this.zzbaw.hashCode();
        }
        final int n12 = 31 * (hashCode11 + n11);
        int hashCode12;
        if (this.zzaVu == null) {
            hashCode12 = 0;
        }
        else {
            hashCode12 = this.zzaVu.hashCode();
        }
        final int n13 = 31 * (hashCode12 + n12);
        int hashCode13;
        if (this.appId == null) {
            hashCode13 = 0;
        }
        else {
            hashCode13 = this.appId.hashCode();
        }
        final int n14 = 31 * (hashCode13 + n13);
        int hashCode14;
        if (this.zzaMV == null) {
            hashCode14 = 0;
        }
        else {
            hashCode14 = this.zzaMV.hashCode();
        }
        final int n15 = 31 * (hashCode14 + n14);
        int hashCode15;
        if (this.zzbax == null) {
            hashCode15 = 0;
        }
        else {
            hashCode15 = this.zzbax.hashCode();
        }
        final int n16 = 31 * (hashCode15 + n15);
        int hashCode16;
        if (this.zzbay == null) {
            hashCode16 = 0;
        }
        else {
            hashCode16 = this.zzbay.hashCode();
        }
        final int n17 = 31 * (hashCode16 + n16);
        int hashCode17;
        if (this.zzbaz == null) {
            hashCode17 = 0;
        }
        else {
            hashCode17 = this.zzbaz.hashCode();
        }
        final int n18 = 31 * (hashCode17 + n17);
        int hashCode18;
        if (this.zzbaA == null) {
            hashCode18 = 0;
        }
        else {
            hashCode18 = this.zzbaA.hashCode();
        }
        final int n19 = 31 * (hashCode18 + n18);
        int hashCode19;
        if (this.zzbaB == null) {
            hashCode19 = 0;
        }
        else {
            hashCode19 = this.zzbaB.hashCode();
        }
        final int n20 = 31 * (hashCode19 + n19);
        int hashCode20;
        if (this.zzbaC == null) {
            hashCode20 = 0;
        }
        else {
            hashCode20 = this.zzbaC.hashCode();
        }
        final int n21 = 31 * (hashCode20 + n20);
        int hashCode21;
        if (this.zzbaD == null) {
            hashCode21 = 0;
        }
        else {
            hashCode21 = this.zzbaD.hashCode();
        }
        final int n22 = 31 * (hashCode21 + n21);
        int hashCode22;
        if (this.zzaVx == null) {
            hashCode22 = 0;
        }
        else {
            hashCode22 = this.zzaVx.hashCode();
        }
        final int n23 = 31 * (hashCode22 + n22);
        int hashCode23;
        if (this.zzaVt == null) {
            hashCode23 = 0;
        }
        else {
            hashCode23 = this.zzaVt.hashCode();
        }
        final int n24 = 31 * (hashCode23 + n23);
        final Boolean zzbaE = this.zzbaE;
        int hashCode24 = 0;
        if (zzbaE != null) {
            hashCode24 = this.zzbaE.hashCode();
        }
        return 31 * (n24 + hashCode24) + zzss.hashCode(this.zzbaF);
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbal != null) {
            zzsn.zzA(1, this.zzbal);
        }
        if (this.zzbam != null && this.zzbam.length > 0) {
            for (int i = 0; i < this.zzbam.length; ++i) {
                final zzb zzb = this.zzbam[i];
                if (zzb != null) {
                    zzsn.zza(2, zzb);
                }
            }
        }
        if (this.zzban != null && this.zzban.length > 0) {
            for (int j = 0; j < this.zzban.length; ++j) {
                final zzg zzg = this.zzban[j];
                if (zzg != null) {
                    zzsn.zza(3, zzg);
                }
            }
        }
        if (this.zzbao != null) {
            zzsn.zzb(4, this.zzbao);
        }
        if (this.zzbap != null) {
            zzsn.zzb(5, this.zzbap);
        }
        if (this.zzbaq != null) {
            zzsn.zzb(6, this.zzbaq);
        }
        if (this.zzbas != null) {
            zzsn.zzb(7, this.zzbas);
        }
        if (this.zzbat != null) {
            zzsn.zzn(8, this.zzbat);
        }
        if (this.osVersion != null) {
            zzsn.zzn(9, this.osVersion);
        }
        if (this.zzbau != null) {
            zzsn.zzn(10, this.zzbau);
        }
        if (this.zzbav != null) {
            zzsn.zzn(11, this.zzbav);
        }
        if (this.zzbaw != null) {
            zzsn.zzA(12, this.zzbaw);
        }
        if (this.zzaVu != null) {
            zzsn.zzn(13, this.zzaVu);
        }
        if (this.appId != null) {
            zzsn.zzn(14, this.appId);
        }
        if (this.zzaMV != null) {
            zzsn.zzn(16, this.zzaMV);
        }
        if (this.zzbax != null) {
            zzsn.zzb(17, this.zzbax);
        }
        if (this.zzbay != null) {
            zzsn.zzb(18, this.zzbay);
        }
        if (this.zzbaz != null) {
            zzsn.zzn(19, this.zzbaz);
        }
        if (this.zzbaA != null) {
            zzsn.zze(20, this.zzbaA);
        }
        if (this.zzbaB != null) {
            zzsn.zzn(21, this.zzbaB);
        }
        if (this.zzbaC != null) {
            zzsn.zzb(22, this.zzbaC);
        }
        if (this.zzbaD != null) {
            zzsn.zzA(23, this.zzbaD);
        }
        if (this.zzaVx != null) {
            zzsn.zzn(24, this.zzaVx);
        }
        if (this.zzaVt != null) {
            zzsn.zzn(25, this.zzaVt);
        }
        if (this.zzbar != null) {
            zzsn.zzb(26, this.zzbar);
        }
        if (this.zzbaE != null) {
            zzsn.zze(28, this.zzbaE);
        }
        if (this.zzbaF != null) {
            final int length = this.zzbaF.length;
            int k = 0;
            if (length > 0) {
                while (k < this.zzbaF.length) {
                    final zza zza = this.zzbaF[k];
                    if (zza != null) {
                        zzsn.zza(29, zza);
                    }
                    ++k;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zze zzDX() {
        this.zzbal = null;
        this.zzbam = zzb.zzDR();
        this.zzban = zzg.zzDZ();
        this.zzbao = null;
        this.zzbap = null;
        this.zzbaq = null;
        this.zzbar = null;
        this.zzbas = null;
        this.zzbat = null;
        this.osVersion = null;
        this.zzbau = null;
        this.zzbav = null;
        this.zzbaw = null;
        this.zzaVu = null;
        this.appId = null;
        this.zzaMV = null;
        this.zzbax = null;
        this.zzbay = null;
        this.zzbaz = null;
        this.zzbaA = null;
        this.zzbaB = null;
        this.zzbaC = null;
        this.zzbaD = null;
        this.zzaVx = null;
        this.zzaVt = null;
        this.zzbaE = null;
        this.zzbaF = zza.zzDP();
        this.zzbuu = -1;
        return this;
    }
    
    public zze zzG(final zzsm zzsm) throws IOException {
    Label_0248:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0248;
                    }
                    continue;
                }
                case 0: {
                    break Label_0248;
                }
                case 8: {
                    this.zzbal = zzsm.zzJb();
                    continue;
                }
                case 18: {
                    final int zzc = zzsx.zzc(zzsm, 18);
                    int i;
                    if (this.zzbam == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzbam.length;
                    }
                    final zzb[] zzbam = new zzb[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzbam, 0, zzbam, 0, i);
                    }
                    while (i < -1 + zzbam.length) {
                        zzsm.zza(zzbam[i] = new zzb());
                        zzsm.zzIX();
                        ++i;
                    }
                    zzsm.zza(zzbam[i] = new zzb());
                    this.zzbam = zzbam;
                    continue;
                }
                case 26: {
                    final int zzc2 = zzsx.zzc(zzsm, 26);
                    int j;
                    if (this.zzban == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzban.length;
                    }
                    final zzg[] zzban = new zzg[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zzban, 0, zzban, 0, j);
                    }
                    while (j < -1 + zzban.length) {
                        zzsm.zza(zzban[j] = new zzg());
                        zzsm.zzIX();
                        ++j;
                    }
                    zzsm.zza(zzban[j] = new zzg());
                    this.zzban = zzban;
                    continue;
                }
                case 32: {
                    this.zzbao = zzsm.zzJa();
                    continue;
                }
                case 40: {
                    this.zzbap = zzsm.zzJa();
                    continue;
                }
                case 48: {
                    this.zzbaq = zzsm.zzJa();
                    continue;
                }
                case 56: {
                    this.zzbas = zzsm.zzJa();
                    continue;
                }
                case 66: {
                    this.zzbat = zzsm.readString();
                    continue;
                }
                case 74: {
                    this.osVersion = zzsm.readString();
                    continue;
                }
                case 82: {
                    this.zzbau = zzsm.readString();
                    continue;
                }
                case 90: {
                    this.zzbav = zzsm.readString();
                    continue;
                }
                case 96: {
                    this.zzbaw = zzsm.zzJb();
                    continue;
                }
                case 106: {
                    this.zzaVu = zzsm.readString();
                    continue;
                }
                case 114: {
                    this.appId = zzsm.readString();
                    continue;
                }
                case 130: {
                    this.zzaMV = zzsm.readString();
                    continue;
                }
                case 136: {
                    this.zzbax = zzsm.zzJa();
                    continue;
                }
                case 144: {
                    this.zzbay = zzsm.zzJa();
                    continue;
                }
                case 154: {
                    this.zzbaz = zzsm.readString();
                    continue;
                }
                case 160: {
                    this.zzbaA = zzsm.zzJc();
                    continue;
                }
                case 170: {
                    this.zzbaB = zzsm.readString();
                    continue;
                }
                case 176: {
                    this.zzbaC = zzsm.zzJa();
                    continue;
                }
                case 184: {
                    this.zzbaD = zzsm.zzJb();
                    continue;
                }
                case 194: {
                    this.zzaVx = zzsm.readString();
                    continue;
                }
                case 202: {
                    this.zzaVt = zzsm.readString();
                    continue;
                }
                case 208: {
                    this.zzbar = zzsm.zzJa();
                    continue;
                }
                case 224: {
                    this.zzbaE = zzsm.zzJc();
                    continue;
                }
                case 234: {
                    final int zzc3 = zzsx.zzc(zzsm, 234);
                    int k;
                    if (this.zzbaF == null) {
                        k = 0;
                    }
                    else {
                        k = this.zzbaF.length;
                    }
                    final zza[] zzbaF = new zza[zzc3 + k];
                    if (k != 0) {
                        System.arraycopy(this.zzbaF, 0, zzbaF, 0, k);
                    }
                    while (k < -1 + zzbaF.length) {
                        zzsm.zza(zzbaF[k] = new zza());
                        zzsm.zzIX();
                        ++k;
                    }
                    zzsm.zza(zzbaF[k] = new zza());
                    this.zzbaF = zzbaF;
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzbal != null) {
            zzz += zzsn.zzC(1, this.zzbal);
        }
        if (this.zzbam != null && this.zzbam.length > 0) {
            int n = zzz;
            for (int i = 0; i < this.zzbam.length; ++i) {
                final zzb zzb = this.zzbam[i];
                if (zzb != null) {
                    n += zzsn.zzc(2, zzb);
                }
            }
            zzz = n;
        }
        if (this.zzban != null && this.zzban.length > 0) {
            int n2 = zzz;
            for (int j = 0; j < this.zzban.length; ++j) {
                final zzg zzg = this.zzban[j];
                if (zzg != null) {
                    n2 += zzsn.zzc(3, zzg);
                }
            }
            zzz = n2;
        }
        if (this.zzbao != null) {
            zzz += zzsn.zzd(4, this.zzbao);
        }
        if (this.zzbap != null) {
            zzz += zzsn.zzd(5, this.zzbap);
        }
        if (this.zzbaq != null) {
            zzz += zzsn.zzd(6, this.zzbaq);
        }
        if (this.zzbas != null) {
            zzz += zzsn.zzd(7, this.zzbas);
        }
        if (this.zzbat != null) {
            zzz += zzsn.zzo(8, this.zzbat);
        }
        if (this.osVersion != null) {
            zzz += zzsn.zzo(9, this.osVersion);
        }
        if (this.zzbau != null) {
            zzz += zzsn.zzo(10, this.zzbau);
        }
        if (this.zzbav != null) {
            zzz += zzsn.zzo(11, this.zzbav);
        }
        if (this.zzbaw != null) {
            zzz += zzsn.zzC(12, this.zzbaw);
        }
        if (this.zzaVu != null) {
            zzz += zzsn.zzo(13, this.zzaVu);
        }
        if (this.appId != null) {
            zzz += zzsn.zzo(14, this.appId);
        }
        if (this.zzaMV != null) {
            zzz += zzsn.zzo(16, this.zzaMV);
        }
        if (this.zzbax != null) {
            zzz += zzsn.zzd(17, this.zzbax);
        }
        if (this.zzbay != null) {
            zzz += zzsn.zzd(18, this.zzbay);
        }
        if (this.zzbaz != null) {
            zzz += zzsn.zzo(19, this.zzbaz);
        }
        if (this.zzbaA != null) {
            zzz += zzsn.zzf(20, this.zzbaA);
        }
        if (this.zzbaB != null) {
            zzz += zzsn.zzo(21, this.zzbaB);
        }
        if (this.zzbaC != null) {
            zzz += zzsn.zzd(22, this.zzbaC);
        }
        if (this.zzbaD != null) {
            zzz += zzsn.zzC(23, this.zzbaD);
        }
        if (this.zzaVx != null) {
            zzz += zzsn.zzo(24, this.zzaVx);
        }
        if (this.zzaVt != null) {
            zzz += zzsn.zzo(25, this.zzaVt);
        }
        if (this.zzbar != null) {
            zzz += zzsn.zzd(26, this.zzbar);
        }
        if (this.zzbaE != null) {
            zzz += zzsn.zzf(28, this.zzbaE);
        }
        if (this.zzbaF != null) {
            final int length = this.zzbaF.length;
            int k = 0;
            if (length > 0) {
                while (k < this.zzbaF.length) {
                    final zza zza = this.zzbaF[k];
                    if (zza != null) {
                        zzz += zzsn.zzc(29, zza);
                    }
                    ++k;
                }
            }
        }
        return zzz;
    }
}
