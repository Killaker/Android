package com.google.android.gms.internal;

import java.io.*;

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
