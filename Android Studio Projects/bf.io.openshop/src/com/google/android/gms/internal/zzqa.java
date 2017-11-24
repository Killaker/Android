package com.google.android.gms.internal;

import java.io.*;

public interface zzqa
{
    public static final class zza extends zzsu
    {
        private static volatile zza[] zzaZR;
        public String name;
        public Boolean zzaZS;
        
        public zza() {
            this.zzDL();
        }
        
        public static zza[] zzDK() {
            Label_0027: {
                if (zza.zzaZR != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zza.zzaZR == null) {
                        zza.zzaZR = new zza[0];
                    }
                    return zza.zzaZR;
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
                if (this.name == null) {
                    if (zza.name != null) {
                        return false;
                    }
                }
                else if (!this.name.equals(zza.name)) {
                    return false;
                }
                if (this.zzaZS == null) {
                    if (zza.zzaZS != null) {
                        return false;
                    }
                }
                else if (!this.zzaZS.equals(zza.zzaZS)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.name == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.name.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            final Boolean zzaZS = this.zzaZS;
            int hashCode2 = 0;
            if (zzaZS != null) {
                hashCode2 = this.zzaZS.hashCode();
            }
            return n2 + hashCode2;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.name != null) {
                zzsn.zzn(1, this.name);
            }
            if (this.zzaZS != null) {
                zzsn.zze(2, this.zzaZS);
            }
            super.writeTo(zzsn);
        }
        
        public zza zzDL() {
            this.name = null;
            this.zzaZS = null;
            this.zzbuu = -1;
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.name != null) {
                zzz += zzsn.zzo(1, this.name);
            }
            if (this.zzaZS != null) {
                zzz += zzsn.zzf(2, this.zzaZS);
            }
            return zzz;
        }
        
