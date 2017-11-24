package com.google.android.gms.measurement;

import android.util.*;
import android.net.*;
import android.text.*;
import java.util.*;
import java.io.*;

public final class zzb implements zzi
{
    private static final Uri zzaUf;
    private final LogPrinter zzaUg;
    
    static {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("uri");
        uri$Builder.authority("local");
        zzaUf = uri$Builder.build();
    }
    
    public zzb() {
        this.zzaUg = new LogPrinter(4, "GA/LogCatTransport");
    }
    
    @Override
    public void zzb(final zzc zzc) {
        final ArrayList<Object> list = new ArrayList<Object>(zzc.zzAv());
        Collections.sort(list, (Comparator<? super Object>)new Comparator<zze>() {
            public int zza(final zze zze, final zze zze2) {
                return zze.getClass().getCanonicalName().compareTo(zze2.getClass().getCanonicalName());
            }
        });
        final StringBuilder sb = new StringBuilder();
        final Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            final String string = ((zze<?>)iterator.next()).toString();
            if (!TextUtils.isEmpty((CharSequence)string)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(string);
            }
        }
        this.zzaUg.println(sb.toString());
    }
    
    @Override
    public Uri zziA() {
        return zzb.zzaUf;
    }
}
