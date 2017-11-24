package com.facebook.internal;

import com.facebook.*;

static final class CollectionMapper$1 implements OnMapperCompleteListener {
    final /* synthetic */ Mutable val$didReturnError;
    final /* synthetic */ OnMapperCompleteListener val$onMapperCompleteListener;
    final /* synthetic */ Mutable val$pendingJobCount;
    
    @Override
    public void onComplete() {
        if (!(boolean)this.val$didReturnError.value) {
            final Mutable val$pendingJobCount = this.val$pendingJobCount;
            final Integer value = -1 + (int)this.val$pendingJobCount.value;
            val$pendingJobCount.value = (T)value;
            if (value == 0) {
                this.val$onMapperCompleteListener.onComplete();
            }
        }
    }
    
    @Override
    public void onError(final FacebookException ex) {
        if (this.val$didReturnError.value) {
            return;
        }
        this.val$didReturnError.value = (T)true;
        ((OnErrorListener)this.val$onMapperCompleteListener).onError(ex);
    }
}