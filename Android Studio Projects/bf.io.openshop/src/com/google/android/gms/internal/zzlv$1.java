package com.google.android.gms.internal;

import android.util.*;
import com.google.android.gms.common.api.*;

class zzlv$1 implements Runnable {
    @Override
    public void run() {
        synchronized (zzlv.zza(zzlv.this)) {
            if (zzlv.zzb(zzlv.this) <= zzlv.zzc(zzlv.this).elapsedRealtime() && zzlv.zzd(zzlv.this) != null) {
                Log.i("ClearcutLoggerApiImpl", "disconnect managed GoogleApiClient");
                zzlv.zzd(zzlv.this).disconnect();
                zzlv.zza(zzlv.this, null);
            }
        }
    }
}