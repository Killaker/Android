package android.support.v4.content;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

static final class ModernAsyncTask$1 implements ThreadFactory {
    private final AtomicInteger mCount = new AtomicInteger(1);
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "ModernAsyncTask #" + this.mCount.getAndIncrement());
    }
}