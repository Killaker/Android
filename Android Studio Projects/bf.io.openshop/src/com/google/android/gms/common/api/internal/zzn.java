package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.*;
import android.support.annotation.*;
import android.content.*;
import android.net.*;

abstract class zzn extends BroadcastReceiver
{
    protected Context mContext;
    
    @Nullable
    public static <T extends zzn> T zza(final Context context, final T t) {
        return zza(context, t, zzc.zzoK());
    }
    
    @Nullable
    public static <T extends zzn> T zza(final Context mContext, T t, final zzc zzc) {
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        mContext.registerReceiver((BroadcastReceiver)t, intentFilter);
        t.mContext = mContext;
        if (!zzc.zzi(mContext, "com.google.android.gms")) {
            t.zzpJ();
            t.unregister();
            t = null;
        }
        return t;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final Uri data = intent.getData();
        Object schemeSpecificPart = null;
        if (data != null) {
            schemeSpecificPart = data.getSchemeSpecificPart();
        }
        if ("com.google.android.gms".equals(schemeSpecificPart)) {
            this.zzpJ();
            this.unregister();
        }
    }
    
    public void unregister() {
        synchronized (this) {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver((BroadcastReceiver)this);
            }
            this.mContext = null;
        }
    }
    
    protected abstract void zzpJ();
}
