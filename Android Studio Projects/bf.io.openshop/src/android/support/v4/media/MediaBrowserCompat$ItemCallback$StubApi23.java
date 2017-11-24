package android.support.v4.media;

import android.support.annotation.*;
import android.os.*;

private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback
{
    @Override
    public void onError(@NonNull final String s) {
        MediaBrowserCompat.ItemCallback.this.onError(s);
    }
    
    @Override
    public void onItemLoaded(final Parcel parcel) {
        parcel.setDataPosition(0);
        final MediaItem mediaItem = (MediaItem)MediaItem.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        MediaBrowserCompat.ItemCallback.this.onItemLoaded(mediaItem);
    }
}
