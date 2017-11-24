package bolts;

import java.util.concurrent.atomic.*;

static final class Task$6 implements Continuation<Object, Void> {
    final /* synthetic */ bolts.TaskCompletionSource val$firstCompleted;
    final /* synthetic */ AtomicBoolean val$isAnyTaskComplete;
    
    @Override
    public Void then(final Task<Object> result) {
        if (this.val$isAnyTaskComplete.compareAndSet(false, true)) {
            this.val$firstCompleted.setResult(result);
        }
        return null;
    }
}