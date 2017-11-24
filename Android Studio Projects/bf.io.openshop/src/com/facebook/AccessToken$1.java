package com.facebook;

import com.facebook.internal.*;
import android.os.*;
import java.util.*;
import org.json.*;

static final class AccessToken$1 implements GraphMeRequestWithCacheCallback {
    final /* synthetic */ AccessTokenCreationCallback val$accessTokenCallback;
    final /* synthetic */ String val$applicationId;
    final /* synthetic */ Bundle val$extras;
    
    @Override
    public void onFailure(final FacebookException ex) {
        this.val$accessTokenCallback.onError(ex);
    }
    
    @Override
    public void onSuccess(final JSONObject jsonObject) {
        try {
            this.val$extras.putString("user_id", jsonObject.getString("id"));
            this.val$accessTokenCallback.onSuccess(AccessToken.access$000(null, this.val$extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, new Date(), this.val$applicationId));
        }
        catch (JSONException ex) {
            this.val$accessTokenCallback.onError(new FacebookException("Unable to generate access token due to missing user id"));
        }
    }
}