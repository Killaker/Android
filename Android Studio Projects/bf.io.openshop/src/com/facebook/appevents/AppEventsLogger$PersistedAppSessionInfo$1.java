package com.facebook.appevents;

static final class AppEventsLogger$PersistedAppSessionInfo$1 implements Runnable {
    @Override
    public void run() {
        PersistedAppSessionInfo.saveAppSessionInformation(AppEventsLogger.access$1100());
    }
}