package com.facebook.share.widget;

import android.content.*;
import com.facebook.internal.*;
import android.os.*;

private class LikeControllerBroadcastReceiver extends BroadcastReceiver
{
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        final Bundle extras = intent.getExtras();
        int n = 1;
        if (extras != null) {
            final String string = extras.getString("com.facebook.sdk.LikeActionController.OBJECT_ID");
            if (Utility.isNullOrEmpty(string) || Utility.areObjectsEqual(LikeView.access$600(LikeView.this), string)) {
                n = 1;
            }
            else {
                n = 0;
            }
        }
        if (n != 0) {
            if ("com.facebook.sdk.LikeActionController.UPDATED".equals(action)) {
                LikeView.access$700(LikeView.this);
                return;
            }
            if ("com.facebook.sdk.LikeActionController.DID_ERROR".equals(action)) {
                if (LikeView.access$800(LikeView.this) != null) {
                    LikeView.access$800(LikeView.this).onError(NativeProtocol.getExceptionFromErrorData(extras));
                }
            }
            else if ("com.facebook.sdk.LikeActionController.DID_RESET".equals(action)) {
                LikeView.access$1000(LikeView.this, LikeView.access$600(LikeView.this), LikeView.access$900(LikeView.this));
                LikeView.access$700(LikeView.this);
            }
        }
    }
}
