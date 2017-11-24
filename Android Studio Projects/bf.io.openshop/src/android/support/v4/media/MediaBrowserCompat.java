package android.support.v4.media;

import android.support.annotation.*;
import android.support.v4.media.session.*;
import java.lang.ref.*;
import android.util.*;
import android.support.v4.os.*;
import android.support.v4.util.*;
import android.text.*;
import android.support.v4.app.*;
import android.content.*;
import android.os.*;
import java.lang.annotation.*;
import java.util.*;

public final class MediaBrowserCompat
{
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    private static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;
    
    public MediaBrowserCompat(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
        if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplApi23(context, componentName, connectionCallback, bundle);
            return;
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaBrowserImpl)new MediaBrowserImplApi21(context, componentName, connectionCallback, bundle);
            return;
        }
        this.mImpl = (MediaBrowserImpl)new MediaBrowserServiceImplBase(context, componentName, connectionCallback, bundle);
    }
    
    public void connect() {
        this.mImpl.connect();
    }
    
    public void disconnect() {
        this.mImpl.disconnect();
    }
    
    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }
    
    public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
        this.mImpl.getItem(s, itemCallback);
    }
    
    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }
    
    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isConnected() {
        return this.mImpl.isConnected();
    }
    
    public void subscribe(@NonNull final String s, @NonNull final Bundle bundle, @NonNull final SubscriptionCallback subscriptionCallback) {
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.subscribe(s, bundle, subscriptionCallback);
    }
    
    public void subscribe(@NonNull final String s, @NonNull final SubscriptionCallback subscriptionCallback) {
        this.mImpl.subscribe(s, null, subscriptionCallback);
    }
    
    public void unsubscribe(@NonNull final String s) {
        this.mImpl.unsubscribe(s, null);
    }
    
    public void unsubscribe(@NonNull final String s, @NonNull final Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("options are null");
        }
        this.mImpl.unsubscribe(s, bundle);
    }
    
    private static class CallbackHandler extends Handler
    {
        private final MediaBrowserServiceCallbackImpl mCallbackImpl;
        private WeakReference<Messenger> mCallbacksMessengerRef;
        
        CallbackHandler(final MediaBrowserServiceCallbackImpl mCallbackImpl) {
            this.mCallbackImpl = mCallbackImpl;
        }
        
        public void handleMessage(final Message message) {
            if (this.mCallbacksMessengerRef == null) {
                return;
            }
            final Bundle data = message.getData();
            data.setClassLoader(MediaSessionCompat.class.getClassLoader());
            switch (message.what) {
                default: {
                    Log.w("MediaBrowserCompat", "Unhandled message: " + message + "\n  Client version: " + 1 + "\n  Service version: " + message.arg1);
                }
                case 1: {
                    this.mCallbackImpl.onServiceConnected(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), (MediaSessionCompat.Token)data.getParcelable("data_media_session_token"), data.getBundle("data_root_hints"));
                }
                case 2: {
                    this.mCallbackImpl.onConnectionFailed(this.mCallbacksMessengerRef.get());
                }
                case 3: {
                    this.mCallbackImpl.onLoadChildren(this.mCallbacksMessengerRef.get(), data.getString("data_media_item_id"), data.getParcelableArrayList("data_media_item_list"), data.getBundle("data_options"));
                }
            }
        }
        
        void setCallbacksMessenger(final Messenger messenger) {
            this.mCallbacksMessengerRef = new WeakReference<Messenger>(messenger);
        }
    }
    
    public static class ConnectionCallback
    {
        private ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;
        
        public ConnectionCallback() {
            if (Build$VERSION.SDK_INT >= 21) {
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback((MediaBrowserCompatApi21.ConnectionCallback)new StubApi21());
                return;
            }
            this.mConnectionCallbackObj = null;
        }
        
        public void onConnected() {
        }
        
        public void onConnectionFailed() {
        }
        
        public void onConnectionSuspended() {
        }
        
        void setInternalConnectionCallback(final ConnectionCallbackInternal mConnectionCallbackInternal) {
            this.mConnectionCallbackInternal = mConnectionCallbackInternal;
        }
        
        interface ConnectionCallbackInternal
        {
            void onConnected();
            
            void onConnectionFailed();
            
            void onConnectionSuspended();
        }
        
        private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback
        {
            @Override
            public void onConnected() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnected();
            }
            
            @Override
            public void onConnectionFailed() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
            }
            
            @Override
            public void onConnectionSuspended() {
                if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                    MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
                }
                MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
            }
        }
    }
    
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
    
    interface MediaBrowserImpl
    {
        void connect();
        
        void disconnect();
        
        @Nullable
        Bundle getExtras();
        
        void getItem(@NonNull final String p0, @NonNull final ItemCallback p1);
        
        @NonNull
        String getRoot();
        
        ComponentName getServiceComponent();
        
        @NonNull
        MediaSessionCompat.Token getSessionToken();
        
        boolean isConnected();
        
        void subscribe(@NonNull final String p0, final Bundle p1, @NonNull final SubscriptionCallback p2);
        
        void unsubscribe(@NonNull final String p0, final Bundle p1);
    }
    
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
            connectionCallback.setInternalConnectionCallback((ConnectionCallbackInternal)this);
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
    
    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21
    {
        public MediaBrowserImplApi23(final Context context, final ComponentName componentName, final ConnectionCallback connectionCallback, final Bundle bundle) {
            super(context, componentName, connectionCallback, bundle);
        }
        
        @Override
        public void getItem(@NonNull final String s, @NonNull final ItemCallback itemCallback) {
            MediaBrowserCompatApi23.getItem(this.mBrowserObj, s, itemCallback.mItemCallbackObj);
        }
    }
    
    interface MediaBrowserServiceCallbackImpl
    {
        void onConnectionFailed(final Messenger p0);
        
        void onLoadChildren(final Messenger p0, final String p1, final List p2, final Bundle p3);
        
        void onServiceConnected(final Messenger p0, final String p1, final MediaSessionCompat.Token p2, final Bundle p3);
    }
    
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
    
    public static class MediaItem implements Parcelable
    {
        public static final Parcelable$Creator<MediaItem> CREATOR;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<MediaItem>() {
                public MediaItem createFromParcel(final Parcel parcel) {
                    return new MediaItem(parcel);
                }
                
                public MediaItem[] newArray(final int n) {
                    return new MediaItem[n];
                }
            };
        }
        
        private MediaItem(final Parcel parcel) {
            this.mFlags = parcel.readInt();
            this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        }
        
        public MediaItem(@NonNull final MediaDescriptionCompat mDescription, final int mFlags) {
            if (mDescription == null) {
                throw new IllegalArgumentException("description cannot be null");
            }
            if (TextUtils.isEmpty((CharSequence)mDescription.getMediaId())) {
                throw new IllegalArgumentException("description must have a non-empty media id");
            }
            this.mFlags = mFlags;
            this.mDescription = mDescription;
        }
        
        public int describeContents() {
            return 0;
        }
        
        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }
        
        public int getFlags() {
            return this.mFlags;
        }
        
        @NonNull
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
        
        public boolean isBrowsable() {
            return (0x1 & this.mFlags) != 0x0;
        }
        
        public boolean isPlayable() {
            return (0x2 & this.mFlags) != 0x0;
        }
        
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("MediaItem{");
            sb.append("mFlags=").append(this.mFlags);
            sb.append(", mDescription=").append(this.mDescription);
            sb.append('}');
            return sb.toString();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            parcel.writeInt(this.mFlags);
            this.mDescription.writeToParcel(parcel, n);
        }
        
        @Retention(RetentionPolicy.SOURCE)
        public @interface Flags {
        }
    }
    
    private static class ServiceBinderWrapper
    {
        private Messenger mMessenger;
        
        public ServiceBinderWrapper(final IBinder binder) {
            this.mMessenger = new Messenger(binder);
        }
        
        private void sendRequest(final int what, final Bundle data, final Messenger replyTo) throws RemoteException {
            final Message obtain = Message.obtain();
            obtain.what = what;
            obtain.arg1 = 1;
            obtain.setData(data);
            obtain.replyTo = replyTo;
            this.mMessenger.send(obtain);
        }
        
        void addSubscription(final String s, final Bundle bundle, final Messenger messenger) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putBundle("data_options", bundle);
            this.sendRequest(3, bundle2, messenger);
        }
        
        void connect(final Context context, final Bundle bundle, final Messenger messenger) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_package_name", context.getPackageName());
            bundle2.putBundle("data_root_hints", bundle);
            this.sendRequest(1, bundle2, messenger);
        }
        
        void disconnect(final Messenger messenger) throws RemoteException {
            this.sendRequest(2, null, messenger);
        }
        
        void getMediaItem(final String s, final ResultReceiver resultReceiver) throws RemoteException {
            final Bundle bundle = new Bundle();
            bundle.putString("data_media_item_id", s);
            bundle.putParcelable("data_result_receiver", (Parcelable)resultReceiver);
            this.sendRequest(5, bundle, null);
        }
        
        void registerCallbackMessenger(final Messenger messenger) throws RemoteException {
            this.sendRequest(6, null, messenger);
        }
        
        void removeSubscription(final String s, final Bundle bundle, final Messenger messenger) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putBundle("data_options", bundle);
            this.sendRequest(4, bundle2, messenger);
        }
    }
    
    private static class Subscription
    {
        private final List<SubscriptionCallback> mCallbacks;
        private final List<Bundle> mOptionsList;
        
        public Subscription() {
            this.mCallbacks = new ArrayList<SubscriptionCallback>();
            this.mOptionsList = new ArrayList<Bundle>();
        }
        
        public SubscriptionCallback getCallback(final Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); ++i) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    return this.mCallbacks.get(i);
                }
            }
            return null;
        }
        
        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }
        
        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }
        
        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }
        
        public boolean remove(final Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); ++i) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.remove(i);
                    this.mOptionsList.remove(i);
                    return true;
                }
            }
            return false;
        }
        
        public void setCallbackForOptions(final SubscriptionCallback subscriptionCallback, final Bundle bundle) {
            for (int i = 0; i < this.mOptionsList.size(); ++i) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), bundle)) {
                    this.mCallbacks.set(i, subscriptionCallback);
                    return;
                }
            }
            this.mCallbacks.add(subscriptionCallback);
            this.mOptionsList.add(bundle);
        }
    }
    
    public abstract static class SubscriptionCallback
    {
        public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list) {
        }
        
        public void onChildrenLoaded(@NonNull final String s, final List<MediaItem> list, @NonNull final Bundle bundle) {
        }
        
        public void onError(@NonNull final String s) {
        }
        
        public void onError(@NonNull final String s, @NonNull final Bundle bundle) {
        }
    }
    
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
}
