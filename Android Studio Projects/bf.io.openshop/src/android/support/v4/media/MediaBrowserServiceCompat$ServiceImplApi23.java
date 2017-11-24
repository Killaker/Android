package android.support.v4.media;

import android.support.v4.os.*;
import android.os.*;

private class ServiceImplApi23 extends MediaBrowserServiceCompat.ServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceImplApi23
{
    @Override
    public void getMediaItem(final String s, final ItemCallback itemCallback) {
        this.mServiceImpl.getMediaItem(s, new ResultReceiver(MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this)) {
            @Override
            protected void onReceiveResult(final int n, final Bundle bundle) {
                final MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem)bundle.getParcelable("media_item");
                Parcel obtain = null;
                if (mediaItem != null) {
                    obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                }
                itemCallback.onItemLoaded(n, bundle, obtain);
            }
        });
    }
}
