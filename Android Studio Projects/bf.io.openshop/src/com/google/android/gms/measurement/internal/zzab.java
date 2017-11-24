package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.*;
import android.os.*;
import com.google.android.gms.common.internal.*;
import java.util.*;
import android.annotation.*;
import android.text.*;
import com.google.android.gms.internal.*;
import android.support.annotation.*;
import android.app.*;
import android.content.*;
import android.net.*;

public class zzab extends zzz
{
    private zza zzaYD;
    private AppMeasurement.zza zzaYE;
    private boolean zzaYF;
    
    protected zzab(final zzw zzw) {
        super(zzw);
    }
    
    @WorkerThread
    private void zzDm() {
        try {
            this.zzh(Class.forName(this.zzDn()));
        }
        catch (ClassNotFoundException ex) {
            this.zzAo().zzCI().zzfg("Tag Manager is not found and thus will not be used");
        }
    }
    
    private String zzDn() {
        return "com.google.android.gms.tagmanager.TagManagerService";
    }
    
    private void zza(final String s, final String s2, final Bundle bundle, final boolean b, final String s3) {
        this.zza(s, s2, bundle, b, s3, this.zzjl().currentTimeMillis());
    }
    
    private void zza(final String s, final String s2, final Bundle bundle, final boolean b, final String s3, final long n) {
        zzx.zzcM(s);
        this.zzCk().zzfr(s2);
        final Bundle bundle2 = new Bundle();
        if (bundle != null) {
            final int zzBA = this.zzCp().zzBA();
            final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
            int n2 = 0;
            while (iterator.hasNext()) {
                final String s4 = iterator.next();
                this.zzCk().zzft(s4);
                if (zzaj.zzfq(s4)) {
                    final int n3 = n2 + 1;
                    zzx.zzb(n3 <= zzBA, (Object)("Event can't contain more then " + zzBA + " params"));
                    n2 = n3;
                }
                final Object zzk = this.zzCk().zzk(s4, bundle.get(s4));
                if (zzk != null) {
                    this.zzCk().zza(bundle2, s4, zzk);
                }
            }
        }
        final int zzBD = this.zzCp().zzBD();
        String substring;
        if (s.length() <= zzBD) {
            substring = s;
        }
        else {
            substring = s.substring(0, zzBD);
        }
        bundle2.putString("_o", substring);
        this.zza(s, s2, n, bundle2, b, s3);
    }
    
    @WorkerThread
    private void zza(final String s, final String s2, final Object o, final long n) {
        zzx.zzcM(s);
        zzx.zzcM(s2);
        this.zzjk();
        this.zzjj();
        this.zzjv();
        if (!this.zzCo().zzAr()) {
            this.zzAo().zzCJ().zzfg("User property not set since app measurement is disabled");
        }
        else if (this.zzaTV.zzCS()) {
            this.zzAo().zzCJ().zze("Setting user property (FE)", s2, o);
            this.zzCi().zza(new UserAttributeParcel(s2, n, o, s));
        }
    }
    
    @WorkerThread
    private void zzas(final boolean measurementEnabled) {
        this.zzjk();
        this.zzjj();
        this.zzjv();
        this.zzAo().zzCJ().zzj("Setting app measurement enabled (FE)", measurementEnabled);
        this.zzCo().setMeasurementEnabled(measurementEnabled);
        this.zzCi().zzDo();
    }
    
    @WorkerThread
    private void zzb(final String s, final String s2, final long n, final Bundle bundle, final boolean b, final String s3) {
        zzx.zzcM(s);
        zzx.zzcM(s2);
        zzx.zzz(bundle);
        this.zzjk();
        this.zzjv();
        if (!this.zzCo().zzAr()) {
            this.zzAo().zzCJ().zzfg("Event not sent since app measurement is disabled");
        }
        else {
            if (!this.zzaYF) {
                this.zzaYF = true;
                this.zzDm();
            }
            if (b && this.zzaYE != null && !zzaj.zzfv(s2)) {
                this.zzAo().zzCJ().zze("Passing event to registered event handler (FE)", s2, bundle);
                this.zzaYE.zza(s, s2, bundle, n);
                return;
            }
            if (this.zzaTV.zzCS()) {
                this.zzAo().zzCJ().zze("Logging event (FE)", s2, bundle);
                this.zzCi().zzb(new EventParcel(s2, new EventParams(bundle), s, n), s3);
            }
        }
    }
    
