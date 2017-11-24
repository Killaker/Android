package com.google.android.gms.internal;

import android.os.*;

public final class zzmt implements zzmq
{
    private static zzmt zzaoa;
    
    public static zzmq zzsc() {
        synchronized (zzmt.class) {
            if (zzmt.zzaoa == null) {
                zzmt.zzaoa = new zzmt();
            }
            return zzmt.zzaoa;
        }
    }
    
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    @Override
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
    
    @Override
    public long nanoTime() {
        return System.nanoTime();
    }
}
