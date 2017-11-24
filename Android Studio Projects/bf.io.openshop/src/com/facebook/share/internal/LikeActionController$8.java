package com.facebook.share.internal;

import com.facebook.*;
import android.os.*;

class LikeActionController$8 implements Callback {
    final /* synthetic */ Bundle val$analyticsParameters;
    final /* synthetic */ PublishUnlikeRequestWrapper val$unlikeRequest;
    
    @Override
    public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
        LikeActionController.access$1802(LikeActionController.this, false);
        if (((AbstractRequestWrapper)this.val$unlikeRequest).getError() != null) {
            LikeActionController.access$1900(LikeActionController.this, true);
            return;
        }
        LikeActionController.access$1102(LikeActionController.this, null);
        LikeActionController.access$2002(LikeActionController.this, false);
        LikeActionController.access$1200(LikeActionController.this).logSdkEvent("fb_like_control_did_unlike", null, this.val$analyticsParameters);
        LikeActionController.access$2100(LikeActionController.this, this.val$analyticsParameters);
    }
}