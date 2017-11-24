package bolts;

class Task$15$1 implements Continuation<TContinuationResult, Void> {
    @Override
    public Void then(final Task<TContinuationResult> task) {
        if (Runnable.this.val$ct != null && Runnable.this.val$ct.isCancellationRequested()) {
            Runnable.this.val$tcs.setCancelled();
            return null;
        }
        if (task.isCancelled()) {
            Runnable.this.val$tcs.setCancelled();
            return null;
        }
        if (task.isFaulted()) {
            Runnable.this.val$tcs.setError(task.getError());
            return null;
        }
        Runnable.this.val$tcs.setResult(task.getResult());
        return null;
    }
}