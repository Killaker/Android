package com.google.android.gms.common;

import android.content.*;
import android.os.*;
import android.util.*;

private static class zza extends Handler
{
    private final Context zzsa;
    
    zza(final Context context) {
        Looper looper;
        if (Looper.myLooper() == null) {
            looper = Looper.getMainLooper();
        }
        else {
            looper = Looper.myLooper();
        }
        super(looper);
        this.zzsa = context.getApplicationContext();
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + message.what);
                break;
            }
            case 1: {
                final int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzsa);
                if (GooglePlayServicesUtil.isUserRecoverableError(googlePlayServicesAvailable)) {
                    GooglePlayServicesUtil.zzb(googlePlayServicesAvailable, this.zzsa);
                    return;
                }
                break;
            }
        }
    }
}
