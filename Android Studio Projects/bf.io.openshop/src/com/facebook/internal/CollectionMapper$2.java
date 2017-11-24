package com.facebook.internal;

import com.facebook.*;

static final class CollectionMapper$2 implements OnMapValueCompleteListener {
    final /* synthetic */ Collection val$collection;
    final /* synthetic */ OnMapperCompleteListener val$jobCompleteListener;
    final /* synthetic */ Object val$key;
    
    @Override
    public void onComplete(final Object o) {
        this.val$collection.set(this.val$key, o, this.val$jobCompleteListener);
        this.val$jobCompleteListener.onComplete();
    }
    
    @Override
    public void onError(final FacebookException ex) {
        ((OnErrorListener)this.val$jobCompleteListener).onError(ex);
    }
}