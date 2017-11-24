package com.facebook.share.internal;

import com.facebook.*;
import com.facebook.internal.*;

class LikeActionController$7$1 implements Callback {
    final /* synthetic */ PublishLikeRequestWrapper val$likeRequest;
    
    @Override
    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
        LikeActionController.access$1802(RequestCompletionCallback.this.this$0, false);
        if (((AbstractRequestWrapper)this.val$likeRequest).getError() != null) {
            LikeActionController.access$1900(RequestCompletionCallback.this.this$0, false);
            return;
        }
        LikeActionController.access$1102(RequestCompletionCallback.this.this$0, Utility.coerceValueIfNullOrEmpty(this.val$likeRequest.unlikeToken, null));
        LikeActionController.access$2002(RequestCompletionCallback.this.this$0, true);
        LikeActionController.access$1200(RequestCompletionCallback.this.this$0).logSdkEvent("fb_like_control_did_like", null, RequestCompletionCallback.this.val$analyticsParameters);
        LikeActionController.access$2100(RequestCompletionCallback.this.this$0, RequestCompletionCallback.this.val$analyticsParameters);
    }
}