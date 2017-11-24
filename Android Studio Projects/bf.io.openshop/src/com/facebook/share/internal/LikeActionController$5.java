package com.facebook.share.internal;

import com.facebook.*;
import android.content.*;

static final class LikeActionController$5 extends AccessTokenTracker {
    @Override
    protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        if (accessToken2 == null) {
            LikeActionController.access$302((1 + LikeActionController.access$300()) % 1000);
            applicationContext.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putInt("OBJECT_SUFFIX", LikeActionController.access$300()).apply();
            LikeActionController.access$400().clear();
            LikeActionController.access$500().clearCache();
        }
        LikeActionController.access$600(null, "com.facebook.sdk.LikeActionController.DID_RESET");
    }
}