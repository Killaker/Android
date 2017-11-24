package com.facebook;

import org.json.*;

class AccessTokenManager$3 implements Callback {
    final /* synthetic */ RefreshResult val$refreshResult;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        final JSONObject jsonObject = graphResponse.getJSONObject();
        if (jsonObject == null) {
            return;
        }
        this.val$refreshResult.accessToken = jsonObject.optString("access_token");
        this.val$refreshResult.expiresAt = jsonObject.optInt("expires_at");
    }
}