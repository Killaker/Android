package com.squareup.picasso;

import java.lang.ref.*;

static class RequestWeakReference<M> extends WeakReference<M>
{
    final Action action;
    
    public RequestWeakReference(final Action action, final M m, final ReferenceQueue<? super M> referenceQueue) {
        super(m, referenceQueue);
        this.action = action;
    }
}
