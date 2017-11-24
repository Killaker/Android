package com.google.android.gms.internal;

import java.io.*;

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
