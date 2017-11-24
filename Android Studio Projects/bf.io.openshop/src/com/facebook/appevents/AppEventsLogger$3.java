package com.facebook.appevents;

static final class AppEventsLogger$3 implements Runnable {
    @Override
    public void run() {
        if (AppEventsLogger.getFlushBehavior() != FlushBehavior.EXPLICIT_ONLY) {
            AppEventsLogger.access$300(FlushReason.TIMER);
        }
    }
}