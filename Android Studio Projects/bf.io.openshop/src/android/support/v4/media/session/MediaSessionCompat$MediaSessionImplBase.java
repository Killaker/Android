package android.support.v4.media.session;

import android.media.*;
import android.app.*;
import java.util.*;
import android.graphics.*;
import android.support.v4.media.*;
import android.net.*;
import android.view.*;
import android.content.*;
import android.os.*;

static class MediaSessionImplBase implements MediaSessionImpl
{
    private final AudioManager mAudioManager;
    private volatile Callback mCallback;
    private final ComponentName mComponentName;
    private final Context mContext;
    private final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks;
    private boolean mDestroyed;
    private Bundle mExtras;
    private int mFlags;
    private MessageHandler mHandler;
    private boolean mIsActive;
    private boolean mIsMbrRegistered;
    private boolean mIsRccRegistered;
    private int mLocalStream;
    private final Object mLock;
    private final PendingIntent mMediaButtonEventReceiver;
    private MediaMetadataCompat mMetadata;
    private final String mPackageName;
    private List<QueueItem> mQueue;
    private CharSequence mQueueTitle;
    private int mRatingType;
    private final Object mRccObj;
    private PendingIntent mSessionActivity;
    private PlaybackStateCompat mState;
    private final MediaSessionStub mStub;
    private final String mTag;
    private final Token mToken;
    private VolumeProviderCompat.Callback mVolumeCallback;
    private VolumeProviderCompat mVolumeProvider;
    private int mVolumeType;
    
    public MediaSessionImplBase(final Context mContext, final String mTag, final ComponentName mComponentName, final PendingIntent mMediaButtonEventReceiver) {
        this.mLock = new Object();
        this.mControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
        this.mDestroyed = false;
        this.mIsActive = false;
        this.mIsRccRegistered = false;
        this.mIsMbrRegistered = false;
        this.mVolumeCallback = new VolumeProviderCompat.Callback() {
            @Override
            public void onVolumeChanged(final VolumeProviderCompat volumeProviderCompat) {
                if (MediaSessionImplBase.this.mVolumeProvider != volumeProviderCompat) {
                    return;
                }
                MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
            }
        };
        if (mComponentName == null) {
            throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
        }
        this.mContext = mContext;
        this.mPackageName = mContext.getPackageName();
        this.mAudioManager = (AudioManager)mContext.getSystemService("audio");
        this.mTag = mTag;
        this.mComponentName = mComponentName;
        this.mMediaButtonEventReceiver = mMediaButtonEventReceiver;
        this.mStub = new MediaSessionStub();
        this.mToken = new Token(this.mStub);
        this.mRatingType = 0;
        this.mVolumeType = 1;
        this.mLocalStream = 3;
        if (Build$VERSION.SDK_INT >= 14) {
            this.mRccObj = MediaSessionCompatApi14.createRemoteControlClient(mMediaButtonEventReceiver);
            return;
        }
        this.mRccObj = null;
    }
    