    public void setMeasurementEnabled(final boolean b) {
        this.zzjv();
        this.zzjj();
        this.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzab.this.zzas(b);
            }
        });
    }
    
    @TargetApi(14)
    public void zzDk() {
        if (this.getContext().getApplicationContext() instanceof Application) {
            final Application application = (Application)this.getContext().getApplicationContext();
            if (this.zzaYD == null) {
                this.zzaYD = new zza();
            }
            application.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zzaYD);
            application.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zzaYD);
            this.zzAo().zzCK().zzfg("Registered activity lifecycle callback");
        }
    }
    
    @WorkerThread
    public void zzDl() {
        this.zzjk();
        this.zzjj();
        this.zzjv();
        if (this.zzaTV.zzCS()) {
            this.zzCi().zzDl();
            final String zzCQ = this.zzCo().zzCQ();
            if (!TextUtils.isEmpty((CharSequence)zzCQ) && !zzCQ.equals(this.zzCh().zzCy())) {
                final Bundle bundle = new Bundle();
                bundle.putString("_po", zzCQ);
                this.zze("auto", "_ou", bundle);
            }
        }
    }
    
    protected void zza(final String s, final String s2, final long n, final Bundle bundle, final boolean b, final String s3) {
        zzx.zzz(bundle);
        this.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzab.this.zzb(s, s2, n, bundle, b, s3);
            }
        });
    }
    
    void zza(final String s, final String s2, final long n, final Object o) {
        this.zzCn().zzg(new Runnable() {
            @Override
            public void run() {
                zzab.this.zza(s, s2, o, n);
            }
        });
    }
    
    public void zza(final String s, final String s2, final Object o) {
        zzx.zzcM(s);
        final long currentTimeMillis = this.zzjl().currentTimeMillis();
        this.zzCk().zzfs(s2);
        if (o != null) {
            this.zzCk().zzl(s2, o);
            final Object zzm = this.zzCk().zzm(s2, o);
            if (zzm != null) {
                this.zza(s, s2, currentTimeMillis, zzm);
            }
            return;
        }
        this.zza(s, s2, currentTimeMillis, null);
    }
    
    public void zze(final String s, final String s2, final Bundle bundle) {
        this.zzjj();
        this.zza(s, s2, bundle, true, null);
    }
    
    @WorkerThread
    public void zzh(final Class<?> clazz) {
        try {
            clazz.getDeclaredMethod("initialize", Context.class).invoke(null, this.getContext());
        }
        catch (Exception ex) {
            this.zzAo().zzCF().zzj("Failed to invoke Tag Manager's initialize() method", ex);
        }
    }
    
    @Override
    protected void zziJ() {
    }
    
    @TargetApi(14)
    @MainThread
    private class zza implements Application$ActivityLifecycleCallbacks
    {
        private boolean zzfo(final String s) {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                zzab.this.zza("auto", "_ldl", s);
                return true;
            }
            return false;
        }
        
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            String queryParameter;
            try {
                zzab.this.zzAo().zzCK().zzfg("onActivityCreated");
                final Intent intent = activity.getIntent();
                if (intent == null) {
                    return;
                }
                final Uri data = intent.getData();
                if (data == null || !data.isHierarchical()) {
                    return;
                }
                queryParameter = data.getQueryParameter("referrer");
                if (TextUtils.isEmpty((CharSequence)queryParameter)) {
                    return;
                }
                if (!queryParameter.contains("gclid")) {
                    zzab.this.zzAo().zzCJ().zzfg("Activity created with data 'referrer' param without gclid");
                    return;
                }
            }
            catch (Throwable t) {
                zzab.this.zzAo().zzCE().zzj("Throwable caught in onActivityCreated", t);
                return;
            }
            zzab.this.zzAo().zzCJ().zzj("Activity created with referrer", queryParameter);
            this.zzfo(queryParameter);
        }
        
        public void onActivityDestroyed(final Activity activity) {
        }
        
        @MainThread
        public void onActivityPaused(final Activity activity) {
            zzab.this.zzCm().zzDw();
        }
        
        @MainThread
        public void onActivityResumed(final Activity activity) {
            zzab.this.zzCm().zzDu();
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
        }
        
        public void onActivityStopped(final Activity activity) {
        }
    }
}
