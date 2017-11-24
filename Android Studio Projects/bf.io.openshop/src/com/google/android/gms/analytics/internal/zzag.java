package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.*;
import android.os.*;
import android.net.*;
import android.content.*;

class zzag extends BroadcastReceiver
{
    static final String zzSZ;
    private final zzf zzQj;
    private boolean zzTa;
    private boolean zzTb;
    
    static {
        zzSZ = zzag.class.getName();
    }
    
    zzag(final zzf zzQj) {
        zzx.zzz(zzQj);
        this.zzQj = zzQj;
    }
    
    private Context getContext() {
        return this.zzQj.getContext();
    }
    
    private zzb zziH() {
        return this.zzQj.zziH();
    }
    
    private zzaf zzjm() {
        return this.zzQj.zzjm();
    }
    
    private void zzlz() {
        this.zzjm();
        this.zziH();
    }
    
    public boolean isConnected() {
        if (!this.zzTa) {
            this.zzQj.zzjm().zzbg("Connectivity unknown. Receiver not registered");
        }
        return this.zzTb;
    }
    
    public boolean isRegistered() {
        return this.zzTa;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.zzlz();
        final String action = intent.getAction();
        this.zzQj.zzjm().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzlB = this.zzlB();
            if (this.zzTb != zzlB) {
                this.zzTb = zzlB;
                this.zziH().zzJ(zzlB);
            }
        }
        else {
            if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
                this.zzQj.zzjm().zzd("NetworkBroadcastReceiver received unknown action", action);
                return;
            }
            if (!intent.hasExtra(zzag.zzSZ)) {
                this.zziH().zzjg();
            }
        }
    }
    
    public void unregister() {
        if (!this.isRegistered()) {
            return;
        }
        this.zzQj.zzjm().zzbd("Unregistering connectivity change receiver");
        this.zzTa = false;
        this.zzTb = false;
        final Context context = this.getContext();
        try {
            context.unregisterReceiver((BroadcastReceiver)this);
        }
        catch (IllegalArgumentException ex) {
            this.zzjm().zze("Failed to unregister the network broadcast receiver", ex);
        }
    }
    
    public void zzlA() {
        if (Build$VERSION.SDK_INT <= 10) {
            return;
        }
        final Context context = this.getContext();
        final Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzag.zzSZ, true);
        context.sendOrderedBroadcast(intent, (String)null);
    }
    
    protected boolean zzlB() {
        final ConnectivityManager connectivityManager = (ConnectivityManager)this.getContext().getSystemService("connectivity");
        try {
            final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public void zzly() {
        this.zzlz();
        if (this.zzTa) {
            return;
        }
        final Context context = this.getContext();
        context.registerReceiver((BroadcastReceiver)this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        final IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
        intentFilter.addCategory(context.getPackageName());
        context.registerReceiver((BroadcastReceiver)this, intentFilter);
        this.zzTb = this.zzlB();
        this.zzQj.zzjm().zza("Registering connectivity change receiver. Network connected", this.zzTb);
        this.zzTa = true;
    }
}
