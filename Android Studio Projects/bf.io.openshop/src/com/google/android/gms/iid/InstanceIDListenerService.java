package com.google.android.gms.iid;

import android.app.*;
import android.util.*;
import android.content.*;
import android.support.v4.content.*;
import android.os.*;
import com.google.android.gms.gcm.*;
import java.io.*;

public class InstanceIDListenerService extends Service
{
    static String ACTION;
    private static String zzaLH;
    private static String zzaMY;
    private static String zzaMZ;
    MessengerCompat zzaMW;
    BroadcastReceiver zzaMX;
    int zzaNa;
    int zzaNb;
    
    static {
        InstanceIDListenerService.ACTION = "action";
        InstanceIDListenerService.zzaMY = "google.com/iid";
        InstanceIDListenerService.zzaMZ = "CMD";
        InstanceIDListenerService.zzaLH = "gcm.googleapis.com/refresh";
    }
    
    public InstanceIDListenerService() {
        this.zzaMW = new MessengerCompat(new Handler(Looper.getMainLooper()) {
            public void handleMessage(final Message message) {
                InstanceIDListenerService.this.zza(message, MessengerCompat.zzc(message));
            }
        });
        this.zzaMX = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("InstanceID", 3)) {
                    intent.getStringExtra("registration_id");
                    Log.d("InstanceID", "Received GSF callback using dynamic receiver: " + intent.getExtras());
                }
                InstanceIDListenerService.this.zzp(intent);
                InstanceIDListenerService.this.stop();
            }
        };
    }
    
    static void zza(final Context context, final zzd zzd) {
        zzd.zzyG();
        final Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.putExtra(InstanceIDListenerService.zzaMZ, "RST");
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }
    
    private void zza(final Message message, final int n) {
        zzc.zzaN((Context)this);
        this.getPackageManager();
        if (n != zzc.zzaNi && n != zzc.zzaNh) {
            Log.w("InstanceID", "Message from unexpected caller " + n + " mine=" + zzc.zzaNh + " appid=" + zzc.zzaNi);
            return;
        }
        this.zzp((Intent)message.obj);
    }
    
    static void zzaM(final Context context) {
        final Intent intent = new Intent("com.google.android.gms.iid.InstanceID");
        intent.setPackage(context.getPackageName());
        intent.putExtra(InstanceIDListenerService.zzaMZ, "SYNC");
        context.startService(intent);
    }
    
    public IBinder onBind(final Intent intent) {
        if (intent != null && "com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
            return this.zzaMW.getBinder();
        }
        return null;
    }
    
    public void onCreate() {
        final IntentFilter intentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
        intentFilter.addCategory(this.getPackageName());
        this.registerReceiver(this.zzaMX, intentFilter, "com.google.android.c2dm.permission.RECEIVE", (Handler)null);
    }
    
    public void onDestroy() {
        this.unregisterReceiver(this.zzaMX);
    }
    
    public int onStartCommand(final Intent intent, final int n, final int n2) {
        this.zzhl(n2);
        if (intent == null) {
            this.stop();
            return 2;
        }
        try {
            if ("com.google.android.gms.iid.InstanceID".equals(intent.getAction())) {
                if (Build$VERSION.SDK_INT <= 18) {
                    final Intent intent2 = (Intent)intent.getParcelableExtra("GSF");
                    if (intent2 != null) {
                        this.startService(intent2);
                        return 1;
                    }
                }
                this.zzp(intent);
            }
            this.stop();
            if (intent.getStringExtra("from") != null) {
                WakefulBroadcastReceiver.completeWakefulIntent(intent);
            }
            return 2;
        }
        finally {
            this.stop();
        }
    }
    
    public void onTokenRefresh() {
    }
    
    void stop() {
        synchronized (this) {
            --this.zzaNa;
            if (this.zzaNa == 0) {
                this.stopSelf(this.zzaNb);
            }
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Stop " + this.zzaNa + " " + this.zzaNb);
            }
        }
    }
    
    public void zzal(final boolean b) {
        this.onTokenRefresh();
    }
    
    void zzhl(final int zzaNb) {
        synchronized (this) {
            ++this.zzaNa;
            if (zzaNb > this.zzaNb) {
                this.zzaNb = zzaNb;
            }
        }
    }
    
    public void zzp(final Intent intent) {
        String stringExtra = intent.getStringExtra("subtype");
        InstanceID instanceID;
        if (stringExtra == null) {
            instanceID = InstanceID.getInstance((Context)this);
        }
        else {
            final Bundle bundle = new Bundle();
            bundle.putString("subtype", stringExtra);
            instanceID = InstanceID.zza((Context)this, bundle);
        }
        final String stringExtra2 = intent.getStringExtra(InstanceIDListenerService.zzaMZ);
        if (intent.getStringExtra("error") != null || intent.getStringExtra("registration_id") != null) {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Register result in service " + stringExtra);
            }
            instanceID.zzyC().zzw(intent);
        }
        else {
            if (Log.isLoggable("InstanceID", 3)) {
                Log.d("InstanceID", "Service command " + stringExtra + " " + stringExtra2 + " " + intent.getExtras());
            }
            if (intent.getStringExtra("unregistered") != null) {
                final zzd zzyB = instanceID.zzyB();
                if (stringExtra == null) {
                    stringExtra = "";
                }
                zzyB.zzef(stringExtra);
                instanceID.zzyC().zzw(intent);
                return;
            }
            if (InstanceIDListenerService.zzaLH.equals(intent.getStringExtra("from"))) {
                instanceID.zzyB().zzef(stringExtra);
                this.zzal(false);
                return;
            }
            if ("RST".equals(stringExtra2)) {
                instanceID.zzyA();
                this.zzal(true);
                return;
            }
            if ("RST_FULL".equals(stringExtra2)) {
                if (!instanceID.zzyB().isEmpty()) {
                    instanceID.zzyB().zzyG();
                    this.zzal(true);
                }
            }
            else {
                if ("SYNC".equals(stringExtra2)) {
                    instanceID.zzyB().zzef(stringExtra);
                    this.zzal(false);
                    return;
                }
                if ("PING".equals(stringExtra2)) {
                    try {
                        GoogleCloudMessaging.getInstance((Context)this).send(InstanceIDListenerService.zzaMY, zzc.zzyF(), 0L, intent.getExtras());
                    }
                    catch (IOException ex) {
                        Log.w("InstanceID", "Failed to send ping response");
                    }
                }
            }
        }
    }
}
