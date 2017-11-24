package android.support.v4.media;

import android.content.*;
import android.support.annotation.*;
import android.os.*;
import android.media.browse.*;
import java.util.*;

class MediaBrowserCompatApi21
{
    static final String NULL_MEDIA_ITEM_ID = "android.support.v4.media.MediaBrowserCompat.NULL_MEDIA_ITEM";
    
    public static void connect(final Object o) {
        ((MediaBrowser)o).connect();
    }
    
    public static Object createBrowser(final Context context, final ComponentName componentName, final Object o, final Bundle bundle) {
        return new MediaBrowser(context, componentName, (MediaBrowser$ConnectionCallback)o, bundle);
    }
    
    public static Object createConnectionCallback(final ConnectionCallback connectionCallback) {
        return new ConnectionCallbackProxy(connectionCallback);
    }
    
    public static Object createSubscriptionCallback(final SubscriptionCallback subscriptionCallback) {
        return new SubscriptionCallbackProxy(subscriptionCallback);
    }
    
    public static void disconnect(final Object o) {
        ((MediaBrowser)o).disconnect();
    }
    
    public static Bundle getExtras(final Object o) {
        return ((MediaBrowser)o).getExtras();
    }
    
    public static String getRoot(final Object o) {
        return ((MediaBrowser)o).getRoot();
    }
    
    public static ComponentName getServiceComponent(final Object o) {
        return ((MediaBrowser)o).getServiceComponent();
    }
    
    public static Object getSessionToken(final Object o) {
        return ((MediaBrowser)o).getSessionToken();
    }
    
    public static boolean isConnected(final Object o) {
        return ((MediaBrowser)o).isConnected();
    }
    
    public static void subscribe(final Object o, final String s, final Object o2) {
        ((MediaBrowser)o).subscribe(s, (MediaBrowser$SubscriptionCallback)o2);
    }
    
    public static void unsubscribe(final Object o, final String s) {
        ((MediaBrowser)o).unsubscribe(s);
    }
    
    interface ConnectionCallback
    {
        void onConnected();
        
        void onConnectionFailed();
        
        void onConnectionSuspended();
    }
    
    static class ConnectionCallbackProxy<T extends ConnectionCallback> extends MediaBrowser$ConnectionCallback
    {
        protected final T mConnectionCallback;
        
        public ConnectionCallbackProxy(final T mConnectionCallback) {
            this.mConnectionCallback = mConnectionCallback;
        }
        
        public void onConnected() {
            ((ConnectionCallback)this.mConnectionCallback).onConnected();
        }
        
        public void onConnectionFailed() {
            ((ConnectionCallback)this.mConnectionCallback).onConnectionFailed();
        }
        
        public void onConnectionSuspended() {
            ((ConnectionCallback)this.mConnectionCallback).onConnectionSuspended();
        }
    }
    
    interface SubscriptionCallback
    {
        void onChildrenLoaded(@NonNull final String p0, final List<Parcel> p1);
        
        void onError(@NonNull final String p0);
    }
    
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
}
