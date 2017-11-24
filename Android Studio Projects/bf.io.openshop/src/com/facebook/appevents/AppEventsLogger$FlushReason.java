package com.facebook.appevents;

private enum FlushReason
{
    EAGER_FLUSHING_EVENT, 
    EVENT_THRESHOLD, 
    EXPLICIT, 
    PERSISTED_EVENTS, 
    SESSION_CHANGE, 
    TIMER;
}
