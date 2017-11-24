package android.support.v4.media;

import android.support.annotation.*;
import android.media.browse.*;
import android.os.*;

static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser$ItemCallback
{
    protected final T mItemCallback;
    
    public ItemCallbackProxy(final T mItemCallback) {
        this.mItemCallback = mItemCallback;
    }
    
    public void onError(@NonNull final String s) {
        ((ItemCallback)this.mItemCallback).onError(s);
    }
    
    public void onItemLoaded(final MediaBrowser$MediaItem mediaBrowser$MediaItem) {
        final Parcel obtain = Parcel.obtain();
        mediaBrowser$MediaItem.writeToParcel(obtain, 0);
        ((ItemCallback)this.mItemCallback).onItemLoaded(obtain);
    }
}
