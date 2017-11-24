package com.google.android.gms.tagmanager;

import android.os.*;
import android.content.*;

class zzbl extends BroadcastReceiver
{
    static final String zzSZ;
    private final zzct zzbjA;
    
    static {
        zzSZ = zzbl.class.getName();
    }
    
    zzbl(final zzct zzbjA) {
        this.zzbjA = zzbjA;
    }
    
    public static void zzbb(final Context context) {
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzbl.zzSZ, true);
        context.sendBroadcast(intent);
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final Bundle extras = intent.getExtras();
            Boolean b = Boolean.FALSE;
            if (extras != null) {
                b = intent.getExtras().getBoolean("noConnectivity");
            }
            this.zzbjA.zzay(!b);
        }
        else if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(zzbl.zzSZ)) {
            this.zzbjA.zzjg();
        }
    }
    
    public void zzba(final Context context) {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver((BroadcastReceiver)this, intentFilter);
        final IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
        intentFilter2.addCategory(context.getPackageName());
        context.registerReceiver((BroadcastReceiver)this, intentFilter2);
    }
}
