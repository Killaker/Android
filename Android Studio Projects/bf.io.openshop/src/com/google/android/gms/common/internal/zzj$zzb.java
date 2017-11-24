package com.google.android.gms.common.internal;

import com.google.android.gms.common.*;
import android.app.*;
import android.os.*;
import android.util.*;

final class zzb extends Handler
{
    public zzb(final Looper looper) {
        super(looper);
    }
    
    private void zza(final Message message) {
        final zzc zzc = (zzc)message.obj;
        zzc.zzqM();
        zzc.unregister();
    }
    
    private boolean zzb(final Message message) {
        return message.what == 2 || message.what == 1 || message.what == 5;
    }
    
    public void handleMessage(final Message message) {
        if (zzj.this.zzalI.get() != message.arg1) {
            if (this.zzb(message)) {
                this.zza(message);
            }
            return;
        }
        if ((message.what == 1 || message.what == 5) && !zzj.this.isConnecting()) {
            this.zza(message);
            return;
        }
        if (message.what == 3) {
            final ConnectionResult connectionResult = new ConnectionResult(message.arg2, null);
            zzj.zzb(zzj.this).zza(connectionResult);
            zzj.this.onConnectionFailed(connectionResult);
            return;
        }
        if (message.what == 4) {
            zzj.zza(zzj.this, 4, null);
            if (zzj.zzc(zzj.this) != null) {
                zzj.zzc(zzj.this).onConnectionSuspended(message.arg2);
            }
            zzj.this.onConnectionSuspended(message.arg2);
            zzj.zza(zzj.this, 4, 1, null);
            return;
        }
        if (message.what == 2 && !zzj.this.isConnected()) {
            this.zza(message);
            return;
        }
        if (this.zzb(message)) {
            ((zzc)message.obj).zzqN();
            return;
        }
        Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, (Throwable)new Exception());
    }
}
