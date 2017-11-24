package com.facebook.share.internal;

import com.facebook.internal.*;
import com.facebook.*;
import android.content.*;
import com.facebook.share.*;

static final class ShareInternalUtility$3 implements Callback {
    final /* synthetic */ FacebookCallback val$callback;
    final /* synthetic */ int val$requestCode;
    
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return ShareInternalUtility.handleActivityResult(this.val$requestCode, n, intent, ShareInternalUtility.getShareResultProcessor(this.val$callback));
    }
}