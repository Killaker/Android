package com.google.android.gms.flags.impl;

import android.content.*;
import java.util.concurrent.*;
import com.google.android.gms.internal.*;

public class zzb
{
    private static SharedPreferences zzaBZ;
    
    static {
        zzb.zzaBZ = null;
    }
    
    public static SharedPreferences zzw(final Context context) {
        synchronized (SharedPreferences.class) {
            if (zzb.zzaBZ == null) {
                zzb.zzaBZ = zzpl.zzb((Callable<SharedPreferences>)new Callable<SharedPreferences>() {
                    public SharedPreferences zzvw() {
                        return context.getSharedPreferences("google_sdk_flags", 1);
                    }
                });
            }
            return zzb.zzaBZ;
        }
    }
}
