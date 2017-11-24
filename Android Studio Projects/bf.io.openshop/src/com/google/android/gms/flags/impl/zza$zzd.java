package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

public static class zzd extends zza<String>
{
    public static String zza(final SharedPreferences sharedPreferences, final String s, final String s2) {
        return zzpl.zzb((Callable<String>)new Callable<String>() {
            public String zzkp() {
                return sharedPreferences.getString(s, s2);
            }
        });
    }
}
