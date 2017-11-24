package com.google.android.gms.gcm;

import android.os.*;
import android.content.*;

class GcmListenerService$2 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ Intent val$intent;
    
    protected Void zzb(final Void... array) {
        GcmListenerService.zza(GcmListenerService.this, this.val$intent);
        return null;
    }
}