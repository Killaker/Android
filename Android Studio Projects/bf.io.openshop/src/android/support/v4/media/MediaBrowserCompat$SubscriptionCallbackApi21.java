package android.support.v4.media;

import android.support.annotation.*;
import android.os.*;
import java.util.*;

static class SubscriptionCallbackApi21 extends SubscriptionCallback
{
    private Bundle mOptions;
    SubscriptionCallback mSubscriptionCallback;
    private final Object mSubscriptionCallbackObj;
    
    public SubscriptionCallbackApi21(final SubscriptionCallback mSubscriptionCallback, final Bundle mOptions) {
        this.mSubscriptionCallback = mSubscriptionCallback;
        this.mOptions = mOptions;
        this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback((MediaBrowserCompatApi21.SubscriptionCallback)new StubApi21());
    }
    
    @Override
    public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list) {
        this.mSubscriptionCallback.onChildrenLoaded(s, list);
    }
    
    @Override
    public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list, @NonNull final Bundle bundle) {
        this.mSubscriptionCallback.onChildrenLoaded(s, list, bundle);
    }
    
    @Override
    public void onError(@NonNull final String s) {
        this.mSubscriptionCallback.onError(s);
    }
    
    @Override
    public void onError(@NonNull final String s, @NonNull final Bundle bundle) {
        this.mSubscriptionCallback.onError(s, bundle);
    }
    
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
            if (SubscriptionCallbackApi21.this.mOptions != null) {
                SubscriptionCallbackApi21.this.onChildrenLoaded(s, MediaBrowserCompatUtils.applyOptions(list2, SubscriptionCallbackApi21.this.mOptions), SubscriptionCallbackApi21.this.mOptions);
                return;
            }
            SubscriptionCallbackApi21.this.onChildrenLoaded(s, list2);
        }
        
        @Override
        public void onError(@NonNull final String s) {
            if (SubscriptionCallbackApi21.this.mOptions != null) {
                SubscriptionCallbackApi21.this.onError(s, SubscriptionCallbackApi21.this.mOptions);
                return;
            }
            SubscriptionCallbackApi21.this.onError(s);
        }
    }
}
