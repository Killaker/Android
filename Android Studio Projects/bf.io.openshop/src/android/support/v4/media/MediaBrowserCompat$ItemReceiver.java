package android.support.v4.media;

import android.support.v4.os.*;
import android.os.*;

private static class ItemReceiver extends ResultReceiver
{
    private final ItemCallback mCallback;
    private final String mMediaId;
    
    ItemReceiver(final String mMediaId, final ItemCallback mCallback, final Handler handler) {
        super(handler);
        this.mMediaId = mMediaId;
        this.mCallback = mCallback;
    }
    
    @Override
    protected void onReceiveResult(final int n, final Bundle bundle) {
        bundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
        if (n != 0 || bundle == null || !bundle.containsKey("media_item")) {
            this.mCallback.onError(this.mMediaId);
            return;
        }
        final Parcelable parcelable = bundle.getParcelable("media_item");
        if (parcelable instanceof MediaItem) {
            this.mCallback.onItemLoaded((MediaItem)parcelable);
            return;
        }
        this.mCallback.onError(this.mMediaId);
    }
}
