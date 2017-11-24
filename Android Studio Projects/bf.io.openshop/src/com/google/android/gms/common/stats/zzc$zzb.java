package com.google.android.gms.common.stats;

import com.google.android.gms.internal.*;

public static final class zzb
{
    public static zzlz<Long> zzanE;
    public static zzlz<Integer> zzanz;
    
    static {
        zzb.zzanz = zzlz.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        zzb.zzanE = zzlz.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000L));
    }
}
