package com.google.android.gms.common.stats;

import com.google.android.gms.internal.*;

public final class zzc
{
    public static zzlz<Integer> zzanx;
    public static zzlz<Integer> zzany;
    
    static {
        zzc.zzanx = zzlz.zza("gms:common:stats:max_num_of_events", Integer.valueOf(100));
        zzc.zzany = zzlz.zza("gms:common:stats:max_chunk_size", Integer.valueOf(100));
    }
    
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
    
    public static final class zzb
    {
        public static zzlz<Long> zzanE;
        public static zzlz<Integer> zzanz;
        
        static {
            zzb.zzanz = zzlz.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
            zzb.zzanE = zzlz.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000L));
        }
    }
}