    private void adjustVolume(final int n, final int n2) {
        if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onAdjustVolume(n);
            }
            return;
        }
        this.mAudioManager.adjustStreamVolume(this.mLocalStream, n, n2);
    }
    
    private MediaMetadataCompat cloneMetadataIfNeeded(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            mediaMetadataCompat = null;
        }
        else if (mediaMetadataCompat.containsKey("android.media.metadata.ART") || mediaMetadataCompat.containsKey("android.media.metadata.ALBUM_ART")) {
            final MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder(mediaMetadataCompat);
            final Bitmap bitmap = mediaMetadataCompat.getBitmap("android.media.metadata.ART");
            if (bitmap != null) {
                builder.putBitmap("android.media.metadata.ART", bitmap.copy(bitmap.getConfig(), false));
            }
            final Bitmap bitmap2 = mediaMetadataCompat.getBitmap("android.media.metadata.ALBUM_ART");
            if (bitmap2 != null) {
                builder.putBitmap("android.media.metadata.ALBUM_ART", bitmap2.copy(bitmap2.getConfig(), false));
            }
            return builder.build();
        }
        return mediaMetadataCompat;
    }
    
    private PlaybackStateCompat getStateWithUpdatedPosition() {
        PlaybackStateCompat build;
        while (true) {
            long long1 = -1L;
            while (true) {
                long n2 = 0L;
                Label_0208: {
                    synchronized (this.mLock) {
                        final PlaybackStateCompat mState = this.mState;
                        if (this.mMetadata != null && this.mMetadata.containsKey("android.media.metadata.DURATION")) {
                            long1 = this.mMetadata.getLong("android.media.metadata.DURATION");
                        }
                        // monitorexit(this.mLock)
                        build = null;
                        Label_0193: {
                            if (mState != null) {
                                if (mState.getState() != 3 && mState.getState() != 4) {
                                    final int state = mState.getState();
                                    build = null;
                                    if (state != 5) {
                                        break Label_0193;
                                    }
                                }
                                final long lastPositionUpdateTime = mState.getLastPositionUpdateTime();
                                final long elapsedRealtime = SystemClock.elapsedRealtime();
                                final long n = lcmp(lastPositionUpdateTime, 0L);
                                build = null;
                                if (n > 0) {
                                    n2 = (long)(mState.getPlaybackSpeed() * (elapsedRealtime - lastPositionUpdateTime)) + mState.getPosition();
                                    if (long1 < 0L || n2 <= long1) {
                                        break Label_0208;
                                    }
                                    n2 = long1;
                                    final PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder(mState);
                                    builder.setState(mState.getState(), n2, mState.getPlaybackSpeed(), elapsedRealtime);
                                    build = builder.build();
                                }
                            }
                        }
                        if (build == null) {
                            return mState;
                        }
                        break;
                    }
                }
                if (n2 < 0L) {
                    n2 = 0L;
                    continue;
                }
                continue;
            }
        }
        return build;
    }
    
    private void postToHandler(final int n) {
        this.postToHandler(n, null);
    }
    
    private void postToHandler(final int n, final Object o) {
        this.postToHandler(n, o, null);
    }
    
    private void postToHandler(final int n, final Object o, final Bundle bundle) {
        synchronized (this.mLock) {
            if (this.mHandler != null) {
                this.mHandler.post(n, o, bundle);
            }
        }
    }
    
    private void sendEvent(final String s, final Bundle bundle) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0036_Outer:
        while (true) {
            Label_0042: {
                if (n < 0) {
                    break Label_0042;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onEvent(s, bundle);
                        --n;
                        continue Label_0036_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0033_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onMetadataChanged(mediaMetadataCompat);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendQueue(final List<QueueItem> list) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0033_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onQueueChanged(list);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendQueueTitle(final CharSequence charSequence) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0033_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onQueueTitleChanged(charSequence);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendSessionDestroyed() {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0032_Outer:
        while (true) {
            Label_0038: {
                if (n < 0) {
                    break Label_0038;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onSessionDestroyed();
                        --n;
                        continue Label_0032_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                        this.mControllerCallbacks.kill();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendState(final PlaybackStateCompat playbackStateCompat) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0033_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onPlaybackStateChanged(playbackStateCompat);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void sendVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) {
        int n = -1 + this.mControllerCallbacks.beginBroadcast();
    Label_0033_Outer:
        while (true) {
            Label_0039: {
                if (n < 0) {
                    break Label_0039;
                }
                final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                while (true) {
                    try {
                        mediaControllerCallback.onVolumeInfoChanged(parcelableVolumeInfo);
                        --n;
                        continue Label_0033_Outer;
                        this.mControllerCallbacks.finishBroadcast();
                    }
                    catch (RemoteException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    private void setVolumeTo(final int n, final int n2) {
        if (this.mVolumeType == 2) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onSetVolumeTo(n);
            }
            return;
        }
        this.mAudioManager.setStreamVolume(this.mLocalStream, n, n2);
    }
    
    private boolean update() {
        boolean b;
        if (this.mIsActive) {
            if (Build$VERSION.SDK_INT >= 8) {
                if (!this.mIsMbrRegistered && (0x1 & this.mFlags) != 0x0) {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.registerMediaButtonEventReceiver(this.mContext, this.mMediaButtonEventReceiver, this.mComponentName);
                    }
                    else {
                        MediaSessionCompatApi8.registerMediaButtonEventReceiver(this.mContext, this.mComponentName);
                    }
                    this.mIsMbrRegistered = true;
                }
                else if (this.mIsMbrRegistered && (0x1 & this.mFlags) == 0x0) {
                    if (Build$VERSION.SDK_INT >= 18) {
                        MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonEventReceiver, this.mComponentName);
                    }
                    else {
                        MediaSessionCompatApi8.unregisterMediaButtonEventReceiver(this.mContext, this.mComponentName);
                    }
                    this.mIsMbrRegistered = false;
                }
            }
            final int sdk_INT = Build$VERSION.SDK_INT;
            b = false;
            if (sdk_INT >= 14) {
                if (!this.mIsRccRegistered && (0x2 & this.mFlags) != 0x0) {
                    MediaSessionCompatApi14.registerRemoteControlClient(this.mContext, this.mRccObj);
                    this.mIsRccRegistered = true;
                    b = true;
                }
                else {
                    final boolean mIsRccRegistered = this.mIsRccRegistered;
                    b = false;
                    if (mIsRccRegistered) {
                        final int n = 0x2 & this.mFlags;
                        b = false;
                        if (n == 0) {
                            MediaSessionCompatApi14.setState(this.mRccObj, 0);
                            MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                            return this.mIsRccRegistered = false;
                        }
                    }
                }
            }
        }
        else {
            if (this.mIsMbrRegistered) {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(this.mContext, this.mMediaButtonEventReceiver, this.mComponentName);
                }
                else {
                    MediaSessionCompatApi8.unregisterMediaButtonEventReceiver(this.mContext, this.mComponentName);
                }
                this.mIsMbrRegistered = false;
            }
            final boolean mIsRccRegistered2 = this.mIsRccRegistered;
            b = false;
            if (mIsRccRegistered2) {
                MediaSessionCompatApi14.setState(this.mRccObj, 0);
                MediaSessionCompatApi14.unregisterRemoteControlClient(this.mContext, this.mRccObj);
                return this.mIsRccRegistered = false;
            }
        }
        return b;
    }
    
    @Override
    public Object getMediaSession() {
        return null;
    }
    
    @Override
    public Object getRemoteControlClient() {
        return this.mRccObj;
    }
    
    @Override
    public Token getSessionToken() {
        return this.mToken;
    }
    
    @Override
    public boolean isActive() {
        return this.mIsActive;
    }
    
    @Override
    public void release() {
        this.mIsActive = false;
        this.mDestroyed = true;
        this.update();
        this.sendSessionDestroyed();
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
        this.sendEvent(s, bundle);
    }
    
    @Override
    public void setActive(final boolean mIsActive) {
        if (mIsActive != this.mIsActive) {
            this.mIsActive = mIsActive;
            if (this.update()) {
                this.setMetadata(this.mMetadata);
                this.setPlaybackState(this.mState);
            }
        }
    }
    
    @Override
    public void setCallback(final Callback mCallback, Handler handler) {
        this.mCallback = mCallback;
        if (mCallback == null) {
            if (Build$VERSION.SDK_INT >= 18) {
                MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, null);
            }
            if (Build$VERSION.SDK_INT >= 19) {
                MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, null);
            }
        }
        else {
            if (handler == null) {
                handler = new Handler();
            }
            synchronized (this.mLock) {
                this.mHandler = new MessageHandler(handler.getLooper());
                // monitorexit(this.mLock)
                final MediaSessionCompatApi19.Callback callback = new MediaSessionCompatApi19.Callback() {
                    @Override
                    public void onSeekTo(final long n) {
                        MediaSessionImplBase.this.postToHandler(11, n);
                    }
                    
                    @Override
                    public void onSetRating(final Object o) {
                        MediaSessionImplBase.this.postToHandler(12, RatingCompat.fromRating(o));
                    }
                };
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(this.mRccObj, MediaSessionCompatApi18.createPlaybackPositionUpdateListener((MediaSessionCompatApi18.Callback)callback));
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setOnMetadataUpdateListener(this.mRccObj, MediaSessionCompatApi19.createMetadataUpdateListener((MediaSessionCompatApi19.Callback)callback));
                }
            }
        }
    }
    
    @Override
    public void setExtras(final Bundle mExtras) {
        this.mExtras = mExtras;
    }
    
    @Override
    public void setFlags(final int mFlags) {
        synchronized (this.mLock) {
            this.mFlags = mFlags;
            // monitorexit(this.mLock)
            this.update();
        }
    }
    
    @Override
    public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
    }
    
    @Override
    public void setMetadata(MediaMetadataCompat cloneMetadataIfNeeded) {
        if (Build$VERSION.SDK_INT >= 14 && cloneMetadataIfNeeded != null) {
            cloneMetadataIfNeeded = this.cloneMetadataIfNeeded(cloneMetadataIfNeeded);
        }
        while (true) {
            synchronized (this.mLock) {
                this.mMetadata = cloneMetadataIfNeeded;
                // monitorexit(this.mLock)
                this.sendMetadata(cloneMetadataIfNeeded);
                if (!this.mIsActive) {
                    return;
                }
            }
            if (Build$VERSION.SDK_INT >= 19) {
                final Object mRccObj = this.mRccObj;
                Bundle bundle = null;
                if (cloneMetadataIfNeeded != null) {
                    bundle = cloneMetadataIfNeeded.getBundle();
                }
                long actions;
                if (this.mState == null) {
                    actions = 0L;
                }
                else {
                    actions = this.mState.getActions();
                }
                MediaSessionCompatApi19.setMetadata(mRccObj, bundle, actions);
                return;
            }
            if (Build$VERSION.SDK_INT >= 14) {
                final Object mRccObj2 = this.mRccObj;
                Bundle bundle2 = null;
                if (cloneMetadataIfNeeded != null) {
                    bundle2 = cloneMetadataIfNeeded.getBundle();
                }
                MediaSessionCompatApi14.setMetadata(mRccObj2, bundle2);
            }
        }
    }
    
    @Override
    public void setPlaybackState(final PlaybackStateCompat mState) {
        while (true) {
            synchronized (this.mLock) {
                this.mState = mState;
                // monitorexit(this.mLock)
                this.sendState(mState);
                if (!this.mIsActive) {
                    return;
                }
            }
            if (mState == null) {
                if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setState(this.mRccObj, 0);
                    MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, 0L);
                }
            }
            else {
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setState(this.mRccObj, mState.getState(), mState.getPosition(), mState.getPlaybackSpeed(), mState.getLastPositionUpdateTime());
                }
                else if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setState(this.mRccObj, mState.getState());
                }
                if (Build$VERSION.SDK_INT >= 19) {
                    MediaSessionCompatApi19.setTransportControlFlags(this.mRccObj, mState.getActions());
                    return;
                }
                if (Build$VERSION.SDK_INT >= 18) {
                    MediaSessionCompatApi18.setTransportControlFlags(this.mRccObj, mState.getActions());
                    return;
                }
                if (Build$VERSION.SDK_INT >= 14) {
                    MediaSessionCompatApi14.setTransportControlFlags(this.mRccObj, mState.getActions());
                }
            }
        }
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
        if (this.mVolumeProvider != null) {
            this.mVolumeProvider.setCallback(null);
        }
        this.mVolumeType = 1;
        this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat mVolumeProvider) {
        if (mVolumeProvider == null) {
            throw new IllegalArgumentException("volumeProvider may not be null");
        }
        if (this.mVolumeProvider != null) {
            this.mVolumeProvider.setCallback(null);
        }
        this.mVolumeType = 2;
        this.mVolumeProvider = mVolumeProvider;
        this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
        mVolumeProvider.setCallback(this.mVolumeCallback);
    }
    
    @Override
    public void setQueue(final List<QueueItem> mQueue) {
        this.sendQueue(this.mQueue = mQueue);
    }
    
    @Override
    public void setQueueTitle(final CharSequence mQueueTitle) {
        this.sendQueueTitle(this.mQueueTitle = mQueueTitle);
    }
    
    @Override
    public void setRatingType(final int mRatingType) {
        this.mRatingType = mRatingType;
    }
    
    @Override
    public void setSessionActivity(final PendingIntent mSessionActivity) {
        synchronized (this.mLock) {
            this.mSessionActivity = mSessionActivity;
        }
    }
    
    private static final class Command
    {
        public final String command;
        public final Bundle extras;
        public final ResultReceiver stub;
        
        public Command(final String command, final Bundle extras, final ResultReceiver stub) {
            this.command = command;
            this.extras = extras;
            this.stub = stub;
        }
    }
    
    class MediaSessionStub extends Stub
    {
        public void adjustVolume(final int n, final int n2, final String s) {
            MediaSessionImplBase.this.adjustVolume(n, n2);
        }
        
        public void fastForward() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(9);
        }
        
        public Bundle getExtras() {
            synchronized (MediaSessionImplBase.this.mLock) {
                return MediaSessionImplBase.this.mExtras;
            }
        }
        
        public long getFlags() {
            synchronized (MediaSessionImplBase.this.mLock) {
                return MediaSessionImplBase.this.mFlags;
            }
        }
        
        public PendingIntent getLaunchPendingIntent() {
            synchronized (MediaSessionImplBase.this.mLock) {
                return MediaSessionImplBase.this.mSessionActivity;
            }
        }
        
        public MediaMetadataCompat getMetadata() {
            return MediaSessionImplBase.this.mMetadata;
        }
        
        public String getPackageName() {
            return MediaSessionImplBase.this.mPackageName;
        }
        
        public PlaybackStateCompat getPlaybackState() {
            return MediaSessionImplBase.this.getStateWithUpdatedPosition();
        }
        
        public List<QueueItem> getQueue() {
            synchronized (MediaSessionImplBase.this.mLock) {
                return MediaSessionImplBase.this.mQueue;
            }
        }
        
        public CharSequence getQueueTitle() {
            return MediaSessionImplBase.this.mQueueTitle;
        }
        
        public int getRatingType() {
            return MediaSessionImplBase.this.mRatingType;
        }
        
        public String getTag() {
            return MediaSessionImplBase.this.mTag;
        }
        
        public ParcelableVolumeInfo getVolumeAttributes() {
            synchronized (MediaSessionImplBase.this.mLock) {
                final int access$400 = MediaSessionImplBase.this.mVolumeType;
                final int access$401 = MediaSessionImplBase.this.mLocalStream;
                final VolumeProviderCompat access$402 = MediaSessionImplBase.this.mVolumeProvider;
                int volumeControl;
                int n;
                int n2;
                if (access$400 == 2) {
                    volumeControl = access$402.getVolumeControl();
                    n = access$402.getMaxVolume();
                    n2 = access$402.getCurrentVolume();
                }
                else {
                    volumeControl = 2;
                    n = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(access$401);
                    n2 = MediaSessionImplBase.this.mAudioManager.getStreamVolume(access$401);
                }
                // monitorexit(MediaSessionImplBase.access$1400(this.this$0))
                return new ParcelableVolumeInfo(access$400, access$401, volumeControl, n, n2);
            }
        }
        
        public boolean isTransportControlEnabled() {
            return (0x2 & MediaSessionImplBase.this.mFlags) != 0x0;
        }
        
        public void next() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(7);
        }
        
        public void pause() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(5);
        }
        
        public void play() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(1);
        }
        
        public void playFromMediaId(final String s, final Bundle bundle) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(2, s, bundle);
        }
        
        public void playFromSearch(final String s, final Bundle bundle) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(3, s, bundle);
        }
        
        public void playFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(18, uri, bundle);
        }
        
        public void previous() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(8);
        }
        
        public void rate(final RatingCompat ratingCompat) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(12, ratingCompat);
        }
        
        public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
            Label_0017: {
                if (!MediaSessionImplBase.this.mDestroyed) {
                    break Label_0017;
                }
                try {
                    mediaControllerCallback.onSessionDestroyed();
                    return;
                    MediaSessionImplBase.this.mControllerCallbacks.register((IInterface)mediaControllerCallback);
                }
                catch (Exception ex) {}
            }
        }
        
        public void rewind() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(10);
        }
        
        public void seekTo(final long n) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(11, n);
        }
        
        public void sendCommand(final String s, final Bundle bundle, final ResultReceiverWrapper resultReceiverWrapper) {
            MediaSessionImplBase.this.postToHandler(15, new Command(s, bundle, resultReceiverWrapper.mResultReceiver));
        }
        
        public void sendCustomAction(final String s, final Bundle bundle) throws RemoteException {
            MediaSessionImplBase.this.postToHandler(13, s, bundle);
        }
        
        public boolean sendMediaButton(final KeyEvent keyEvent) {
            final boolean b = (0x1 & MediaSessionImplBase.this.mFlags) != 0x0;
            if (b) {
                MediaSessionImplBase.this.postToHandler(14, keyEvent);
            }
            return b;
        }
        
        public void setVolumeTo(final int n, final int n2, final String s) {
            MediaSessionImplBase.this.setVolumeTo(n, n2);
        }
        
        public void skipToQueueItem(final long n) {
            MediaSessionImplBase.this.postToHandler(4, n);
        }
        
        public void stop() throws RemoteException {
            MediaSessionImplBase.this.postToHandler(6);
        }
        
        public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
            MediaSessionImplBase.this.mControllerCallbacks.unregister((IInterface)mediaControllerCallback);
        }
    }
    
    private class MessageHandler extends Handler
    {
        private static final int KEYCODE_MEDIA_PAUSE = 127;
        private static final int KEYCODE_MEDIA_PLAY = 126;
        private static final int MSG_ADJUST_VOLUME = 16;
        private static final int MSG_COMMAND = 15;
        private static final int MSG_CUSTOM_ACTION = 13;
        private static final int MSG_FAST_FORWARD = 9;
        private static final int MSG_MEDIA_BUTTON = 14;
        private static final int MSG_NEXT = 7;
        private static final int MSG_PAUSE = 5;
        private static final int MSG_PLAY = 1;
        private static final int MSG_PLAY_MEDIA_ID = 2;
        private static final int MSG_PLAY_SEARCH = 3;
        private static final int MSG_PLAY_URI = 18;
        private static final int MSG_PREVIOUS = 8;
        private static final int MSG_RATE = 12;
        private static final int MSG_REWIND = 10;
        private static final int MSG_SEEK_TO = 11;
        private static final int MSG_SET_VOLUME = 17;
        private static final int MSG_SKIP_TO_ITEM = 4;
        private static final int MSG_STOP = 6;
        
        public MessageHandler(final Looper looper) {
            super(looper);
        }
        
        private void onMediaButtonEvent(final KeyEvent keyEvent, final Callback callback) {
            int n = 1;
            if (keyEvent != null && keyEvent.getAction() == 0) {
                long actions;
                if (MediaSessionImplBase.this.mState == null) {
                    actions = 0L;
                }
                else {
                    actions = MediaSessionImplBase.this.mState.getActions();
                }
                switch (keyEvent.getKeyCode()) {
                    default: {}
                    case 79:
                    case 85: {
                        int n2;
                        if (MediaSessionImplBase.this.mState != null && MediaSessionImplBase.this.mState.getState() == 3) {
                            n2 = n;
                        }
                        else {
                            n2 = 0;
                        }
                        int n3;
                        if ((0x204L & actions) != 0x0L) {
                            n3 = n;
                        }
                        else {
                            n3 = 0;
                        }
                        if ((0x202L & actions) == 0x0L) {
                            n = 0;
                        }
                        if (n2 != 0 && n != 0) {
                            callback.onPause();
                            return;
                        }
                        if (n2 == 0 && n3 != 0) {
                            callback.onPlay();
                            return;
                        }
                        break;
                    }
                    case 126: {
                        if ((0x4L & actions) != 0x0L) {
                            callback.onPlay();
                            return;
                        }
                        break;
                    }
                    case 127: {
                        if ((0x2L & actions) != 0x0L) {
                            callback.onPause();
                            return;
                        }
                        break;
                    }
                    case 87: {
                        if ((0x20L & actions) != 0x0L) {
                            callback.onSkipToNext();
                            return;
                        }
                        break;
                    }
                    case 88: {
                        if ((0x10L & actions) != 0x0L) {
                            callback.onSkipToPrevious();
                            return;
                        }
                        break;
                    }
                    case 86: {
                        if ((0x1L & actions) != 0x0L) {
                            callback.onStop();
                            return;
                        }
                        break;
                    }
                    case 90: {
                        if ((0x40L & actions) != 0x0L) {
                            callback.onFastForward();
                            return;
                        }
                        break;
                    }
                    case 89: {
                        if ((0x8L & actions) != 0x0L) {
                            callback.onRewind();
                            return;
                        }
                        break;
                    }
                }
            }
        }
        
        public void handleMessage(final Message message) {
            final Callback access$2700 = MediaSessionImplBase.this.mCallback;
            if (access$2700 != null) {
                switch (message.what) {
                    default: {}
                    case 1: {
                        access$2700.onPlay();
                    }
                    case 2: {
                        access$2700.onPlayFromMediaId((String)message.obj, message.getData());
                    }
                    case 3: {
                        access$2700.onPlayFromSearch((String)message.obj, message.getData());
                    }
                    case 18: {
                        access$2700.onPlayFromUri((Uri)message.obj, message.getData());
                    }
                    case 4: {
                        access$2700.onSkipToQueueItem((long)message.obj);
                    }
                    case 5: {
                        access$2700.onPause();
                    }
                    case 6: {
                        access$2700.onStop();
                    }
                    case 7: {
                        access$2700.onSkipToNext();
                    }
                    case 8: {
                        access$2700.onSkipToPrevious();
                    }
                    case 9: {
                        access$2700.onFastForward();
                    }
                    case 10: {
                        access$2700.onRewind();
                    }
                    case 11: {
                        access$2700.onSeekTo((long)message.obj);
                    }
                    case 12: {
                        access$2700.onSetRating((RatingCompat)message.obj);
                    }
                    case 13: {
                        access$2700.onCustomAction((String)message.obj, message.getData());
                    }
                    case 14: {
                        final KeyEvent keyEvent = (KeyEvent)message.obj;
                        final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                        intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)keyEvent);
                        if (!access$2700.onMediaButtonEvent(intent)) {
                            this.onMediaButtonEvent(keyEvent, access$2700);
                            return;
                        }
                        break;
                    }
                    case 15: {
                        final Command command = (Command)message.obj;
                        access$2700.onCommand(command.command, command.extras, command.stub);
                    }
                    case 16: {
                        MediaSessionImplBase.this.adjustVolume((int)message.obj, 0);
                    }
                    case 17: {
                        MediaSessionImplBase.this.setVolumeTo((int)message.obj, 0);
                    }
                }
            }
        }
        
        public void post(final int n) {
            this.post(n, null);
        }
        
        public void post(final int n, final Object o) {
            this.obtainMessage(n, o).sendToTarget();
        }
        
        public void post(final int n, final Object o, final int n2) {
            this.obtainMessage(n, n2, 0, o).sendToTarget();
        }
        
        public void post(final int n, final Object o, final Bundle data) {
            final Message obtainMessage = this.obtainMessage(n, o);
            obtainMessage.setData(data);
            obtainMessage.sendToTarget();
        }
    }
}
