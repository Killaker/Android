package com.google.android.gms.internal;

import java.io.*;

public static final class zze extends zzsu
{
    private static volatile zze[] zzaZK;
    public String zzaZL;
    public zzc zzaZM;
    public Integer zzaZv;
    
    public zze() {
        this.zzDI();
    }
    
    public static zze[] zzDH() {
        Label_0027: {
            if (zze.zzaZK != null) {
                break Label_0027;
            }
            synchronized (zzss.zzbut) {
                if (zze.zzaZK == null) {
                    zze.zzaZK = new zze[0];
                }
                return zze.zzaZK;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zze)) {
                return false;
            }
            final zze zze = (zze)o;
            if (this.zzaZv == null) {
                if (zze.zzaZv != null) {
                    return false;
                }
            }
            else if (!this.zzaZv.equals(zze.zzaZv)) {
                return false;
            }
            if (this.zzaZL == null) {
                if (zze.zzaZL != null) {
                    return false;
                }
            }
            else if (!this.zzaZL.equals(zze.zzaZL)) {
                return false;
            }
            if (this.zzaZM == null) {
                if (zze.zzaZM != null) {
                    return false;
                }
            }
            else if (!this.zzaZM.equals(zze.zzaZM)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int n = 31 * (527 + this.getClass().getName().hashCode());
        int hashCode;
        if (this.zzaZv == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.zzaZv.hashCode();
        }
        final int n2 = 31 * (hashCode + n);
        int hashCode2;
        if (this.zzaZL == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.zzaZL.hashCode();
        }
        final int n3 = 31 * (hashCode2 + n2);
        final zzc zzaZM = this.zzaZM;
        int hashCode3 = 0;
        if (zzaZM != null) {
            hashCode3 = this.zzaZM.hashCode();
        }
        return n3 + hashCode3;
    }
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzaZv != null) {
            zzsn.zzA(1, this.zzaZv);
        }
        if (this.zzaZL != null) {
            zzsn.zzn(2, this.zzaZL);
        }
        if (this.zzaZM != null) {
            zzsn.zza(3, this.zzaZM);
        }
        super.writeTo(zzsn);
    }
    
    public zze zzDI() {
        this.zzaZv = null;
        this.zzaZL = null;
        this.zzaZM = null;
        this.zzbuu = -1;
        return this;
    }
    
    public zze zzx(final zzsm zzsm) throws IOException {
    Label_0056:
        while (true) {
            final int zzIX = zzsm.zzIX();
            switch (zzIX) {
                default: {
                    if (!zzsx.zzb(zzsm, zzIX)) {
                        break Label_0056;
                    }
                    continue;
                }
                case 0: {
                    break Label_0056;
                }
                case 8: {
                    this.zzaZv = zzsm.zzJb();
                    continue;
                }
                case 18: {
                    this.zzaZL = zzsm.readString();
                    continue;
                }
                case 26: {
                    if (this.zzaZM == null) {
                        this.zzaZM = new zzc();
                    }
                    zzsm.zza(this.zzaZM);
                    continue;
                }
            }
        }
        return this;
    }
    
    @Override
    protected int zzz() {
        int zzz = super.zzz();
        if (this.zzaZv != null) {
            zzz += zzsn.zzC(1, this.zzaZv);
        }
        if (this.zzaZL != null) {
            zzz += zzsn.zzo(2, this.zzaZL);
        }
        if (this.zzaZM != null) {
            zzz += zzsn.zzc(3, this.zzaZM);
        }
        return zzz;
    }
}
