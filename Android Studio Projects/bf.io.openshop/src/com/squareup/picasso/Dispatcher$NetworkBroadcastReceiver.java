package com.squareup.picasso;

import android.net.*;
import android.content.*;

static class NetworkBroadcastReceiver extends BroadcastReceiver
{
    static final String EXTRA_AIRPLANE_STATE = "state";
    private final Dispatcher dispatcher;
    
    NetworkBroadcastReceiver(final Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                if (intent.hasExtra("state")) {
                    this.dispatcher.dispatchAirplaneModeChange(intent.getBooleanExtra("state", false));
                }
            }
            else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                this.dispatcher.dispatchNetworkStateChange(Utils.getService(context, "connectivity").getActiveNetworkInfo());
            }
        }
    }
    
    void register() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        if (this.dispatcher.scansNetworkChanges) {
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
        this.dispatcher.context.registerReceiver((BroadcastReceiver)this, intentFilter);
    }
    
    void unregister() {
        this.dispatcher.context.unregisterReceiver((BroadcastReceiver)this);
    }
}
