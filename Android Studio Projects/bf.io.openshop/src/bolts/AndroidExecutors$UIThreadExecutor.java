package bolts;

import java.util.concurrent.*;
import android.os.*;

private static class UIThreadExecutor implements Executor
{
    @Override
    public void execute(final Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
