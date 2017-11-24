package com.google.android.gms.internal;

import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import com.google.android.gms.common.internal.*;

public class zznk implements ThreadFactory
{
    private final int mPriority;
    private final String zzaoq;
    private final AtomicInteger zzaor;
    private final ThreadFactory zzaos;
    
    public zznk(final String s) {
        this(s, 0);
    }
    
    public zznk(final String s, final int mPriority) {
        this.zzaor = new AtomicInteger();
        this.zzaos = Executors.defaultThreadFactory();
        this.zzaoq = zzx.zzb(s, "Name must not be null");
        this.mPriority = mPriority;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = this.zzaos.newThread(new zznl(runnable, this.mPriority));
        thread.setName(this.zzaoq + "[" + this.zzaor.getAndIncrement() + "]");
        return thread;
    }
}
