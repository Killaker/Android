package com.google.android.gms.analytics;

import android.app.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import com.google.android.gms.analytics.internal.*;

public final class AnalyticsService extends Service
{
    private static Boolean zzOO;
    private final Handler mHandler;
    
    public AnalyticsService() {
        this.mHandler = new Handler();
    }
    
    public static boolean zzZ(final Context context) {
        zzx.zzz(context);
        if (AnalyticsService.zzOO != null) {
            return AnalyticsService.zzOO;
        }
        final boolean zza = zzam.zza(context, AnalyticsService.class);
        AnalyticsService.zzOO = zza;
        return zza;
    }
    
    private void zziz() {
        try {
            synchronized (AnalyticsReceiver.zzqy) {
                final zzrp zzOM = AnalyticsReceiver.zzOM;
                if (zzOM != null && zzOM.isHeld()) {
                    zzOM.release();
                }
            }
        }
        catch (SecurityException ex) {}
    }
    
    public IBinder onBind(final Intent intent) {
        return null;
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public void onCreate() {
        super.onCreate();
        final zzf zzaa = zzf.zzaa((Context)this);
        final zzaf zzjm = zzaa.zzjm();
        if (zzaa.zzjn().zzkr()) {
            zzjm.zzbd("Device AnalyticsService is starting up");
            return;
        }
        zzjm.zzbd("Local AnalyticsService is starting up");
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public void onDestroy() {
        final zzf zzaa = zzf.zzaa((Context)this);
        final zzaf zzjm = zzaa.zzjm();
        if (zzaa.zzjn().zzkr()) {
            zzjm.zzbd("Device AnalyticsService is shutting down");
        }
        else {
            zzjm.zzbd("Local AnalyticsService is shutting down");
        }
        super.onDestroy();
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.zziz();
        final zzf zzaa = zzf.zzaa((Context)this);
        final zzaf zzjm = zzaa.zzjm();
        final String action = intent.getAction();
        if (zzaa.zzjn().zzkr()) {
            zzjm.zza("Device AnalyticsService called. startId, action", n2, action);
        }
        else {
            zzjm.zza("Local AnalyticsService called. startId, action", n2, action);
        }
        if ("com.google.android.gms.analytics.ANALYTICS_DISPATCH".equals(action)) {
            zzaa.zziH().zza(new zzw() {
                @Override
                public void zzc(final Throwable t) {
                    AnalyticsService.this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (AnalyticsService.this.stopSelfResult(n2)) {
                                if (!zzaa.zzjn().zzkr()) {
                                    zzjm.zzbd("Local AnalyticsService processed last dispatch request");
                                    return;
                                }
                                zzjm.zzbd("Device AnalyticsService processed last dispatch request");
                            }
                        }
                    });
                }
            });
        }
        return 2;
    }
}
