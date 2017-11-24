package okhttp3.internal;

import java.util.concurrent.*;

static final class Util$1 implements ThreadFactory {
    final /* synthetic */ boolean val$daemon;
    final /* synthetic */ String val$name;
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, this.val$name);
        thread.setDaemon(this.val$daemon);
        return thread;
    }
}