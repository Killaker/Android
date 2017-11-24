package com.google.android.gms.analytics;

import android.content.*;
import android.support.annotation.*;
import java.util.*;
import android.annotation.*;
import com.google.android.gms.common.internal.*;
import android.app.*;
import com.google.android.gms.analytics.internal.*;
import android.util.*;
import android.os.*;

public final class GoogleAnalytics extends com.google.android.gms.analytics.zza
{
    private static List<Runnable> zzPe;
    private boolean zzPf;
    private Set<zza> zzPg;
    private boolean zzPh;
    private boolean zzPi;
    private volatile boolean zzPj;
    private boolean zzPk;
    private boolean zzqA;
    
    static {
        GoogleAnalytics.zzPe = new ArrayList<Runnable>();
    }
    
    public GoogleAnalytics(final com.google.android.gms.analytics.internal.zzf zzf) {
        super(zzf);
        this.zzPg = new HashSet<zza>();
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public static GoogleAnalytics getInstance(final Context context) {
        return com.google.android.gms.analytics.internal.zzf.zzaa(context).zzjz();
    }
    
    public static void zziF() {
        Label_0054: {
            synchronized (GoogleAnalytics.class) {
                if (GoogleAnalytics.zzPe == null) {
                    break Label_0054;
                }
                final Iterator<Runnable> iterator = GoogleAnalytics.zzPe.iterator();
                while (iterator.hasNext()) {
                    iterator.next().run();
                }
            }
            GoogleAnalytics.zzPe = null;
        }
    }
    // monitorexit(GoogleAnalytics.class)
    
    private com.google.android.gms.analytics.internal.zzb zziH() {
        return this.zzix().zziH();
    }
    
    private zzan zziI() {
        return this.zzix().zziI();
    }
    
    public void dispatchLocalHits() {
        this.zziH().zzjd();
    }
    
    @TargetApi(14)
    public void enableAutoActivityReports(final Application application) {
        if (Build$VERSION.SDK_INT >= 14 && !this.zzPh) {
            application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new zzb());
            this.zzPh = true;
        }
    }
    
    public boolean getAppOptOut() {
        return this.zzPj;
    }
    
    public String getClientId() {
        zzx.zzcE("getClientId can not be called from the main thread");
        return this.zzix().zzjC().zzkk();
    }
    
    @Deprecated
    public Logger getLogger() {
        return zzae.getLogger();
    }
    
    public boolean isDryRunEnabled() {
        return this.zzPi;
    }
    
    public boolean isInitialized() {
        return this.zzqA && !this.zzPf;
    }
    
    public Tracker newTracker(final int n) {
        synchronized (this) {
            final Tracker tracker = new Tracker(this.zzix(), null, null);
            if (n > 0) {
                final zzal zzal = new zzak(this.zzix()).zzah(n);
                if (zzal != null) {
                    tracker.zza(zzal);
                }
            }
            tracker.zza();
            return tracker;
        }
    }
    
    public Tracker newTracker(final String s) {
        synchronized (this) {
            final Tracker tracker = new Tracker(this.zzix(), s, null);
            tracker.zza();
            return tracker;
        }
    }
    
    public void reportActivityStart(final Activity activity) {
        if (!this.zzPh) {
            this.zzj(activity);
        }
    }
    
    public void reportActivityStop(final Activity activity) {
        if (!this.zzPh) {
            this.zzk(activity);
        }
    }
    
    public void setAppOptOut(final boolean zzPj) {
        this.zzPj = zzPj;
        if (this.zzPj) {
            this.zziH().zzjc();
        }
    }
    
    public void setDryRun(final boolean zzPi) {
        this.zzPi = zzPi;
    }
    
    public void setLocalDispatchPeriod(final int localDispatchPeriod) {
        this.zziH().setLocalDispatchPeriod(localDispatchPeriod);
    }
    
    @Deprecated
    public void setLogger(final Logger logger) {
        zzae.setLogger(logger);
        if (!this.zzPk) {
            Log.i((String)zzy.zzRL.get(), "GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzy.zzRL.get() + " DEBUG");
            this.zzPk = true;
        }
    }
    
    public void zza() {
        this.zziE();
        this.zzqA = true;
    }
    
    void zza(final zza zza) {
        this.zzPg.add(zza);
        final Context context = this.zzix().getContext();
        if (context instanceof Application) {
            this.enableAutoActivityReports((Application)context);
        }
    }
    
    void zzb(final zza zza) {
        this.zzPg.remove(zza);
    }
    
    void zziE() {
        final zzan zziI = this.zziI();
        if (zziI.zzlj()) {
            this.getLogger().setLogLevel(zziI.getLogLevel());
        }
        if (zziI.zzln()) {
            this.setDryRun(zziI.zzlo());
        }
        if (zziI.zzlj()) {
            final Logger logger = zzae.getLogger();
            if (logger != null) {
                logger.setLogLevel(zziI.getLogLevel());
            }
        }
    }
    
    void zziG() {
        this.zziH().zzje();
    }
    
    void zzj(final Activity activity) {
        final Iterator<zza> iterator = this.zzPg.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzl(activity);
        }
    }
    
    void zzk(final Activity activity) {
        final Iterator<zza> iterator = this.zzPg.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzm(activity);
        }
    }
    
    interface zza
    {
        void zzl(final Activity p0);
        
        void zzm(final Activity p0);
    }
    
    @TargetApi(14)
    class zzb implements Application$ActivityLifecycleCallbacks
    {
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        public void onActivityPaused(final Activity activity) {
        }
        
        public void onActivityResumed(final Activity activity) {
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            GoogleAnalytics.this.zzj(activity);
        }
        
        public void onActivityStopped(final Activity activity) {
            GoogleAnalytics.this.zzk(activity);
        }
    }
}
