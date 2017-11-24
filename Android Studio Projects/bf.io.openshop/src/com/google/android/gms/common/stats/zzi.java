package com.google.android.gms.common.stats;

import java.util.*;
import android.text.*;
import android.util.*;
import com.google.android.gms.internal.*;
import android.content.*;
import android.os.*;

public class zzi
{
    private static String TAG;
    private static zzi zzanY;
    private static Integer zzanv;
    
    static {
        zzi.TAG = "WakeLockTracker";
        zzi.zzanY = new zzi();
    }
    
    private static int getLogLevel() {
        try {
            if (zzmp.zzkr()) {
                return zzc.zzb.zzanz.get();
            }
            return zzd.LOG_LEVEL_OFF;
        }
        catch (SecurityException ex) {
            return zzd.LOG_LEVEL_OFF;
        }
    }
    
    private static boolean zzav(final Context context) {
        if (zzi.zzanv == null) {
            zzi.zzanv = getLogLevel();
        }
        return zzi.zzanv != zzd.LOG_LEVEL_OFF;
    }
    
    public static zzi zzrZ() {
        return zzi.zzanY;
    }
    
    public void zza(final Context context, final String s, final int n, final String s2, final String s3, final int n2, final List<String> list) {
        this.zza(context, s, n, s2, s3, n2, list, 0L);
    }
    
    public void zza(final Context context, final String s, final int n, final String s2, final String s3, final int n2, final List<String> list, final long n3) {
        if (zzav(context)) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                Log.e(zzi.TAG, "missing wakeLock key. " + s);
                return;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (7 == n || 8 == n || 10 == n || 11 == n) {
                final WakeLockEvent wakeLockEvent = new WakeLockEvent(currentTimeMillis, n, s2, n2, list, s, SystemClock.elapsedRealtime(), zzmv.zzax(context), s3, context.getPackageName(), zzmv.zzay(context), n3);
                try {
                    context.startService(new Intent().setComponent(zzd.zzanF).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)wakeLockEvent));
                }
                catch (Exception ex) {
                    Log.wtf(zzi.TAG, (Throwable)ex);
                }
            }
        }
    }
}
