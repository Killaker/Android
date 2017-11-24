package com.google.android.gms.analytics.internal;

public enum zzo
{
    zzRu, 
    zzRv;
    
    public static zzo zzbn(final String s) {
        if ("GZIP".equalsIgnoreCase(s)) {
            return zzo.zzRv;
        }
        return zzo.zzRu;
    }
}
