package android.support.v4.media;

import android.support.annotation.*;
import android.os.*;

public abstract static class ItemCallback
{
    final Object mItemCallbackObj;
    
    public ItemCallback() {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback((MediaBrowserCompatApi23.ItemCallback)new StubApi23());
            return;
        }
        this.mItemCallbackObj = null;
    }
    
    public void onError(@NonNull final String s) {
    }
    
    public void onItemLoaded(final MediaItem mediaItem) {
    }
    
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
}
