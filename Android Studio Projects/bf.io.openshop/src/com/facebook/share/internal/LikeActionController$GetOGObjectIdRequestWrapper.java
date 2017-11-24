package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;

private class GetOGObjectIdRequestWrapper extends AbstractRequestWrapper
{
    String verifiedObjectId;
    
    GetOGObjectIdRequestWrapper(final String s, final LikeView.ObjectType objectType) {
        super(s, objectType);
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "og_object.fields(id)");
        bundle.putString("ids", s);
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "", bundle, HttpMethod.GET));
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        if (facebookRequestError.getErrorMessage().contains("og_object")) {
            this.error = null;
            return;
        }
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error getting the FB id for object '%s' with type '%s' : %s", this.objectId, this.objectType, facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
        final JSONObject tryGetJSONObjectFromResponse = Utility.tryGetJSONObjectFromResponse(graphResponse.getJSONObject(), this.objectId);
        if (tryGetJSONObjectFromResponse != null) {
            final JSONObject optJSONObject = tryGetJSONObjectFromResponse.optJSONObject("og_object");
            if (optJSONObject != null) {
                this.verifiedObjectId = optJSONObject.optString("id");
            }
        }
    }
}
