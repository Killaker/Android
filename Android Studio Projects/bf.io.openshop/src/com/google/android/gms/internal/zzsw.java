package com.google.android.gms.internal;

import java.util.*;
import java.io.*;

final class zzsw
{
    final int tag;
    final byte[] zzbuv;
    
    zzsw(final int tag, final byte[] zzbuv) {
        this.tag = tag;
        this.zzbuv = zzbuv;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzsw)) {
                return false;
            }
            final zzsw zzsw = (zzsw)o;
            if (this.tag != zzsw.tag || !Arrays.equals(this.zzbuv, zzsw.zzbuv)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * (527 + this.tag) + Arrays.hashCode(this.zzbuv);
    }
    
    void writeTo(final zzsn zzsn) throws IOException {
        zzsn.zzmB(this.tag);
        zzsn.zzH(this.zzbuv);
    }
    
    int zzz() {
        return 0 + zzsn.zzmC(this.tag) + this.zzbuv.length;
    }
}
