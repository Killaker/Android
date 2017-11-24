package com.facebook;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

static final class FacebookSdk$1 implements ThreadFactory {
    private final AtomicInteger counter = new AtomicInteger(0);
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
    }
}