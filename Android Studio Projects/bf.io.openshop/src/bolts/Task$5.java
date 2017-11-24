package bolts;

import java.util.concurrent.atomic.*;

static final class Task$5 implements Continuation<TResult, Void> {
    final /* synthetic */ bolts.TaskCompletionSource val$firstCompleted;
    final /* synthetic */ AtomicBoolean val$isAnyTaskComplete;
    
    @Override
    public Void then(final Task<TResult> result) {
        if (this.val$isAnyTaskComplete.compareAndSet(false, true)) {
            this.val$firstCompleted.setResult(result);
        }
        return null;
    }
}