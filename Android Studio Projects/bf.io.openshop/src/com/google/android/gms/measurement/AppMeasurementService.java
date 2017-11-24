package com.google.android.gms.measurement;

import android.app.*;
import com.google.android.gms.common.internal.*;
import com.google.android.gms.measurement.internal.*;
import com.google.android.gms.internal.*;
import android.content.*;
import android.os.*;
import android.support.annotation.*;

public final class AppMeasurementService extends Service
{
    private static Boolean zzOO;
    private final Handler mHandler;
    
    public AppMeasurementService() {
        this.mHandler = new Handler();
    }
    
    private zzp zzAo() {
        return zzw.zzaT((Context)this).zzAo();
    }
    
    public static boolean zzZ(final Context context) {
        zzx.zzz(context);
        if (AppMeasurementService.zzOO != null) {
            return AppMeasurementService.zzOO;
        }
        final boolean zza = zzaj.zza(context, AppMeasurementService.class);
        AppMeasurementService.zzOO = zza;
        return zza;
    }
    
    private void zziz() {
        try {
            synchronized (AppMeasurementReceiver.zzqy) {
                final zzrp zzOM = AppMeasurementReceiver.zzOM;
                if (zzOM != null && zzOM.isHeld()) {
                    zzOM.release();
                }
            }
        }
        catch (SecurityException ex) {}
    }
    
    @MainThread
    public IBinder onBind(final Intent intent) {
        if (intent == null) {
            this.zzAo().zzCE().zzfg("onBind called with null intent");
            return null;
        }
        final String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return (IBinder)new com.google.android.gms.measurement.internal.zzx(zzw.zzaT((Context)this));
        }
        this.zzAo().zzCF().zzj("onBind received unknown action", action);
        return null;
    }
    
    @MainThread
    public void onCreate() {
        super.onCreate();
        final zzw zzaT = zzw.zzaT((Context)this);
        final zzp zzAo = zzaT.zzAo();
        if (zzaT.zzCp().zzkr()) {
            zzAo.zzCK().zzfg("Device AppMeasurementService is starting up");
            return;
        }
        zzAo.zzCK().zzfg("Local AppMeasurementService is starting up");
    }
    
    @MainThread
    public void onDestroy() {
        final zzw zzaT = zzw.zzaT((Context)this);
        final zzp zzAo = zzaT.zzAo();
        if (zzaT.zzCp().zzkr()) {
            zzAo.zzCK().zzfg("Device AppMeasurementService is shutting down");
        }
        else {
            zzAo.zzCK().zzfg("Local AppMeasurementService is shutting down");
        }
        super.onDestroy();
    }
    
    @MainThread
    public void onRebind(final Intent intent) {
        if (intent == null) {
            this.zzAo().zzCE().zzfg("onRebind called with null intent");
            return;
        }
        this.zzAo().zzCK().zzj("onRebind called. action", intent.getAction());
    }
    
    @MainThread
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.zziz();
        final zzw zzaT = zzw.zzaT((Context)this);
        final zzp zzAo = zzaT.zzAo();
        final String action = intent.getAction();
        if (zzaT.zzCp().zzkr()) {
            zzAo.zzCK().zze("Device AppMeasurementService called. startId, action", n2, action);
        }
        else {
            zzAo.zzCK().zze("Local AppMeasurementService called. startId, action", n2, action);
        }
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zzaT.zzCn().zzg(new Runnable() {
                @Override
                public void run() {
                    zzaT.zzDc();
                    AppMeasurementService.this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (AppMeasurementService.this.stopSelfResult(n2)) {
                                if (!zzaT.zzCp().zzkr()) {
                                    zzAo.zzCK().zzfg("Local AppMeasurementService processed last upload request");
                                    return;
                                }
                                zzAo.zzCK().zzfg("Device AppMeasurementService processed last upload request");
                            }
                        }
                    });
                }
            });
        }
        return 2;
    }
    
    @MainThread
    public boolean onUnbind(final Intent intent) {
        if (intent == null) {
            this.zzAo().zzCE().zzfg("onUnbind called with null intent");
            return true;
        }
        this.zzAo().zzCK().zzj("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
