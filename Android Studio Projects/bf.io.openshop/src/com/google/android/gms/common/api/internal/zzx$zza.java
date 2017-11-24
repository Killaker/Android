package com.google.android.gms.common.api.internal;

import android.os.*;
import android.util.*;
import com.google.android.gms.common.api.*;

private final class zza extends Handler
{
    public zza(final Looper looper) {
        super(looper);
    }
    
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
            }
            case 0: {
                final PendingResult pendingResult = (PendingResult)message.obj;
                final Object zzf = zzx.zzf(zzx.this);
                // monitorenter(zzf)
                Label_0116: {
                    if (pendingResult != null) {
                        break Label_0116;
                    }
                    try {
                        zzx.zza(zzx.zzg(zzx.this), new Status(13, "Transform returned null"));
                        return;
                    }
                    finally {
                    }
                    // monitorexit(zzf)
                }
                if (pendingResult instanceof zzt) {
                    zzx.zza(zzx.zzg(zzx.this), ((zzt<?>)pendingResult).getStatus());
                    return;
                }
                zzx.zzg(zzx.this).zza(pendingResult);
            }
            case 1: {
                final RuntimeException ex = (RuntimeException)message.obj;
                Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: " + ex.getMessage());
                throw ex;
            }
        }
    }
}
