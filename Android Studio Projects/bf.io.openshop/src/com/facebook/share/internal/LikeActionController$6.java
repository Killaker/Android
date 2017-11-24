package com.facebook.share.internal;

import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;

class LikeActionController$6 extends ResultProcessor {
    final /* synthetic */ Bundle val$analyticsParameters;
    
    @Override
    public void onCancel(final AppCall appCall) {
        this.onError(appCall, new FacebookOperationCanceledException());
    }
    
    @Override
    public void onError(final AppCall appCall, final FacebookException ex) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Like Dialog failed with error : %s", ex);
        Bundle val$analyticsParameters;
        if (this.val$analyticsParameters == null) {
            val$analyticsParameters = new Bundle();
        }
        else {
            val$analyticsParameters = this.val$analyticsParameters;
        }
        val$analyticsParameters.putString("call_id", appCall.getCallId().toString());
        LikeActionController.access$1400(LikeActionController.this, "present_dialog", val$analyticsParameters);
        LikeActionController.access$1500(LikeActionController.this, "com.facebook.sdk.LikeActionController.DID_ERROR", NativeProtocol.createBundleForException(ex));
    }
    
    @Override
    public void onSuccess(final AppCall appCall, final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("object_is_liked")) {
            return;
        }
        final boolean boolean1 = bundle.getBoolean("object_is_liked");
        String access$700 = LikeActionController.access$700(LikeActionController.this);
        String s = LikeActionController.access$800(LikeActionController.this);
        if (bundle.containsKey("like_count_string")) {
            access$700 = (s = bundle.getString("like_count_string"));
        }
        String access$701 = LikeActionController.access$900(LikeActionController.this);
        String s2 = LikeActionController.access$1000(LikeActionController.this);
        if (bundle.containsKey("social_sentence")) {
            access$701 = (s2 = bundle.getString("social_sentence"));
        }
        String s3;
        if (bundle.containsKey("object_is_liked")) {
            s3 = bundle.getString("unlike_token");
        }
        else {
            s3 = LikeActionController.access$1100(LikeActionController.this);
        }
        Bundle val$analyticsParameters;
        if (this.val$analyticsParameters == null) {
            val$analyticsParameters = new Bundle();
        }
        else {
            val$analyticsParameters = this.val$analyticsParameters;
        }
        val$analyticsParameters.putString("call_id", appCall.getCallId().toString());
        LikeActionController.access$1200(LikeActionController.this).logSdkEvent("fb_like_control_dialog_did_succeed", null, val$analyticsParameters);
        LikeActionController.access$1300(LikeActionController.this, boolean1, access$700, s, access$701, s2, s3);
    }
}