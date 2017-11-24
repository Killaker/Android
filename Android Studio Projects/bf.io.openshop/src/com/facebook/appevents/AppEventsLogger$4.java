package com.facebook.appevents;

import com.facebook.internal.*;
import java.util.*;

static final class AppEventsLogger$4 implements Runnable {
    @Override
    public void run() {
        final HashSet<String> set = new HashSet<String>();
        synchronized (AppEventsLogger.access$400()) {
            final Iterator<AccessTokenAppIdPair> iterator = AppEventsLogger.access$500().keySet().iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next().getApplicationId());
            }
        }
        // monitorexit(o)
        final Iterator<Object> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            Utility.queryAppSettings(iterator2.next(), true);
        }
    }
}