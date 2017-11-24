package com.google.android.gms.internal;

import java.io.*;

public interface zzqb
{
    public static final class zza extends zzsu
    {
        private static volatile zza[] zzaZZ;
        public Integer zzaZr;
        public zzf zzbaa;
        public zzf zzbab;
        public Boolean zzbac;
        
        public zza() {
            this.zzDQ();
        }
        
        public static zza[] zzDP() {
            Label_0027: {
                if (zza.zzaZZ != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zza.zzaZZ == null) {
                        zza.zzaZZ = new zza[0];
                    }
                    return zza.zzaZZ;
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
                if (this.zzbaa == null) {
                    if (zza.zzbaa != null) {
                        return false;
                    }
                }
                else if (!this.zzbaa.equals(zza.zzbaa)) {
                    return false;
                }
                if (this.zzbab == null) {
                    if (zza.zzbab != null) {
                        return false;
                    }
                }
                else if (!this.zzbab.equals(zza.zzbab)) {
                    return false;
                }
                if (this.zzbac == null) {
                    if (zza.zzbac != null) {
                        return false;
                    }
                }
                else if (!this.zzbac.equals(zza.zzbac)) {
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
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzbaa == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzbaa.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzbab == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzbab.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final Boolean zzbac = this.zzbac;
            int hashCode4 = 0;
            if (zzbac != null) {
                hashCode4 = this.zzbac.hashCode();
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzaZr != null) {
                zzsn.zzA(1, this.zzaZr);
            }
            if (this.zzbaa != null) {
                zzsn.zza(2, this.zzbaa);
            }
            if (this.zzbab != null) {
                zzsn.zza(3, this.zzbab);
            }
            if (this.zzbac != null) {
                zzsn.zze(4, this.zzbac);
            }
            super.writeTo(zzsn);
        }
        
        public zza zzC(final zzsm zzsm) throws IOException {
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
                        this.zzaZr = zzsm.zzJb();
                        continue;
                    }
                    case 18: {
                        if (this.zzbaa == null) {
                            this.zzbaa = new zzf();
                        }
                        zzsm.zza(this.zzbaa);
                        continue;
                    }
                    case 26: {
                        if (this.zzbab == null) {
                            this.zzbab = new zzf();
                        }
                        zzsm.zza(this.zzbab);
                        continue;
                    }
                    case 32: {
                        this.zzbac = zzsm.zzJc();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public zza zzDQ() {
            this.zzaZr = null;
            this.zzbaa = null;
            this.zzbab = null;
            this.zzbac = null;
            this.zzbuu = -1;
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzaZr != null) {
                zzz += zzsn.zzC(1, this.zzaZr);
            }
            if (this.zzbaa != null) {
                zzz += zzsn.zzc(2, this.zzbaa);
            }
            if (this.zzbab != null) {
                zzz += zzsn.zzc(3, this.zzbab);
            }
            if (this.zzbac != null) {
                zzz += zzsn.zzf(4, this.zzbac);
            }
            return zzz;
        }
    }
    
    public static final class zzb extends zzsu
    {
        private static volatile zzb[] zzbad;
        public Integer count;
        public String name;
        public zzc[] zzbae;
        public Long zzbaf;
        public Long zzbag;
        
        public zzb() {
            this.zzDS();
        }
        
        public static zzb[] zzDR() {
            Label_0027: {
                if (zzb.zzbad != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzb.zzbad == null) {
                        zzb.zzbad = new zzb[0];
                    }
                    return zzb.zzbad;
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
                if (!zzss.equals(this.zzbae, zzb.zzbae)) {
                    return false;
                }
                if (this.name == null) {
                    if (zzb.name != null) {
                        return false;
                    }
                }
                else if (!this.name.equals(zzb.name)) {
                    return false;
                }
                if (this.zzbaf == null) {
                    if (zzb.zzbaf != null) {
                        return false;
                    }
                }
                else if (!this.zzbaf.equals(zzb.zzbaf)) {
                    return false;
                }
                if (this.zzbag == null) {
                    if (zzb.zzbag != null) {
                        return false;
                    }
                }
                else if (!this.zzbag.equals(zzb.zzbag)) {
                    return false;
                }
                if (this.count == null) {
                    if (zzb.count != null) {
                        return false;
                    }
                }
                else if (!this.count.equals(zzb.count)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzbae));
            int hashCode;
            if (this.name == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.name.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.zzbaf == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzbaf.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzbag == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzbag.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final Integer count = this.count;
            int hashCode4 = 0;
            if (count != null) {
                hashCode4 = this.count.hashCode();
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzbae != null && this.zzbae.length > 0) {
                for (int i = 0; i < this.zzbae.length; ++i) {
                    final zzc zzc = this.zzbae[i];
                    if (zzc != null) {
                        zzsn.zza(1, zzc);
                    }
                }
            }
            if (this.name != null) {
                zzsn.zzn(2, this.name);
            }
            if (this.zzbaf != null) {
                zzsn.zzb(3, this.zzbaf);
            }
            if (this.zzbag != null) {
                zzsn.zzb(4, this.zzbag);
            }
            if (this.count != null) {
                zzsn.zzA(5, this.count);
            }
            super.writeTo(zzsn);
        }
        
        public zzb zzD(final zzsm zzsm) throws IOException {
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
                    case 10: {
                        final int zzc = zzsx.zzc(zzsm, 10);
                        int i;
                        if (this.zzbae == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzbae.length;
                        }
                        final zzc[] zzbae = new zzc[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzbae, 0, zzbae, 0, i);
                        }
                        while (i < -1 + zzbae.length) {
                            zzsm.zza(zzbae[i] = new zzc());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzbae[i] = new zzc());
                        this.zzbae = zzbae;
                        continue;
                    }
                    case 18: {
                        this.name = zzsm.readString();
                        continue;
                    }
                    case 24: {
                        this.zzbaf = zzsm.zzJa();
                        continue;
                    }
                    case 32: {
                        this.zzbag = zzsm.zzJa();
                        continue;
                    }
                    case 40: {
                        this.count = zzsm.zzJb();
                        continue;
                    }
                }
            }
            return this;
        }
        
