package com.facebook.share.internal;

import android.content.*;
import com.facebook.*;
import com.facebook.internal.*;

static final class LikeActionController$1 implements CreationCallback {
    final /* synthetic */ Intent val$data;
    final /* synthetic */ int val$requestCode;
    final /* synthetic */ int val$resultCode;
    
    @Override
    public void onComplete(final LikeActionController likeActionController, final FacebookException ex) {
        if (ex == null) {
            LikeActionController.access$000(likeActionController, this.val$requestCode, this.val$resultCode, this.val$data);
            return;
        }
        Utility.logd(LikeActionController.access$100(), ex);
    }
}