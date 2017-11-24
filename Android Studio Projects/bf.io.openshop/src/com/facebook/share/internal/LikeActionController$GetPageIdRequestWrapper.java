package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;

private class GetPageIdRequestWrapper extends AbstractRequestWrapper
{
    boolean objectIsPage;
    String verifiedObjectId;
    
    GetPageIdRequestWrapper(final String s, final LikeView.ObjectType objectType) {
        super(s, objectType);
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id");
        bundle.putString("ids", s);
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error getting the FB id for object '%s' with type '%s' : %s", this.objectId, this.objectType, facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
        final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(graphResponse.getJSONObject(), this.objectId);
        if (tryGetJSONObjectFromResponse != null) {
            this.verifiedObjectId = tryGetJSONObjectFromResponse.optString("id");
            this.objectIsPage = !Utility.isNullOrEmpty(this.verifiedObjectId);
        }
    }
}
