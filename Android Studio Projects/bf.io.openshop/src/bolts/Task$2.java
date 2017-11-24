package bolts;

import java.util.concurrent.*;

static final class Task$2 implements Runnable {
    final /* synthetic */ ScheduledFuture val$scheduled;
    final /* synthetic */ bolts.TaskCompletionSource val$tcs;
    
    @Override
    public void run() {
        this.val$scheduled.cancel(true);
        this.val$tcs.trySetCancelled();
    }
}