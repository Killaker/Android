package com.google.android.gms.common.internal;

import java.util.*;

public class zzv
{
    private final String separator;
    
    private zzv(final String separator) {
        this.separator = separator;
    }
    
    public static zzv zzcL(final String s) {
        return new zzv(s);
    }
    
    public final String zza(final Iterable<?> iterable) {
        return this.zza(new StringBuilder(), iterable).toString();
    }
    
    public final StringBuilder zza(final StringBuilder sb, final Iterable<?> iterable) {
        final Iterator<?> iterator = iterable.iterator();
        if (iterator.hasNext()) {
            sb.append(this.zzx(iterator.next()));
            while (iterator.hasNext()) {
                sb.append(this.separator);
                sb.append(this.zzx(iterator.next()));
            }
        }
        return sb;
    }
    
    CharSequence zzx(final Object o) {
        if (o instanceof CharSequence) {
            return (CharSequence)o;
        }
        return o.toString();
    }
}
