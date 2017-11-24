package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

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
