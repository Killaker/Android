package com.facebook.appevents;

static final class AppEventsLogger$1 implements Runnable {
    final /* synthetic */ long val$eventTime;
    final /* synthetic */ AppEventsLogger val$logger;
    final /* synthetic */ String val$sourceApplicationInfo;
    
    @Override
    public void run() {
        AppEventsLogger.access$100(this.val$logger, this.val$eventTime, this.val$sourceApplicationInfo);
    }
}