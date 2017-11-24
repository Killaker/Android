package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;

private class PublishLikeRequestWrapper extends AbstractRequestWrapper
{
    String unlikeToken;
    
    PublishLikeRequestWrapper(final String s, final LikeView.ObjectType objectType) {
        super(s, objectType);
        final Bundle bundle = new Bundle();
        bundle.putString("object", s);
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.POST));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        if (facebookRequestError.getErrorCode() == 3501) {
            this.error = null;
            return;
        }
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error liking object '%s' with type '%s' : %s", this.objectId, this.objectType, facebookRequestError);
        LikeActionController.access$2400(LikeActionController.this, "publish_like", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
        this.unlikeToken = Utility.safeGetStringFromResponse(graphResponse.getJSONObject(), "id");
    }
}
