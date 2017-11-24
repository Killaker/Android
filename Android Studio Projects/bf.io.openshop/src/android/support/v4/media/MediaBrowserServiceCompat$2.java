package android.support.v4.media;

import android.os.*;
import java.util.*;

class MediaBrowserServiceCompat$2 implements Runnable {
    final /* synthetic */ Bundle val$options;
    final /* synthetic */ String val$parentId;
    
    @Override
    public void run() {
        final Iterator<IBinder> iterator = MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).keySet().iterator();
        while (iterator.hasNext()) {
            final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(iterator.next());
            final List<Bundle> list = connectionRecord.subscriptions.get(this.val$parentId);
            if (list != null) {
                for (final Bundle bundle : list) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(this.val$options, bundle)) {
                        MediaBrowserServiceCompat.access$1000(MediaBrowserServiceCompat.this, this.val$parentId, connectionRecord, bundle);
                        break;
                    }
                }
            }
        }
    }
}