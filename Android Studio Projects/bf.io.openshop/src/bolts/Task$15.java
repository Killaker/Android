package bolts;

import java.util.concurrent.*;

static final class Task$15 implements Runnable {
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ Task val$task;
    final /* synthetic */ bolts.TaskCompletionSource val$tcs;
    
    @Override
    public void run() {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            this.val$tcs.setCancelled();
            return;
        }
        try {
            if (this.val$continuation.then(this.val$task) == null) {
                this.val$tcs.setResult(null);
                return;
            }
            goto Label_0064;
        }
        catch (CancellationException ex) {
            this.val$tcs.setCancelled();
        }
        catch (Exception error) {
            this.val$tcs.setError(error);
        }
    }
}