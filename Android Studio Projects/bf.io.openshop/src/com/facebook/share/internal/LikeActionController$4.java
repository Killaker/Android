package com.facebook.share.internal;

import com.facebook.*;

static final class LikeActionController$4 implements Runnable {
    final /* synthetic */ CreationCallback val$callback;
    final /* synthetic */ LikeActionController val$controller;
    final /* synthetic */ FacebookException val$error;
    
    @Override
    public void run() {
        this.val$callback.onComplete(this.val$controller, this.val$error);
    }
}