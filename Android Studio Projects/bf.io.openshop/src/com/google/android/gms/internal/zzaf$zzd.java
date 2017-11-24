package com.google.android.gms.internal;

import java.io.*;

public static final class zzd extends zzso<zzd>
{
    public zzag.zza[] zziC;
    public zzag.zza[] zziD;
    public zzc[] zziE;
    
    public zzd() {
        this.zzG();
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
                final boolean equals = zzss.equals(this.zziC, zzd.zziC);
                b = false;
                if (equals) {
                    final boolean equals2 = zzss.equals(this.zziD, zzd.zziD);
                    b = false;
                    if (equals2) {
                        final boolean equals3 = zzss.equals(this.zziE, zzd.zziE);
                        b = false;
                        if (equals3) {
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
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zziC)) + zzss.hashCode(this.zziD)) + zzss.hashCode(this.zziE));
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
        if (this.zziC != null && this.zziC.length > 0) {
            for (int i = 0; i < this.zziC.length; ++i) {
                final zzag.zza zza = this.zziC[i];
                if (zza != null) {
                    zzsn.zza(1, zza);
                }
            }
        }
        if (this.zziD != null && this.zziD.length > 0) {
            for (int j = 0; j < this.zziD.length; ++j) {
                final zzag.zza zza2 = this.zziD[j];
                if (zza2 != null) {
                    zzsn.zza(2, zza2);
                }
            }
        }
        if (this.zziE != null) {
            final int length = this.zziE.length;
            int k = 0;
            if (length > 0) {
                while (k < this.zziE.length) {
                    final zzc zzc = this.zziE[k];
                    if (zzc != null) {
                        zzsn.zza(3, zzc);
                    }
                    ++k;
                }
            }
        }
        super.writeTo(zzsn);
    }
    
    public zzd zzG() {
        this.zziC = zzag.zza.zzQ();
        this.zziD = zzag.zza.zzQ();
        this.zziE = zzc.zzE();
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzd zzd(final zzsm zzsm) throws IOException {
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
                    final int zzc = zzsx.zzc(zzsm, 10);
                    int i;
                    if (this.zziC == null) {
                        i = 0;
                    }
                    else {
                        i = this.zziC.length;
                    }
                    final zzag.zza[] zziC = new zzag.zza[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zziC, 0, zziC, 0, i);
                    }
                    while (i < -1 + zziC.length) {
                        zzsm.zza(zziC[i] = new zzag.zza());
                        zzsm.zzIX();
                        ++i;
                    }
                    zzsm.zza(zziC[i] = new zzag.zza());
                    this.zziC = zziC;
                    continue;
                }
                case 18: {
                    final int zzc2 = zzsx.zzc(zzsm, 18);
                    int j;
                    if (this.zziD == null) {
                        j = 0;
                    }
                    else {
                        j = this.zziD.length;
                    }
                    final zzag.zza[] zziD = new zzag.zza[zzc2 + j];
                    if (j != 0) {
                        System.arraycopy(this.zziD, 0, zziD, 0, j);
                    }
                    while (j < -1 + zziD.length) {
                        zzsm.zza(zziD[j] = new zzag.zza());
                        zzsm.zzIX();
                        ++j;
                    }
                    zzsm.zza(zziD[j] = new zzag.zza());
                    this.zziD = zziD;
                    continue;
                }
                case 26: {
                    final int zzc3 = zzsx.zzc(zzsm, 26);
                    int k;
                    if (this.zziE == null) {
                        k = 0;
                    }
                    else {
                        k = this.zziE.length;
                    }
                    final zzc[] zziE = new zzc[zzc3 + k];
                    if (k != 0) {
                        System.arraycopy(this.zziE, 0, zziE, 0, k);
                    }
                    while (k < -1 + zziE.length) {
                        zzsm.zza(zziE[k] = new zzc());
                        zzsm.zzIX();
                        ++k;
                    }
                    zzsm.zza(zziE[k] = new zzc());
                    this.zziE = zziE;
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zziC != null && this.zziC.length > 0) {
            int n = zzz;
            for (int i = 0; i < this.zziC.length; ++i) {
                final zzag.zza zza = this.zziC[i];
                if (zza != null) {
                    n += zzsn.zzc(1, zza);
                }
            }
            zzz = n;
        }
        if (this.zziD != null && this.zziD.length > 0) {
            int n2 = zzz;
            for (int j = 0; j < this.zziD.length; ++j) {
                final zzag.zza zza2 = this.zziD[j];
                if (zza2 != null) {
                    n2 += zzsn.zzc(2, zza2);
                }
            }
            zzz = n2;
        }
        if (this.zziE != null) {
            final int length = this.zziE.length;
            int k = 0;
            if (length > 0) {
                while (k < this.zziE.length) {
                    final zzc zzc = this.zziE[k];
                    if (zzc != null) {
                        zzz += zzsn.zzc(3, zzc);
                    }
                    ++k;
                }
            }
        }
        return zzz;
    }
}
