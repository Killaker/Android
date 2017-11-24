package android.support.v4.media;

import android.support.annotation.*;
import android.media.browse.*;
import android.os.*;
import java.util.*;

static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends MediaBrowser$SubscriptionCallback
{
    protected final T mSubscriptionCallback;
    
    public SubscriptionCallbackProxy(final T mSubscriptionCallback) {
        this.mSubscriptionCallback = mSubscriptionCallback;
    }
    
    public void onChildrenLoaded(@NonNull final String s, List<MediaBrowser$MediaItem> list) {
        if (list != null && list.size() == 1 && list.get(0).getMediaId().equals("android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM")) {
            list = null;
        }
        List<Parcel> list2 = null;
        if (list != null) {
            list2 = new ArrayList<Parcel>();
            for (final MediaBrowser$MediaItem mediaBrowser$MediaItem : list) {
                final Parcel obtain = Parcel.obtain();
                mediaBrowser$MediaItem.writeToParcel(obtain, 0);
                list2.add(obtain);
            }
        }
        ((SubscriptionCallback)this.mSubscriptionCallback).onChildrenLoaded(s, list2);
    }
    
    public void onError(@NonNull final String s) {
        ((SubscriptionCallback)this.mSubscriptionCallback).onError(s);
    }
}
