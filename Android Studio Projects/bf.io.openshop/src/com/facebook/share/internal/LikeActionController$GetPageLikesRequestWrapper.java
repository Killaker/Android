package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;

private class GetPageLikesRequestWrapper extends AbstractRequestWrapper implements LikeRequestWrapper
{
    private boolean objectIsLiked;
    private String pageId;
    
    GetPageLikesRequestWrapper(final String pageId) {
        super(pageId, LikeView.ObjectType.PAGE);
        this.objectIsLiked = LikeActionController.access$2500(LikeActionController.this);
        this.pageId = pageId;
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/likes/" + pageId, bundle, HttpMethod.GET));
    }
    
    @Override
    public String getUnlikeToken() {
        return null;
    }
    
    @Override
    public boolean isObjectLiked() {
        return this.objectIsLiked;
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error fetching like status for page id '%s': %s", this.pageId, facebookRequestError);
        LikeActionController.access$2400(LikeActionController.this, "get_page_like", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
        final JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(graphResponse.getJSONObject(), "data");
        if (tryGetJSONArrayFromResponse != null && tryGetJSONArrayFromResponse.length() > 0) {
            this.objectIsLiked = true;
        }
    }
}
