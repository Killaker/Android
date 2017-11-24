package com.google.android.gms.internal;

import java.io.*;

public static final class zzg extends zzsu
{
    private static volatile zzg[] zzbaI;
    public String name;
    public Float zzaZo;
    public String zzamJ;
    public Long zzbaJ;
    public Long zzbai;
    
    public zzg() {
        this.zzEa();
    }
    
    public static zzg[] zzDZ() {
        Label_0027: {
            if (zzg.zzbaI != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zzg.zzbaI == null) {
                    zzg.zzbaI = new zzg[0];
                }
                return zzg.zzbaI;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzg)) {
                return false;
            }
            final zzg zzg = (zzg)o;
            if (this.zzbaJ == null) {
                if (zzg.zzbaJ != null) {
                    return false;
                }
            }
            else if (!this.zzbaJ.equals(zzg.zzbaJ)) {
                return false;
            }
            if (this.name == null) {
                if (zzg.name != null) {
                    return false;
                }
            }
            else if (!this.name.equals(zzg.name)) {
                return false;
            }
            if (this.zzamJ == null) {
                if (zzg.zzamJ != null) {
                    return false;
                }
            }
            else if (!this.zzamJ.equals(zzg.zzamJ)) {
                return false;
            }
            if (this.zzbai == null) {
                if (zzg.zzbai != null) {
                    return false;
                }
            }
            else if (!this.zzbai.equals(zzg.zzbai)) {
                return false;
            }
            if (this.zzaZo == null) {
                if (zzg.zzaZo != null) {
                    return false;
                }
            }
            else if (!this.zzaZo.equals(zzg.zzaZo)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.zzbaJ == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzbaJ.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.name == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.name.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        int hashCode3;
        if (this.zzamJ == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.zzamJ.hashCode();
        }
        final int n4 = 31 * (hashCode3 + n3);
        int hashCode4;
        if (this.zzbai == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = this.zzbai.hashCode();
        }
        final int n5 = 31 * (hashCode4 + n4);
        final Float zzaZo = this.zzaZo;
        int hashCode5 = 0;
        if (zzaZo != null) {
            hashCode5 = this.zzaZo.hashCode();
        }
        return n5 + hashCode5;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbaJ != null) {
            zzsn.zzb(1, this.zzbaJ);
        }
        if (this.name != null) {
            zzsn.zzn(2, this.name);
        }
        if (this.zzamJ != null) {
            zzsn.zzn(3, this.zzamJ);
        }
        if (this.zzbai != null) {
            zzsn.zzb(4, this.zzbai);
        }
        if (this.zzaZo != null) {
            zzsn.zzb(5, this.zzaZo);
        }
        super.writeTo(zzsn);
    }
    
    public zzg zzEa() {
        this.zzbaJ = null;
        this.name = null;
        this.zzamJ = null;
        this.zzbai = null;
        this.zzaZo = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zzg zzI(final zzsm zzsm) throws IOException {
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
                case 8: {
                    this.zzbaJ = zzsm.zzJa();
                    continue;
                }
                case 18: {
                    this.name = zzsm.readString();
                    continue;
                }
                case 26: {
                    this.zzamJ = zzsm.readString();
                    continue;
                }
                case 32: {
                    this.zzbai = zzsm.zzJa();
                    continue;
                }
                case 45: {
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
        if (this.zzbaJ != null) {
            zzz += zzsn.zzd(1, this.zzbaJ);
        }
        if (this.name != null) {
            zzz += zzsn.zzo(2, this.name);
        }
        if (this.zzamJ != null) {
            zzz += zzsn.zzo(3, this.zzamJ);
        }
        if (this.zzbai != null) {
            zzz += zzsn.zzd(4, this.zzbai);
        }
        if (this.zzaZo != null) {
            zzz += zzsn.zzc(5, this.zzaZo);
        }
        return zzz;
    }
}
