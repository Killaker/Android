package com.google.android.gms.analytics.internal;

public enum zzm
{
    zzRk, 
    zzRl, 
    zzRm, 
    zzRn, 
    zzRo, 
    zzRp;
    
    public static zzm zzbm(final String s) {
        if ("BATCH_BY_SESSION".equalsIgnoreCase(s)) {
            return zzm.zzRl;
        }
        if ("BATCH_BY_TIME".equalsIgnoreCase(s)) {
            return zzm.zzRm;
        }
        if ("BATCH_BY_BRUTE_FORCE".equalsIgnoreCase(s)) {
            return zzm.zzRn;
        }
        if ("BATCH_BY_COUNT".equalsIgnoreCase(s)) {
            return zzm.zzRo;
        }
        if ("BATCH_BY_SIZE".equalsIgnoreCase(s)) {
            return zzm.zzRp;
        }
        return zzm.zzRk;
    }
}
