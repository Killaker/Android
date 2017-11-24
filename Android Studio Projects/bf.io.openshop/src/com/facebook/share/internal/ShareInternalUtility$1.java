package com.facebook.share.internal;

import com.facebook.internal.*;
import com.facebook.share.*;
import com.facebook.*;
import android.os.*;

static final class ShareInternalUtility$1 extends ResultProcessor {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public void onCancel(final AppCall appCall) {
        ShareInternalUtility.invokeOnCancelCallback(this.val$callback);
    }
    
    @Override
    public void onError(final AppCall appCall, final FacebookException ex) {
        ShareInternalUtility.invokeOnErrorCallback(this.val$callback, ex);
    }
    
    @Override
    public void onSuccess(final AppCall appCall, final Bundle bundle) {
        if (bundle != null) {
            final String nativeDialogCompletionGesture = ShareInternalUtility.getNativeDialogCompletionGesture(bundle);
            if (nativeDialogCompletionGesture == null || "post".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                ShareInternalUtility.invokeOnSuccessCallback(this.val$callback, ShareInternalUtility.getShareDialogPostId(bundle));
            }
            else {
                if ("cancel".equalsIgnoreCase(nativeDialogCompletionGesture)) {
                    ShareInternalUtility.invokeOnCancelCallback(this.val$callback);
                    return;
                }
                ShareInternalUtility.invokeOnErrorCallback(this.val$callback, new FacebookException("UnknownError"));
            }
        }
    }
}