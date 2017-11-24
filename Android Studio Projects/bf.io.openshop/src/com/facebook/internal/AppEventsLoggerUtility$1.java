package com.facebook.internal;

import java.util.*;

static final class AppEventsLoggerUtility$1 extends HashMap<GraphAPIActivityType, String> {
    {
        this.put(GraphAPIActivityType.MOBILE_INSTALL_EVENT, "MOBILE_APP_INSTALL");
        this.put(GraphAPIActivityType.CUSTOM_APP_EVENTS, "CUSTOM_APP_EVENTS");
    }
}