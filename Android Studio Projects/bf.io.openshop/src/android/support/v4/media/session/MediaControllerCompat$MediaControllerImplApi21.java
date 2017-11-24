package android.support.v4.media.session;

import android.content.*;
import android.view.*;
import android.support.v4.media.*;
import java.util.*;
import android.app.*;
import android.os.*;

static class MediaControllerImplApi21 implements MediaControllerImpl
{
    protected final Object mControllerObj;
    
    public MediaControllerImplApi21(final Context context, final MediaSessionCompat.Token token) throws RemoteException {
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, token.getToken());
        if (this.mControllerObj == null) {
            throw new RemoteException();
        }
    }
    
    public MediaControllerImplApi21(final Context context, final MediaSessionCompat mediaSessionCompat) {
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
    }
    
    @Override
    public void adjustVolume(final int n, final int n2) {
        MediaControllerCompatApi21.adjustVolume(this.mControllerObj, n, n2);
    }
    
    @Override
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
    }
    
    @Override
    public Bundle getExtras() {
        return MediaControllerCompatApi21.getExtras(this.mControllerObj);
    }
    
    @Override
    public long getFlags() {
        return MediaControllerCompatApi21.getFlags(this.mControllerObj);
    }
    
    @Override
    public Object getMediaController() {
        return this.mControllerObj;
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        final Object metadata = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
        if (metadata != null) {
            return MediaMetadataCompat.fromMediaMetadata(metadata);
        }
        return null;
    }
    
    @Override
    public String getPackageName() {
        return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
    }
    
    @Override
    public PlaybackInfo getPlaybackInfo() {
        final Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
        if (playbackInfo != null) {
            return new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(playbackInfo), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(playbackInfo));
        }
        return null;
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        final Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
        if (playbackState != null) {
            return PlaybackStateCompat.fromPlaybackState(playbackState);
        }
        return null;
    }
    
    @Override
    public List<MediaSessionCompat.QueueItem> getQueue() {
        final List<Object> queue = MediaControllerCompatApi21.getQueue(this.mControllerObj);
        List<MediaSessionCompat.QueueItem> list;
        if (queue == null) {
            list = null;
        }
        else {
            list = new ArrayList<MediaSessionCompat.QueueItem>();
            final Iterator<Object> iterator = queue.iterator();
            while (iterator.hasNext()) {
                list.add(MediaSessionCompat.QueueItem.obtain(iterator.next()));
            }
        }
        return list;
    }
    
    @Override
    public CharSequence getQueueTitle() {
        return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
    }
    
    @Override
    public int getRatingType() {
        return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
    }
    
    @Override
    public PendingIntent getSessionActivity() {
        return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
    }
    
    @Override
    public TransportControls getTransportControls() {
        final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
        if (transportControls != null) {
            return new TransportControlsApi21(transportControls);
        }
        return null;
    }
    
    @Override
    public void registerCallback(final Callback callback, final Handler handler) {
        MediaControllerCompatApi21.registerCallback(this.mControllerObj, callback.mCallbackObj, handler);
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        MediaControllerCompatApi21.sendCommand(this.mControllerObj, s, bundle, resultReceiver);
    }
    
    @Override
    public void setVolumeTo(final int n, final int n2) {
        MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, n, n2);
    }
    
    @Override
    public void unregisterCallback(final Callback callback) {
        MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback.mCallbackObj);
    }
}
