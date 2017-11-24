package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.*;
import android.database.*;
import android.net.*;

public abstract class zzc
{
    protected final DataHolder zzahi;
    protected int zzaje;
    private int zzajf;
    
    public zzc(final DataHolder dataHolder, final int n) {
        this.zzahi = zzx.zzz(dataHolder);
        this.zzbF(n);
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = o instanceof zzc;
        boolean b2 = false;
        if (b) {
            final zzc zzc = (zzc)o;
            final boolean equal = zzw.equal(zzc.zzaje, this.zzaje);
            b2 = false;
            if (equal) {
                final boolean equal2 = zzw.equal(zzc.zzajf, this.zzajf);
                b2 = false;
                if (equal2) {
                    final DataHolder zzahi = zzc.zzahi;
                    final DataHolder zzahi2 = this.zzahi;
                    b2 = false;
                    if (zzahi == zzahi2) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    protected boolean getBoolean(final String s) {
        return this.zzahi.zze(s, this.zzaje, this.zzajf);
    }
    
    protected byte[] getByteArray(final String s) {
        return this.zzahi.zzg(s, this.zzaje, this.zzajf);
    }
    
    protected float getFloat(final String s) {
        return this.zzahi.zzf(s, this.zzaje, this.zzajf);
    }
    
    protected int getInteger(final String s) {
        return this.zzahi.zzc(s, this.zzaje, this.zzajf);
    }
    
    protected long getLong(final String s) {
        return this.zzahi.zzb(s, this.zzaje, this.zzajf);
    }
    
    protected String getString(final String s) {
        return this.zzahi.zzd(s, this.zzaje, this.zzajf);
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzaje, this.zzajf, this.zzahi);
    }
    
    public boolean isDataValid() {
        return !this.zzahi.isClosed();
    }
    
    protected void zza(final String s, final CharArrayBuffer charArrayBuffer) {
        this.zzahi.zza(s, this.zzaje, this.zzajf, charArrayBuffer);
    }
    
    protected void zzbF(final int zzaje) {
        zzx.zzab(zzaje >= 0 && zzaje < this.zzahi.getCount());
        this.zzaje = zzaje;
        this.zzajf = this.zzahi.zzbH(this.zzaje);
    }
    
    protected Uri zzcA(final String s) {
        return this.zzahi.zzh(s, this.zzaje, this.zzajf);
    }
    
    protected boolean zzcB(final String s) {
        return this.zzahi.zzi(s, this.zzaje, this.zzajf);
    }
    
    public boolean zzcz(final String s) {
        return this.zzahi.zzcz(s);
    }
    
    protected int zzqc() {
        return this.zzaje;
    }
}
