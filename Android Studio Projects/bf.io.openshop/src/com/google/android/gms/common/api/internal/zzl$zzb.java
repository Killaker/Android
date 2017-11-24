package com.google.android.gms.common.api.internal;

import android.os.*;
import android.util.*;

final class zzb extends Handler
{
    zzb(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GACStateManager", "Unknown message id: " + message.what);
            }
            case 1: {
                ((zza)message.obj).zzd(zzl.this);
            }
            case 2: {
                throw (RuntimeException)message.obj;
            }
        }
    }
}
