package com.google.android.gms.internal;

import java.io.*;

public static final class zzc extends zzsu
{
    private static volatile zzc[] zzaZY;
    public String key;
    public String value;
    
    public zzc() {
        this.zzDO();
    }
    
    public static zzc[] zzDN() {
        Label_0027: {
            if (zzc.zzaZY != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzc.zzaZY == null) {
                    zzc.zzaZY = new zzc[0];
                }
                return zzc.zzaZY;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzc)) {
                return false;
            }
            final zzc zzc = (zzc)o;
            if (this.key == null) {
                if (zzc.key != null) {
                    return false;
                }
            }
            else if (!this.key.equals(zzc.key)) {
                return false;
            }
            if (this.value == null) {
                if (zzc.value != null) {
                    return false;
                }
            }
            else if (!this.value.equals(zzc.value)) {
                return false;
            }
        }
        return true;
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
        final String value = this.value;
        int hashCode2 = 0;
        if (value != null) {
            hashCode2 = this.value.hashCode();
        }
        return n2 + hashCode2;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.key != null) {
            zzsn.zzn(1, this.key);
        }
        if (this.value != null) {
            zzsn.zzn(2, this.value);
        }
        super.writeTo(zzsn);
    }
    
    public zzc zzB(final zzsm zzsm) throws IOException {
    Label_0048:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0048;
                    }
                    continue;
                }
                case 0: {
                    break Label_0048;
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
    
    public zzc zzDO() {
        this.key = null;
        this.value = null;
        this.zzbuu = -1;
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.key != null) {
            zzz += zzsn.zzo(1, this.key);
        }
        if (this.value != null) {
            zzz += zzsn.zzo(2, this.value);
        }
        return zzz;
    }
}
