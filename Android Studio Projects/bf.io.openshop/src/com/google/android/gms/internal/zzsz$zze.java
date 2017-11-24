package com.google.android.gms.internal;

import java.io.*;

public static final class zze extends zzso<zze>
{
    private static volatile zze[] zzbvj;
    public String key;
    public String value;
    
    public zze() {
        this.zzJH();
    }
    
    public static zze[] zzJG() {
        Label_0027: {
            if (zze.zzbvj != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zze.zzbvj == null) {
                    zze.zzbvj = new zze[0];
                }
                return zze.zzbvj;
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
            final boolean b2 = o instanceof zze;
            b = false;
            if (b2) {
                final zze zze = (zze)o;
                if (this.key == null) {
                    final String key = zze.key;
                    b = false;
                    if (key != null) {
                        return b;
                    }
                }
                else if (!this.key.equals(zze.key)) {
                    return false;
                }
                if (this.value == null) {
                    final String value = zze.value;
                    b = false;
                    if (value != null) {
                        return b;
                    }
                }
                else if (!this.value.equals(zze.value)) {
                    return false;
                }
                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                    if (zze.zzbuj != null) {
                        final boolean empty = zze.zzbuj.isEmpty();
                        b = false;
                        if (!empty) {
                            return b;
                        }
                    }
                    return true;
                }
                return this.zzbuj.equals(zze.zzbuj);
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.key == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.key.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.value == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.value.hashCode();
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
        if (!this.key.equals("")) {
            zzsn.zzn(1, this.key);
        }
        if (!this.value.equals("")) {
            zzsn.zzn(2, this.value);
        }
        super.writeTo(zzsn);
    }
    
    public zze zzJH() {
        this.key = "";
        this.value = "";
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zze zzW(final zzsm zzsm) throws IOException {
    Label_0049:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0049;
                    }
                    continue;
                }
                case 0: {
                    break Label_0049;
                }
                case 10: {
                    this.key = zzsm.readString();
                    continue;
                }
                case 18: {
                    this.value = zzsm.readString();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (!this.key.equals("")) {
            zzz += zzsn.zzo(1, this.key);
        }
        if (!this.value.equals("")) {
            zzz += zzsn.zzo(2, this.value);
        }
        return zzz;
    }
}
