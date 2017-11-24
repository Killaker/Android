package com.facebook.share.internal;

import com.facebook.share.widget.*;
import android.os.*;
import com.facebook.*;
import com.facebook.internal.*;
import org.json.*;

private class GetOGObjectLikesRequestWrapper extends AbstractRequestWrapper implements LikeRequestWrapper
{
    private final String objectId;
    private boolean objectIsLiked;
    private final LikeView.ObjectType objectType;
    private String unlikeToken;
    
    GetOGObjectLikesRequestWrapper(final String objectId, final LikeView.ObjectType objectType) {
        super(objectId, objectType);
        this.objectIsLiked = LikeActionController.access$2500(LikeActionController.this);
        this.objectId = objectId;
        this.objectType = objectType;
        final Bundle bundle = new Bundle();
        bundle.putString("fields", "id,application");
        bundle.putString("object", this.objectId);
        ((AbstractRequestWrapper)this).setRequest(new GraphRequest(AccessToken.getCurrentAccessToken(), "me/og.likes", bundle, HttpMethod.GET));
    }
    
    @Override
    public String getUnlikeToken() {
        return this.unlikeToken;
    }
    
    @Override
    public boolean isObjectLiked() {
        return this.objectIsLiked;
    }
    
    @Override
    protected void processError(final FacebookRequestError facebookRequestError) {
        Logger.log(LoggingBehavior.REQUESTS, LikeActionController.access$100(), "Error fetching like status for object '%s' with type '%s' : %s", this.objectId, this.objectType, facebookRequestError);
        LikeActionController.access$2400(LikeActionController.this, "get_og_object_like", facebookRequestError);
    }
    
    @Override
    protected void processSuccess(final GraphResponse graphResponse) {
        final JSONArray tryGetJSONArrayFromResponse = Utility.tryGetJSONArrayFromResponse(graphResponse.getJSONObject(), "data");
        if (tryGetJSONArrayFromResponse != null) {
            for (int i = 0; i < tryGetJSONArrayFromResponse.length(); ++i) {
                final JSONObject optJSONObject = tryGetJSONArrayFromResponse.optJSONObject(i);
                if (optJSONObject != null) {
                    this.objectIsLiked = true;
                    final JSONObject optJSONObject2 = optJSONObject.optJSONObject("application");
                    final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                    if (optJSONObject2 != null && currentAccessToken != null && Utility.areObjectsEqual(currentAccessToken.getApplicationId(), optJSONObject2.optString("id"))) {
                        this.unlikeToken = optJSONObject.optString("id");
                    }
                }
            }
        }
    }
}
