package com.google.android.gms.common.internal;

import android.content.*;
import android.os.*;

public final class zze implements ServiceConnection
{
    private final int zzalO;
    
    public zze(final int zzalO) {
        this.zzalO = zzalO;
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        zzx.zzb(binder, "Expecting a valid IBinder");
        synchronized (zzj.zza(zzj.this)) {
            zzj.zza(zzj.this, zzs.zza.zzaS(binder));
            // monitorexit(zzj.zza(this.zzalL))
            zzj.this.zzm(0, this.zzalO);
        }
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        synchronized (zzj.zza(zzj.this)) {
            zzj.zza(zzj.this, null);
            // monitorexit(zzj.zza(this.zzalL))
            zzj.this.mHandler.sendMessage(zzj.this.mHandler.obtainMessage(4, this.zzalO, 1));
        }
    }
}
