package com.google.android.gms.measurement.internal;

import android.annotation.*;
import android.support.annotation.*;
import android.text.*;
import android.app.*;
import android.os.*;
import android.content.*;
import android.net.*;

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
