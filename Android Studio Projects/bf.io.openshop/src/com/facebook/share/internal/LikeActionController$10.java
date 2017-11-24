package com.facebook.share.internal;

import com.facebook.internal.*;
import android.os.*;

class LikeActionController$10 implements CompletedListener {
    @Override
    public void completed(final Bundle bundle) {
        if (bundle == null || !bundle.containsKey("com.facebook.platform.extra.OBJECT_IS_LIKED")) {
            return;
        }
        final boolean boolean1 = bundle.getBoolean("com.facebook.platform.extra.OBJECT_IS_LIKED");
        String s;
        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE")) {
            s = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE");
        }
        else {
            s = LikeActionController.access$700(LikeActionController.this);
        }
        String s2;
        if (bundle.containsKey("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE")) {
            s2 = bundle.getString("com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE");
        }
        else {
            s2 = LikeActionController.access$800(LikeActionController.this);
        }
        String s3;
        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE")) {
            s3 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE");
        }
        else {
            s3 = LikeActionController.access$900(LikeActionController.this);
        }
        String s4;
        if (bundle.containsKey("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE")) {
            s4 = bundle.getString("com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE");
        }
        else {
            s4 = LikeActionController.access$1000(LikeActionController.this);
        }
        String s5;
        if (bundle.containsKey("com.facebook.platform.extra.UNLIKE_TOKEN")) {
            s5 = bundle.getString("com.facebook.platform.extra.UNLIKE_TOKEN");
        }
        else {
            s5 = LikeActionController.access$1100(LikeActionController.this);
        }
        LikeActionController.access$1300(LikeActionController.this, boolean1, s, s2, s3, s4, s5);
    }
}