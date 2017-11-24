package com.google.android.gms.analytics;

import android.annotation.*;
import android.app.*;
import android.os.*;

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
