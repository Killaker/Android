package com.google.android.gms.common.api.internal;

import android.os.*;
import com.google.android.gms.common.internal.*;

private final class zza extends Handler
{
    public zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        boolean b = true;
        if (message.what != (b ? 1 : 0)) {
            b = false;
        }
        zzx.zzac(b);
        zzq.this.zzb((zzb<? super L>)message.obj);
    }
}
