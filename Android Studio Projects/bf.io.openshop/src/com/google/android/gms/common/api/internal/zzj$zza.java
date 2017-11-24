package com.google.android.gms.common.api.internal;

import android.os.*;
import android.util.*;

final class zza extends Handler
{
    zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
            }
            case 1: {
                zzj.zzc(zzj.this);
            }
            case 2: {
                zzj.zzb(zzj.this);
            }
        }
    }
}
