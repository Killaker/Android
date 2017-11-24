package com.facebook.share.internal;

import com.facebook.*;
import com.facebook.internal.*;

class LikeActionController$9$1 implements Callback {
    final /* synthetic */ GetEngagementRequestWrapper val$engagementRequest;
    final /* synthetic */ LikeRequestWrapper val$likeRequestWrapper;
    
    @Override
    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
        if (((RequestWrapper)this.val$likeRequestWrapper).getError() != null || ((AbstractRequestWrapper)this.val$engagementRequest).getError() != null) {
            Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Unable to refresh like state for id: '%s'", LikeActionController.access$2200(RequestCompletionCallback.this.this$0));
            return;
        }
        LikeActionController.access$1300(RequestCompletionCallback.this.this$0, this.val$likeRequestWrapper.isObjectLiked(), this.val$engagementRequest.likeCountStringWithLike, this.val$engagementRequest.likeCountStringWithoutLike, this.val$engagementRequest.socialSentenceStringWithLike, this.val$engagementRequest.socialSentenceStringWithoutLike, this.val$likeRequestWrapper.getUnlikeToken());
    }
}