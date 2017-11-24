package com.google.android.gms.clearcut;

import android.content.*;

public class zza
{
    private static int zzaeO;
    public static final zza zzaeP;
    
    static {
        zza.zzaeO = -1;
        zzaeP = new zza();
    }
    
    public int zzah(final Context context) {
        if (zza.zzaeO < 0) {
            zza.zzaeO = context.getSharedPreferences("bootCount", 0).getInt("bootCount", 1);
        }
        return zza.zzaeO;
    }
}
