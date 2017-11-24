package android.support.v4.media;

import java.util.*;
import android.util.*;
import android.os.*;

class MediaBrowserServiceCompat$3 extends Result<List<MediaBrowserCompat.MediaItem>> {
    final /* synthetic */ ConnectionRecord val$connection;
    final /* synthetic */ Bundle val$options;
    final /* synthetic */ String val$parentId;
    
    void onResultSent(final List<MediaBrowserCompat.MediaItem> list, final int n) {
        if (MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(this.val$connection.callbacks.asBinder()) != this.val$connection) {
            return;
        }
        while (true) {
            Label_0113: {
                if ((n & 0x1) == 0x0) {
                    break Label_0113;
                }
                final List<MediaBrowserCompat.MediaItem> applyOptions = MediaBrowserCompatUtils.applyOptions(list, this.val$options);
                try {
                    this.val$connection.callbacks.onLoadChildren(this.val$parentId, applyOptions, this.val$options);
                    return;
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserServiceCompat", "Calling onLoadChildren() failed for id=" + this.val$parentId + " package=" + this.val$connection.pkg);
                    return;
                }
            }
            final List<MediaBrowserCompat.MediaItem> applyOptions = list;
            continue;
        }
    }
}