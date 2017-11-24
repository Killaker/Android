package android.support.v4.media.session;

import android.util.*;
import android.view.*;
import android.support.v4.media.*;
import java.util.*;
import android.app.*;
import android.os.*;

static class MediaControllerImplBase implements MediaControllerImpl
{
    private IMediaSession mBinder;
    private MediaSessionCompat.Token mToken;
    private TransportControls mTransportControls;
    
    public MediaControllerImplBase(final MediaSessionCompat.Token mToken) {
        this.mToken = mToken;
        this.mBinder = IMediaSession.Stub.asInterface((IBinder)mToken.getToken());
    }
    
    @Override
    public void adjustVolume(final int n, final int n2) {
        try {
            this.mBinder.adjustVolume(n, n2, null);
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in adjustVolume. " + ex);
        }
    }
    
    @Override
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("event may not be null.");
        }
        try {
            this.mBinder.sendMediaButton(keyEvent);
            return false;
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent. " + ex);
            return false;
        }
    }
    
    @Override
    public Bundle getExtras() {
        try {
            return this.mBinder.getExtras();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getExtras. " + ex);
            return null;
        }
    }
    
    @Override
    public long getFlags() {
        try {
            return this.mBinder.getFlags();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getFlags. " + ex);
            return 0L;
        }
    }
    
    @Override
    public Object getMediaController() {
        return null;
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        try {
            return this.mBinder.getMetadata();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getMetadata. " + ex);
            return null;
        }
    }
    
    @Override
    public String getPackageName() {
        try {
            return this.mBinder.getPackageName();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getPackageName. " + ex);
            return null;
        }
    }
    
    @Override
    public PlaybackInfo getPlaybackInfo() {
        try {
            final ParcelableVolumeInfo volumeAttributes = this.mBinder.getVolumeAttributes();
            return new PlaybackInfo(volumeAttributes.volumeType, volumeAttributes.audioStream, volumeAttributes.controlType, volumeAttributes.maxVolume, volumeAttributes.currentVolume);
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo. " + ex);
            return null;
        }
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        try {
            return this.mBinder.getPlaybackState();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getPlaybackState. " + ex);
            return null;
        }
    }
    
    @Override
    public List<MediaSessionCompat.QueueItem> getQueue() {
        try {
            return this.mBinder.getQueue();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getQueue. " + ex);
            return null;
        }
    }
    
    @Override
    public CharSequence getQueueTitle() {
        try {
            return this.mBinder.getQueueTitle();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getQueueTitle. " + ex);
            return null;
        }
    }
    
    @Override
    public int getRatingType() {
        try {
            return this.mBinder.getRatingType();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getRatingType. " + ex);
            return 0;
        }
    }
    
    @Override
    public PendingIntent getSessionActivity() {
        try {
            return this.mBinder.getLaunchPendingIntent();
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in getSessionActivity. " + ex);
            return null;
        }
    }
    
    @Override
    public TransportControls getTransportControls() {
        if (this.mTransportControls == null) {
            this.mTransportControls = new TransportControlsBase(this.mBinder);
        }
        return this.mTransportControls;
    }
    
    @Override
    public void registerCallback(final Callback callback, final Handler handler) {
        if (callback == null) {
            throw new IllegalArgumentException("callback may not be null.");
        }
        try {
            this.mBinder.asBinder().linkToDeath((IBinder$DeathRecipient)callback, 0);
            this.mBinder.registerCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
            callback.setHandler(handler);
            callback.mRegistered = true;
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in registerCallback. " + ex);
            callback.onSessionDestroyed();
        }
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        try {
            this.mBinder.sendCommand(s, bundle, new MediaSessionCompat.ResultReceiverWrapper(resultReceiver));
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in sendCommand. " + ex);
        }
    }
    
    @Override
    public void setVolumeTo(final int n, final int n2) {
        try {
            this.mBinder.setVolumeTo(n, n2, null);
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in setVolumeTo. " + ex);
        }
    }
    
    @Override
    public void unregisterCallback(final Callback callback) {
        if (callback == null) {
            throw new IllegalArgumentException("callback may not be null.");
        }
        try {
            this.mBinder.unregisterCallbackListener((IMediaControllerCallback)callback.mCallbackObj);
            this.mBinder.asBinder().unlinkToDeath((IBinder$DeathRecipient)callback, 0);
            callback.mRegistered = false;
        }
        catch (RemoteException ex) {
            Log.e("MediaControllerCompat", "Dead object in unregisterCallback. " + ex);
        }
    }
}
