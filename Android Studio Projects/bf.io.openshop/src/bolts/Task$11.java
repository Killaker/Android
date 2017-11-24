package bolts;

import java.util.concurrent.*;

class Task$11 implements Continuation<TResult, Void> {
    final /* synthetic */ Continuation val$continuation;
    final /* synthetic */ CancellationToken val$ct;
    final /* synthetic */ Executor val$executor;
    final /* synthetic */ bolts.TaskCompletionSource val$tcs;
    
    @Override
    public Void then(final Task<TResult> task) {
        Task.access$100(this.val$tcs, this.val$continuation, task, this.val$executor, this.val$ct);
        return null;
    }
}