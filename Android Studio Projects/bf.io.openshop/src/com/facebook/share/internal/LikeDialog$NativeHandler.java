package com.facebook.share.internal;

import com.facebook.internal.*;
import android.os.*;
import android.util.*;

private class NativeHandler extends ModeHandler
{
    public boolean canShow(final LikeContent likeContent) {
        return likeContent != null && LikeDialog.canShowNativeDialog();
    }
    
    public AppCall createAppCall(final LikeContent likeContent) {
        final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForNativeDialog(baseAppCall, (DialogPresenter.ParameterProvider)new DialogPresenter.ParameterProvider() {
            @Override
            public Bundle getLegacyParameters() {
                Log.e("LikeDialog", "Attempting to present the Like Dialog with an outdated Facebook app on the device");
                return new Bundle();
            }
            
            @Override
            public Bundle getParameters() {
                return LikeDialog.access$200(likeContent);
            }
        }, LikeDialog.access$300());
        return baseAppCall;
    }
}
