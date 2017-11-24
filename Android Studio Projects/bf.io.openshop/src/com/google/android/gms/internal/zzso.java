package com.google.android.gms.internal;

import java.io.*;

public abstract class zzso<M extends zzso<M>> extends zzsu
{
    protected zzsq zzbuj;
    
    @Override
    public void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbuj != null) {
            for (int i = 0; i < this.zzbuj.size(); ++i) {
                this.zzbuj.zzmG(i).writeTo(zzsn);
            }
        }
    }
    
    public M zzJp() throws CloneNotSupportedException {
        final zzso zzso = (zzso)super.clone();
        zzss.zza(this, zzso);
        return (M)zzso;
    }
    
    public final <T> T zza(final zzsp<M, T> zzsp) {
        if (this.zzbuj != null) {
            final zzsr zzmF = this.zzbuj.zzmF(zzsx.zzmJ(zzsp.tag));
            if (zzmF != null) {
                return zzmF.zzb(zzsp);
            }
        }
        return null;
    }
    
    protected final boolean zza(final zzsm zzsm, final int n) throws IOException {
        final int position = zzsm.getPosition();
        if (!zzsm.zzmo(n)) {
            return false;
        }
        final int zzmJ = zzsx.zzmJ(n);
        final zzsw zzsw = new zzsw(n, zzsm.zzz(position, zzsm.getPosition() - position));
        zzsr zzmF = null;
        if (this.zzbuj == null) {
            this.zzbuj = new zzsq();
        }
        else {
            zzmF = this.zzbuj.zzmF(zzmJ);
        }
        if (zzmF == null) {
            zzmF = new zzsr();
            this.zzbuj.zza(zzmJ, zzmF);
        }
        zzmF.zza(zzsw);
        return true;
    }
    
    @Override
    protected int zzz() {
        int i = 0;
        int n;
        if (this.zzbuj != null) {
            n = 0;
            while (i < this.zzbuj.size()) {
                n += this.zzbuj.zzmG(i).zzz();
                ++i;
            }
        }
        else {
            n = 0;
        }
        return n;
    }
}
