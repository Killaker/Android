package android.support.v4.media;

import android.support.v4.util.*;
import android.content.*;
import android.support.annotation.*;
import android.text.*;
import android.util.*;
import android.support.v4.os.*;
import android.support.v4.media.session.*;
import android.support.v4.app.*;
import android.os.*;
import java.util.*;

static class MediaBrowserImplApi21 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallbackInternal
{
    private static final boolean DBG;
    protected Object mBrowserObj;
    private Messenger mCallbacksMessenger;
    private final CallbackHandler mHandler;
    private ServiceBinderWrapper mServiceBinderWrapper;
    private final ComponentName mServiceComponent;
    private final ArrayMap<String, Subscription> mSubscriptions;
    
    public MediaBrowserImplApi21(final Context context, final ComponentName mServiceComponent, final ConnectionCallback connectionCallback, final Bundle bundle) {
        this.mHandler = new CallbackHandler(this);
        this.mSubscriptions = new ArrayMap<String, Subscription>();
        this.mServiceComponent = mServiceComponent;
        connectionCallback.setInternalConnectionCallback((ConnectionCallback.ConnectionCallbackInternal)this);
        this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context, mServiceComponent, connectionCallback.mConnectionCallbackObj, bundle);
    }
    
    @Override
    public void connect() {
        MediaBrowserCompatApi21.connect(this.mBrowserObj);
    }
    
    @Override
    public void disconnect() {
        MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
    }
    
    @Nullable
    @Override
    public Bundle getExtras() {
        return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
    }
    
    @Override
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("mediaId is empty.");
        }
        if (itemCallback == null) {
            throw new IllegalArgumentException("cb is null.");
        }
        if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    itemCallback.onError(s);
                }
            });
            return;
        }
        if (this.mServiceBinderWrapper == null) {
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    itemCallback.onItemLoaded(null);
                }
            });
            return;
        }
        final ItemReceiver itemReceiver = new ItemReceiver(s, itemCallback, this.mHandler);
        try {
            this.mServiceBinderWrapper.getMediaItem(s, itemReceiver);
        }
        catch (RemoteException ex) {
            Log.i("MediaBrowserCompat", "Remote error getting media item: " + s);
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    itemCallback.onError(s);
                }
            });
        }
    }
    
    @NonNull
    @Override
    public String getRoot() {
        return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
    }
    
    @Override
    public ComponentName getServiceComponent() {
        return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
    }
    
    @NonNull
    @Override
    public MediaSessionCompat.Token getSessionToken() {
        return MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
    }
    
    @Override
    public boolean isConnected() {
        return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
    }
    
    @Override
    public void onConnected() {
        final Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        if (extras != null) {
            final IBinder binder = BundleCompat.getBinder(extras, "extra_messenger");
            if (binder != null) {
                this.mServiceBinderWrapper = new ServiceBinderWrapper(binder);
                this.mCallbacksMessenger = new Messenger((Handler)this.mHandler);
                this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                while (true) {
                    try {
                        this.mServiceBinderWrapper.registerCallbackMessenger(this.mCallbacksMessenger);
                        this.onServiceConnected(this.mCallbacksMessenger, null, null, null);
                    }
                    catch (RemoteException ex) {
                        Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void onConnectionFailed() {
    }
    
    @Override
    public void onConnectionFailed(final Messenger messenger) {
    }
    
    @Override
    public void onConnectionSuspended() {
        this.mServiceBinderWrapper = null;
        this.mCallbacksMessenger = null;
    }
    
    @Override
    public void onLoadChildren(final Messenger messenger, final String s, final List list, @NonNull final Bundle bundle) {
        if (this.mCallbacksMessenger == messenger) {
            final Subscription subscription = this.mSubscriptions.get(s);
            if (subscription != null) {
                subscription.getCallback(bundle).onChildrenLoaded(s, list, bundle);
            }
        }
    }
    
    @Override
    public void onServiceConnected(final Messenger messenger, final String s, final MediaSessionCompat.Token token, final Bundle bundle) {
        for (final Map.Entry<String, Subscription> entry : this.mSubscriptions.entrySet()) {
            final String s2 = entry.getKey();
            final Subscription subscription = entry.getValue();
            final List<Bundle> optionsList = subscription.getOptionsList();
            final List<SubscriptionCallback> callbacks = subscription.getCallbacks();
            for (int i = 0; i < optionsList.size(); ++i) {
                if (optionsList.get(i) == null) {
                    MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s2, ((SubscriptionCallbackApi21)callbacks.get(i)).mSubscriptionCallbackObj);
                }
                else {
                    try {
                        this.mServiceBinderWrapper.addSubscription(s2, optionsList.get(i), this.mCallbacksMessenger);
                    }
                    catch (RemoteException ex) {
                        Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s2);
                    }
                }
            }
        }
    }
    
    @Override
    public void subscribe(@NonNull final String s, final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
        final SubscriptionCallbackApi21 subscriptionCallbackApi21 = new SubscriptionCallbackApi21(subscriptionCallback, bundle);
        Subscription subscription = this.mSubscriptions.get(s);
        if (subscription == null) {
            subscription = new Subscription();
            this.mSubscriptions.put(s, subscription);
        }
        subscription.setCallbackForOptions(subscriptionCallbackApi21, bundle);
        if (MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
            if (bundle == null || this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, s, subscriptionCallbackApi21.mSubscriptionCallbackObj);
            }
            else {
                try {
                    this.mServiceBinderWrapper.addSubscription(s, bundle, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.i("MediaBrowserCompat", "Remote error subscribing media item: " + s);
                }
            }
        }
    }
    
    @Override
    public void unsubscribe(@NonNull final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty.");
        }
        final Subscription subscription = this.mSubscriptions.get(s);
        if (subscription != null && subscription.remove(bundle)) {
            if (bundle == null || this.mServiceBinderWrapper == null) {
                if (this.mServiceBinderWrapper != null || subscription.isEmpty()) {
                    MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, s);
                }
            }
            else if (this.mServiceBinderWrapper == null) {
                try {
                    this.mServiceBinderWrapper.removeSubscription(s, bundle, this.mCallbacksMessenger);
                }
                catch (RemoteException ex) {
                    Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                }
            }
        }
        if (subscription != null && subscription.isEmpty()) {
            this.mSubscriptions.remove(s);
        }
    }
}