        public zzb zzDS() {
            this.zzbae = zzc.zzDT();
            this.name = null;
            this.zzbaf = null;
            this.zzbag = null;
            this.count = null;
            this.zzbuu = -1;
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzbae != null && this.zzbae.length > 0) {
                for (int i = 0; i < this.zzbae.length; ++i) {
                    final zzc zzc = this.zzbae[i];
                    if (zzc != null) {
                        zzz += zzsn.zzc(1, zzc);
                    }
                }
            }
            if (this.name != null) {
                zzz += zzsn.zzo(2, this.name);
            }
            if (this.zzbaf != null) {
                zzz += zzsn.zzd(3, this.zzbaf);
            }
            if (this.zzbag != null) {
                zzz += zzsn.zzd(4, this.zzbag);
            }
            if (this.count != null) {
                zzz += zzsn.zzC(5, this.count);
            }
            return zzz;
        }
    }
    
    public static final class zzc extends zzsu
    {
        private static volatile zzc[] zzbah;
        public String name;
        public Float zzaZo;
        public String zzamJ;
        public Long zzbai;
        
        public zzc() {
            this.zzDU();
        }
        
        public static zzc[] zzDT() {
            Label_0027: {
                if (zzc.zzbah != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzc.zzbah == null) {
                        zzc.zzbah = new zzc[0];
                    }
                    return zzc.zzbah;
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
                if (this.name == null) {
                    if (zzc.name != null) {
                        return false;
                    }
                }
                else if (!this.name.equals(zzc.name)) {
                    return false;
                }
                if (this.zzamJ == null) {
                    if (zzc.zzamJ != null) {
                        return false;
                    }
                }
                else if (!this.zzamJ.equals(zzc.zzamJ)) {
                    return false;
                }
                if (this.zzbai == null) {
                    if (zzc.zzbai != null) {
                        return false;
                    }
                }
                else if (!this.zzbai.equals(zzc.zzbai)) {
                    return false;
                }
                if (this.zzaZo == null) {
                    if (zzc.zzaZo != null) {
                        return false;
                    }
                }
                else if (!this.zzaZo.equals(zzc.zzaZo)) {
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
            int hashCode2;
            if (this.zzamJ == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzamJ.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzbai == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzbai.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            final Float zzaZo = this.zzaZo;
            int hashCode4 = 0;
            if (zzaZo != null) {
                hashCode4 = this.zzaZo.hashCode();
            }
            return n4 + hashCode4;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.name != null) {
                zzsn.zzn(1, this.name);
            }
            if (this.zzamJ != null) {
                zzsn.zzn(2, this.zzamJ);
            }
            if (this.zzbai != null) {
                zzsn.zzb(3, this.zzbai);
            }
            if (this.zzaZo != null) {
                zzsn.zzb(4, this.zzaZo);
            }
            super.writeTo(zzsn);
        }
        
        public zzc zzDU() {
            this.name = null;
            this.zzamJ = null;
            this.zzbai = null;
            this.zzaZo = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzc zzE(final zzsm zzsm) throws IOException {
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
                        this.name = zzsm.readString();
                        continue;
                    }
                    case 18: {
                        this.zzamJ = zzsm.readString();
                        continue;
                    }
                    case 24: {
                        this.zzbai = zzsm.zzJa();
                        continue;
                    }
                    case 37: {
                        this.zzaZo = zzsm.readFloat();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.name != null) {
                zzz += zzsn.zzo(1, this.name);
            }
            if (this.zzamJ != null) {
                zzz += zzsn.zzo(2, this.zzamJ);
            }
            if (this.zzbai != null) {
                zzz += zzsn.zzd(3, this.zzbai);
            }
            if (this.zzaZo != null) {
                zzz += zzsn.zzc(4, this.zzaZo);
            }
            return zzz;
        }
    }
    
    public static final class zzd extends zzsu
    {
        public zze[] zzbaj;
        
        public zzd() {
            this.zzDV();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzd)) {
                    return false;
                }
                if (!zzss.equals(this.zzbaj, ((zzd)o).zzbaj)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return 31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzbaj);
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzbaj != null && this.zzbaj.length > 0) {
                for (int i = 0; i < this.zzbaj.length; ++i) {
                    final zze zze = this.zzbaj[i];
                    if (zze != null) {
                        zzsn.zza(1, zze);
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzd zzDV() {
            this.zzbaj = zze.zzDW();
            this.zzbuu = -1;
            return this;
        }
        
        public zzd zzF(final zzsm zzsm) throws IOException {
        Label_0040:
            while (true) {
                final int zzIX = zzsm.zzIX();
                switch (zzIX) {
                    default: {
                        if (!zzsx.zzb(zzsm, zzIX)) {
                            break Label_0040;
                        }
                        continue;
                    }
                    case 0: {
                        break Label_0040;
                    }
                    case 10: {
                        final int zzc = zzsx.zzc(zzsm, 10);
                        int i;
                        if (this.zzbaj == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzbaj.length;
                        }
                        final zze[] zzbaj = new zze[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzbaj, 0, zzbaj, 0, i);
                        }
                        while (i < -1 + zzbaj.length) {
                            zzsm.zza(zzbaj[i] = new zze());
                            zzsm.zzIX();
                            ++i;
                        }
                        zzsm.zza(zzbaj[i] = new zze());
                        this.zzbaj = zzbaj;
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzbaj != null && this.zzbaj.length > 0) {
                for (int i = 0; i < this.zzbaj.length; ++i) {
                    final zze zze = this.zzbaj[i];
                    if (zze != null) {
                        zzz += zzsn.zzc(1, zze);
                    }
                }
            }
            return zzz;
        }
    }
    
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
    
    public static final class zzf extends zzsu
    {
        public long[] zzbaG;
        public long[] zzbaH;
        
        public zzf() {
            this.zzDY();
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzf)) {
                    return false;
                }
                final zzf zzf = (zzf)o;
                if (!zzss.equals(this.zzbaG, zzf.zzbaG)) {
                    return false;
                }
                if (!zzss.equals(this.zzbaH, zzf.zzbaH)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return 31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzbaG)) + zzss.hashCode(this.zzbaH);
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzbaG != null && this.zzbaG.length > 0) {
                for (int i = 0; i < this.zzbaG.length; ++i) {
                    zzsn.zza(1, this.zzbaG[i]);
                }
            }
            if (this.zzbaH != null) {
                final int length = this.zzbaH.length;
                int j = 0;
                if (length > 0) {
                    while (j < this.zzbaH.length) {
                        zzsn.zza(2, this.zzbaH[j]);
                        ++j;
                    }
                }
            }
            super.writeTo(zzsn);
        }
        
        public zzf zzDY() {
            this.zzbaG = zzsx.zzbux;
            this.zzbaH = zzsx.zzbux;
            this.zzbuu = -1;
            return this;
        }
        
        public zzf zzH(final zzsm zzsm) throws IOException {
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
                        final int zzc = zzsx.zzc(zzsm, 8);
                        int i;
                        if (this.zzbaG == null) {
                            i = 0;
                        }
                        else {
                            i = this.zzbaG.length;
                        }
                        final long[] zzbaG = new long[zzc + i];
                        if (i != 0) {
                            System.arraycopy(this.zzbaG, 0, zzbaG, 0, i);
                        }
                        while (i < -1 + zzbaG.length) {
                            zzbaG[i] = zzsm.zzIZ();
                            zzsm.zzIX();
                            ++i;
                        }
                        zzbaG[i] = zzsm.zzIZ();
                        this.zzbaG = zzbaG;
                        continue;
                    }
                    case 10: {
                        final int zzmq = zzsm.zzmq(zzsm.zzJf());
                        final int position = zzsm.getPosition();
                        int n = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzIZ();
                            ++n;
                        }
                        zzsm.zzms(position);
                        int j;
                        if (this.zzbaG == null) {
                            j = 0;
                        }
                        else {
                            j = this.zzbaG.length;
                        }
                        final long[] zzbaG2 = new long[n + j];
                        if (j != 0) {
                            System.arraycopy(this.zzbaG, 0, zzbaG2, 0, j);
                        }
                        while (j < zzbaG2.length) {
                            zzbaG2[j] = zzsm.zzIZ();
                            ++j;
                        }
                        this.zzbaG = zzbaG2;
                        zzsm.zzmr(zzmq);
                        continue;
                    }
                    case 16: {
                        final int zzc2 = zzsx.zzc(zzsm, 16);
                        int k;
                        if (this.zzbaH == null) {
                            k = 0;
                        }
                        else {
                            k = this.zzbaH.length;
                        }
                        final long[] zzbaH = new long[zzc2 + k];
                        if (k != 0) {
                            System.arraycopy(this.zzbaH, 0, zzbaH, 0, k);
                        }
                        while (k < -1 + zzbaH.length) {
                            zzbaH[k] = zzsm.zzIZ();
                            zzsm.zzIX();
                            ++k;
                        }
                        zzbaH[k] = zzsm.zzIZ();
                        this.zzbaH = zzbaH;
                        continue;
                    }
                    case 18: {
                        final int zzmq2 = zzsm.zzmq(zzsm.zzJf());
                        final int position2 = zzsm.getPosition();
                        int n2 = 0;
                        while (zzsm.zzJk() > 0) {
                            zzsm.zzIZ();
                            ++n2;
                        }
                        zzsm.zzms(position2);
                        int l;
                        if (this.zzbaH == null) {
                            l = 0;
                        }
                        else {
                            l = this.zzbaH.length;
                        }
                        final long[] zzbaH2 = new long[n2 + l];
                        if (l != 0) {
                            System.arraycopy(this.zzbaH, 0, zzbaH2, 0, l);
                        }
                        while (l < zzbaH2.length) {
                            zzbaH2[l] = zzsm.zzIZ();
                            ++l;
                        }
                        this.zzbaH = zzbaH2;
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
            int n2;
            if (this.zzbaG != null && this.zzbaG.length > 0) {
                int j = 0;
                int n = 0;
                while (j < this.zzbaG.length) {
                    n += zzsn.zzar(this.zzbaG[j]);
                    ++j;
                }
                n2 = zzz + n + 1 * this.zzbaG.length;
            }
            else {
                n2 = zzz;
            }
            if (this.zzbaH != null && this.zzbaH.length > 0) {
                int n3 = 0;
                while (i < this.zzbaH.length) {
                    n3 += zzsn.zzar(this.zzbaH[i]);
                    ++i;
                }
                n2 = n2 + n3 + 1 * this.zzbaH.length;
            }
            return n2;
        }
    }
    
    public static final class zzg extends zzsu
    {
        private static volatile zzg[] zzbaI;
        public String name;
        public Float zzaZo;
        public String zzamJ;
        public Long zzbaJ;
        public Long zzbai;
        
        public zzg() {
            this.zzEa();
        }
        
        public static zzg[] zzDZ() {
            Label_0027: {
                if (zzg.zzbaI != null) {
                    break Label_0027;
                }
                synchronized (zzss.zzbut) {
                    if (zzg.zzbaI == null) {
                        zzg.zzbaI = new zzg[0];
                    }
                    return zzg.zzbaI;
                }
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof zzg)) {
                    return false;
                }
                final zzg zzg = (zzg)o;
                if (this.zzbaJ == null) {
                    if (zzg.zzbaJ != null) {
                        return false;
                    }
                }
                else if (!this.zzbaJ.equals(zzg.zzbaJ)) {
                    return false;
                }
                if (this.name == null) {
                    if (zzg.name != null) {
                        return false;
                    }
                }
                else if (!this.name.equals(zzg.name)) {
                    return false;
                }
                if (this.zzamJ == null) {
                    if (zzg.zzamJ != null) {
                        return false;
                    }
                }
                else if (!this.zzamJ.equals(zzg.zzamJ)) {
                    return false;
                }
                if (this.zzbai == null) {
                    if (zzg.zzbai != null) {
                        return false;
                    }
                }
                else if (!this.zzbai.equals(zzg.zzbai)) {
                    return false;
                }
                if (this.zzaZo == null) {
                    if (zzg.zzaZo != null) {
                        return false;
                    }
                }
                else if (!this.zzaZo.equals(zzg.zzaZo)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            final int n = 31 * (527 + this.getClass().getName().hashCode());
            int hashCode;
            if (this.zzbaJ == null) {
                hashCode = 0;
            }
            else {
                hashCode = this.zzbaJ.hashCode();
            }
            final int n2 = 31 * (hashCode + n);
            int hashCode2;
            if (this.name == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.name.hashCode();
            }
            final int n3 = 31 * (hashCode2 + n2);
            int hashCode3;
            if (this.zzamJ == null) {
                hashCode3 = 0;
            }
            else {
                hashCode3 = this.zzamJ.hashCode();
            }
            final int n4 = 31 * (hashCode3 + n3);
            int hashCode4;
            if (this.zzbai == null) {
                hashCode4 = 0;
            }
            else {
                hashCode4 = this.zzbai.hashCode();
            }
            final int n5 = 31 * (hashCode4 + n4);
            final Float zzaZo = this.zzaZo;
            int hashCode5 = 0;
            if (zzaZo != null) {
                hashCode5 = this.zzaZo.hashCode();
            }
            return n5 + hashCode5;
        }
        
        @Override
        public void writeTo(final zzsn zzsn) throws IOException {
            if (this.zzbaJ != null) {
                zzsn.zzb(1, this.zzbaJ);
            }
            if (this.name != null) {
                zzsn.zzn(2, this.name);
            }
            if (this.zzamJ != null) {
                zzsn.zzn(3, this.zzamJ);
            }
            if (this.zzbai != null) {
                zzsn.zzb(4, this.zzbai);
            }
            if (this.zzaZo != null) {
                zzsn.zzb(5, this.zzaZo);
            }
            super.writeTo(zzsn);
        }
        
        public zzg zzEa() {
            this.zzbaJ = null;
            this.name = null;
            this.zzamJ = null;
            this.zzbai = null;
            this.zzaZo = null;
            this.zzbuu = -1;
            return this;
        }
        
        public zzg zzI(final zzsm zzsm) throws IOException {
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
                        this.zzbaJ = zzsm.zzJa();
                        continue;
                    }
                    case 18: {
                        this.name = zzsm.readString();
                        continue;
                    }
                    case 26: {
                        this.zzamJ = zzsm.readString();
                        continue;
                    }
                    case 32: {
                        this.zzbai = zzsm.zzJa();
                        continue;
                    }
                    case 45: {
                        this.zzaZo = zzsm.readFloat();
                        continue;
                    }
                }
            }
            return this;
        }
        
        @Override
        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzbaJ != null) {
                zzz += zzsn.zzd(1, this.zzbaJ);
            }
            if (this.name != null) {
                zzz += zzsn.zzo(2, this.name);
            }
            if (this.zzamJ != null) {
                zzz += zzsn.zzo(3, this.zzamJ);
            }
            if (this.zzbai != null) {
                zzz += zzsn.zzd(4, this.zzbai);
            }
            if (this.zzaZo != null) {
                zzz += zzsn.zzc(5, this.zzaZo);
            }
            return zzz;
        }
    }
}
