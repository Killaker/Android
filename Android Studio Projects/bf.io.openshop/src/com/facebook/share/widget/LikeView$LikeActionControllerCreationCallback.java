package com.facebook.share.widget;

import com.facebook.share.internal.*;
import com.facebook.*;

private class LikeActionControllerCreationCallback implements CreationCallback
{
    private boolean isCancelled;
    
    public void cancel() {
        this.isCancelled = true;
    }
    
    @Override
    public void onComplete(final LikeActionController likeActionController, FacebookException ex) {
        if (this.isCancelled) {
            return;
        }
        if (likeActionController != null) {
            if (!likeActionController.shouldEnableView()) {
                ex = new FacebookException("Cannot use LikeView. The device may not be supported.");
            }
            LikeView.access$1100(LikeView.this, likeActionController);
            LikeView.access$700(LikeView.this);
        }
        if (ex != null && LikeView.access$800(LikeView.this) != null) {
            LikeView.access$800(LikeView.this).onError(ex);
        }
        LikeView.access$1202(LikeView.this, null);
    }
}
