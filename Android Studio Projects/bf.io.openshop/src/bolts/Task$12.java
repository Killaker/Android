package bolts;

class Task$12 implements Continuation<TResult, Task<TContinuationResult>> {
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    
    @Override
    public Task<TContinuationResult> then(final Task<TResult> task) {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            return Task.cancelled();
        }
        if (task.isFaulted()) {
            return Task.forError(task.getError());
        }
        if (task.isCancelled()) {
            return Task.cancelled();
        }
        return task.continueWith((Continuation<TResult, TContinuationResult>)this.val$continuation);
    }
}