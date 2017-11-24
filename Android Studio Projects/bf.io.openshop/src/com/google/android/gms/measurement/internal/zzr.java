package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.*;
import android.support.annotation.*;
import android.content.*;

class zzr extends BroadcastReceiver
{
    static final String zzSZ;
    private boolean zzTa;
    private boolean zzTb;
    private final zzw zzaTV;
    
    static {
        zzSZ = zzr.class.getName();
    }
    
    zzr(final zzw zzaTV) {
        zzx.zzz(zzaTV);
        this.zzaTV = zzaTV;
    }
    
    private Context getContext() {
        return this.zzaTV.getContext();
    }
    
    private zzp zzAo() {
        return this.zzaTV.zzAo();
    }
    
    @WorkerThread
    public boolean isRegistered() {
        this.zzaTV.zzjk();
        return this.zzTa;
    }
    
    @MainThread
    public void onReceive(final Context context, final Intent intent) {
        this.zzaTV.zzjv();
        final String action = intent.getAction();
        this.zzAo().zzCK().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            final boolean zzlB = this.zzaTV.zzCW().zzlB();
            if (this.zzTb != zzlB) {
                this.zzTb = zzlB;
                this.zzaTV.zzCn().zzg(new Runnable() {
                    @Override
                    public void run() {
                        zzr.this.zzaTV.zzJ(zzlB);
                    }
                });
            }
            return;
        }
        this.zzAo().zzCF().zzj("NetworkBroadcastReceiver received unknown action", action);
    }
    
    @WorkerThread
    public void unregister() {
        this.zzaTV.zzjv();
        this.zzaTV.zzjk();
        if (!this.isRegistered()) {
            return;
        }
        this.zzAo().zzCK().zzfg("Unregistering connectivity change receiver");
        this.zzTa = false;
        this.zzTb = false;
        final Context context = this.getContext();
        try {
            context.unregisterReceiver((BroadcastReceiver)this);
        }
        catch (IllegalArgumentException ex) {
            this.zzAo().zzCE().zzj("Failed to unregister the network broadcast receiver", ex);
        }
    }
    
    @WorkerThread
    public void zzly() {
        this.zzaTV.zzjv();
        this.zzaTV.zzjk();
        if (this.zzTa) {
            return;
        }
        this.getContext().registerReceiver((BroadcastReceiver)this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzTb = this.zzaTV.zzCW().zzlB();
        this.zzAo().zzCK().zzj("Registering connectivity change receiver. Network connected", this.zzTb);
        this.zzTa = true;
    }
}
