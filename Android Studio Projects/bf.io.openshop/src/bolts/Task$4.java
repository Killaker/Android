package bolts;

import java.util.concurrent.*;

static final class Task$4 implements Runnable {
    final /* synthetic */ Callable val$callable;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ bolts.TaskCompletionSource val$tcs;
    
    @Override
    public void run() {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            this.val$tcs.setCancelled();
            return;
        }
        try {
            this.val$tcs.setResult(this.val$callable.call());
        }
        catch (CancellationException ex) {
            this.val$tcs.setCancelled();
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}