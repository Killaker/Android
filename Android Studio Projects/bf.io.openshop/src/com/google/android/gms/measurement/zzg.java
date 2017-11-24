package com.google.android.gms.measurement;

import android.content.*;
import com.google.android.gms.common.internal.*;
import android.net.*;
import android.text.*;
import android.content.pm.*;
import com.google.android.gms.internal.*;
import java.util.*;
import com.google.android.gms.analytics.internal.*;
import android.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import android.os.*;

public final class zzg
{
    private static volatile zzg zzaUv;
    private final Context mContext;
    private volatile zzpq zzQX;
    private final List<zzh> zzaUw;
    private final com.google.android.gms.measurement.zzb zzaUx;
    private final zza zzaUy;
    private Thread.UncaughtExceptionHandler zzaUz;
    
    zzg(final Context context) {
        final Context applicationContext = context.getApplicationContext();
        zzx.zzz(applicationContext);
        this.mContext = applicationContext;
        this.zzaUy = new zza();
        this.zzaUw = new CopyOnWriteArrayList<zzh>();
        this.zzaUx = new com.google.android.gms.measurement.zzb();
    }
    
    public static zzg zzaS(final Context context) {
        zzx.zzz(context);
        Label_0034: {
            if (zzg.zzaUv != null) {
                break Label_0034;
            }
            synchronized (zzg.class) {
                if (zzg.zzaUv == null) {
                    zzg.zzaUv = new zzg(context);
                }
                return zzg.zzaUv;
            }
        }
    }
    
    private void zzb(final com.google.android.gms.measurement.zzc zzc) {
        zzx.zzcE("deliver should be called from worker thread");
        zzx.zzb(zzc.zzAz(), (Object)"Measurement must be submitted");
        final List<zzi> zzAw = zzc.zzAw();
        if (!zzAw.isEmpty()) {
            final HashSet<Uri> set = new HashSet<Uri>();
            for (final zzi zzi : zzAw) {
                final Uri zziA = zzi.zziA();
                if (!set.contains(zziA)) {
                    set.add(zziA);
                    zzi.zzb(zzc);
                }
            }
        }
    }
    
    public static void zzjk() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public zzpq zzAH() {
        Label_0133: {
            if (this.zzQX != null) {
                break Label_0133;
            }
            synchronized (this) {
                Label_0131: {
                    if (this.zzQX != null) {
                        break Label_0131;
                    }
                    final zzpq zzQX = new zzpq();
                    final PackageManager packageManager = this.mContext.getPackageManager();
                    String s = this.mContext.getPackageName();
                    zzQX.setAppId(s);
                    zzQX.setAppInstallerId(packageManager.getInstallerPackageName(s));
                    try {
                        final PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        String versionName = null;
                        if (packageInfo != null) {
                            final CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                s = applicationLabel.toString();
                            }
                            versionName = packageInfo.versionName;
                        }
                        zzQX.setAppName(s);
                        zzQX.setAppVersion(versionName);
                        this.zzQX = zzQX;
                        // monitorexit(this)
                        return this.zzQX;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        Log.e("GAv4", "Error retrieving package info: appName set to " + s);
                        final String versionName = null;
                    }
                }
            }
        }
    }
    
    public zzps zzAI() {
        final DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        final zzps zzps = new zzps();
        zzps.setLanguage(zzam.zza(Locale.getDefault()));
        zzps.zziB(displayMetrics.widthPixels);
        zzps.zziC(displayMetrics.heightPixels);
        return zzps;
    }
    
    public void zza(final Thread.UncaughtExceptionHandler zzaUz) {
        this.zzaUz = zzaUz;
    }
    
    public <V> Future<V> zzc(final Callable<V> callable) {
        zzx.zzz(callable);
        if (Thread.currentThread() instanceof zzc) {
            final FutureTask<V> futureTask = new FutureTask<V>(callable);
            futureTask.run();
            return futureTask;
        }
        return this.zzaUy.submit(callable);
    }
    
    void zze(final com.google.android.gms.measurement.zzc zzc) {
        if (zzc.zzAD()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        }
        if (zzc.zzAz()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        }
        final com.google.android.gms.measurement.zzc zzAu = zzc.zzAu();
        zzAu.zzAA();
        this.zzaUy.execute(new Runnable() {
            @Override
            public void run() {
                zzAu.zzAB().zza(zzAu);
                final Iterator<zzh> iterator = zzg.this.zzaUw.iterator();
                while (iterator.hasNext()) {
                    iterator.next().zza(zzAu);
                }
                zzg.this.zzb(zzAu);
            }
        });
    }
    
    public void zzf(final Runnable runnable) {
        zzx.zzz(runnable);
        this.zzaUy.submit(runnable);
    }
    
    private class zza extends ThreadPoolExecutor
    {
        public zza() {
            super(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
            this.setThreadFactory(new zzb());
        }
        
        @Override
        protected <T> RunnableFuture<T> newTaskFor(final Runnable runnable, final T t) {
            return new FutureTask<T>(runnable, t) {
                @Override
                protected void setException(final Throwable exception) {
                    final Thread.UncaughtExceptionHandler zzb = zzg.this.zzaUz;
                    if (zzb != null) {
                        zzb.uncaughtException(Thread.currentThread(), exception);
                    }
                    else if (Log.isLoggable("GAv4", 6)) {
                        Log.e("GAv4", "MeasurementExecutor: job failed with " + exception);
                    }
                    super.setException(exception);
                }
            };
        }
    }
    
    private static class zzb implements ThreadFactory
    {
        private static final AtomicInteger zzaUD;
        
        static {
            zzaUD = new AtomicInteger();
        }
        
        @Override
        public Thread newThread(final Runnable runnable) {
            return new zzc(runnable, "measurement-" + zzb.zzaUD.incrementAndGet());
        }
    }
    
    private static class zzc extends Thread
    {
        zzc(final Runnable runnable, final String s) {
            super(runnable, s);
        }
        
        @Override
        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }
}