        public zza zzz(final zzsm zzsm) throws IOException {
        Label_0048:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0048;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0048;
                    }
                    case 10: {
                        this.name = zzsm.readString();
                        continue;
                    }
                    case 16: {
                        this.zzaZS = zzsm.zzJc();
                        continue;
                    }
                }
            }
            return this;
        }
    }
    
    public static final class zzb extends zzsu
    {
        public String zzaVt;
        public Long zzaZT;
        public Integer zzaZU;
        public zzc[] zzaZV;
        public zza[] zzaZW;
        public zzpz.zza[] zzaZX;
        
        public zzb() {
            this.zzDM();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzb)) {
                    return false;
                }
                final zzb zzb = (zzb)o;
                if (this.zzaZT == null) {
                    if (zzb.zzaZT != null) {
                        return false;
                    }
                }
                else if (!this.zzaZT.equals(zzb.zzaZT)) {
                    return false;
                }
                if (this.zzaVt == null) {
                    if (zzb.zzaVt != null) {
                        return false;
                    }
                }
                else if (!this.zzaVt.equals(zzb.zzaVt)) {
                    return false;
                }
                if (this.zzaZU == null) {
                    if (zzb.zzaZU != null) {
                        return false;
                    }
                }
                else if (!this.zzaZU.equals(zzb.zzaZU)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZV, zzb.zzaZV)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZW, zzb.zzaZW)) {
                    return false;
                }
                if (!zzss.equals(this.zzaZX, zzb.zzaZX)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzaZT == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzaZT.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzaVt == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaVt.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            final Integer zzaZU = this.zzaZU;
            int hashCode3 = 0;
            if (zzaZU != null) {
                hashCode3 = this.zzaZU.hashCode();
            }
            return 31 * (31 * (31 * (n3 + hashCode3) + zzss.hashCode(this.zzaZV)) + zzss.hashCode(this.zzaZW)) + zzss.hashCode(this.zzaZX);
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZT != null) {
                zzsn.zzb(1, this.zzaZT);
            }
            if (this.zzaVt != null) {
                zzsn.zzn(2, this.zzaVt);
            }
            if (this.zzaZU != null) {
                zzsn.zzA(3, this.zzaZU);
            }
            if (this.zzaZV != null && this.zzaZV.length > 0) {
                for (int i = 0; i < this.zzaZV.length; ++i) {
                    final zzc zzc = this.zzaZV[i];
                    if (zzc != null) {
                        zzsn.zza(4, zzc);
                    }
                }
            }
            if (this.zzaZW != null && this.zzaZW.length > 0) {
                for (int j = 0; j < this.zzaZW.length; ++j) {
                    final zza zza = this.zzaZW[j];
                    if (zza != null) {
                        zzsn.zza(5, zza);
                    }
                }
            }
            if (this.zzaZX != null) {
                final int length = this.zzaZX.length;
                int k = 0;
                if (length > 0) {
                    while (k < this.zzaZX.length) {
                        final zzpz.zza zza2 = this.zzaZX[k];
                        if (zza2 != null) {
                            zzsn.zza(6, zza2);
                        }
                        ++k;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzb zzA(final zzsm zzsm) throws IOException {
        Label_0080:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0080;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0080;
                    }
                    case 8: {
                        this.zzaZT = zzsm.zzJa();
                        continue;
                    }
                    case 18: {
                        this.zzaVt = zzsm.readString();
                        continue;
                    }
                    case 24: {
                        this.zzaZU = zzsm.zzJb();
                        continue;
                    }
                    case 34: {
                        final int zzc = zzsx.zzc(zzsm, 34);
                        int i;
                        if (this.zzaZV == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzaZV.length;
                        }
                        final zzc[] zzaZV = new zzc[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzaZV, 0, zzaZV, 0, i);
                        }
                        while (i < -1 + zzaZV.length) {
                            zzsm.zza(zzaZV[i] = new zzc());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzaZV[i] = new zzc());
                        this.zzaZV = zzaZV;
                        continue;
                    }
                    case 42: {
                        final int zzc2 = zzsx.zzc(zzsm, 42);
                        int j;
                        if (this.zzaZW == null) {
                            j = 0;
                        }
                        else {
                            j = this.zzaZW.length;
                        }
                        final zza[] zzaZW = new zza[zzc2 + j];
                        if (j != 0) {
                            System.arraycopy(this.zzaZW, 0, zzaZW, 0, j);
                        }
                        while (j < -1 + zzaZW.length) {
                            zzsm.zza(zzaZW[j] = new zza());
                            zzsm.zzIX();
                            ++j;
                        }
                        zzsm.zza(zzaZW[j] = new zza());
                        this.zzaZW = zzaZW;
                        continue;
                    }
                    case 50: {
                        final int zzc3 = zzsx.zzc(zzsm, 50);
                        int k;
                        if (this.zzaZX == null) {
                            k = 0;
                        }
                        else {
                            k = this.zzaZX.length;
                        }
                        final zzpz.zza[] zzaZX = new zzpz.zza[zzc3 + k];
                        if (k != 0) {
                            System.arraycopy(this.zzaZX, 0, zzaZX, 0, k);
                        }
                        while (k < -1 + zzaZX.length) {
                            zzsm.zza(zzaZX[k] = new zzpz.zza());
                            zzsm.zzIX();
                            ++k;
                        }
                        zzsm.zza(zzaZX[k] = new zzpz.zza());
                        this.zzaZX = zzaZX;
                        continue;
                    }
                }
            }
            return this;
        }
        
        public zzb zzDM() {
            this.zzaZT = null;
            this.zzaVt = null;
            this.zzaZU = null;
            this.zzaZV = zzc.zzDN();
            this.zzaZW = zza.zzDK();
            this.zzaZX = zzpz.zza.zzDA();
            this.zzbuu = -1;
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZT != null) {
                zzz += zzsn.zzd(1, this.zzaZT);
            }
            if (this.zzaVt != null) {
                zzz += zzsn.zzo(2, this.zzaVt);
            }
            if (this.zzaZU != null) {
                zzz += zzsn.zzC(3, this.zzaZU);
            }
            if (this.zzaZV != null && this.zzaZV.length > 0) {
                int n = zzz;
                for (int i = 0; i < this.zzaZV.length; ++i) {
                    final zzc zzc = this.zzaZV[i];
                    if (zzc != null) {
                        n += zzsn.zzc(4, zzc);
                    }
                }
                zzz = n;
            }
            if (this.zzaZW != null && this.zzaZW.length > 0) {
                int n2 = zzz;
                for (int j = 0; j < this.zzaZW.length; ++j) {
                    final zza zza = this.zzaZW[j];
                    if (zza != null) {
                        n2 += zzsn.zzc(5, zza);
                    }
                }
                zzz = n2;
            }
            if (this.zzaZX != null) {
                final int length = this.zzaZX.length;
                int k = 0;
                if (length > 0) {
                    while (k < this.zzaZX.length) {
                        final zzpz.zza zza2 = this.zzaZX[k];
                        if (zza2 != null) {
                            zzz += zzsn.zzc(6, zza2);
                        }
                        ++k;
                    }
                }
            }
            return zzz;
        }
    }
    
    public static final class zzc extends zzsu
    {
        private static volatile zzc[] zzaZY;
        public String key;
        public String value;
        
        public zzc() {
            this.zzDO();
        }
        
        public static zzc[] zzDN() {
            Label_0027: {
                if (zzc.zzaZY != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzc.zzaZY == null) {
                        zzc.zzaZY = new zzc[0];
                    }
                    return zzc.zzaZY;
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
                if (this.key == null) {
                    if (zzc.key != null) {
                        return false;
                    }
                }
                else if (!this.key.equals(zzc.key)) {
                    return false;
                }
                if (this.value == null) {
                    if (zzc.value != null) {
                        return false;
                    }
                }
                else if (!this.value.equals(zzc.value)) {
                    return false;
                }
            }
            return true;
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
            final String value = this.value;
            int hashCode2 = 0;
            if (value != null) {
                hashCode2 = this.value.hashCode();
            }
            return n2 + hashCode2;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.key != null) {
                zzsn.zzn(1, this.key);
            }
            if (this.value != null) {
                zzsn.zzn(2, this.value);
            }
            super.writeTo(zzsn);
        }
        
        public zzc zzB(final zzsm zzsm) throws IOException {
        Label_0048:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0048;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0048;
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
        
        public zzc zzDO() {
            this.key = null;
            this.value = null;
            this.zzbuu = -1;
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.key != null) {
                zzz += zzsn.zzo(1, this.key);
            }
            if (this.value != null) {
                zzz += zzsn.zzo(2, this.value);
            }
            return zzz;
        }
    }
}
