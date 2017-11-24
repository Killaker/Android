package com.google.android.gms.internal;

public final class zzsq implements Cloneable
{
    private static final zzsr zzbum;
    private int mSize;
    private boolean zzbun;
    private int[] zzbuo;
    private zzsr[] zzbup;
    
    static {
        zzbum = new zzsr();
    }
    
    zzsq() {
        this(10);
    }
    
    zzsq(final int n) {
        this.zzbun = false;
        final int idealIntArraySize = this.idealIntArraySize(n);
        this.zzbuo = new int[idealIntArraySize];
        this.zzbup = new zzsr[idealIntArraySize];
        this.mSize = 0;
    }
    
    private void gc() {
        final int mSize = this.mSize;
        final int[] zzbuo = this.zzbuo;
        final zzsr[] zzbup = this.zzbup;
        int i = 0;
        int mSize2 = 0;
        while (i < mSize) {
            final zzsr zzsr = zzbup[i];
            if (zzsr != zzsq.zzbum) {
                if (i != mSize2) {
                    zzbuo[mSize2] = zzbuo[i];
                    zzbup[mSize2] = zzsr;
                    zzbup[i] = null;
                }
                ++mSize2;
            }
            ++i;
        }
        this.zzbun = false;
        this.mSize = mSize2;
    }
    
    private int idealByteArraySize(int n) {
        for (int i = 4; i < 32; ++i) {
            if (n <= -12 + (1 << i)) {
                n = -12 + (1 << i);
                break;
            }
        }
        return n;
    }
    
    private int idealIntArraySize(final int n) {
        return this.idealByteArraySize(n * 4) / 4;
    }
    
    private boolean zza(final int[] array, final int[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean zza(final zzsr[] array, final zzsr[] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            if (!array[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }
    
    private int zzmH(final int n) {
        int i = 0;
        int n2 = -1 + this.mSize;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            final int n4 = this.zzbuo[n3];
            if (n4 < n) {
                i = n3 + 1;
            }
            else {
                if (n4 <= n) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return ~i;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzsq)) {
                return false;
            }
            final zzsq zzsq = (zzsq)o;
            if (this.size() != zzsq.size()) {
                return false;
            }
            if (!this.zza(this.zzbuo, zzsq.zzbuo, this.mSize) || !this.zza(this.zzbup, zzsq.zzbup, this.mSize)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        if (this.zzbun) {
            this.gc();
        }
        int n = 17;
        for (int i = 0; i < this.mSize; ++i) {
            n = 31 * (n * 31 + this.zzbuo[i]) + this.zzbup[i].hashCode();
        }
        return n;
    }
    
    public boolean isEmpty() {
        return this.size() == 0;
    }
    
    int size() {
        if (this.zzbun) {
            this.gc();
        }
        return this.mSize;
    }
    
    public final zzsq zzJq() {
        int i = 0;
        final int size = this.size();
        final zzsq zzsq = new zzsq(size);
        System.arraycopy(this.zzbuo, 0, zzsq.zzbuo, 0, size);
        while (i < size) {
            if (this.zzbup[i] != null) {
                zzsq.zzbup[i] = this.zzbup[i].zzJr();
            }
            ++i;
        }
        zzsq.mSize = size;
        return zzsq;
    }
    
    void zza(final int n, final zzsr zzsr) {
        final int zzmH = this.zzmH(n);
        if (zzmH >= 0) {
            this.zzbup[zzmH] = zzsr;
            return;
        }
        int n2 = ~zzmH;
        if (n2 < this.mSize && this.zzbup[n2] == zzsq.zzbum) {
            this.zzbuo[n2] = n;
            this.zzbup[n2] = zzsr;
            return;
        }
        if (this.zzbun && this.mSize >= this.zzbuo.length) {
            this.gc();
            n2 = (-1 ^ this.zzmH(n));
        }
        if (this.mSize >= this.zzbuo.length) {
            final int idealIntArraySize = this.idealIntArraySize(1 + this.mSize);
            final int[] zzbuo = new int[idealIntArraySize];
            final zzsr[] zzbup = new zzsr[idealIntArraySize];
            System.arraycopy(this.zzbuo, 0, zzbuo, 0, this.zzbuo.length);
            System.arraycopy(this.zzbup, 0, zzbup, 0, this.zzbup.length);
            this.zzbuo = zzbuo;
            this.zzbup = zzbup;
        }
        if (this.mSize - n2 != 0) {
            System.arraycopy(this.zzbuo, n2, this.zzbuo, n2 + 1, this.mSize - n2);
            System.arraycopy(this.zzbup, n2, this.zzbup, n2 + 1, this.mSize - n2);
        }
        this.zzbuo[n2] = n;
        this.zzbup[n2] = zzsr;
        ++this.mSize;
    }
    
    zzsr zzmF(final int n) {
        final int zzmH = this.zzmH(n);
        if (zzmH < 0 || this.zzbup[zzmH] == zzsq.zzbum) {
            return null;
        }
        return this.zzbup[zzmH];
    }
    
    zzsr zzmG(final int n) {
        if (this.zzbun) {
            this.gc();
        }
        return this.zzbup[n];
    }
}
