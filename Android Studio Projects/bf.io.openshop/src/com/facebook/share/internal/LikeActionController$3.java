package com.facebook.share.internal;

import com.facebook.internal.*;
import android.content.*;

static final class LikeActionController$3 implements Callback {
    @Override
    public boolean onActivityResult(final int n, final Intent intent) {
        return LikeActionController.handleOnActivityResult(RequestCodeOffset.Like.toRequestCode(), n, intent);
    }
}