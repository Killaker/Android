package com.google.android.gms.internal;

import java.io.*;

public interface zzpz
{
    public static final class zza extends zzsu
    {
        private static volatile zza[] zzaZq;
        public Integer zzaZr;
        public zze[] zzaZs;
        public zzb[] zzaZt;
        
        public zza() {
            this.zzDB();
        }
        
        public static zza[] zzDA() {
            Label_0027: {
                if (zza.zzaZq != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zza.zzaZq == null) {
                        zza.zzaZq = new zza[0];
                    }
                    return zza.zzaZq;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zza)) {
                    return false;
                }
                final zza zza = (zza)o;
                if (this.zzaZr == null) {
                    if (zza.zzaZr != null) {
                        return false;
                    }
                }
                else if (!this.zzaZr.equals(zza.zzaZr)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZs, zza.zzaZs)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZt, zza.zzaZt)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzaZr == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZr.hashCode();
            }
            return 31 * (31 * (hashCode + n) + zzss.hashCode(this.zzaZs)) + zzss.hashCode(this.zzaZt);
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZr != null) {
                zzsn.zzA(1, this.zzaZr);
            }
            if (this.zzaZs != null && this.zzaZs.length > 0) {
                for (int i = 0; i < this.zzaZs.length; ++i) {
                    final zze zze = this.zzaZs[i];
                    if (zze != null) {
                        zzsn.zza(2, zze);
                    }
                }
            }
            if (this.zzaZt != null) {
                final int length = this.zzaZt.length;
                int j = 0;
                if (length > 0) {
                    while (j < this.zzaZt.length) {
                        final zzb zzb = this.zzaZt[j];
                        if (zzb != null) {
                            zzsn.zza(3, zzb);
                        }
                        ++j;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zza zzDB() {
            this.zzaZr = null;
            this.zzaZs = zze.zzDH();
            this.zzaZt = zzb.zzDC();
            this.zzbuu = -1;
            return this;
        }
        
        public zza zzt(final zzsm zzsm) throws IOException {
        Label_0056:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0056;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0056;
                    }
                    case 8: {
                        this.zzaZr = zzsm.zzJb();
                        continue;
                    }
                    case 18: {
                        final int zzc = zzsx.zzc(zzsm, 18);
                        int i;
                        if (this.zzaZs == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzaZs.length;
                        }
                        final zze[] zzaZs = new zze[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzaZs, 0, zzaZs, 0, i);
                        }
                        while (i < -1 + zzaZs.length) {
                            zzsm.zza(zzaZs[i] = new zze());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzaZs[i] = new zze());
                        this.zzaZs = zzaZs;
                        continue;
                    }
                    case 26: {
                        final int zzc2 = zzsx.zzc(zzsm, 26);
                        int j;
                        if (this.zzaZt == null) {
                            j = 0;
                        }
                        else {
                            j = this.zzaZt.length;
                        }
                        final zzb[] zzaZt = new zzb[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.zzaZt, 0, zzaZt, 0, j);
                        }
                        while (j < -1 + zzaZt.length) {
                            zzsm.zza(zzaZt[j] = new zzb());
                            zzsm.zzIX();
                            ++j;
                        }
                        zzsm.zza(zzaZt[j] = new zzb());
                        this.zzaZt = zzaZt;
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZr != null) {
                zzz += zzsn.zzC(1, this.zzaZr);
            }
            if (this.zzaZs != null && this.zzaZs.length > 0) {
                int n = zzz;
                for (int i = 0; i < this.zzaZs.length; ++i) {
                    final zze zze = this.zzaZs[i];
                    if (zze != null) {
                        n += zzsn.zzc(2, zze);
                    }
                }
                zzz = n;
            }
            if (this.zzaZt != null) {
                final int length = this.zzaZt.length;
                int j = 0;
                if (length > 0) {
                    while (j < this.zzaZt.length) {
                        final zzb zzb = this.zzaZt[j];
                        if (zzb != null) {
                            zzz += zzsn.zzc(3, zzb);
                        }
                        ++j;
                    }
                }
            }
            return zzz;
        }
    }
    
    public static final class zzb extends zzsu
    {
        private static volatile zzb[] zzaZu;
        public Integer zzaZv;
        public String zzaZw;
        public zzc[] zzaZx;
        public Boolean zzaZy;
        public zzd zzaZz;
        
        public zzb() {
            this.zzDD();
        }
        
        public static zzb[] zzDC() {
            Label_0027: {
                if (zzb.zzaZu != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzb.zzaZu == null) {
                        zzb.zzaZu = new zzb[0];
                    }
                    return zzb.zzaZu;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzb)) {
                    return false;
                }
                final zzb zzb = (zzb)o;
                if (this.zzaZv == null) {
                    if (zzb.zzaZv != null) {
                        return false;
                    }
                }
                else if (!this.zzaZv.equals(zzb.zzaZv)) {
                    return false;
                }
                if (this.zzaZw == null) {
                    if (zzb.zzaZw != null) {
                        return false;
                    }
                }
                else if (!this.zzaZw.equals(zzb.zzaZw)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZx, zzb.zzaZx)) {
                    return false;
                }
                if (this.zzaZy == null) {
                    if (zzb.zzaZy != null) {
                        return false;
                    }
                }
                else if (!this.zzaZy.equals(zzb.zzaZy)) {
                    return false;
                }
                if (this.zzaZz == null) {
                    if (zzb.zzaZz != null) {
                        return false;
                    }
                }
                else if (!this.zzaZz.equals(zzb.zzaZz)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzaZv == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZv.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzaZw == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaZw.hashCode();
            }
            final int n3 = 31 * (31 * (hashCode2 + n2) + zzss.hashCode(this.zzaZx));
            int hashCode3;
            if (this.zzaZy == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzaZy.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final zzd zzaZz = this.zzaZz;
            int hashCode4 = 0;
            if (zzaZz != null) {
                hashCode4 = this.zzaZz.hashCode();
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZv != null) {
                zzsn.zzA(1, this.zzaZv);
            }
            if (this.zzaZw != null) {
                zzsn.zzn(2, this.zzaZw);
            }
            if (this.zzaZx != null && this.zzaZx.length > 0) {
                for (int i = 0; i < this.zzaZx.length; ++i) {
                    final zzc zzc = this.zzaZx[i];
                    if (zzc != null) {
                        zzsn.zza(3, zzc);
                    }
                }
            }
            if (this.zzaZy != null) {
                zzsn.zze(4, this.zzaZy);
            }
            if (this.zzaZz != null) {
                zzsn.zza(5, this.zzaZz);
            }
            super.writeTo(zzsn);
        }
        
        public zzb zzDD() {
            this.zzaZv = null;
            this.zzaZw = null;
            this.zzaZx = zzc.zzDE();
            this.zzaZy = null;
            this.zzaZz = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzb zzu(final zzsm zzsm) throws IOException {
        Label_0072:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0072;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0072;
                    }
                    case 8: {
                        this.zzaZv = zzsm.zzJb();
                        continue;
                    }
                    case 18: {
                        this.zzaZw = zzsm.readString();
                        continue;
                    }
                    case 26: {
                        final int zzc = zzsx.zzc(zzsm, 26);
                        int i;
                        if (this.zzaZx == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzaZx.length;
                        }
                        final zzc[] zzaZx = new zzc[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzaZx, 0, zzaZx, 0, i);
                        }
                        while (i < -1 + zzaZx.length) {
                            zzsm.zza(zzaZx[i] = new zzc());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzaZx[i] = new zzc());
                        this.zzaZx = zzaZx;
                        continue;
                    }
                    case 32: {
                        this.zzaZy = zzsm.zzJc();
                        continue;
                    }
                    case 42: {
                        if (this.zzaZz == null) {
                            this.zzaZz = new zzd();
                        }
                        zzsm.zza(this.zzaZz);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZv != null) {
                zzz += zzsn.zzC(1, this.zzaZv);
            }
            if (this.zzaZw != null) {
                zzz += zzsn.zzo(2, this.zzaZw);
            }
            if (this.zzaZx != null && this.zzaZx.length > 0) {
                int n = zzz;
                for (int i = 0; i < this.zzaZx.length; ++i) {
                    final zzc zzc = this.zzaZx[i];
                    if (zzc != null) {
                        n += zzsn.zzc(3, zzc);
                    }
                }
                zzz = n;
            }
            if (this.zzaZy != null) {
                zzz += zzsn.zzf(4, this.zzaZy);
            }
            if (this.zzaZz != null) {
                zzz += zzsn.zzc(5, this.zzaZz);
            }
            return zzz;
        }
    }
    
    public static final class zzc extends zzsu
    {
        private static volatile zzc[] zzaZA;
        public zzf zzaZB;
        public zzd zzaZC;
        public Boolean zzaZD;
        public String zzaZE;
        
        public zzc() {
            this.zzDF();
        }
        
        public static zzc[] zzDE() {
            Label_0027: {
                if (zzc.zzaZA != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzc.zzaZA == null) {
                        zzc.zzaZA = new zzc[0];
                    }
                    return zzc.zzaZA;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzc)) {
                    return false;
                }
                final zzc zzc = (zzc)o;
                if (this.zzaZB == null) {
                    if (zzc.zzaZB != null) {
                        return false;
                    }
                }
                else if (!this.zzaZB.equals(zzc.zzaZB)) {
                    return false;
                }
                if (this.zzaZC == null) {
                    if (zzc.zzaZC != null) {
                        return false;
                    }
                }
                else if (!this.zzaZC.equals(zzc.zzaZC)) {
                    return false;
                }
                if (this.zzaZD == null) {
                    if (zzc.zzaZD != null) {
                        return false;
                    }
                }
                else if (!this.zzaZD.equals(zzc.zzaZD)) {
                    return false;
                }
                if (this.zzaZE == null) {
                    if (zzc.zzaZE != null) {
                        return false;
                    }
                }
                else if (!this.zzaZE.equals(zzc.zzaZE)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzaZB == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZB.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzaZC == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaZC.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzaZD == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzaZD.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final String zzaZE = this.zzaZE;
            int hashCode4 = 0;
            if (zzaZE != null) {
                hashCode4 = this.zzaZE.hashCode();
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZB != null) {
                zzsn.zza(1, this.zzaZB);
            }
            if (this.zzaZC != null) {
                zzsn.zza(2, this.zzaZC);
            }
            if (this.zzaZD != null) {
                zzsn.zze(3, this.zzaZD);
            }
            if (this.zzaZE != null) {
                zzsn.zzn(4, this.zzaZE);
            }
            super.writeTo(zzsn);
        }
        
        public zzc zzDF() {
            this.zzaZB = null;
            this.zzaZC = null;
            this.zzaZD = null;
            this.zzaZE = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzc zzv(final zzsm zzsm) throws IOException {
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
                    case 10: {
                        if (this.zzaZB == null) {
                            this.zzaZB = new zzf();
                        }
                        zzsm.zza(this.zzaZB);
                        continue;
                    }
                    case 18: {
                        if (this.zzaZC == null) {
                            this.zzaZC = new zzd();
                        }
                        zzsm.zza(this.zzaZC);
                        continue;
                    }
                    case 24: {
                        this.zzaZD = zzsm.zzJc();
                        continue;
                    }
                    case 34: {
                        this.zzaZE = zzsm.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZB != null) {
                zzz += zzsn.zzc(1, this.zzaZB);
            }
            if (this.zzaZC != null) {
                zzz += zzsn.zzc(2, this.zzaZC);
            }
            if (this.zzaZD != null) {
                zzz += zzsn.zzf(3, this.zzaZD);
            }
            if (this.zzaZE != null) {
                zzz += zzsn.zzo(4, this.zzaZE);
            }
            return zzz;
        }
    }
    
    public static final class zzd extends zzsu
    {
        public Integer zzaZF;
        public Boolean zzaZG;
        public String zzaZH;
        public String zzaZI;
        public String zzaZJ;
        
        public zzd() {
            this.zzDG();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzd)) {
                    return false;
                }
                final zzd zzd = (zzd)o;
                if (this.zzaZF == null) {
                    if (zzd.zzaZF != null) {
                        return false;
                    }
                }
                else if (!this.zzaZF.equals(zzd.zzaZF)) {
                    return false;
                }
                if (this.zzaZG == null) {
                    if (zzd.zzaZG != null) {
                        return false;
                    }
                }
                else if (!this.zzaZG.equals(zzd.zzaZG)) {
                    return false;
                }
                if (this.zzaZH == null) {
                    if (zzd.zzaZH != null) {
                        return false;
                    }
                }
                else if (!this.zzaZH.equals(zzd.zzaZH)) {
                    return false;
                }
                if (this.zzaZI == null) {
                    if (zzd.zzaZI != null) {
                        return false;
                    }
                }
                else if (!this.zzaZI.equals(zzd.zzaZI)) {
                    return false;
                }
                if (this.zzaZJ == null) {
                    if (zzd.zzaZJ != null) {
                        return false;
                    }
                }
                else if (!this.zzaZJ.equals(zzd.zzaZJ)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int intValue;
            if (this.zzaZF == null) {
                intValue = 0;
            }
            else {
                intValue = this.zzaZF;
            }
            final int n2 = 31 * (intValue + n);
            int hashCode;
            if (this.zzaZG == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZG.hashCode();
            }
            final int n3 = 31 * (hashCode + n2);
            int hashCode2;
            if (this.zzaZH == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaZH.hashCode();
            }
            final int n4 = 31 * (hashCode2 + n3);
            int hashCode3;
            if (this.zzaZI == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzaZI.hashCode();
            }
            final int n5 = 31 * (hashCode3 + n4);
            final String zzaZJ = this.zzaZJ;
            int hashCode4 = 0;
            if (zzaZJ != null) {
                hashCode4 = this.zzaZJ.hashCode();
            }
            return n5 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZF != null) {
                zzsn.zzA(1, this.zzaZF);
            }
            if (this.zzaZG != null) {
                zzsn.zze(2, this.zzaZG);
            }
            if (this.zzaZH != null) {
                zzsn.zzn(3, this.zzaZH);
            }
            if (this.zzaZI != null) {
                zzsn.zzn(4, this.zzaZI);
            }
            if (this.zzaZJ != null) {
                zzsn.zzn(5, this.zzaZJ);
            }
            super.writeTo(zzsn);
        }
        
        public zzd zzDG() {
            this.zzaZF = null;
            this.zzaZG = null;
            this.zzaZH = null;
            this.zzaZI = null;
            this.zzaZJ = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzd zzw(final zzsm zzsm) throws IOException {
        Label_0072:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0072;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0072;
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
                            case 4: {
                                this.zzaZF = zzJb;
                                continue;
                            }
                        }
                        break;
                    }
                    case 16: {
                        this.zzaZG = zzsm.zzJc();
                        continue;
                    }
                    case 26: {
                        this.zzaZH = zzsm.readString();
                        continue;
                    }
                    case 34: {
                        this.zzaZI = zzsm.readString();
                        continue;
                    }
                    case 42: {
                        this.zzaZJ = zzsm.readString();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZF != null) {
                zzz += zzsn.zzC(1, this.zzaZF);
            }
            if (this.zzaZG != null) {
                zzz += zzsn.zzf(2, this.zzaZG);
            }
            if (this.zzaZH != null) {
                zzz += zzsn.zzo(3, this.zzaZH);
            }
            if (this.zzaZI != null) {
                zzz += zzsn.zzo(4, this.zzaZI);
            }
            if (this.zzaZJ != null) {
                zzz += zzsn.zzo(5, this.zzaZJ);
            }
            return zzz;
        }
    }
    
    public static final class zze extends zzsu
    {
        private static volatile zze[] zzaZK;
        public String zzaZL;
        public zzc zzaZM;
        public Integer zzaZv;
        
        public zze() {
            this.zzDI();
        }
        
        public static zze[] zzDH() {
            Label_0027: {
                if (zze.zzaZK != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zze.zzaZK == null) {
                        zze.zzaZK = new zze[0];
                    }
                    return zze.zzaZK;
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
                if (this.zzaZv == null) {
                    if (zze.zzaZv != null) {
                        return false;
                    }
                }
                else if (!this.zzaZv.equals(zze.zzaZv)) {
                    return false;
                }
                if (this.zzaZL == null) {
                    if (zze.zzaZL != null) {
                        return false;
                    }
                }
                else if (!this.zzaZL.equals(zze.zzaZL)) {
                    return false;
                }
                if (this.zzaZM == null) {
                    if (zze.zzaZM != null) {
                        return false;
                    }
                }
                else if (!this.zzaZM.equals(zze.zzaZM)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzaZv == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZv.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzaZL == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaZL.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            final zzc zzaZM = this.zzaZM;
            int hashCode3 = 0;
            if (zzaZM != null) {
                hashCode3 = this.zzaZM.hashCode();
            }
            return n3 + hashCode3;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZv != null) {
                zzsn.zzA(1, this.zzaZv);
            }
            if (this.zzaZL != null) {
                zzsn.zzn(2, this.zzaZL);
            }
            if (this.zzaZM != null) {
                zzsn.zza(3, this.zzaZM);
            }
            super.writeTo(zzsn);
        }
        
        public zze zzDI() {
            this.zzaZv = null;
            this.zzaZL = null;
            this.zzaZM = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zze zzx(final zzsm zzsm) throws IOException {
        Label_0056:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0056;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0056;
                    }
                    case 8: {
                        this.zzaZv = zzsm.zzJb();
                        continue;
                    }
                    case 18: {
                        this.zzaZL = zzsm.readString();
                        continue;
                    }
                    case 26: {
                        if (this.zzaZM == null) {
                            this.zzaZM = new zzc();
                        }
                        zzsm.zza(this.zzaZM);
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZv != null) {
                zzz += zzsn.zzC(1, this.zzaZv);
            }
            if (this.zzaZL != null) {
                zzz += zzsn.zzo(2, this.zzaZL);
            }
            if (this.zzaZM != null) {
                zzz += zzsn.zzc(3, this.zzaZM);
            }
            return zzz;
        }
    }
    
    public static final class zzf extends zzsu
    {
        public Integer zzaZN;
        public String zzaZO;
        public Boolean zzaZP;
        public String[] zzaZQ;
        
        public zzf() {
            this.zzDJ();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzf)) {
                    return false;
                }
                final zzf zzf = (zzf)o;
                if (this.zzaZN == null) {
                    if (zzf.zzaZN != null) {
                        return false;
                    }
                }
                else if (!this.zzaZN.equals(zzf.zzaZN)) {
                    return false;
                }
                if (this.zzaZO == null) {
                    if (zzf.zzaZO != null) {
                        return false;
                    }
                }
                else if (!this.zzaZO.equals(zzf.zzaZO)) {
                    return false;
                }
                if (this.zzaZP == null) {
                    if (zzf.zzaZP != null) {
                        return false;
                    }
                }
                else if (!this.zzaZP.equals(zzf.zzaZP)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZQ, zzf.zzaZQ)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int intValue;
            if (this.zzaZN == null) {
                intValue = 0;
            }
            else {
                intValue = this.zzaZN;
            }
            final int n2 = 31 * (intValue + n);
            int hashCode;
            if (this.zzaZO == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZO.hashCode();
            }
            final int n3 = 31 * (hashCode + n2);
            final Boolean zzaZP = this.zzaZP;
            int hashCode2 = 0;
            if (zzaZP != null) {
                hashCode2 = this.zzaZP.hashCode();
            }
            return 31 * (n3 + hashCode2) + zzss.hashCode(this.zzaZQ);
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZN != null) {
                zzsn.zzA(1, this.zzaZN);
            }
            if (this.zzaZO != null) {
                zzsn.zzn(2, this.zzaZO);
            }
            if (this.zzaZP != null) {
                zzsn.zze(3, this.zzaZP);
            }
            if (this.zzaZQ != null && this.zzaZQ.length > 0) {
                for (int i = 0; i < this.zzaZQ.length; ++i) {
                    final String s = this.zzaZQ[i];
                    if (s != null) {
                        zzsn.zzn(4, s);
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzf zzDJ() {
            this.zzaZN = null;
            this.zzaZO = null;
            this.zzaZP = null;
            this.zzaZQ = zzsx.zzbuB;
            this.zzbuu = -1;
            return this;
        }
        
        public zzf zzy(final zzsm zzsm) throws IOException {
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
                            case 6: {
                                this.zzaZN = zzJb;
                                continue;
                            }
                        }
                        break;
                    }
                    case 18: {
                        this.zzaZO = zzsm.readString();
                        continue;
                    }
                    case 24: {
                        this.zzaZP = zzsm.zzJc();
                        continue;
                    }
                    case 34: {
                        final int zzc = zzsx.zzc(zzsm, 34);
                        int i;
                        if (this.zzaZQ == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzaZQ.length;
                        }
                        final String[] zzaZQ = new String[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzaZQ, 0, zzaZQ, 0, i);
                        }
                        while (i < -1 + zzaZQ.length) {
                            zzaZQ[i] = zzsm.readString();
                            zzsm.zzIX();
                            ++i;
                        }
                        zzaZQ[i] = zzsm.readString();
                        this.zzaZQ = zzaZQ;
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
            if (this.zzaZN != null) {
                zzz += zzsn.zzC(1, this.zzaZN);
            }
            if (this.zzaZO != null) {
                zzz += zzsn.zzo(2, this.zzaZO);
            }
            if (this.zzaZP != null) {
                zzz += zzsn.zzf(3, this.zzaZP);
            }
            if (this.zzaZQ != null && this.zzaZQ.length > 0) {
                int n = 0;
                int n2 = 0;
                while (i < this.zzaZQ.length) {
                    final String s = this.zzaZQ[i];
                    if (s != null) {
                        ++n2;
                        n += zzsn.zzgO(s);
                    }
                    ++i;
                }
                zzz = zzz + n + n2 * 1;
            }
            return zzz;
        }
    }
}
