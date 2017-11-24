package com.facebook.share.internal;

import com.facebook.internal.*;
import com.facebook.*;

class LikeActionController$11 implements Callback {
    final /* synthetic */ RequestCompletionCallback val$completionHandler;
    final /* synthetic */ GetOGObjectIdRequestWrapper val$objectIdRequest;
    final /* synthetic */ GetPageIdRequestWrapper val$pageIdRequest;
    
    @Override
    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
        LikeActionController.access$1602(LikeActionController.this, this.val$objectIdRequest.verifiedObjectId);
        if (Utility.isNullOrEmpty(LikeActionController.access$1600(LikeActionController.this))) {
            LikeActionController.access$1602(LikeActionController.this, this.val$pageIdRequest.verifiedObjectId);
            LikeActionController.access$2302(LikeActionController.this, this.val$pageIdRequest.objectIsPage);
        }
        if (Utility.isNullOrEmpty(LikeActionController.access$1600(LikeActionController.this))) {
            Logger.log(LoggingBehavior.DEVELOPER_ERRORS, LikeActionController.access$100(), "Unable to verify the FB id for '%s'. Verify that it is a valid FB object or page", LikeActionController.access$2200(LikeActionController.this));
            final LikeActionController this$0 = LikeActionController.this;
            FacebookRequestError facebookRequestError;
            if (((AbstractRequestWrapper)this.val$pageIdRequest).getError() != null) {
                facebookRequestError = ((AbstractRequestWrapper)this.val$pageIdRequest).getError();
            }
            else {
                facebookRequestError = ((AbstractRequestWrapper)this.val$objectIdRequest).getError();
            }
            LikeActionController.access$2400(this$0, "get_verified_id", facebookRequestError);
        }
        if (this.val$completionHandler != null) {
            this.val$completionHandler.onComplete();
        }
    }
}