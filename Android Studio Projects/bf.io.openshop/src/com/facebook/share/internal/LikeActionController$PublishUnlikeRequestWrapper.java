package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.internal.*;
import com.facebook.*;

private class PublishUnlikeRequestWrapper extends AbstractRequestWrapper
{
    private String unlikeToken;
    
    PublishUnlikeRequestWrapper(final String unlikeToken) {
        super(null, null);
        this.unlikeToken = unlikeToken;
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), unlikeToken, null, HttpMethod.DELETE));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error unliking object with unlike token '%s' : %s", this.unlikeToken, facebookRequestError);
        LikeActionController.access$2400(LikeActionController.this, "publish_unlike", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
    }
}
