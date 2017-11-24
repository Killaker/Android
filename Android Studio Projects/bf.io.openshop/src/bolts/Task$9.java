package bolts;

import java.util.concurrent.*;

class Task$9 implements Continuation<Void, Task<Void>> {
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ Executor val$executor;
    final /* synthetic */ Callable val$predicate;
    final /* synthetic */ Capture val$predicateContinuation;
    
    @Override
    public Task<Void> then(final Task<Void> task) throws Exception {
        if (this.val$ct != null && this.val$ct.isCancellationRequested()) {
            return Task.cancelled();
        }
        if (this.val$predicate.call()) {
            return Task.forResult((Object)null).onSuccessTask((Continuation<Object, Task<Object>>)this.val$continuation, this.val$executor).onSuccessTask(this.val$predicateContinuation.get(), this.val$executor);
        }
        return Task.forResult((Void)null);
    }
}