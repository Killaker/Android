package com.google.android.gms.iid;

import android.content.*;
import android.util.*;

class InstanceIDListenerService$2 extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("InstanceID", 3)) {
            intent.getStringExtra("registration_id");
            Log.d("InstanceID", "Received GSF callback using dynamic receiver: " + intent.getExtras());
        }
        InstanceIDListenerService.this.zzp(intent);
        InstanceIDListenerService.this.stop();
    }
}