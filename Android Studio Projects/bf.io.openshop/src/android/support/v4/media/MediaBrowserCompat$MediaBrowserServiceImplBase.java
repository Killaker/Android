package android.support.v4.media;

import android.support.v4.media.session.*;
import android.support.v4.util.*;
import android.util.*;
import android.content.*;
import android.support.annotation.*;
import android.text.*;
import android.support.v4.os.*;
import java.util.*;
import android.os.*;

static class MediaBrowserServiceImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl
{
    private static final int CONNECT_STATE_CONNECTED = 2;
    private static final int CONNECT_STATE_CONNECTING = 1;
    private static final int CONNECT_STATE_DISCONNECTED = 0;
    private static final int CONNECT_STATE_SUSPENDED = 3;
    private static final boolean DBG;
    private final ConnectionCallback mCallback;
    private Messenger mCallbacksMessenger;
    private final Context mContext;
    private Bundle mExtras;
    private final CallbackHandler mHandler;
    private MediaSessionCompat.Token mMediaSessionToken;
    private final Bundle mRootHints;
    private String mRootId;
    private ServiceBinderWrapper mServiceBinderWrapper;
    private final ComponentName mServiceComponent;
    private MediaServiceConnection mServiceConnection;
    private int mState;
    private final ArrayMap<String, Subscription> mSubscriptions;
    
