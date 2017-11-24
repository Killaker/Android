package com.google.android.gms.common.internal;

import android.content.*;
import android.os.*;
import java.util.*;

public class zza implements ServiceConnection
{
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        synchronized (zzm.zza(zzb.this.zzamh)) {
            zzb.zza(zzb.this, binder);
            zzb.zza(zzb.this, componentName);
            final Iterator<ServiceConnection> iterator = zzb.zzb(zzb.this).iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(componentName, binder);
            }
        }
        zzb.zza(zzb.this, 1);
    }
    // monitorexit(hashMap)
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (zzm.zza(zzb.this.zzamh)) {
            zzb.zza(zzb.this, (IBinder)null);
            zzb.zza(zzb.this, componentName);
            final Iterator<ServiceConnection> iterator = zzb.zzb(zzb.this).iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(componentName);
            }
        }
        zzb.zza(zzb.this, 2);
    }
    // monitorexit(hashMap)
}
