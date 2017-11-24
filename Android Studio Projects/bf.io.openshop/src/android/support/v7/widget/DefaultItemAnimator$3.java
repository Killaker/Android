package android.support.v7.widget;

import java.util.*;

class DefaultItemAnimator$3 implements Runnable {
    final /* synthetic */ ArrayList val$additions;
    
    @Override
    public void run() {
        final Iterator<ViewHolder> iterator = this.val$additions.iterator();
        while (iterator.hasNext()) {
            DefaultItemAnimator.access$400(DefaultItemAnimator.this, iterator.next());
        }
        this.val$additions.clear();
        DefaultItemAnimator.access$500(DefaultItemAnimator.this).remove(this.val$additions);
    }
}