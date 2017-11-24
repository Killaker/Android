package com.google.android.gms.gcm;

import android.util.*;
import android.os.*;

private class zza extends Thread
{
    private final Bundle mExtras;
    private final String mTag;
    private final zzc zzaLK;
    
    zza(final String mTag, final IBinder binder, final Bundle mExtras) {
        this.mTag = mTag;
        this.zzaLK = zzc.zza.zzbZ(binder);
        this.mExtras = mExtras;
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        final int onRunTask = GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras));
        try {
            this.zzaLK.zzhe(onRunTask);
        }
        catch (RemoteException ex) {
            Log.e("GcmTaskService", "Error reporting result of operation to scheduler for " + this.mTag);
        }
        finally {
            GcmTaskService.zza(GcmTaskService.this, this.mTag);
        }
    }
}
