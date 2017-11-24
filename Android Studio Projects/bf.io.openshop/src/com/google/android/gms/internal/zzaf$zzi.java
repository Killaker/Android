package com.google.android.gms.internal;

import java.io.*;

public static final class zzi extends zzso<zzi>
{
    private static volatile zzi[] zzjq;
    public String name;
    public zzag.zza zzjr;
    public zzd zzjs;
    
    public zzi() {
        this.zzO();
    }
    
    public static zzi[] zzN() {
        Label_0027: {
            if (zzi.zzjq != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzi.zzjq == null) {
                    zzi.zzjq = new zzi[0];
                }
                return zzi.zzjq;
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
            final boolean b2 = o instanceof zzi;
            b = false;
            if (b2) {
                final zzi zzi = (zzi)o;
                if (this.name == null) {
                    final String name = zzi.name;
                    b = false;
                    if (name != null) {
                        return b;
                    }
                }
                else if (!this.name.equals(zzi.name)) {
                    return false;
                }
                if (this.zzjr == null) {
                    final zzag.zza zzjr = zzi.zzjr;
                    b = false;
                    if (zzjr != null) {
                        return b;
                    }
                }
                else if (!this.zzjr.equals(zzi.zzjr)) {
                    return false;
                }
                if (this.zzjs == null) {
                    final zzd zzjs = zzi.zzjs;
                    b = false;
                    if (zzjs != null) {
                        return b;
                    }
                }
                else if (!this.zzjs.equals(zzi.zzjs)) {
                    return false;
                }
                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                    if (zzi.zzbuj != null) {
                        final boolean empty = zzi.zzbuj.isEmpty();
                        b = false;
                        if (!empty) {
                            return b;
                        }
                    }
                    return true;
                }
                return this.zzbuj.equals(zzi.zzbuj);
            }
        }
        return b;
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
        if (this.zzjr == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzjr.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzjs == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzjs.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        final zzsq zzbuj = this.zzbuj;
        int hashCode4 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode4 = 0;
            if (!empty) {
                hashCode4 = this.zzbuj.hashCode();
            }
        }
        return n4 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (!this.name.equals("")) {
            zzsn.zzn(1, this.name);
        }
        if (this.zzjr != null) {
            zzsn.zza(2, this.zzjr);
        }
        if (this.zzjs != null) {
            zzsn.zza(3, this.zzjs);
        }
        super.writeTo(zzsn);
    }
    
    public zzi zzO() {
        this.name = "";
        this.zzjr = null;
        this.zzjs = null;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzi zzi(final zzsm zzsm) throws IOException {
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
                    this.name = zzsm.readString();
                    continue;
                }
                case 18: {
                    if (this.zzjr == null) {
                        this.zzjr = new zzag.zza();
                    }
                    zzsm.zza(this.zzjr);
                    continue;
                }
                case 26: {
                    if (this.zzjs == null) {
                        this.zzjs = new zzd();
                    }
                    zzsm.zza(this.zzjs);
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (!this.name.equals("")) {
            zzz += zzsn.zzo(1, this.name);
        }
        if (this.zzjr != null) {
            zzz += zzsn.zzc(2, this.zzjr);
        }
        if (this.zzjs != null) {
            zzz += zzsn.zzc(3, this.zzjs);
        }
        return zzz;
    }
}
