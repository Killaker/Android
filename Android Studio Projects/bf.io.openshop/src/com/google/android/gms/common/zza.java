package com.google.android.gms.common;

import java.util.concurrent.*;
import android.content.*;
import android.os.*;

public class zza implements ServiceConnection
{
    private final BlockingQueue<IBinder> zzafA;
    boolean zzafz;
    
    public zza() {
        this.zzafz = false;
        this.zzafA = new LinkedBlockingQueue<IBinder>();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.zzafA.add(binder);
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
    
    public IBinder zzoJ() throws InterruptedException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("BlockingServiceConnection.getService() called on main thread");
        }
        if (this.zzafz) {
            throw new IllegalStateException();
        }
        this.zzafz = true;
        return this.zzafA.take();
    }
}
