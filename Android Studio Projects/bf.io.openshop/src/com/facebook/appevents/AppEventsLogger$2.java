package com.facebook.appevents;

static final class AppEventsLogger$2 implements Runnable {
    final /* synthetic */ long val$eventTime;
    final /* synthetic */ AppEventsLogger val$logger;
    
    @Override
    public void run() {
        AppEventsLogger.access$200(this.val$logger, this.val$eventTime);
    }
}