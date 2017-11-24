package com.google.android.gms.internal;

import java.io.*;

public static final class zzj extends zzso<zzj>
{
    public zzi[] zzjt;
    public zzf zzju;
    public String zzjv;
    
    public zzj() {
        this.zzP();
    }
    
    public static zzj zzd(final byte[] array) throws zzst {
        return zzsu.mergeFrom(new zzj(), array);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzj;
            b = false;
            if (b2) {
                final zzj zzj = (zzj)o;
                final boolean equals = zzss.equals(this.zzjt, zzj.zzjt);
                b = false;
                if (equals) {
                    if (this.zzju == null) {
                        final zzf zzju = zzj.zzju;
                        b = false;
                        if (zzju != null) {
                            return b;
                        }
                    }
                    else if (!this.zzju.equals(zzj.zzju)) {
                        return false;
                    }
                    if (this.zzjv == null) {
                        final String zzjv = zzj.zzjv;
                        b = false;
                        if (zzjv != null) {
                            return b;
                        }
                    }
                    else if (!this.zzjv.equals(zzj.zzjv)) {
                        return false;
                    }
                    if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                        if (zzj.zzbuj != null) {
                            final boolean empty = zzj.zzbuj.isEmpty();
                            b = false;
                            if (!empty) {
                                return b;
                            }
                        }
                        return true;
                    }
                    return this.zzbuj.equals(zzj.zzbuj);
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (527 + this.getClass().getName().hashCode()) + zzss.hashCode(this.zzjt));
        int hashCode;
        if (this.zzju == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzju.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.zzjv == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzjv.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        final zzsq zzbuj = this.zzbuj;
        int hashCode3 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode3 = 0;
            if (!empty) {
                hashCode3 = this.zzbuj.hashCode();
            }
        }
        return n3 + hashCode3;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzjt != null && this.zzjt.length > 0) {
            for (int i = 0; i < this.zzjt.length; ++i) {
                final zzi zzi = this.zzjt[i];
                if (zzi != null) {
                    zzsn.zza(1, zzi);
                }
            }
        }
        if (this.zzju != null) {
            zzsn.zza(2, this.zzju);
        }
        if (!this.zzjv.equals("")) {
            zzsn.zzn(3, this.zzjv);
        }
        super.writeTo(zzsn);
    }
    
    public zzj zzP() {
        this.zzjt = zzi.zzN();
        this.zzju = null;
        this.zzjv = "";
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzj zzj(final zzsm zzsm) throws IOException {
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
                    if (this.zzjt == null) {
                        i = 0;
                    }
                    else {
                        i = this.zzjt.length;
                    }
                    final zzi[] zzjt = new zzi[zzc + i];
                    if (i != 0) {
                        System.arraycopy(this.zzjt, 0, zzjt, 0, i);
                    }
                    while (i < -1 + zzjt.length) {
                        zzsm.zza(zzjt[i] = new zzi());
                        zzsm.zzIX();
                        ++i;
                    }
                    zzsm.zza(zzjt[i] = new zzi());
                    this.zzjt = zzjt;
                    continue;
                }
                case 18: {
                    if (this.zzju == null) {
                        this.zzju = new zzf();
                    }
                    zzsm.zza(this.zzju);
                    continue;
                }
                case 26: {
                    this.zzjv = zzsm.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzjt != null && this.zzjt.length > 0) {
            for (int i = 0; i < this.zzjt.length; ++i) {
                final zzi zzi = this.zzjt[i];
                if (zzi != null) {
                    zzz += zzsn.zzc(1, zzi);
                }
            }
        }
        if (this.zzju != null) {
            zzz += zzsn.zzc(2, this.zzju);
        }
        if (!this.zzjv.equals("")) {
            zzz += zzsn.zzo(3, this.zzjv);
        }
        return zzz;
    }
}
