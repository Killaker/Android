package android.support.v4.media;

import android.support.v4.os.*;
import android.os.*;

class MediaBrowserServiceCompat$4 extends Result<MediaBrowserCompat.MediaItem> {
    final /* synthetic */ ResultReceiver val$receiver;
    
    void onResultSent(final MediaBrowserCompat.MediaItem mediaItem, final int n) {
        final Bundle bundle = new Bundle();
        bundle.putParcelable("media_item", (Parcelable)mediaItem);
        this.val$receiver.send(0, bundle);
    }
}