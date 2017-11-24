package com.google.android.gms.internal;

import java.io.*;
import java.util.*;

class zzsr implements Cloneable
{
    private zzsp<?, ?> zzbuq;
    private Object zzbur;
    private List<zzsw> zzbus;
    
    zzsr() {
        this.zzbus = new ArrayList<zzsw>();
    }
    
    private byte[] toByteArray() throws IOException {
        final byte[] array = new byte[this.zzz()];
        this.writeTo(zzsn.zzE(array));
        return array;
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b;
        if (o == this) {
            b = true;
        }
        else {
            final boolean b2 = o instanceof zzsr;
            b = false;
            if (b2) {
                final zzsr zzsr = (zzsr)o;
                if (this.zzbur != null && zzsr.zzbur != null) {
                    final zzsp<?, ?> zzbuq = this.zzbuq;
                    final zzsp<?, ?> zzbuq2 = zzsr.zzbuq;
                    b = false;
                    if (zzbuq == zzbuq2) {
                        if (!this.zzbuq.zzbuk.isArray()) {
                            return this.zzbur.equals(zzsr.zzbur);
                        }
                        if (this.zzbur instanceof byte[]) {
                            return Arrays.equals((byte[])this.zzbur, (byte[])zzsr.zzbur);
                        }
                        if (this.zzbur instanceof int[]) {
                            return Arrays.equals((int[])this.zzbur, (int[])zzsr.zzbur);
                        }
                        if (this.zzbur instanceof long[]) {
                            return Arrays.equals((long[])this.zzbur, (long[])zzsr.zzbur);
                        }
                        if (this.zzbur instanceof float[]) {
                            return Arrays.equals((float[])this.zzbur, (float[])zzsr.zzbur);
                        }
                        if (this.zzbur instanceof double[]) {
                            return Arrays.equals((double[])this.zzbur, (double[])zzsr.zzbur);
                        }
                        if (this.zzbur instanceof boolean[]) {
                            return Arrays.equals((boolean[])this.zzbur, (boolean[])zzsr.zzbur);
                        }
                        return Arrays.deepEquals((Object[])this.zzbur, (Object[])zzsr.zzbur);
                    }
                }
                else {
                    if (this.zzbus != null && zzsr.zzbus != null) {
                        return this.zzbus.equals(zzsr.zzbus);
                    }
                    try {
                        return Arrays.equals(this.toByteArray(), zzsr.toByteArray());
                    }
                    catch (IOException ex) {
                        throw new IllegalStateException(ex);
                    }
                }
            }
        }
        return b;
    }
    
    @Override
    public int hashCode() {
        try {
            return Arrays.hashCode(this.toByteArray()) + 527;
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    void writeTo(final zzsn zzsn) throws IOException {
        if (this.zzbur != null) {
            this.zzbuq.zza(this.zzbur, zzsn);
        }
        else {
            final Iterator<zzsw> iterator = this.zzbus.iterator();
            while (iterator.hasNext()) {
                iterator.next().writeTo(zzsn);
            }
        }
    }
    
    public final zzsr zzJr() {
        int i = 0;
        final zzsr zzsr = new zzsr();
        try {
            zzsr.zzbuq = this.zzbuq;
            if (this.zzbus == null) {
                zzsr.zzbus = null;
            }
            else {
                zzsr.zzbus.addAll(this.zzbus);
            }
            if (this.zzbur == null) {
                return zzsr;
            }
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError((Object)ex);
        }
        if (this.zzbur instanceof zzsu) {
            zzsr.zzbur = ((zzsu)this.zzbur).clone();
            return zzsr;
        }
        if (this.zzbur instanceof byte[]) {
            zzsr.zzbur = ((byte[])this.zzbur).clone();
            return zzsr;
        }
        if (this.zzbur instanceof byte[][]) {
            final byte[][] array = (byte[][])this.zzbur;
            final byte[][] zzbur = new byte[array.length][];
            zzsr.zzbur = zzbur;
            for (int j = 0; j < array.length; ++j) {
                zzbur[j] = array[j].clone();
            }
        }
        else {
            if (this.zzbur instanceof boolean[]) {
                zzsr.zzbur = ((boolean[])this.zzbur).clone();
                return zzsr;
            }
            if (this.zzbur instanceof int[]) {
                zzsr.zzbur = ((int[])this.zzbur).clone();
                return zzsr;
            }
            if (this.zzbur instanceof long[]) {
                zzsr.zzbur = ((long[])this.zzbur).clone();
                return zzsr;
            }
            if (this.zzbur instanceof float[]) {
                zzsr.zzbur = ((float[])this.zzbur).clone();
                return zzsr;
            }
            if (this.zzbur instanceof double[]) {
                zzsr.zzbur = ((double[])this.zzbur).clone();
                return zzsr;
            }
            if (this.zzbur instanceof zzsu[]) {
                final zzsu[] array2 = (zzsu[])this.zzbur;
                final zzsu[] zzbur2 = new zzsu[array2.length];
                zzsr.zzbur = zzbur2;
                while (i < array2.length) {
                    zzbur2[i] = array2[i].clone();
                    ++i;
                }
            }
        }
        return zzsr;
    }
    
    void zza(final zzsw zzsw) {
        this.zzbus.add(zzsw);
    }
    
     <T> T zzb(final zzsp<?, T> zzbuq) {
        if (this.zzbur != null) {
            if (this.zzbuq != zzbuq) {
                throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
            }
        }
        else {
            this.zzbuq = zzbuq;
            this.zzbur = zzbuq.zzJ(this.zzbus);
            this.zzbus = null;
        }
        return (T)this.zzbur;
    }
    
    int zzz() {
        int zzY;
        if (this.zzbur != null) {
            zzY = this.zzbuq.zzY(this.zzbur);
        }
        else {
            final Iterator<zzsw> iterator = this.zzbus.iterator();
            zzY = 0;
            while (iterator.hasNext()) {
                zzY += iterator.next().zzz();
            }
        }
        return zzY;
    }
}
