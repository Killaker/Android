package com.facebook.share.internal;

import android.os.*;
import com.facebook.internal.*;
import com.facebook.*;

class LikeActionController$7 implements RequestCompletionCallback {
    final /* synthetic */ Bundle val$analyticsParameters;
    
    @Override
    public void onComplete() {
        if (Utility.isNullOrEmpty(LikeActionController.access$1600(LikeActionController.this))) {
            final Bundle bundle = new Bundle();
            bundle.putString("com.facebook.platform.status.ERROR_DESCRIPTION", "Invalid Object Id");
            LikeActionController.access$1500(LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", bundle);
            return;
        }
        final GraphRequestBatch graphRequestBatch = new GraphRequestBatch();
        final PublishLikeRequestWrapper publishLikeRequestWrapper = new PublishLikeRequestWrapper(LikeActionController.access$1600(LikeActionController.this), LikeActionController.access$1700(LikeActionController.this));
        ((AbstractRequestWrapper)publishLikeRequestWrapper).addToBatch(graphRequestBatch);
        graphRequestBatch.addCallback((GraphRequestBatch.Callback)new GraphRequestBatch.Callback() {
            @Override
            public void onBatchCompleted(final GraphRequestBatch graphRequestBatch) {
                LikeActionController.access$1802(LikeActionController.this, false);
                if (((AbstractRequestWrapper)publishLikeRequestWrapper).getError() != null) {
                    LikeActionController.access$1900(LikeActionController.this, false);
                    return;
                }
                LikeActionController.access$1102(LikeActionController.this, Utility.coerceValueIfNullOrEmpty(publishLikeRequestWrapper.unlikeToken, null));
                LikeActionController.access$2002(LikeActionController.this, true);
                LikeActionController.access$1200(LikeActionController.this).logSdkEvent("fb_like_control_did_like", null, RequestCompletionCallback.this.val$analyticsParameters);
                LikeActionController.access$2100(LikeActionController.this, RequestCompletionCallback.this.val$analyticsParameters);
            }
        });
        graphRequestBatch.executeAsync();
    }
}