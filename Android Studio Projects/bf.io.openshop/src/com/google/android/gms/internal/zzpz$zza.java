package com.google.android.gms.internal;

import java.io.*;

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
