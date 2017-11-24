package com.google.android.gms.gcm;

import android.content.*;

class GcmListenerService$1 implements Runnable {
    final /* synthetic */ Intent val$intent;
    
    @Override
    public void run() {
        GcmListenerService.zza(GcmListenerService.this, this.val$intent);
    }
}