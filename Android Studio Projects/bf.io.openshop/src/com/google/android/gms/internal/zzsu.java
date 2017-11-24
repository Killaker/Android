package com.google.android.gms.internal;

import java.io.*;
import java.util.*;

public abstract class zzsu
{
    protected volatile int zzbuu;
    
    public zzsu() {
        this.zzbuu = -1;
    }
    
    public static final <T extends zzsu> T mergeFrom(final T t, final byte[] array) throws zzst {
        return mergeFrom(t, array, 0, array.length);
    }
    
    public static final <T extends zzsu> T mergeFrom(final T t, final byte[] array, final int n, final int n2) throws zzst {
        try {
            final zzsm zza = zzsm.zza(array, n, n2);
            t.mergeFrom(zza);
            zza.zzmn(0);
            return t;
        }
        catch (zzst zzst) {
            throw zzst;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }
    
    public static final boolean messageNanoEquals(final zzsu zzsu, final zzsu zzsu2) {
        boolean b;
        if (zzsu == zzsu2) {
            b = true;
        }
        else {
            b = false;
            if (zzsu != null) {
                b = false;
                if (zzsu2 != null) {
                    final Class<? extends zzsu> class1 = zzsu.getClass();
                    final Class<? extends zzsu> class2 = zzsu2.getClass();
                    b = false;
                    if (class1 == class2) {
                        final int serializedSize = zzsu.getSerializedSize();
                        final int serializedSize2 = zzsu2.getSerializedSize();
                        b = false;
                        if (serializedSize2 == serializedSize) {
                            final byte[] array = new byte[serializedSize];
                            final byte[] array2 = new byte[serializedSize];
                            toByteArray(zzsu, array, 0, serializedSize);
                            toByteArray(zzsu2, array2, 0, serializedSize);
                            return Arrays.equals(array, array2);
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public static final void toByteArray(final zzsu zzsu, final byte[] array, final int n, final int n2) {
        try {
            final zzsn zzb = zzsn.zzb(array, n, n2);
            zzsu.writeTo(zzb);
            zzb.zzJo();
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final byte[] toByteArray(final zzsu zzsu) {
        final byte[] array = new byte[zzsu.getSerializedSize()];
        toByteArray(zzsu, array, 0, array.length);
        return array;
    }
    
    public zzsu clone() throws CloneNotSupportedException {
        return (zzsu)super.clone();
    }
    
    public int getCachedSize() {
        if (this.zzbuu < 0) {
            this.getSerializedSize();
        }
        return this.zzbuu;
    }
    
    public int getSerializedSize() {
        return this.zzbuu = this.zzz();
    }
    
    public abstract zzsu mergeFrom(final zzsm p0) throws IOException;
    
    @Override
    public String toString() {
        return zzsv.zzf(this);
    }
    
    public void writeTo(final zzsn zzsn) throws IOException {
    }
    
    protected int zzz() {
        return 0;
    }
}
