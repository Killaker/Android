package bolts;

static final class Task$1 implements Runnable {
    final /* synthetic */ bolts.TaskCompletionSource val$tcs;
    
    @Override
    public void run() {
        this.val$tcs.trySetResult(null);
    }
}