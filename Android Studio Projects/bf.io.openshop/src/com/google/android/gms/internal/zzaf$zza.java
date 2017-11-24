package com.google.android.gms.internal;

import java.io.*;

public static final class zza extends zzso<zza>
{
    public int level;
    public int zziq;
    public int zzir;
    
    public zza() {
        this.zzB();
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zza;
            b = false;
            if (b2) {
                final zza zza = (zza)o;
                final int level = this.level;
                final int level2 = zza.level;
                b = false;
                if (level == level2) {
                    final int zziq = this.zziq;
                    final int zziq2 = zza.zziq;
                    b = false;
                    if (zziq == zziq2) {
                        final int zzir = this.zzir;
                        final int zzir2 = zza.zzir;
                        b = false;
                        if (zzir == zzir2) {
                            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                if (zza.zzbuj != null) {
                                    final boolean empty = zza.zzbuj.isEmpty();
                                    b = false;
                                    if (!empty) {
                                        return b;
                                    }
                                }
                                return true;
                            }
                            return this.zzbuj.equals(zza.zzbuj);
                        }
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + this.level) + this.zziq) + this.zzir);
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
        if (this.level != 1) {
            zzsn.zzA(1, this.level);
        }
        if (this.zziq != 0) {
            zzsn.zzA(2, this.zziq);
        }
        if (this.zzir != 0) {
            zzsn.zzA(3, this.zzir);
        }
        super.writeTo(zzsn);
    }
    
    public zza zzB() {
        this.level = 1;
        this.zziq = 0;
        this.zzir = 0;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zza zza(final zzsm zzsm) throws IOException {
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
                case 8: {
                    final int zzJb = zzsm.zzJb();
                    switch (zzJb) {
                        default: {
                            continue;
                        }
                        case 1:
                        case 2:
                        case 3: {
                            this.level = zzJb;
                            continue;
                        }
                    }
                    break;
                }
                case 16: {
                    this.zziq = zzsm.zzJb();
                    continue;
                }
                case 24: {
                    this.zzir = zzsm.zzJb();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.level != 1) {
            zzz += zzsn.zzC(1, this.level);
        }
        if (this.zziq != 0) {
            zzz += zzsn.zzC(2, this.zziq);
        }
        if (this.zzir != 0) {
            zzz += zzsn.zzC(3, this.zzir);
        }
        return zzz;
    }
}
