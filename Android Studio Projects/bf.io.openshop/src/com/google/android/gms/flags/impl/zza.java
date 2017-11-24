package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

public abstract class zza<T>
{
    public static class zza extends com.google.android.gms.flags.impl.zza<Boolean>
    {
        public static Boolean zza(final SharedPreferences sharedPreferences, final String s, final Boolean b) {
            return zzpl.zzb((Callable<Boolean>)new Callable<Boolean>() {
                public Boolean zzvt() {
                    return sharedPreferences.getBoolean(s, (boolean)b);
                }
            });
        }
    }
    
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
}
