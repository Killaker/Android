package com.facebook.appevents;

import android.content.*;

static final class AppEventsLogger$5 implements Runnable {
    final /* synthetic */ AccessTokenAppIdPair val$accessTokenAppId;
    final /* synthetic */ Context val$context;
    final /* synthetic */ AppEvent val$event;
    
    @Override
    public void run() {
        AppEventsLogger.access$600(this.val$context, this.val$accessTokenAppId).addEvent(this.val$event);
        AppEventsLogger.access$700();
    }
}