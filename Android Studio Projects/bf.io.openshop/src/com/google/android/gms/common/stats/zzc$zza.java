package com.google.android.gms.common.stats;

import com.google.android.gms.internal.*;

public static final class zza
{
    public static zzlz<String> zzanA;
    public static zzlz<String> zzanB;
    public static zzlz<String> zzanC;
    public static zzlz<String> zzanD;
    public static zzlz<Long> zzanE;
    public static zzlz<Integer> zzanz;
    
    static {
        zza.zzanz = zzlz.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        zza.zzanA = zzlz.zzv("gms:common:stats:connections:ignored_calling_processes", "");
        zza.zzanB = zzlz.zzv("gms:common:stats:connections:ignored_calling_services", "");
        zza.zzanC = zzlz.zzv("gms:common:stats:connections:ignored_target_processes", "");
        zza.zzanD = zzlz.zzv("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
        zza.zzanE = zzlz.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
    }
}
