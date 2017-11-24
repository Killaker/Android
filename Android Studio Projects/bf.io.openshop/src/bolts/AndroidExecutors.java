package bolts;

import android.annotation.*;
import java.util.concurrent.*;
import android.os.*;

final class AndroidExecutors
{
    static final int CORE_POOL_SIZE = 0;
    private static final int CPU_COUNT = 0;
    private static final AndroidExecutors INSTANCE;
    static final long KEEP_ALIVE_TIME = 1L;
    static final int MAX_POOL_SIZE;
    private final Executor uiThread;
    
    static {
        INSTANCE = new AndroidExecutors();
        MAX_POOL_SIZE = 1 + 2 * AndroidExecutors.CPU_COUNT;
    }
    
    private AndroidExecutors() {
        this.uiThread = new UIThreadExecutor();
    }
    
    @SuppressLint({ "NewApi" })
    public static void allowCoreThreadTimeout(final ThreadPoolExecutor threadPoolExecutor, final boolean b) {
        if (Build$VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(b);
        }
    }
    
    public static ExecutorService newCachedThreadPool() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(AndroidExecutors.CORE_POOL_SIZE, AndroidExecutors.MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        allowCoreThreadTimeout(threadPoolExecutor, true);
        return threadPoolExecutor;
    }
    
    public static ExecutorService newCachedThreadPool(final ThreadFactory threadFactory) {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(AndroidExecutors.CORE_POOL_SIZE, AndroidExecutors.MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
        allowCoreThreadTimeout(threadPoolExecutor, true);
        return threadPoolExecutor;
    }
    
    public static Executor uiThread() {
        return AndroidExecutors.INSTANCE.uiThread;
    }
    
    private static class UIThreadExecutor implements Executor
    {
        @Override
        public void execute(final Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