    public MediaBrowserServiceImplBase(final Context mContext, final ComponentName mServiceComponent, final ConnectionCallback mCallback, final Bundle mRootHints) {
        this.mHandler = new CallbackHandler(this);
        this.mSubscriptions = new ArrayMap<String, Subscription>();
        this.mState = 0;
        if (mContext == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (mServiceComponent == null) {
            throw new IllegalArgumentException("service component must not be null");
        }
        if (mCallback == null) {
            throw new IllegalArgumentException("connection callback must not be null");
        }
        this.mContext = mContext;
        this.mServiceComponent = mServiceComponent;
        this.mCallback = mCallback;
        this.mRootHints = mRootHints;
    }
    
    private void forceCloseConnection() {
        if (this.mServiceConnection != null) {
            this.mContext.unbindService((ServiceConnection)this.mServiceConnection);
        }
        this.mState = 0;
        this.mServiceConnection = null;
        this.mServiceBinderWrapper = null;
        this.mCallbacksMessenger = null;
        this.mRootId = null;
        this.mMediaSessionToken = null;
    }
    
    private static String getStateLabel(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN/" + n;
            }
            case 0: {
                return "CONNECT_STATE_DISCONNECTED";
            }
            case 1: {
                return "CONNECT_STATE_CONNECTING";
            }
            case 2: {
                return "CONNECT_STATE_CONNECTED";
            }
            case 3: {
                return "CONNECT_STATE_SUSPENDED";
            }
        }
    }
    
    private boolean isCurrent(final Messenger messenger, final String s) {
        if (this.mCallbacksMessenger != messenger) {
            if (this.mState != 0) {
                Log.i("MediaBrowserCompat", s + " for " + this.mServiceComponent + " with mCallbacksMessenger=" + this.mCallbacksMessenger + " this=" + this);
            }
            return false;
        }
        return true;
    }
    
    @Override
    public void connect() {
        if (this.mState != 0) {
            throw new IllegalStateException("connect() called while not disconnected (state=" + getStateLabel(this.mState) + ")");
        }
        if (this.mServiceBinderWrapper != null) {
            throw new RuntimeException("mServiceBinderWrapper should be null. Instead it is " + this.mServiceBinderWrapper);
        }
        if (this.mCallbacksMessenger != null) {
            throw new RuntimeException("mCallbacksMessenger should be null. Instead it is " + this.mCallbacksMessenger);
        }
        this.mState = 1;
        final Intent intent = new Intent("android.media.browse.MediaBrowserService");
        intent.setComponent(this.mServiceComponent);
        final MediaServiceConnection mServiceConnection = new MediaServiceConnection();
        this.mServiceConnection = mServiceConnection;
        while (true) {
            try {
                final int bindService = this.mContext.bindService(intent, (ServiceConnection)this.mServiceConnection, 1) ? 1 : 0;
                if (bindService == 0) {
                    this.mHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (mServiceConnection == MediaBrowserServiceImplBase.this.mServiceConnection) {
                                MediaBrowserServiceImplBase.this.forceCloseConnection();
                                MediaBrowserServiceImplBase.this.mCallback.onConnectionFailed();
                            }
                        }
                    });
                }
            }
            catch (Exception ex) {
                Log.e("MediaBrowserCompat", "Failed binding to service " + this.mServiceComponent);
                final int bindService = 0;
                continue;
            }
            break;
        }
    }
    
    @Override
    public void disconnect() {
        while (true) {
            if (this.mCallbacksMessenger == null) {
                break Label_0018;
            }
            try {
                this.mServiceBinderWrapper.disconnect(this.mCallbacksMessenger);
                this.forceCloseConnection();
            }
            catch (RemoteException ex) {
                Log.w("MediaBrowserCompat", "RemoteException during connect for " + this.mServiceComponent);
                continue;
            }
            break;
        }
    }
    
    void dump() {
        Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
        Log.d("MediaBrowserCompat", "  mServiceComponent=" + this.mServiceComponent);
        Log.d("MediaBrowserCompat", "  mCallback=" + this.mCallback);
        Log.d("MediaBrowserCompat", "  mRootHints=" + this.mRootHints);
        Log.d("MediaBrowserCompat", "  mState=" + getStateLabel(this.mState));
        Log.d("MediaBrowserCompat", "  mServiceConnection=" + this.mServiceConnection);
        Log.d("MediaBrowserCompat", "  mServiceBinderWrapper=" + this.mServiceBinderWrapper);
        Log.d("MediaBrowserCompat", "  mCallbacksMessenger=" + this.mCallbacksMessenger);
        Log.d("MediaBrowserCompat", "  mRootId=" + this.mRootId);
        Log.d("MediaBrowserCompat", "  mMediaSessionToken=" + this.mMediaSessionToken);
    }
    
    @Nullable
    @Override
    public Bundle getExtras() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getExtras() called while not connected (state=" + getStateLabel(this.mState) + ")");
        }
        return this.mExtras;
    }
    
    @Override
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("mediaId is empty.");
        }
        if (itemCallback == null) {
            throw new IllegalArgumentException("cb is null.");
        }
        if (this.mState != 2) {
            Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
            this.mHandler.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    itemCallback.onError(s);
                }
            });
            return;
        }
        final ItemReceiver itemReceiver = new ItemReceiver(s, itemCallback, this.mHandler);
        try {
            this.mServiceBinderWrapper.getMediaItem(s, itemReceiver);
        }
        catch (RemoteException ex) {
            Log.i("MediaBrowserCompat", "Remote error getting media item.");
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
        if (!this.isConnected()) {
            throw new IllegalStateException("getRoot() called while not connected(state=" + getStateLabel(this.mState) + ")");
        }
        return this.mRootId;
    }
    
    @NonNull
    @Override
    public ComponentName getServiceComponent() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getServiceComponent() called while not connected (state=" + this.mState + ")");
        }
        return this.mServiceComponent;
    }
    
    @NonNull
    @Override
    public MediaSessionCompat.Token getSessionToken() {
        if (!this.isConnected()) {
            throw new IllegalStateException("getSessionToken() called while not connected(state=" + this.mState + ")");
        }
        return this.mMediaSessionToken;
    }
    
    @Override
    public boolean isConnected() {
        return this.mState == 2;
    }
    
    @Override
    public void onConnectionFailed(final Messenger messenger) {
        Log.e("MediaBrowserCompat", "onConnectFailed for " + this.mServiceComponent);
        if (!this.isCurrent(messenger, "onConnectFailed")) {
            return;
        }
        if (this.mState != 1) {
            Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
            return;
        }
        this.forceCloseConnection();
        this.mCallback.onConnectionFailed();
    }
    
    @Override
    public void onLoadChildren(final Messenger messenger, final String s, final List list, final Bundle bundle) {
        if (this.isCurrent(messenger, "onLoadChildren")) {
            final Subscription subscription = this.mSubscriptions.get(s);
            if (subscription != null) {
                final SubscriptionCallback callback = subscription.getCallback(bundle);
                if (callback != null) {
                    if (bundle == null) {
                        callback.onChildrenLoaded(s, list);
                        return;
                    }
                    callback.onChildrenLoaded(s, list, bundle);
                }
            }
        }
    }
    
    @Override
    public void onServiceConnected(final Messenger messenger, final String mRootId, final MediaSessionCompat.Token mMediaSessionToken, final Bundle mExtras) {
        if (this.isCurrent(messenger, "onConnect")) {
            if (this.mState != 1) {
                Log.w("MediaBrowserCompat", "onConnect from service while mState=" + getStateLabel(this.mState) + "... ignoring");
                return;
            }
            this.mRootId = mRootId;
            this.mMediaSessionToken = mMediaSessionToken;
            this.mExtras = mExtras;
            this.mState = 2;
            this.mCallback.onConnected();
            try {
                for (final Map.Entry<String, Subscription> entry : this.mSubscriptions.entrySet()) {
                    final String s = entry.getKey();
                    final Iterator<Bundle> iterator2 = entry.getValue().getOptionsList().iterator();
                    while (iterator2.hasNext()) {
                        this.mServiceBinderWrapper.addSubscription(s, iterator2.next(), this.mCallbacksMessenger);
                    }
                }
            }
            catch (RemoteException ex) {
                Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
            }
        }
    }
    
    @Override
    public void subscribe(@NonNull final String s, final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty.");
        }
        if (subscriptionCallback == null) {
            throw new IllegalArgumentException("callback is null");
        }
        Subscription subscription = this.mSubscriptions.get(s);
        if (subscription == null) {
            subscription = new Subscription();
            this.mSubscriptions.put(s, subscription);
        }
        subscription.setCallbackForOptions(subscriptionCallback, bundle);
        if (this.mState != 2) {
            return;
        }
        try {
            this.mServiceBinderWrapper.addSubscription(s, bundle, this.mCallbacksMessenger);
        }
        catch (RemoteException ex) {
            Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException parentId=" + s);
        }
    }
    
    @Override
    public void unsubscribe(@NonNull final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("parentId is empty.");
        }
        final Subscription subscription = this.mSubscriptions.get(s);
        while (true) {
            if (subscription == null || !subscription.remove(bundle) || this.mState != 2) {
                break Label_0063;
            }
            try {
                this.mServiceBinderWrapper.removeSubscription(s, bundle, this.mCallbacksMessenger);
                if (subscription != null && subscription.isEmpty()) {
                    this.mSubscriptions.remove(s);
                }
            }
            catch (RemoteException ex) {
                Log.d("MediaBrowserCompat", "removeSubscription failed with RemoteException parentId=" + s);
                continue;
            }
            break;
        }
    }
    
    private class MediaServiceConnection implements ServiceConnection
    {
        private boolean isCurrent(final String s) {
            if (MediaBrowserServiceImplBase.this.mServiceConnection != this) {
                if (MediaBrowserServiceImplBase.this.mState != 0) {
                    Log.i("MediaBrowserCompat", s + " for " + MediaBrowserServiceImplBase.this.mServiceComponent + " with mServiceConnection=" + MediaBrowserServiceImplBase.this.mServiceConnection + " this=" + this);
                }
                return false;
            }
            return true;
        }
        
        private void postOrRun(final Runnable runnable) {
            if (Thread.currentThread() == MediaBrowserServiceImplBase.this.mHandler.getLooper().getThread()) {
                runnable.run();
                return;
            }
            MediaBrowserServiceImplBase.this.mHandler.post(runnable);
        }
        
        public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
            this.postOrRun(new Runnable() {
                @Override
                public void run() {
                    if (!MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                        return;
                    }
                    MediaBrowserServiceImplBase.this.mServiceBinderWrapper = new ServiceBinderWrapper(binder);
                    MediaBrowserServiceImplBase.this.mCallbacksMessenger = new Messenger((Handler)MediaBrowserServiceImplBase.this.mHandler);
                    MediaBrowserServiceImplBase.this.mHandler.setCallbacksMessenger(MediaBrowserServiceImplBase.this.mCallbacksMessenger);
                    MediaBrowserServiceImplBase.this.mState = 1;
                    try {
                        MediaBrowserServiceImplBase.this.mServiceBinderWrapper.connect(MediaBrowserServiceImplBase.this.mContext, MediaBrowserServiceImplBase.this.mRootHints, MediaBrowserServiceImplBase.this.mCallbacksMessenger);
                    }
                    catch (RemoteException ex) {
                        Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserServiceImplBase.this.mServiceComponent);
                    }
                }
            });
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
            this.postOrRun(new Runnable() {
                @Override
                public void run() {
                    if (!MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                        return;
                    }
                    MediaBrowserServiceImplBase.this.mServiceBinderWrapper = null;
                    MediaBrowserServiceImplBase.this.mCallbacksMessenger = null;
                    MediaBrowserServiceImplBase.this.mHandler.setCallbacksMessenger(null);
                    MediaBrowserServiceImplBase.this.mState = 3;
                    MediaBrowserServiceImplBase.this.mCallback.onConnectionSuspended();
                }
            });
        }
    }
}
