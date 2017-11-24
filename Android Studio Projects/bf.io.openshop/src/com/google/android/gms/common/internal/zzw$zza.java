package com.google.android.gms.common.internal;

import java.util.*;

public static final class zza
{
    private final Object zzML;
    private final List<String> zzamp;
    
    private zza(final Object o) {
        this.zzML = zzx.zzz(o);
        this.zzamp = new ArrayList<String>();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder(100).append(this.zzML.getClass().getSimpleName()).append('{');
        for (int size = this.zzamp.size(), i = 0; i < size; ++i) {
            append.append(this.zzamp.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
    
    public zza zzg(final String s, final Object o) {
        this.zzamp.add(zzx.zzz(s) + "=" + String.valueOf(o));
        return this;
    }
}
