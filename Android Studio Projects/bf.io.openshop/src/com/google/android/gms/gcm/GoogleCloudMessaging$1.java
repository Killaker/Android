package com.google.android.gms.gcm;

import android.os.*;
import android.content.*;
import android.util.*;

class GoogleCloudMessaging$1 extends Handler {
    public void handleMessage(final Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("GCM", "Dropping invalid message");
        }
        final Intent intent = (Intent)message.obj;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(intent.getAction())) {
            GoogleCloudMessaging.zza(GoogleCloudMessaging.this).add(intent);
        }
        else if (!GoogleCloudMessaging.zza(GoogleCloudMessaging.this, intent)) {
            intent.setPackage(GoogleCloudMessaging.zzb(GoogleCloudMessaging.this).getPackageName());
            GoogleCloudMessaging.zzb(GoogleCloudMessaging.this).sendBroadcast(intent);
        }
    }
}