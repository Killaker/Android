package android.support.v7.widget;

import java.util.*;

class DefaultItemAnimator$2 implements Runnable {
    final /* synthetic */ ArrayList val$changes;
    
    @Override
    public void run() {
        final Iterator<ChangeInfo> iterator = this.val$changes.iterator();
        while (iterator.hasNext()) {
            DefaultItemAnimator.access$200(DefaultItemAnimator.this, (ChangeInfo)iterator.next());
        }
        this.val$changes.clear();
        DefaultItemAnimator.access$300(DefaultItemAnimator.this).remove(this.val$changes);
    }
}