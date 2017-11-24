package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.*;
import java.util.concurrent.*;

public abstract class zzs
{
    private static final ExecutorService zzaiv;
    
    static {
        zzaiv = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new zznk("GAC_Transform"));
    }
    
    public static ExecutorService zzpN() {
        return zzs.zzaiv;
    }
}
