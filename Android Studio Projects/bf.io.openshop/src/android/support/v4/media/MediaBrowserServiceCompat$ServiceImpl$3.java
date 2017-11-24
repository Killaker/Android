package android.support.v4.media;

import android.os.*;
import android.util.*;

class MediaBrowserServiceCompat$ServiceImpl$3 implements Runnable {
    final /* synthetic */ ServiceCallbacks val$callbacks;
    final /* synthetic */ String val$id;
    final /* synthetic */ Bundle val$options;
    
    @Override
    public void run() {
        final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).get(this.val$callbacks.asBinder());
        if (connectionRecord == null) {
            Log.w("MediaBrowserServiceCompat", "addSubscription for callback that isn't registered id=" + this.val$id);
            return;
        }
        MediaBrowserServiceCompat.access$700(ServiceImpl.this.this$0, this.val$id, connectionRecord, this.val$options);
    }
}