package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

public static class zzb extends zza<Integer>
{
    public static Integer zza(final SharedPreferences sharedPreferences, final String s, final Integer n) {
        return zzpl.zzb((Callable<Integer>)new Callable<Integer>() {
            public Integer zzvu() {
                return sharedPreferences.getInt(s, (int)n);
            }
        });
    }
}
