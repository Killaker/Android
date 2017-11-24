package com.google.android.gms.tagmanager;

import android.os.*;

class zzcu$2 implements Handler$Callback {
    public boolean handleMessage(final Message message) {
        if (1 == message.what && zzcu.zzHs().equals(message.obj)) {
            zzcu.this.dispatch();
            if (zzcu.zzb(zzcu.this) > 0 && !zzcu.zzc(zzcu.this)) {
                zzcu.zzd(zzcu.this).sendMessageDelayed(zzcu.zzd(zzcu.this).obtainMessage(1, zzcu.zzHs()), (long)zzcu.zzb(zzcu.this));
            }
        }
        return true;
    }
}