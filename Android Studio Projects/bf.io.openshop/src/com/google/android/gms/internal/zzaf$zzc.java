package com.google.android.gms.internal;

import java.io.*;

public static final class zzc extends zzso<zzc>
{
    private static volatile zzc[] zzix;
    public String key;
    public boolean zziA;
    public long zziB;
    public long zziy;
    public long zziz;
    
    public zzc() {
        this.zzF();
    }
    
    public static zzc[] zzE() {
        Label_0027: {
            if (zzc.zzix != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzc.zzix == null) {
                    zzc.zzix = new zzc[0];
                }
                return zzc.zzix;
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
            final boolean b2 = o instanceof zzc;
            b = false;
            if (b2) {
                final zzc zzc = (zzc)o;
                if (this.key == null) {
                    final String key = zzc.key;
                    b = false;
                    if (key != null) {
                        return b;
                    }
                }
                else if (!this.key.equals(zzc.key)) {
                    return false;
                }
                final long n = lcmp(this.zziy, zzc.zziy);
                b = false;
                if (n == 0) {
                    final long n2 = lcmp(this.zziz, zzc.zziz);
                    b = false;
                    if (n2 == 0) {
                        final boolean zziA = this.zziA;
                        final boolean zziA2 = zzc.zziA;
                        b = false;
                        if (zziA == zziA2) {
                            final long n3 = lcmp(this.zziB, zzc.zziB);
                            b = false;
                            if (n3 == 0) {
                                if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                                    if (zzc.zzbuj != null) {
                                        final boolean empty = zzc.zzbuj.isEmpty();
                                        b = false;
                                        if (!empty) {
                                            return b;
                                        }
                                    }
                                    return true;
                                }
                                return this.zzbuj.equals(zzc.zzbuj);
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
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.key == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.key.hashCode();
        }
        final int n2 = 31 * (31 * (31 * (hashCode + n) + (int)(this.zziy ^ this.zziy >>> 32)) + (int)(this.zziz ^ this.zziz >>> 32));
        int n3;
        if (this.zziA) {
            n3 = 1231;
        }
        else {
            n3 = 1237;
        }
        final int n4 = 31 * (31 * (n3 + n2) + (int)(this.zziB ^ this.zziB >>> 32));
        final zzsq zzbuj = this.zzbuj;
        int hashCode2 = 0;
        if (zzbuj != null) {
            final boolean empty = this.zzbuj.isEmpty();
            hashCode2 = 0;
            if (!empty) {
                hashCode2 = this.zzbuj.hashCode();
            }
        }
        return n4 + hashCode2;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (!this.key.equals("")) {
            zzsn.zzn(1, this.key);
        }
        if (this.zziy != 0L) {
            zzsn.zzb(2, this.zziy);
        }
        if (this.zziz != 2147483647L) {
            zzsn.zzb(3, this.zziz);
        }
        if (this.zziA) {
            zzsn.zze(4, this.zziA);
        }
        if (this.zziB != 0L) {
            zzsn.zzb(5, this.zziB);
        }
        super.writeTo(zzsn);
    }
    
    public zzc zzF() {
        this.key = "";
        this.zziy = 0L;
        this.zziz = 2147483647L;
        this.zziA = false;
        this.zziB = 0L;
        this.zzbuj = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzc zzc(final zzsm zzsm) throws IOException {
    Label_0073:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!this.zza(zzsm, zzIX)) {
                        break Label_0073;
                    }
                    continue;
                }
                case 0: {
                    break Label_0073;
                }
                case 10: {
                    this.key = zzsm.readString();
                    continue;
                }
                case 16: {
                    this.zziy = zzsm.zzJa();
                    continue;
                }
                case 24: {
                    this.zziz = zzsm.zzJa();
                    continue;
                }
                case 32: {
                    this.zziA = zzsm.zzJc();
                    continue;
                }
                case 40: {
                    this.zziB = zzsm.zzJa();
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
        if (this.zziy != 0L) {
            zzz += zzsn.zzd(2, this.zziy);
        }
        if (this.zziz != 2147483647L) {
            zzz += zzsn.zzd(3, this.zziz);
        }
        if (this.zziA) {
            zzz += zzsn.zzf(4, this.zziA);
        }
        if (this.zziB != 0L) {
            zzz += zzsn.zzd(5, this.zziB);
        }
        return zzz;
    }
}
