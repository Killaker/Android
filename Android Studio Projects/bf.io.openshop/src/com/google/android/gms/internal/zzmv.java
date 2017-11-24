package com.google.android.gms.internal;

import android.content.*;
import android.annotation.*;
import android.os.*;

public final class zzmv
{
    private static IntentFilter zzaob;
    private static long zzaoc;
    private static float zzaod;
    
    static {
        zzmv.zzaob = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        zzmv.zzaod = Float.NaN;
    }
    
    @TargetApi(20)
    public static int zzax(final Context context) {
        int n = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzmv.zzaob);
        int intExtra;
        if (registerReceiver == null) {
            intExtra = 0;
        }
        else {
            intExtra = registerReceiver.getIntExtra("plugged", 0);
        }
        int n2;
        if ((intExtra & 0x7) != 0x0) {
            n2 = n;
        }
        else {
            n2 = 0;
        }
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        boolean b;
        if (zzne.zzsl()) {
            b = powerManager.isInteractive();
        }
        else {
            b = powerManager.isScreenOn();
        }
        int n3;
        if (b) {
            n3 = n;
        }
        else {
            n3 = 0;
        }
        final int n4 = n3 << 1;
        if (n2 == 0) {
            n = 0;
        }
        return n4 | n;
    }
    
    public static float zzay(final Context context) {
        synchronized (zzmv.class) {
            float n;
            if (SystemClock.elapsedRealtime() - zzmv.zzaoc < 60000L && zzmv.zzaod != Float.NaN) {
                n = zzmv.zzaod;
            }
            else {
                final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zzmv.zzaob);
                if (registerReceiver != null) {
                    zzmv.zzaod = registerReceiver.getIntExtra("level", -1) / registerReceiver.getIntExtra("scale", -1);
                }
                zzmv.zzaoc = SystemClock.elapsedRealtime();
                n = zzmv.zzaod;
            }
            return n;
        }
    }
}
