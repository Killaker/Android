package com.google.android.gms.internal;

import java.io.*;

public static final class zzc extends zzsu
{
    private static volatile zzc[] zzbah;
    public String name;
    public Float zzaZo;
    public String zzamJ;
    public Long zzbai;
    
    public zzc() {
        this.zzDU();
    }
    
    public static zzc[] zzDT() {
        Label_0027: {
            if (zzc.zzbah != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzc.zzbah == null) {
                    zzc.zzbah = new zzc[0];
                }
                return zzc.zzbah;
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
            if (this.name == null) {
                if (zzc.name != null) {
                    return false;
                }
            }
            else if (!this.name.equals(zzc.name)) {
                return false;
            }
            if (this.zzamJ == null) {
                if (zzc.zzamJ != null) {
                    return false;
                }
            }
            else if (!this.zzamJ.equals(zzc.zzamJ)) {
                return false;
            }
            if (this.zzbai == null) {
                if (zzc.zzbai != null) {
                    return false;
                }
            }
            else if (!this.zzbai.equals(zzc.zzbai)) {
                return false;
            }
            if (this.zzaZo == null) {
                if (zzc.zzaZo != null) {
                    return false;
                }
            }
            else if (!this.zzaZo.equals(zzc.zzaZo)) {
                return false;
            }
        }
        return true;
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
        if (this.zzamJ == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzamJ.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzbai == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzbai.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        final Float zzaZo = this.zzaZo;
        int hashCode4 = 0;
        if (zzaZo != null) {
            hashCode4 = this.zzaZo.hashCode();
        }
        return n4 + hashCode4;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.name != null) {
            zzsn.zzn(1, this.name);
        }
        if (this.zzamJ != null) {
            zzsn.zzn(2, this.zzamJ);
        }
        if (this.zzbai != null) {
            zzsn.zzb(3, this.zzbai);
        }
        if (this.zzaZo != null) {
            zzsn.zzb(4, this.zzaZo);
        }
        super.writeTo(zzsn);
    }
    
    public zzc zzDU() {
        this.name = null;
        this.zzamJ = null;
        this.zzbai = null;
        this.zzaZo = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzc zzE(final zzsm zzsm) throws IOException {
    Label_0064:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0064;
                    }
                    continue;
                }
                case 0: {
                    break Label_0064;
                }
                case 10: {
                    this.name = zzsm.readString();
                    continue;
                }
                case 18: {
                    this.zzamJ = zzsm.readString();
                    continue;
                }
                case 24: {
                    this.zzbai = zzsm.zzJa();
                    continue;
                }
                case 37: {
                    this.zzaZo = zzsm.readFloat();
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.name != null) {
            zzz += zzsn.zzo(1, this.name);
        }
        if (this.zzamJ != null) {
            zzz += zzsn.zzo(2, this.zzamJ);
        }
        if (this.zzbai != null) {
            zzz += zzsn.zzd(3, this.zzbai);
        }
        if (this.zzaZo != null) {
            zzz += zzsn.zzc(4, this.zzaZo);
        }
        return zzz;
    }
}
