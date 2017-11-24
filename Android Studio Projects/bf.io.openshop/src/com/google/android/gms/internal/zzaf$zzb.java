package com.google.android.gms.internal;

import java.io.*;

public static final class zzb extends zzso<zzb>
{
    private static volatile zzb[] zzis;
    public int name;
    public int[] zzit;
    public int zziu;
    public boolean zziv;
    public boolean zziw;
    
    public zzb() {
        this.zzD();
    }
    
    public static zzb[] zzC() {
        Label_0027: {
            if (zzb.zzis != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzb.zzis == null) {
                    zzb.zzis = new zzb[0];
                }
                return zzb.zzis;
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
            final boolean b2 = o instanceof zzb;
            b = false;
            if (b2) {
                final zzb zzb = (zzb)o;
                final boolean equals = zzss.equals(this.zzit, zzb.zzit);
                b = false;
                if (equals) {
                    final int zziu = this.zziu;
                    final int zziu2 = zzb.zziu;
                    b = false;
                    if (zziu == zziu2) {
                        final int name = this.name;
                        final int name2 = zzb.name;
                        b = false;
                        if (name == name2) {
                            final boolean zziv = this.zziv;
                            final boolean zziv2 = zzb.zziv;
                            b = false;
                            if (zziv == zziv2) {
                                final boolean zziw = this.zziw;
                                final boolean zziw2 = zzb.zziw;
                                b = false;
                                if (zziw == zziw2) {
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
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        int n = 1231;
        final int n2 = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzit)) + this.zziu) + this.name);
        int n3;
        if (this.zziv) {
            n3 = n;
        }
        else {
            n3 = 1237;
        }
        final int n4 = 31 * (n3 + n2);
        if (!this.zziw) {
            n = 1237;
        }
        final int n5 = 31 * (n4 + n);
        int hashCode;
        if (this.zzbuj == null || this.zzbuj.isEmpty()) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbuj.hashCode();
        }
        return hashCode + n5;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zziw) {
            zzsn.zze(1, this.zziw);
        }
        zzsn.zzA(2, this.zziu);
        if (this.zzit != null && this.zzit.length > 0) {
            for (int i = 0; i < this.zzit.length; ++i) {
                zzsn.zzA(3, this.zzit[i]);
            }
        }
        if (this.name != 0) {
            zzsn.zzA(4, this.name);
        }
        if (this.zziv) {
            zzsn.zze(6, this.zziv);
        }
        super.writeTo(zzsn);
    }
    
    public zzb zzD() {
        this.zzit = zzsx.zzbuw;
        this.zziu = 0;
        this.name = 0;
        this.zziv = false;
        this.zziw = false;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzb zzb(final zzsm zzsm) throws IOException {
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
                case 8: {
                    this.zziw = zzsm.zzJc();
                    continue;
                }
                case 16: {
                    this.zziu = zzsm.zzJb();
                    continue;
                }
                case 24: {
                    final int zzc = zzsx.zzc(zzsm, 24);
                    int i;
                    if (this.zzit == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzit.length;
                    }
                    final int[] zzit = new int[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzit, 0, zzit, 0, i);
                    }
                    while (i < -1 + zzit.length) {
                        zzit[i] = zzsm.zzJb();
                        zzsm.zzIX();
                        ++i;
                    }
                    zzit[i] = zzsm.zzJb();
                    this.zzit = zzit;
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
                    int j;
                    if (this.zzit == null) {
                        j = 0;
                    }
                    else {
                        j = this.zzit.length;
                    }
                    final int[] zzit2 = new int[n + j];
                    if (j != 0) {
                        System.arraycopy(this.zzit, 0, zzit2, 0, j);
                    }
                    while (j < zzit2.length) {
                        zzit2[j] = zzsm.zzJb();
                        ++j;
                    }
                    this.zzit = zzit2;
                    zzsm.zzmr(zzmq);
                    continue;
                }
                case 32: {
                    this.name = zzsm.zzJb();
                    continue;
                }
                case 48: {
                    this.zziv = zzsm.zzJc();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int n = 0;
        int zzz = super.zzz();
        if (this.zziw) {
            zzz += zzsn.zzf(1, this.zziw);
        }
        final int n2 = zzz + zzsn.zzC(2, this.zziu);
        int n3;
        if (this.zzit != null && this.zzit.length > 0) {
            for (int i = 0; i < this.zzit.length; ++i) {
                n += zzsn.zzmx(this.zzit[i]);
            }
            n3 = n2 + n + 1 * this.zzit.length;
        }
        else {
            n3 = n2;
        }
        if (this.name != 0) {
            n3 += zzsn.zzC(4, this.name);
        }
        if (this.zziv) {
            n3 += zzsn.zzf(6, this.zziv);
        }
        return n3;
    }
}
