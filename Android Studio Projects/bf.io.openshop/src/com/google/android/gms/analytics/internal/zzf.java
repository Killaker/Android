package com.google.android.gms.analytics.internal;

import android.content.*;
import com.google.android.gms.measurement.*;
import com.google.android.gms.analytics.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import com.google.android.gms.internal.*;

public class zzf
{
    private static zzf zzQn;
    private final Context mContext;
    private final zzu zzQA;
    private final Context zzQo;
    private final zzr zzQp;
    private final zzaf zzQq;
    private final zzg zzQr;
    private final zzb zzQs;
    private final zzv zzQt;
    private final zzan zzQu;
    private final zzai zzQv;
    private final GoogleAnalytics zzQw;
    private final zzn zzQx;
    private final zza zzQy;
    private final zzk zzQz;
    private final zzmq zzqW;
    
    protected zzf(final com.google.android.gms.analytics.internal.zzg zzg) {
        final Context applicationContext = zzg.getApplicationContext();
        zzx.zzb(applicationContext, "Application context can't be null");
        zzx.zzb(applicationContext instanceof Application, (Object)"getApplicationContext didn't return the application");
        final Context zzjx = zzg.zzjx();
        zzx.zzz(zzjx);
        this.mContext = applicationContext;
        this.zzQo = zzjx;
        this.zzqW = zzg.zzh(this);
        this.zzQp = zzg.zzg(this);
        final zzaf zzf = zzg.zzf(this);
        zzf.zza();
        this.zzQq = zzf;
        if (this.zzjn().zzkr()) {
            this.zzjm().zzbf("Google Analytics " + zze.VERSION + " is starting up.");
        }
        else {
            this.zzjm().zzbf("Google Analytics " + zze.VERSION + " is starting up. " + "To enable debug logging on a device run:\n" + "  adb shell setprop log.tag.GAv4 DEBUG\n" + "  adb logcat -s GAv4");
        }
        final zzai zzq = zzg.zzq(this);
        zzq.zza();
        this.zzQv = zzq;
        final zzan zze = zzg.zze(this);
        zze.zza();
        this.zzQu = zze;
        final zzb zzl = zzg.zzl(this);
        final zzn zzd = zzg.zzd(this);
        final zza zzc = zzg.zzc(this);
        final zzk zzb = zzg.zzb(this);
        final zzu zza = zzg.zza(this);
        final zzg zzab = zzg.zzab(applicationContext);
        zzab.zza(this.zzjw());
        this.zzQr = zzab;
        final GoogleAnalytics zzi = zzg.zzi(this);
        zzd.zza();
        this.zzQx = zzd;
        zzc.zza();
        this.zzQy = zzc;
        zzb.zza();
        this.zzQz = zzb;
        zza.zza();
        this.zzQA = zza;
        final zzv zzp = zzg.zzp(this);
        zzp.zza();
        this.zzQt = zzp;
        zzl.zza();
        this.zzQs = zzl;
        if (this.zzjn().zzkr()) {
            this.zzjm().zzb("Device AnalyticsService version", com.google.android.gms.analytics.internal.zze.VERSION);
        }
        zzi.zza();
        this.zzQw = zzi;
        zzl.start();
    }
    
    private void zza(final zzd zzd) {
        zzx.zzb(zzd, "Analytics service not created/initialized");
        zzx.zzb(zzd.isInitialized(), (Object)"Analytics service not initialized");
    }
    
    public static zzf zzaa(final Context context) {
        zzx.zzz(context);
        Label_0117: {
            if (zzf.zzQn != null) {
                break Label_0117;
            }
            synchronized (zzf.class) {
                if (zzf.zzQn == null) {
                    final zzmq zzsc = zzmt.zzsc();
                    final long elapsedRealtime = zzsc.elapsedRealtime();
                    final zzf zzf = com.google.android.gms.analytics.internal.zzf.zzQn = new zzf(new com.google.android.gms.analytics.internal.zzg(context.getApplicationContext()));
                    GoogleAnalytics.zziF();
                    final long n = zzsc.elapsedRealtime() - elapsedRealtime;
                    final long longValue = zzy.zzSz.get();
                    if (n > longValue) {
                        zzf.zzjm().zzc("Slow initialization (ms)", n, longValue);
                    }
                }
                return zzf.zzQn;
            }
        }
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public zzb zziH() {
        this.zza(this.zzQs);
        return this.zzQs;
    }
    
    public zzan zziI() {
        this.zza(this.zzQu);
        return this.zzQu;
    }
    
    public zzai zzjA() {
        if (this.zzQv == null || !this.zzQv.isInitialized()) {
            return null;
        }
        return this.zzQv;
    }
    
    public zza zzjB() {
        this.zza(this.zzQy);
        return this.zzQy;
    }
    
    public zzn zzjC() {
        this.zza(this.zzQx);
        return this.zzQx;
    }
    
    public void zzjk() {
        zzg.zzjk();
    }
    
    public zzmq zzjl() {
        return this.zzqW;
    }
    
    public zzaf zzjm() {
        this.zza(this.zzQq);
        return this.zzQq;
    }
    
    public zzr zzjn() {
        return this.zzQp;
    }
    
    public zzg zzjo() {
        zzx.zzz(this.zzQr);
        return this.zzQr;
    }
    
    public zzv zzjp() {
        this.zza(this.zzQt);
        return this.zzQt;
    }
    
    public zzai zzjq() {
        this.zza(this.zzQv);
        return this.zzQv;
    }
    
    public zzk zzjt() {
        this.zza(this.zzQz);
        return this.zzQz;
    }
    
    public zzu zzju() {
        return this.zzQA;
    }
    
    protected Thread.UncaughtExceptionHandler zzjw() {
        return new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread thread, final Throwable t) {
                final zzaf zzjy = zzf.this.zzjy();
                if (zzjy != null) {
                    zzjy.zze("Job execution failed", t);
                }
            }
        };
    }
    
    public Context zzjx() {
        return this.zzQo;
    }
    
    public zzaf zzjy() {
        return this.zzQq;
    }
    
    public GoogleAnalytics zzjz() {
        zzx.zzz(this.zzQw);
        zzx.zzb(this.zzQw.isInitialized(), (Object)"Analytics instance not initialized");
        return this.zzQw;
    }
}
