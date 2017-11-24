package com.google.android.gms.internal;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

public class zzsp<M extends zzso<M>, T>
{
    public final int tag;
    protected final int type;
    protected final Class<T> zzbuk;
    protected final boolean zzbul;
    
    private zzsp(final int type, final Class<T> zzbuk, final int tag, final boolean zzbul) {
        this.type = type;
        this.zzbuk = zzbuk;
        this.tag = tag;
        this.zzbul = zzbul;
    }
    
    private T zzK(final List<zzsw> list) {
        int i = 0;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (int j = 0; j < list.size(); ++j) {
            final zzsw zzsw = list.get(j);
            if (zzsw.zzbuv.length != 0) {
                this.zza(zzsw, list2);
            }
        }
        final int size = list2.size();
        Object cast;
        if (size == 0) {
            cast = null;
        }
        else {
            cast = this.zzbuk.cast(Array.newInstance(this.zzbuk.getComponentType(), size));
            while (i < size) {
                Array.set(cast, i, list2.get(i));
                ++i;
            }
        }
        return (T)cast;
    }
    
    private T zzL(final List<zzsw> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzbuk.cast(this.zzP(zzsm.zzD(list.get(-1 + list.size()).zzbuv)));
    }
    
    public static <M extends zzso<M>, T extends zzsu> zzsp<M, T> zza(final int n, final Class<T> clazz, final long n2) {
        return new zzsp<M, T>(n, clazz, (int)n2, false);
    }
    
    final T zzJ(final List<zzsw> list) {
        if (list == null) {
            return null;
        }
        if (this.zzbul) {
            return this.zzK(list);
        }
        return this.zzL(list);
    }
    
    protected Object zzP(final zzsm zzsm) {
        if (!this.zzbul) {
            goto Label_0101;
        }
        final Class<?> componentType = this.zzbuk.getComponentType();
        try {
            switch (this.type) {
                default: {
                    throw new IllegalArgumentException("Unknown type " + this.type);
                }
                case 10: {
                    goto Label_0109;
                    goto Label_0109;
                }
                case 11: {
                    goto Label_0134;
                    goto Label_0134;
                }
            }
        }
        catch (InstantiationException ex) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, ex);
        }
        catch (IllegalAccessException ex2) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, ex2);
        }
        catch (IOException ex3) {
            throw new IllegalArgumentException("Error reading extension field", ex3);
        }
    }
    
    int zzY(final Object o) {
        if (this.zzbul) {
            return this.zzZ(o);
        }
        return this.zzaa(o);
    }
    
    protected int zzZ(final Object o) {
        int n = 0;
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            if (Array.get(o, i) != null) {
                n += this.zzaa(Array.get(o, i));
            }
        }
        return n;
    }
    
    protected void zza(final zzsw zzsw, final List<Object> list) {
        list.add(this.zzP(zzsm.zzD(zzsw.zzbuv)));
    }
    
    void zza(final Object o, final zzsn zzsn) throws IOException {
        if (this.zzbul) {
            this.zzc(o, zzsn);
            return;
        }
        this.zzb(o, zzsn);
    }
    
    protected int zzaa(final Object o) {
        final int zzmJ = zzsx.zzmJ(this.tag);
        switch (this.type) {
            default: {
                throw new IllegalArgumentException("Unknown type " + this.type);
            }
            case 10: {
                return zzsn.zzb(zzmJ, (zzsu)o);
            }
            case 11: {
                return zzsn.zzc(zzmJ, (zzsu)o);
            }
        }
    }
    
    protected void zzb(final Object o, final zzsn zzsn) {
        Label_0105: {
            try {
                zzsn.zzmB(this.tag);
                switch (this.type) {
                    default: {
                        throw new IllegalArgumentException("Unknown type " + this.type);
                    }
                    case 10: {
                        break;
                    }
                    case 11: {
                        break Label_0105;
                    }
                }
            }
            catch (IOException ex) {
                throw new IllegalStateException(ex);
            }
            final zzsu zzsu = (zzsu)o;
            final int zzmJ = zzsx.zzmJ(this.tag);
            zzsn.zzb(zzsu);
            zzsn.zzE(zzmJ, 4);
            return;
        }
        zzsn.zzc((zzsu)o);
    }
    
    protected void zzc(final Object o, final zzsn zzsn) {
        for (int length = Array.getLength(o), i = 0; i < length; ++i) {
            final Object value = Array.get(o, i);
            if (value != null) {
                this.zzb(value, zzsn);
            }
        }
    }
}
