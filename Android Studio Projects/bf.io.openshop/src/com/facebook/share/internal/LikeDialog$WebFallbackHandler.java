package com.facebook.share.internal;

import com.facebook.internal.*;

private class WebFallbackHandler extends ModeHandler
{
    public boolean canShow(final LikeContent likeContent) {
        return likeContent != null && LikeDialog.canShowWebFallback();
    }
    
    public AppCall createAppCall(final LikeContent likeContent) {
        final AppCall baseAppCall = LikeDialog.this.createBaseAppCall();
        DialogPresenter.setupAppCallForWebFallbackDialog(baseAppCall, LikeDialog.access$200(likeContent), LikeDialog.access$300());
        return baseAppCall;
    }
}
