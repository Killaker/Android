package com.google.android.gms.internal;

import java.io.*;

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
