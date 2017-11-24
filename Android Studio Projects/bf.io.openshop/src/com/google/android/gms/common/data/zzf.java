package com.google.android.gms.common.data;

import java.util.*;

public abstract class zzf<T> extends AbstractDataBuffer<T>
{
    private boolean zzajw;
    private ArrayList<Integer> zzajx;
    
    protected zzf(final DataHolder dataHolder) {
        super(dataHolder);
        this.zzajw = false;
    }
    
    private void zzqh() {
        while (true) {
            Label_0193: {
                while (true) {
                    String zzd;
                    int n;
                    String zzd2;
                    synchronized (this) {
                        if (this.zzajw) {
                            break;
                        }
                        final int count = this.zzahi.getCount();
                        this.zzajx = new ArrayList<Integer>();
                        if (count <= 0) {
                            break Label_0193;
                        }
                        this.zzajx.add(0);
                        final String zzqg = this.zzqg();
                        zzd = this.zzahi.zzd(zzqg, 0, this.zzahi.zzbH(0));
                        n = 1;
                        if (n >= count) {
                            break Label_0193;
                        }
                        final int zzbH = this.zzahi.zzbH(n);
                        zzd2 = this.zzahi.zzd(zzqg, n, zzbH);
                        if (zzd2 == null) {
                            throw new NullPointerException("Missing value for markerColumn: " + zzqg + ", at row: " + n + ", for window: " + zzbH);
                        }
                    }
                    if (!zzd2.equals(zzd)) {
                        this.zzajx.add(n);
                    }
                    else {
                        zzd2 = zzd;
                    }
                    ++n;
                    zzd = zzd2;
                    continue;
                }
            }
            this.zzajw = true;
            break;
        }
    }
    // monitorexit(this)
    
    @Override
    public final T get(final int n) {
        this.zzqh();
        return this.zzk(this.zzbK(n), this.zzbL(n));
    }
    
    @Override
    public int getCount() {
        this.zzqh();
        return this.zzajx.size();
    }
    
    int zzbK(final int n) {
        if (n < 0 || n >= this.zzajx.size()) {
            throw new IllegalArgumentException("Position " + n + " is out of bounds for this buffer");
        }
        return this.zzajx.get(n);
    }
    
    protected int zzbL(final int n) {
        int n2;
        if (n < 0 || n == this.zzajx.size()) {
            n2 = 0;
        }
        else {
            if (n == -1 + this.zzajx.size()) {
                n2 = this.zzahi.getCount() - this.zzajx.get(n);
            }
            else {
                n2 = this.zzajx.get(n + 1) - this.zzajx.get(n);
            }
            if (n2 == 1) {
                final int zzbK = this.zzbK(n);
                final int zzbH = this.zzahi.zzbH(zzbK);
                final String zzqi = this.zzqi();
                if (zzqi != null && this.zzahi.zzd(zzqi, zzbK, zzbH) == null) {
                    return 0;
                }
            }
        }
        return n2;
    }
    
    protected abstract T zzk(final int p0, final int p1);
    
    protected abstract String zzqg();
    
    protected String zzqi() {
        return null;
    }
}
