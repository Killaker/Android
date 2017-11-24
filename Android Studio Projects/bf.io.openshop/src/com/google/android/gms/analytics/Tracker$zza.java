package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.*;
import android.app.*;
import android.text.*;
import java.util.*;
import android.content.*;

private class zza extends zzd implements GoogleAnalytics.zza
{
    private boolean zzPG;
    private int zzPH;
    private long zzPI;
    private boolean zzPJ;
    private long zzPK;
    
    protected zza(final com.google.android.gms.analytics.internal.zzf zzf) {
        super(zzf);
        this.zzPI = -1L;
    }
    
    private void zziN() {
        if (this.zzPI >= 0L || this.zzPG) {
            this.zziC().zza((GoogleAnalytics.zza)Tracker.zza(Tracker.this));
            return;
        }
        this.zziC().zzb((GoogleAnalytics.zza)Tracker.zza(Tracker.this));
    }
    
    public void enableAutoActivityTracking(final boolean zzPG) {
        this.zzPG = zzPG;
        this.zziN();
    }
    
    public void setSessionTimeout(final long zzPI) {
        this.zzPI = zzPI;
        this.zziN();
    }
    
    @Override
    protected void zziJ() {
    }
    
    public boolean zziM() {
        synchronized (this) {
            final boolean zzPJ = this.zzPJ;
            this.zzPJ = false;
            return zzPJ;
        }
    }
    
    boolean zziO() {
        return this.zzjl().elapsedRealtime() >= this.zzPK + Math.max(1000L, this.zzPI);
    }
    
    @Override
    public void zzl(final Activity activity) {
        if (this.zzPH == 0 && this.zziO()) {
            this.zzPJ = true;
        }
        ++this.zzPH;
        if (this.zzPG) {
            final Intent intent = activity.getIntent();
            if (intent != null) {
                Tracker.this.setCampaignParamsOnNextHit(intent.getData());
            }
            final HashMap<Object, CharSequence> hashMap = new HashMap<Object, CharSequence>();
            hashMap.put("&t", "screenview");
            final Tracker zzPF = Tracker.this;
            String s;
            if (Tracker.zzk(Tracker.this) != null) {
                s = Tracker.zzk(Tracker.this).zzo(activity);
            }
            else {
                s = activity.getClass().getCanonicalName();
            }
            zzPF.set("&cd", s);
            if (TextUtils.isEmpty((CharSequence)hashMap.get("&dr"))) {
                final String zzn = Tracker.zzn(activity);
                if (!TextUtils.isEmpty((CharSequence)zzn)) {
                    hashMap.put("&dr", zzn);
                }
            }
            Tracker.this.send((Map<String, String>)hashMap);
        }
    }
    
    @Override
    public void zzm(final Activity activity) {
        --this.zzPH;
        this.zzPH = Math.max(0, this.zzPH);
        if (this.zzPH == 0) {
            this.zzPK = this.zzjl().elapsedRealtime();
        }
    }
}
