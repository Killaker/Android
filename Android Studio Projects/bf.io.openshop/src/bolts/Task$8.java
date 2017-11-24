package bolts;

import java.util.concurrent.atomic.*;
import java.util.*;

static final class Task$8 implements Continuation<Object, Void> {
    final /* synthetic */ bolts.TaskCompletionSource val$allFinished;
    final /* synthetic */ ArrayList val$causes;
    final /* synthetic */ AtomicInteger val$count;
    final /* synthetic */ Object val$errorLock;
    final /* synthetic */ AtomicBoolean val$isCancelled;
    
    @Override
    public Void then(final Task<Object> task) {
        Label_0151: {
            Label_0031: {
                if (!task.isFaulted()) {
                    break Label_0031;
                }
                synchronized (this.val$errorLock) {
                    this.val$causes.add(task.getError());
                    // monitorexit(this.val$errorLock)
                    if (task.isCancelled()) {
                        this.val$isCancelled.set(true);
                    }
                    if (this.val$count.decrementAndGet() == 0) {
                        if (this.val$causes.size() == 0) {
                            break Label_0151;
                        }
                        if (this.val$causes.size() != 1) {
                            break Label_0031;
                        }
                        this.val$allFinished.setError(this.val$causes.get(0));
                    }
                    return null;
                }
            }
            this.val$allFinished.setError(new AggregateException(String.format("There were %d exceptions.", this.val$causes.size()), this.val$causes));
            return null;
        }
        if (this.val$isCancelled.get()) {
            this.val$allFinished.setCancelled();
            return null;
        }
        this.val$allFinished.setResult(null);
        return null;
    }
}