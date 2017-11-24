package com.google.android.gms.measurement;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

private static class zzb implements ThreadFactory
{
    private static final AtomicInteger zzaUD;
    
    static {
        zzaUD = new AtomicInteger();
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new zzc(runnable, "measurement-" + zzb.zzaUD.incrementAndGet());
    }
}
