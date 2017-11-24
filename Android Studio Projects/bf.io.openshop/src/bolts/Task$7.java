package bolts;

import java.util.*;

static final class Task$7 implements Continuation<Void, List<TResult>> {
    final /* synthetic */ Collection val$tasks;
    
    @Override
    public List<TResult> then(final Task<Void> task) throws Exception {
        List<TResult> emptyList;
        if (this.val$tasks.size() == 0) {
            emptyList = Collections.emptyList();
        }
        else {
            emptyList = new ArrayList<TResult>();
            final Iterator<Task<TResult>> iterator = this.val$tasks.iterator();
            while (iterator.hasNext()) {
                emptyList.add(iterator.next().getResult());
            }
        }
        return emptyList;
    }
}