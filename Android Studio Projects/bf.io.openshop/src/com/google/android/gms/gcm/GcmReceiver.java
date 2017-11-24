package com.google.android.gms.gcm;

import android.support.v4.content.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.util.*;

public class GcmReceiver extends WakefulBroadcastReceiver
{
    private static String zzaLH;
    
    static {
        GcmReceiver.zzaLH = "gcm.googleapis.com/refresh";
    }
    
    private void zzj(final Context context, final Intent intent) {
        if (this.isOrderedBroadcast()) {
            this.setResultCode(500);
        }
        this.zzk(context, intent);
        try {
            ComponentName componentName;
            if (context.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0) {
                componentName = WakefulBroadcastReceiver.startWakefulService(context, intent);
            }
            else {
                componentName = context.startService(intent);
                Log.d("GcmReceiver", "Missing wake lock permission, service start may be delayed");
            }
            if (componentName == null) {
                Log.e("GcmReceiver", "Error while delivering the message: ServiceIntent not found.");
                if (this.isOrderedBroadcast()) {
                    this.setResultCode(404);
                }
                return;
            }
        }
        catch (SecurityException ex) {
            Log.e("GcmReceiver", "Error while delivering the message to the serviceIntent", (Throwable)ex);
            if (this.isOrderedBroadcast()) {
                this.setResultCode(401);
            }
            return;
        }
        if (this.isOrderedBroadcast()) {
            this.setResultCode(-1);
        }
    }
    
    private void zzk(final Context context, final Intent intent) {
        final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
        if (resolveService == null || resolveService.serviceInfo == null) {
            Log.e("GcmReceiver", "Failed to resolve target intent service, skipping classname enforcement");
            return;
        }
        final ServiceInfo serviceInfo = resolveService.serviceInfo;
        if (!context.getPackageName().equals(serviceInfo.packageName) || serviceInfo.name == null) {
            Log.e("GcmReceiver", "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + serviceInfo.packageName + "/" + serviceInfo.name);
            return;
        }
        String s = serviceInfo.name;
        if (s.startsWith(".")) {
            s = context.getPackageName() + s;
        }
        if (Log.isLoggable("GcmReceiver", 3)) {
            Log.d("GcmReceiver", "Restricting intent to a specific service: " + s);
        }
        intent.setClassName(context.getPackageName(), s);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        intent.setComponent((ComponentName)null);
        intent.setPackage(context.getPackageName());
        if (Build$VERSION.SDK_INT <= 18) {
            intent.removeCategory(context.getPackageName());
        }
        final String stringExtra = intent.getStringExtra("from");
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction()) || "google.com/iid".equals(stringExtra) || GcmReceiver.zzaLH.equals(stringExtra)) {
            intent.setAction("com.google.android.gms.iid.InstanceID");
        }
        final String stringExtra2 = intent.getStringExtra("gcm.rawData64");
        if (stringExtra2 != null) {
            intent.putExtra("rawData", Base64.decode(stringExtra2, 0));
            intent.removeExtra("gcm.rawData64");
        }
        if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            this.zzi(context, intent);
        }
        else {
            this.zzj(context, intent);
        }
        if (this.isOrderedBroadcast() && this.getResultCode() == 0) {
            this.setResultCode(-1);
        }
    }
    
    public void zzi(final Context context, final Intent intent) {
        this.zzj(context, intent);
    }
}
