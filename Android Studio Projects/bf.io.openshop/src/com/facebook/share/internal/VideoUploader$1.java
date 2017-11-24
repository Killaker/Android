package com.facebook.share.internal;

import com.facebook.*;
import com.facebook.internal.*;

static final class VideoUploader$1 extends AccessTokenTracker {
    @Override
    protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
        if (accessToken != null && (accessToken2 == null || !Utility.areObjectsEqual(accessToken2.getUserId(), accessToken.getUserId()))) {
            VideoUploader.access$200();
        }
    }
}