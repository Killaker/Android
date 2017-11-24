package com.facebook.share.internal;

import com.facebook.*;
import com.facebook.internal.*;
import com.facebook.share.widget.*;

class LikeActionController$9 implements RequestCompletionCallback {
    @Override
    public void onComplete() {
        LikeRequestWrapper likeRequestWrapper = null;
        switch (LikeActionController.access$1700(LikeActionController.this)) {
            default: {
                likeRequestWrapper = new GetOGObjectLikesRequestWrapper(LikeActionController.access$1600(LikeActionController.this), LikeActionController.access$1700(LikeActionController.this));
                break;
            }
            case PAGE: {
                likeRequestWrapper = new GetPageLikesRequestWrapper(LikeActionController.access$1600(LikeActionController.this));
                break;
            }
        }
        final GetEngagementRequestWrapper getEngagementRequestWrapper = new GetEngagementRequestWrapper(LikeActionController.access$1600(LikeActionController.this), LikeActionController.access$1700(LikeActionController.this));
        final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
        ((RequestWrapper)likeRequestWrapper).addToBatch(graphRequestBatch);
        ((AbstractRequestWrapper)getEngagementRequestWrapper).addToBatch(graphRequestBatch);
        graphRequestBatch.addCallback((GraphRequestBatch.Callback)new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                if (((RequestWrapper)likeRequestWrapper).getError() != null || ((AbstractRequestWrapper)getEngagementRequestWrapper).getError() != null) {
                    Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Unable to refresh like state for id: '%s'", LikeActionController.access$2200(LikeActionController.this));
                    return;
                }
                LikeActionController.access$1300(LikeActionController.this, likeRequestWrapper.isObjectLiked(), getEngagementRequestWrapper.likeCountStringWithLike, getEngagementRequestWrapper.likeCountStringWithoutLike, getEngagementRequestWrapper.socialSentenceStringWithLike, getEngagementRequestWrapper.socialSentenceStringWithoutLike, likeRequestWrapper.getUnlikeToken());
            }
        });
        graphRequestBatch.executeAsync();
    }
}