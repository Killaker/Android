package bolts;

class Task$3 implements Continuation<TResult, Task<Void>> {
    @Override
    public Task<Void> then(final Task<TResult> task) throws Exception {
        if (task.isCancelled()) {
            return Task.cancelled();
        }
        if (task.isFaulted()) {
            return Task.forError(task.getError());
        }
        return Task.forResult((Void)null);
    }
}