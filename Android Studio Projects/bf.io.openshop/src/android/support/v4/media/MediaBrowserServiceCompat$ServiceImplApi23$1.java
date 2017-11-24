package android.support.v4.media;

import android.support.v4.os.*;
import android.os.*;

class MediaBrowserServiceCompat$ServiceImplApi23$1 extends ResultReceiver {
    final /* synthetic */ ItemCallback val$cb;
    
    @Override
    protected void onReceiveResult(final int n, final Bundle bundle) {
        final MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem)bundle.getParcelable("media_item");
        Parcel obtain = null;
        if (mediaItem != null) {
            obtain = Parcel.obtain();
            mediaItem.writeToParcel(obtain, 0);
        }
        this.val$cb.onItemLoaded(n, bundle, obtain);
    }
}