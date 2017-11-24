package com.google.android.gms.internal;

import android.support.v4.util.*;
import java.util.*;

public final class zzmr
{
    public static <T> Set<T> zzA(final T t) {
        return Collections.singleton(t);
    }
    
    public static <K, V> Map<K, V> zza(final K k, final V v, final K i, final V v2, final K j, final V v3, final K l, final V v4, final K m, final V v5, final K k2, final V v6) {
        final ArrayMap<K, V> arrayMap = new ArrayMap<K, V>(6);
        arrayMap.put(k, v);
        arrayMap.put(i, v2);
        arrayMap.put(j, v3);
        arrayMap.put(l, v4);
        arrayMap.put(m, v5);
        arrayMap.put(k2, v6);
        return Collections.unmodifiableMap((Map<? extends K, ? extends V>)arrayMap);
    }
    
    public static <T> Set<T> zza(final T t, final T t2, final T t3) {
        final zzmm<T> zzmm = new zzmm<T>(3);
        zzmm.add(t);
        zzmm.add(t2);
        zzmm.add(t3);
        return Collections.unmodifiableSet((Set<? extends T>)zzmm);
    }
    
    public static <T> Set<T> zza(final T t, final T t2, final T t3, final T t4) {
        final zzmm<T> zzmm = new zzmm<T>(4);
        zzmm.add(t);
        zzmm.add(t2);
        zzmm.add(t3);
        zzmm.add(t4);
        return Collections.unmodifiableSet((Set<? extends T>)zzmm);
    }
    
    public static <T> List<T> zzc(final T t, final T t2) {
        final ArrayList<T> list = new ArrayList<T>(2);
        list.add(t);
        list.add(t2);
        return Collections.unmodifiableList((List<? extends T>)list);
    }
    
    public static <T> Set<T> zzc(final T... array) {
        switch (array.length) {
            default: {
                Object o;
                if (array.length <= 32) {
                    o = new zzmm<T>((Collection<? extends T>)Arrays.asList(array));
                }
                else {
                    o = new HashSet<T>((Collection<? extends T>)Arrays.asList(array));
                }
                return Collections.unmodifiableSet((Set<? extends T>)o);
            }
            case 0: {
                return zzsb();
            }
            case 1: {
                return zzA(array[0]);
            }
            case 2: {
                return zzd(array[0], array[1]);
            }
            case 3: {
                return zza(array[0], array[1], array[2]);
            }
            case 4: {
                return zza(array[0], array[1], array[2], array[3]);
            }
        }
    }
    
    public static <T> Set<T> zzd(final T t, final T t2) {
        final zzmm<T> zzmm = new zzmm<T>(2);
        zzmm.add(t);
        zzmm.add(t2);
        return Collections.unmodifiableSet((Set<? extends T>)zzmm);
    }
    
    public static <T> Set<T> zzsb() {
        return Collections.emptySet();
    }
}
