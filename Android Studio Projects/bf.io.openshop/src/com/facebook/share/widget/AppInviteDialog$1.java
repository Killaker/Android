package com.facebook.share.widget;

import com.facebook.*;
import com.facebook.internal.*;
import android.os.*;
import com.facebook.share.internal.*;

class AppInviteDialog$1 extends ResultProcessor {
    final /* synthetic */ FacebookCallback val$callback;
    
    @Override
    public void onSuccess(final AppCall appCall, final Bundle bundle) {
        if ("cancel".equalsIgnoreCase(ShareInternalUtility.getNativeDialogCompletionGesture(bundle))) {
            this.val$callback.onCancel();
            return;
        }
        this.val$callback.onSuccess(new Result(bundle));
    }
}