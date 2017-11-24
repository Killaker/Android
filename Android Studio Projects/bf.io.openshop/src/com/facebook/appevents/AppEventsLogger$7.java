package com.facebook.appevents;

import com.facebook.*;

static final class AppEventsLogger$7 implements Callback {
    final /* synthetic */ AccessTokenAppIdPair val$accessTokenAppId;
    final /* synthetic */ FlushStatistics val$flushState;
    final /* synthetic */ GraphRequest val$postRequest;
    final /* synthetic */ SessionEventsState val$sessionEventsState;
    
    @Override
    public void onCompleted(final GraphResponse graphResponse) {
        AppEventsLogger.access$1000(this.val$accessTokenAppId, this.val$postRequest, graphResponse, this.val$sessionEventsState, this.val$flushState);
    }
}