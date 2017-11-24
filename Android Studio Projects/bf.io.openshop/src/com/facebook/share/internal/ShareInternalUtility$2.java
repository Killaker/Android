package com.facebook.share.internal;

import com.facebook.internal.*;
import android.content.*;
import com.facebook.*;
import com.facebook.share.*;

static final class ShareInternalUtility$2 implements Callback {
    final /* synthetic */ int val$requestCode;
    
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return ShareInternalUtility.handleActivityResult(this.val$requestCode, n, intent, ShareInternalUtility.getShareResultProcessor(null));
    }
}