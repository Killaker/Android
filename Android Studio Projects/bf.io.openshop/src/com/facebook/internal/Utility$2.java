package com.facebook.internal;

import com.facebook.*;

static final class Utility$2 implements Callback {
    final /* synthetic */ String val$accessToken;
    final /* synthetic */ GraphMeRequestWithCacheCallback val$callback;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        if (graphResponse.getError() != null) {
            this.val$callback.onFailure(graphResponse.getError().getException());
            return;
        }
        ProfileInformationCache.putProfileInformation(this.val$accessToken, graphResponse.getJSONObject());
        this.val$callback.onSuccess(graphResponse.getJSONObject());
    }
}