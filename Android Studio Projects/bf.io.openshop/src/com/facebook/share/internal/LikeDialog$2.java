package com.facebook.share.internal;

import com.facebook.internal.*;
import android.content.*;

class LikeDialog$2 implements Callback {
    final /* synthetic */ ResultProcessor val$resultProcessor;
    
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return ShareInternalUtility.handleActivityResult(LikeDialog.this.getRequestCode(), n, intent, this.val$resultProcessor);
    }
}