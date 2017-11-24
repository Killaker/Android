package android.support.v4.media;

import android.os.*;
import android.util.*;

class MediaBrowserServiceCompat$ServiceImpl$4 implements Runnable {
    final /* synthetic */ ServiceCallbacks val$callbacks;
    final /* synthetic */ String val$id;
    final /* synthetic */ Bundle val$options;
    
    @Override
    public void run() {
        final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).get(this.val$callbacks.asBinder());
        if (connectionRecord == null) {
            Log.w("MediaBrowserServiceCompat", "removeSubscription for callback that isn't registered id=" + this.val$id);
        }
        else if (!MediaBrowserServiceCompat.access$800(ServiceImpl.this.this$0, this.val$id, connectionRecord, this.val$options)) {
            Log.w("MediaBrowserServiceCompat", "removeSubscription called for " + this.val$id + " which is not subscribed");
        }
    }
}