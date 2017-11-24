package android.support.v4.content;

import android.os.*;

class LocalBroadcastManager$1 extends Handler {
    public void handleMessage(final Message message) {
        switch (message.what) {
            default: {
                super.handleMessage(message);
            }
            case 1: {
                LocalBroadcastManager.access$000(LocalBroadcastManager.this);
            }
        }
    }
}