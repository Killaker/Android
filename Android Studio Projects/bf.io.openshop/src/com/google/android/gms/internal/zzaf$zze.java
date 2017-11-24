package com.google.android.gms.internal;

import java.io.*;

public static final class zze extends zzso<zze>
{
    private static volatile zze[] zziF;
    public int key;
    public int value;
    
    public zze() {
        this.zzI();
    }
    
    public static zze[] zzH() {
        Label_0027: {
            if (zze.zziF != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zze.zziF == null) {
                    zze.zziF = new zze[0];
                }
                return zze.zziF;
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
                final int key = this.key;
                final int key2 = zze.key;
                b = false;
                if (key == key2) {
                    final int value = this.value;
                    final int value2 = zze.value;
                    b = false;
                    if (value == value2) {
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
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (31 * (31 * (527 + this.getClass().getName().hashCode()) + this.key) + this.value);
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
        zzsn.zzA(1, this.key);
        zzsn.zzA(2, this.value);
        super.writeTo(zzsn);
    }
    
    public zze zzI() {
        this.key = 0;
        this.value = 0;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zze zze(final zzsm zzsm) throws IOException {
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
                case 8: {
                    this.key = zzsm.zzJb();
                    continue;
                }
                case 16: {
                    this.value = zzsm.zzJb();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        return super.zzz() + zzsn.zzC(1, this.key) + zzsn.zzC(2, this.value);
    }
}
