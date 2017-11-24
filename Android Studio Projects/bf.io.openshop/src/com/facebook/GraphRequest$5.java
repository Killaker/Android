package com.facebook;

import android.util.*;
import java.util.*;

static final class GraphRequest$5 implements Runnable {
    final /* synthetic */ ArrayList val$callbacks;
    final /* synthetic */ GraphRequestBatch val$requests;
    
    @Override
    public void run() {
        for (final Pair pair : this.val$callbacks) {
            ((Callback)pair.first).onCompleted((GraphResponse)pair.second);
        }
        final Iterator<GraphRequestBatch.Callback> iterator2 = this.val$requests.getCallbacks().iterator();
        while (iterator2.hasNext()) {
            ((GraphRequestBatch.Callback)iterator2.next()).onBatchCompleted(this.val$requests);
        }
    }
}