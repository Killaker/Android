package com.google.android.gms.tagmanager;

import android.app.*;
import android.os.*;
import java.util.*;

private final class zza implements Runnable
{
    private final long zzCv;
    private final long zzaNY;
    private final String zzblw;
    private final String zzblx;
    private final long zzbly;
    private long zzblz;
    
    zza(final String zzblw, final String zzblx, final long zzaNY, final long zzbly) {
        this.zzblw = zzblw;
        this.zzblx = zzblx;
        this.zzaNY = zzaNY;
        this.zzbly = zzbly;
        this.zzCv = System.currentTimeMillis();
    }
    
    @Override
    public void run() {
        if (this.zzbly > 0L && this.zzblz >= this.zzbly) {
            if (!"0".equals(this.zzblx)) {
                zzdb.zza(zzdb.this).remove(this.zzblx);
            }
            return;
        }
        ++this.zzblz;
        if (this.zzcH()) {
            final long currentTimeMillis = System.currentTimeMillis();
            zzdb.zzb(zzdb.this).push(DataLayer.mapOf("event", this.zzblw, "gtm.timerInterval", String.valueOf(this.zzaNY), "gtm.timerLimit", String.valueOf(this.zzbly), "gtm.timerStartTime", String.valueOf(this.zzCv), "gtm.timerCurrentTime", String.valueOf(currentTimeMillis), "gtm.timerElapsedTime", String.valueOf(currentTimeMillis - this.zzCv), "gtm.timerEventNumber", String.valueOf(this.zzblz), "gtm.triggers", this.zzblx));
        }
        zzdb.zzc(zzdb.this).postDelayed((Runnable)this, this.zzaNY);
    }
    
    protected boolean zzcH() {
        if (zzdb.zzd(zzdb.this)) {
            return zzdb.zze(zzdb.this);
        }
        final ActivityManager activityManager = (ActivityManager)zzdb.zzf(zzdb.this).getSystemService("activity");
        final KeyguardManager keyguardManager = (KeyguardManager)zzdb.zzf(zzdb.this).getSystemService("keyguard");
        final PowerManager powerManager = (PowerManager)zzdb.zzf(zzdb.this).getSystemService("power");
        for (final ActivityManager$RunningAppProcessInfo activityManager$RunningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            if (Process.myPid() == activityManager$RunningAppProcessInfo.pid && activityManager$RunningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && powerManager.isScreenOn()) {
                return true;
            }
        }
        return false;
    }
}
