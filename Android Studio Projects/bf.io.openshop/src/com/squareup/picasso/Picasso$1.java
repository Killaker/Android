package com.squareup.picasso;

import android.os.*;
import java.util.*;

static final class Picasso$1 extends Handler {
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                throw new AssertionError((Object)("Unknown handler message received: " + message.what));
            }
            case 8: {
                final List list = (List)message.obj;
                for (int i = 0; i < list.size(); ++i) {
                    final BitmapHunter bitmapHunter = list.get(i);
                    bitmapHunter.picasso.complete(bitmapHunter);
                }
                break;
            }
            case 3: {
                final Action action = (Action)message.obj;
                if (action.getPicasso().loggingEnabled) {
                    Utils.log("Main", "canceled", action.request.logId(), "target got garbage collected");
                }
                Picasso.access$000(action.picasso, action.getTarget());
                break;
            }
            case 13: {
                final List list2 = (List)message.obj;
                for (int j = 0; j < list2.size(); ++j) {
                    final Action action2 = list2.get(j);
                    action2.picasso.resumeAction(action2);
                }
                break;
            }
        }
    }
}