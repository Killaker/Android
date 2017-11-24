package android.support.v4.media;

import android.support.annotation.*;
import android.os.*;
import java.util.*;

private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback
{
    @Override
    public void onChildrenLoaded(@NonNull final String s, final List<Parcel> list) {
        List<MediaItem> list2 = null;
        if (list != null) {
            list2 = new ArrayList<MediaItem>();
            for (final Parcel parcel : list) {
                parcel.setDataPosition(0);
                list2.add((MediaItem)MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
        }
        if (SubscriptionCallbackApi21.access$400(SubscriptionCallbackApi21.this) != null) {
            SubscriptionCallbackApi21.this.onChildrenLoaded(s, MediaBrowserCompatUtils.applyOptions(list2, SubscriptionCallbackApi21.access$400(SubscriptionCallbackApi21.this)), SubscriptionCallbackApi21.access$400(SubscriptionCallbackApi21.this));
            return;
        }
        SubscriptionCallbackApi21.this.onChildrenLoaded(s, list2);
    }
    
    @Override
    public void onError(@NonNull final String s) {
        if (SubscriptionCallbackApi21.access$400(SubscriptionCallbackApi21.this) != null) {
            SubscriptionCallbackApi21.this.onError(s, SubscriptionCallbackApi21.access$400(SubscriptionCallbackApi21.this));
            return;
        }
        SubscriptionCallbackApi21.this.onError(s);
    }
}
