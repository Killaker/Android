package com.google.android.gms.common.internal;

import android.content.*;

public abstract class zzl
{
    private static final Object zzalX;
    private static zzl zzalY;
    
    static {
        zzalX = new Object();
    }
    
    public static zzl zzau(final Context context) {
        synchronized (zzl.zzalX) {
            if (zzl.zzalY == null) {
                zzl.zzalY = new zzm(context.getApplicationContext());
            }
            return zzl.zzalY;
        }
    }
    
    public abstract boolean zza(final ComponentName p0, final ServiceConnection p1, final String p2);
    
    public abstract boolean zza(final String p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final ComponentName p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final String p0, final ServiceConnection p1, final String p2);
}
