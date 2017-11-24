package com.google.android.gms.internal;

import com.google.android.gms.common.internal.*;
import java.util.*;

public final class zzmn
{
    public static <T> int zza(final T[] array, final T t) {
        int i = 0;
        int length;
        if (array != null) {
            length = array.length;
        }
        else {
            length = 0;
            i = 0;
        }
        while (i < length) {
            if (zzw.equal(array[i], t)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    public static void zza(final StringBuilder sb, final double[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Double.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final float[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Float.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final int[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Integer.toString(array[i]));
        }
    }
    
    public static void zza(final StringBuilder sb, final long[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Long.toString(array[i]));
        }
    }
    
    public static <T> void zza(final StringBuilder sb, final T[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(array[i].toString());
        }
    }
    
    public static void zza(final StringBuilder sb, final String[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("\"").append(array[i]).append("\"");
        }
    }
    
    public static void zza(final StringBuilder sb, final boolean[] array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(Boolean.toString(array[i]));
        }
    }
    
    public static Integer[] zza(final int[] array) {
        Integer[] array2;
        if (array == null) {
            array2 = null;
        }
        else {
            final int length = array.length;
            array2 = new Integer[length];
            for (int i = 0; i < length; ++i) {
                array2[i] = array[i];
            }
        }
        return array2;
    }
    
    public static <T> ArrayList<T> zzb(final T[] array) {
        final int length = array.length;
        final ArrayList list = new ArrayList<T>(length);
        for (int i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return (ArrayList<T>)list;
    }
    
    public static <T> boolean zzb(final T[] array, final T t) {
        return zza(array, t) >= 0;
    }
    
    public static <T> ArrayList<T> zzsa() {
        return new ArrayList<T>();
    }
}
