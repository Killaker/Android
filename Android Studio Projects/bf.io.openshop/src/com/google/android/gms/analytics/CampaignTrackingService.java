package com.google.android.gms.analytics;

import android.app.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.internal.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.text.*;
import com.google.android.gms.analytics.internal.*;

public class CampaignTrackingService extends Service
{
    private static Boolean zzOO;
    private Handler mHandler;
    
    private Handler getHandler() {
        Handler mHandler = this.mHandler;
        if (mHandler == null) {
            mHandler = new Handler(this.getMainLooper());
            this.mHandler = mHandler;
        }
        return mHandler;
    }
    
    public static boolean zzZ(final Context context) {
        zzx.zzz(context);
        if (CampaignTrackingService.zzOO != null) {
            return CampaignTrackingService.zzOO;
        }
        final boolean zza = zzam.zza(context, CampaignTrackingService.class);
        CampaignTrackingService.zzOO = zza;
        return zza;
    }
    
    private void zziz() {
        try {
            synchronized (CampaignTrackingReceiver.zzqy) {
                final zzrp zzOM = CampaignTrackingReceiver.zzOM;
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
        zzf.zzaa((Context)this).zzjm().zzbd("CampaignTrackingService is starting up");
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public void onDestroy() {
        zzf.zzaa((Context)this).zzjm().zzbd("CampaignTrackingService is shutting down");
        super.onDestroy();
    }
    
    @RequiresPermission(allOf = { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.zziz();
        final zzf zzaa = zzf.zzaa((Context)this);
        final zzaf zzjm = zzaa.zzjm();
        String s = null;
        if (zzaa.zzjn().zzkr()) {
            zzjm.zzbh("Unexpected installation campaign (package side)");
        }
        else {
            s = intent.getStringExtra("referrer");
        }
        final Handler handler = this.getHandler();
        if (TextUtils.isEmpty((CharSequence)s)) {
            if (!zzaa.zzjn().zzkr()) {
                zzjm.zzbg("No campaign found on com.android.vending.INSTALL_REFERRER \"referrer\" extra");
            }
            zzaa.zzjo().zzf(new Runnable() {
                @Override
                public void run() {
                    CampaignTrackingService.this.zza(zzjm, handler, n2);
                }
            });
            return 2;
        }
        final int zzkv = zzaa.zzjn().zzkv();
        if (s.length() > zzkv) {
            zzjm.zzc("Campaign data exceed the maximum supported size and will be clipped. size, limit", s.length(), zzkv);
            s = s.substring(0, zzkv);
        }
        zzjm.zza("CampaignTrackingService called. startId, campaign", n2, s);
        zzaa.zziH().zza(s, new Runnable() {
            @Override
            public void run() {
                CampaignTrackingService.this.zza(zzjm, handler, n2);
            }
        });
        return 2;
    }
    
    protected void zza(final zzaf zzaf, final Handler handler, final int n) {
        handler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final boolean stopSelfResult = CampaignTrackingService.this.stopSelfResult(n);
                if (stopSelfResult) {
                    zzaf.zza("Install campaign broadcast processed", stopSelfResult);
                }
            }
        });
    }
}
