package com.facebook.internal;

import java.util.concurrent.atomic.*;
import android.os.*;
import java.util.concurrent.*;
import android.content.*;

private static final class GoogleAdServiceConnection implements ServiceConnection
{
    private AtomicBoolean consumed;
    private final BlockingQueue<IBinder> queue;
    
    private GoogleAdServiceConnection() {
        this.consumed = new AtomicBoolean(false);
        this.queue = new LinkedBlockingDeque<IBinder>();
    }
    
    public IBinder getBinder() throws InterruptedException {
        if (this.consumed.compareAndSet(true, true)) {
            throw new IllegalStateException("Binder already consumed");
        }
        return this.queue.take();
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        try {
            this.queue.put(binder);
        }
        catch (InterruptedException ex) {}
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
    }
}
