package com.google.android.gms.internal;

import android.os.*;

class zznl implements Runnable
{
    private final int mPriority;
    private final Runnable zzx;
    
    public zznl(final Runnable zzx, final int mPriority) {
        this.zzx = zzx;
        this.mPriority = mPriority;
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(this.mPriority);
        this.zzx.run();
    }
}
