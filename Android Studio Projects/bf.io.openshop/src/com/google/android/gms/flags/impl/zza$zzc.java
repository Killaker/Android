package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

public static class zzc extends zza<Long>
{
    public static Long zza(final SharedPreferences sharedPreferences, final String s, final Long n) {
        return zzpl.zzb((Callable<Long>)new Callable<Long>() {
            public Long zzvv() {
                return sharedPreferences.getLong(s, (long)n);
            }
        });
    }
}
