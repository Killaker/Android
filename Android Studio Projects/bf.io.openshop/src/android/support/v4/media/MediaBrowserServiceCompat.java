package android.support.v4.media;

import android.app.*;
import android.support.v4.util.*;
import android.support.v4.media.session.*;
import android.support.v4.os.*;
import android.util.*;
import java.io.*;
import android.support.annotation.*;
import android.content.*;
import android.support.v4.app.*;
import java.util.*;
import android.os.*;
import android.text.*;

public abstract class MediaBrowserServiceCompat extends Service
{
    private static final boolean DBG = false;
    public static final String KEY_MEDIA_ITEM = "media_item";
    private static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    private static final String TAG = "MediaBrowserServiceCompat";
    private final ArrayMap<IBinder, ConnectionRecord> mConnections;
    private final ServiceHandler mHandler;
    private MediaBrowserServiceImpl mImpl;
    MediaSessionCompat.Token mSession;
    
    public MediaBrowserServiceCompat() {
        this.mConnections = new ArrayMap<IBinder, ConnectionRecord>();
        this.mHandler = new ServiceHandler();
    }
    
    private void addSubscription(final String s, final ConnectionRecord connectionRecord, final Bundle bundle) {
        List<Bundle> list = connectionRecord.subscriptions.get(s);
        if (list == null) {
            list = new ArrayList<Bundle>();
        }
        final Iterator<Bundle> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (MediaBrowserCompatUtils.areSameOptions(bundle, iterator.next())) {
                return;
            }
        }
        list.add(bundle);
        connectionRecord.subscriptions.put(s, list);
        this.performLoadChildren(s, connectionRecord, bundle);
    }
    
    private List<MediaBrowserCompat.MediaItem> applyOptions(final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) {
        final int int1 = bundle.getInt("android.media.browse.extra.PAGE", -1);
        final int int2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if (int1 == -1 && int2 == -1) {
            return list;
        }
        final int n = int2 * (int1 - 1);
        int size = n + int2;
        if (int1 < 1 || int2 < 1 || n >= list.size()) {
            return Collections.emptyList();
        }
        if (size > list.size()) {
            size = list.size();
        }
        return list.subList(n, size);
    }
    
    private boolean isValidPackage(final String s, final int n) {
        if (s != null) {
            final String[] packagesForUid = this.getPackageManager().getPackagesForUid(n);
            for (int length = packagesForUid.length, i = 0; i < length; ++i) {
                if (packagesForUid[i].equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private void notifyChildrenChangedInternal(final String s, final Bundle bundle) {
        if (s == null) {
            throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
        }
        this.mHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                final Iterator<IBinder> iterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
                while (iterator.hasNext()) {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(iterator.next());
                    final List<Bundle> list = connectionRecord.subscriptions.get(s);
                    if (list != null) {
                        for (final Bundle bundle : list) {
                            if (MediaBrowserCompatUtils.hasDuplicatedItems(bundle, bundle)) {
                                MediaBrowserServiceCompat.this.performLoadChildren(s, connectionRecord, bundle);
                                break;
                            }
                        }
                    }
                }
            }
        });
    }
    
    private void performLoadChildren(final String s, final ConnectionRecord connectionRecord, final Bundle bundle) {
        final Result<List<MediaBrowserCompat.MediaItem>> result = new Result<List<MediaBrowserCompat.MediaItem>>(s) {
            void onResultSent(final List<MediaBrowserCompat.MediaItem> list, final int n) {
                if (MediaBrowserServiceCompat.this.mConnections.get(connectionRecord.callbacks.asBinder()) != connectionRecord) {
                    return;
                }
                while (true) {
                    Label_0113: {
                        if ((n & 0x1) == 0x0) {
                            break Label_0113;
                        }
                        final List<MediaBrowserCompat.MediaItem> applyOptions = MediaBrowserCompatUtils.applyOptions(list, bundle);
                        try {
                            connectionRecord.callbacks.onLoadChildren(s, applyOptions, bundle);
                            return;
                        }
                        catch (RemoteException ex) {
                            Log.w("MediaBrowserServiceCompat", "Calling onLoadChildren() failed for id=" + s + " package=" + connectionRecord.pkg);
                            return;
                        }
                    }
                    final List<MediaBrowserCompat.MediaItem> applyOptions = list;
                    continue;
                }
            }
        };
        if (bundle == null) {
            this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)result);
        }
        else {
            this.onLoadChildren(s, (Result<List<MediaBrowserCompat.MediaItem>>)result, bundle);
        }
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + connectionRecord.pkg + " id=" + s);
        }
    }
    
    private void performLoadItem(final String s, final ResultReceiver resultReceiver) {
        final Result<MediaBrowserCompat.MediaItem> result = new Result<MediaBrowserCompat.MediaItem>(s) {
            void onResultSent(final MediaBrowserCompat.MediaItem mediaItem, final int n) {
                final Bundle bundle = new Bundle();
                bundle.putParcelable("media_item", (Parcelable)mediaItem);
                resultReceiver.send(0, bundle);
            }
        };
        this.onLoadItem(s, (Result<MediaBrowserCompat.MediaItem>)result);
        if (!((Result)result).isDone()) {
            throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + s);
        }
    }
    
    private boolean removeSubscription(final String s, final ConnectionRecord connectionRecord, final Bundle bundle) {
        final List<Bundle> list = connectionRecord.subscriptions.get(s);
        boolean b = false;
        if (list != null) {
            final Iterator<Bundle> iterator = list.iterator();
            while (true) {
                Bundle bundle2;
                do {
                    final boolean hasNext = iterator.hasNext();
                    b = false;
                    if (hasNext) {
                        bundle2 = iterator.next();
                    }
                    else {
                        if (list.size() == 0) {
                            connectionRecord.subscriptions.remove(s);
                            return b;
                        }
                        return b;
                    }
                } while (!MediaBrowserCompatUtils.areSameOptions(bundle, bundle2));
                b = true;
                list.remove(bundle2);
                continue;
            }
        }
        return b;
    }
    
    public void dump(final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.mSession;
    }
    
    public void notifyChildrenChanged(@NonNull final String s) {
        this.notifyChildrenChangedInternal(s, null);
    }
    
    public void notifyChildrenChanged(@NonNull final String s, @NonNull final Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
        }
        this.notifyChildrenChangedInternal(s, bundle);
    }
    
    public IBinder onBind(final Intent intent) {
        return this.mImpl.onBind(intent);
    }
    
    public void onCreate() {
        super.onCreate();
        if (Build$VERSION.SDK_INT >= 23) {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplApi23();
        }
        else if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplApi21();
        }
        else {
            this.mImpl = (MediaBrowserServiceImpl)new MediaBrowserServiceImplBase();
        }
        this.mImpl.onCreate();
    }
    
    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull final String p0, final int p1, @Nullable final Bundle p2);
    
    public abstract void onLoadChildren(@NonNull final String p0, @NonNull final Result<List<MediaBrowserCompat.MediaItem>> p1);
    
    public void onLoadChildren(@NonNull final String s, @NonNull final Result<List<MediaBrowserCompat.MediaItem>> result, @NonNull final Bundle bundle) {
        result.setFlags(1);
        this.onLoadChildren(s, result);
    }
    
    public void onLoadItem(final String s, final Result<MediaBrowserCompat.MediaItem> result) {
        result.sendResult(null);
    }
    
    public void setSessionToken(final MediaSessionCompat.Token mSession) {
        if (mSession == null) {
            throw new IllegalArgumentException("Session token may not be null.");
        }
        if (this.mSession != null) {
            throw new IllegalStateException("The session token has already been set.");
        }
        this.mSession = mSession;
        this.mHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                for (final IBinder binder : MediaBrowserServiceCompat.this.mConnections.keySet()) {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(binder);
                    try {
                        connectionRecord.callbacks.onConnect(connectionRecord.root.getRootId(), mSession, connectionRecord.root.getExtras());
                    }
                    catch (RemoteException ex) {
                        Log.w("MediaBrowserServiceCompat", "Connection for " + connectionRecord.pkg + " is no longer valid.");
                        MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    }
                }
            }
        });
    }
    
    public static final class BrowserRoot
    {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        private final Bundle mExtras;
        private final String mRootId;
        
        public BrowserRoot(@NonNull final String mRootId, @Nullable final Bundle mExtras) {
            if (mRootId == null) {
                throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
            }
            this.mRootId = mRootId;
            this.mExtras = mExtras;
        }
        
        public Bundle getExtras() {
            return this.mExtras;
        }
        
        public String getRootId() {
            return this.mRootId;
        }
    }
    
    private class ConnectionRecord
    {
        ServiceCallbacks callbacks;
        String pkg;
        BrowserRoot root;
        Bundle rootHints;
        HashMap<String, List<Bundle>> subscriptions;
        
        private ConnectionRecord() {
            this.subscriptions = new HashMap<String, List<Bundle>>();
        }
    }
    
    interface MediaBrowserServiceImpl
    {
        IBinder onBind(final Intent p0);
        
        void onCreate();
    }
    
    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl
    {
        private Object mServiceObj;
        
        @Override
        public IBinder onBind(final Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
        }
        
        @Override
        public void onCreate() {
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi21.createService(), (MediaBrowserServiceCompatApi21.ServiceImplApi21)new ServiceImplApi21());
        }
    }
    
    class MediaBrowserServiceImplApi23 implements MediaBrowserServiceImpl
    {
        private Object mServiceObj;
        
        @Override
        public IBinder onBind(final Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
        }
        
        @Override
        public void onCreate() {
            MediaBrowserServiceCompatApi23.onCreate(this.mServiceObj = MediaBrowserServiceCompatApi23.createService(), (MediaBrowserServiceCompatApi23.ServiceImplApi23)new ServiceImplApi23());
        }
    }
    
    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl
    {
        private Messenger mMessenger;
        
        @Override
        public IBinder onBind(final Intent intent) {
            if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
                return this.mMessenger.getBinder();
            }
            return null;
        }
        
        @Override
        public void onCreate() {
            this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.this.mHandler);
        }
    }
    
    public static class Result<T>
    {
        private Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendResultCalled;
        
        Result(final Object mDebug) {
            this.mDebug = mDebug;
        }
        
        public void detach() {
            if (this.mDetachCalled) {
                throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
            }
            if (this.mSendResultCalled) {
                throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
            }
            this.mDetachCalled = true;
        }
        
        boolean isDone() {
            return this.mDetachCalled || this.mSendResultCalled;
        }
        
        void onResultSent(final T t, final int n) {
        }
        
        public void sendResult(final T t) {
            if (this.mSendResultCalled) {
                throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
            }
            this.mSendResultCalled = true;
            this.onResultSent(t, this.mFlags);
        }
        
        void setFlags(final int mFlags) {
            this.mFlags = mFlags;
        }
    }
    
    private interface ServiceCallbacks
    {
        IBinder asBinder();
        
        void onConnect(final String p0, final MediaSessionCompat.Token p1, final Bundle p2) throws RemoteException;
        
        void onConnectFailed() throws RemoteException;
        
        void onLoadChildren(final String p0, final List<MediaBrowserCompat.MediaItem> p1, final Bundle p2) throws RemoteException;
    }
    
    private class ServiceCallbacksApi21 implements ServiceCallbacks
    {
        final MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks;
        Messenger mMessenger;
        
        ServiceCallbacksApi21(final MediaBrowserServiceCompatApi21.ServiceCallbacks mCallbacks) {
            this.mCallbacks = mCallbacks;
        }
        
        @Override
        public IBinder asBinder() {
            return this.mCallbacks.asBinder();
        }
        
        @Override
        public void onConnect(final String s, final MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.this.mHandler);
            BundleCompat.putBinder(bundle, "extra_messenger", this.mMessenger.getBinder());
            bundle.putInt("extra_service_version", 1);
            this.mCallbacks.onConnect(s, token.getToken(), bundle);
        }
        
        @Override
        public void onConnectFailed() throws RemoteException {
            this.mCallbacks.onConnectFailed();
        }
        
        @Override
        public void onLoadChildren(final String s, final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) throws RemoteException {
            List<Parcel> list2 = null;
            if (list != null) {
                list2 = new ArrayList<Parcel>();
                for (final MediaBrowserCompat.MediaItem mediaItem : list) {
                    final Parcel obtain = Parcel.obtain();
                    mediaItem.writeToParcel(obtain, 0);
                    list2.add(obtain);
                }
            }
            this.mCallbacks.onLoadChildren(s, list2);
        }
    }
    
    private class ServiceCallbacksCompat implements ServiceCallbacks
    {
        final Messenger mCallbacks;
        
        ServiceCallbacksCompat(final Messenger mCallbacks) {
            this.mCallbacks = mCallbacks;
        }
        
        private void sendRequest(final int what, final Bundle data) throws RemoteException {
            final Message obtain = Message.obtain();
            obtain.what = what;
            obtain.arg1 = 1;
            obtain.setData(data);
            this.mCallbacks.send(obtain);
        }
        
        @Override
        public IBinder asBinder() {
            return this.mCallbacks.getBinder();
        }
        
        @Override
        public void onConnect(final String s, final MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putInt("extra_service_version", 1);
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putParcelable("data_media_session_token", (Parcelable)token);
            bundle2.putBundle("data_root_hints", bundle);
            this.sendRequest(1, bundle2);
        }
        
        @Override
        public void onConnectFailed() throws RemoteException {
            this.sendRequest(2, null);
        }
        
        @Override
        public void onLoadChildren(final String s, final List<MediaBrowserCompat.MediaItem> list, final Bundle bundle) throws RemoteException {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("data_media_item_id", s);
            bundle2.putBundle("data_options", bundle);
            if (list != null) {
                ArrayList list2;
                if (list instanceof ArrayList) {
                    list2 = (ArrayList<? extends E>)list;
                }
                else {
                    list2 = new ArrayList((Collection<? extends E>)list);
                }
                bundle2.putParcelableArrayList("data_media_item_list", list2);
            }
            this.sendRequest(3, bundle2);
        }
    }
    
    private final class ServiceHandler extends Handler
    {
        private final ServiceImpl mServiceImpl;
        
        private ServiceHandler() {
            this.mServiceImpl = new ServiceImpl();
        }
        
        public ServiceImpl getServiceImpl() {
            return this.mServiceImpl;
        }
        
        public void handleMessage(final Message message) {
            final Bundle data = message.getData();
            switch (message.what) {
                default: {
                    Log.w("MediaBrowserServiceCompat", "Unhandled message: " + message + "\n  Service version: " + 1 + "\n  Client version: " + message.arg1);
                }
                case 1: {
                    this.mServiceImpl.connect(data.getString("data_package_name"), data.getInt("data_calling_uid"), data.getBundle("data_root_hints"), new ServiceCallbacksCompat(message.replyTo));
                }
                case 2: {
                    this.mServiceImpl.disconnect(new ServiceCallbacksCompat(message.replyTo));
                }
                case 3: {
                    this.mServiceImpl.addSubscription(data.getString("data_media_item_id"), data.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
                }
                case 4: {
                    this.mServiceImpl.removeSubscription(data.getString("data_media_item_id"), data.getBundle("data_options"), new ServiceCallbacksCompat(message.replyTo));
                }
                case 5: {
                    this.mServiceImpl.getMediaItem(data.getString("data_media_item_id"), (ResultReceiver)data.getParcelable("data_result_receiver"));
                }
                case 6: {
                    this.mServiceImpl.registerCallbacks(new ServiceCallbacksCompat(message.replyTo));
                }
            }
        }
        
        public void postOrRun(final Runnable runnable) {
            if (Thread.currentThread() == this.getLooper().getThread()) {
                runnable.run();
                return;
            }
            this.post(runnable);
        }
        
        public boolean sendMessageAtTime(final Message message, final long n) {
            final Bundle data = message.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt("data_calling_uid", Binder.getCallingUid());
            return super.sendMessageAtTime(message, n);
        }
    }
    
    private class ServiceImpl
    {
        public void addSubscription(final String s, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MediaBrowserServiceCompat", "addSubscription for callback that isn't registered id=" + s);
                        return;
                    }
                    MediaBrowserServiceCompat.this.addSubscription(s, connectionRecord, bundle);
                }
            });
        }
        
        public void connect(final String s, final int n, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            if (!MediaBrowserServiceCompat.this.isValidPackage(s, n)) {
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + n + " package=" + s);
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final IBinder binder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    final ConnectionRecord connectionRecord = new ConnectionRecord();
                    connectionRecord.pkg = s;
                    connectionRecord.rootHints = bundle;
                    connectionRecord.callbacks = serviceCallbacks;
                    connectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(s, n, bundle);
                    Label_0182: {
                        if (connectionRecord.root != null) {
                            break Label_0182;
                        }
                        Log.i("MediaBrowserServiceCompat", "No root for client " + s + " from service " + this.getClass().getName());
                        try {
                            serviceCallbacks.onConnectFailed();
                            return;
                        }
                        catch (RemoteException ex) {
                            Log.w("MediaBrowserServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + s);
                            return;
                        }
                        try {
                            MediaBrowserServiceCompat.this.mConnections.put(binder, connectionRecord);
                            if (MediaBrowserServiceCompat.this.mSession != null) {
                                serviceCallbacks.onConnect(connectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, connectionRecord.root.getExtras());
                            }
                        }
                        catch (RemoteException ex2) {
                            Log.w("MediaBrowserServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + s);
                            MediaBrowserServiceCompat.this.mConnections.remove(binder);
                        }
                    }
                }
            });
        }
        
        public void disconnect(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    if (MediaBrowserServiceCompat.this.mConnections.remove(serviceCallbacks.asBinder()) != null) {}
                }
            });
        }
        
        public void getMediaItem(final String s, final ResultReceiver resultReceiver) {
            if (TextUtils.isEmpty((CharSequence)s) || resultReceiver == null) {
                return;
            }
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    MediaBrowserServiceCompat.this.performLoadItem(s, resultReceiver);
                }
            });
        }
        
        public void registerCallbacks(final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final IBinder binder = serviceCallbacks.asBinder();
                    MediaBrowserServiceCompat.this.mConnections.remove(binder);
                    final ConnectionRecord connectionRecord = new ConnectionRecord();
                    connectionRecord.callbacks = serviceCallbacks;
                    MediaBrowserServiceCompat.this.mConnections.put(binder, connectionRecord);
                }
            });
        }
        
        public void removeSubscription(final String s, final Bundle bundle, final ServiceCallbacks serviceCallbacks) {
            MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable() {
                @Override
                public void run() {
                    final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(serviceCallbacks.asBinder());
                    if (connectionRecord == null) {
                        Log.w("MediaBrowserServiceCompat", "removeSubscription for callback that isn't registered id=" + s);
                    }
                    else if (!MediaBrowserServiceCompat.this.removeSubscription(s, connectionRecord, bundle)) {
                        Log.w("MediaBrowserServiceCompat", "removeSubscription called for " + s + " which is not subscribed");
                    }
                }
            });
        }
    }
    
    private class ServiceImplApi21 implements MediaBrowserServiceCompatApi21.ServiceImplApi21
    {
        final ServiceImpl mServiceImpl;
        
        ServiceImplApi21() {
            this.mServiceImpl = MediaBrowserServiceCompat.this.mHandler.getServiceImpl();
        }
        
        @Override
        public void addSubscription(final String s, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.addSubscription(s, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
        }
        
        @Override
        public void connect(final String s, final Bundle bundle, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.connect(s, Binder.getCallingUid(), bundle, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
        }
        
        @Override
        public void disconnect(final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
        }
        
        @Override
        public void removeSubscription(final String s, final MediaBrowserServiceCompatApi21.ServiceCallbacks serviceCallbacks) {
            this.mServiceImpl.removeSubscription(s, null, new MediaBrowserServiceCompat.ServiceCallbacksApi21(serviceCallbacks));
        }
    }
    
    private class ServiceImplApi23 extends MediaBrowserServiceCompat.ServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceImplApi23
    {
        @Override
        public void getMediaItem(final String s, final ItemCallback itemCallback) {
            this.mServiceImpl.getMediaItem(s, new ResultReceiver(MediaBrowserServiceCompat.this.mHandler) {
                @Override
                protected void onReceiveResult(final int n, final Bundle bundle) {
                    final MediaBrowserCompat.MediaItem mediaItem = (MediaBrowserCompat.MediaItem)bundle.getParcelable("media_item");
                    Parcel obtain = null;
                    if (mediaItem != null) {
                        obtain = Parcel.obtain();
                        mediaItem.writeToParcel(obtain, 0);
                    }
                    itemCallback.onItemLoaded(n, bundle, obtain);
                }
            });
        }
    }
}
