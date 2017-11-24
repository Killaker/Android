package com.google.android.gms.measurement;

import android.os.*;

private static class zzc extends Thread
{
    zzc(final Runnable runnable, final String s) {
        super(runnable, s);
    }
    
    @Override
    public void run() {
        Process.setThreadPriority(10);
        super.run();
    }
}
