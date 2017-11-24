package android.support.v4.media.session;

import android.support.v4.media.*;
import java.util.*;
import android.os.*;

public abstract static class Callback implements IBinder$DeathRecipient
{
    private final Object mCallbackObj;
    private MessageHandler mHandler;
    private boolean mRegistered;
    
    public Callback() {
        this.mRegistered = false;
        if (Build$VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaControllerCompatApi21.createCallback((MediaControllerCompatApi21.Callback)new StubApi21());
            return;
        }
        this.mCallbackObj = new StubCompat();
    }
    
    private void setHandler(final Handler handler) {
        this.mHandler = new MessageHandler(handler.getLooper());
    }
    
    public void binderDied() {
        this.onSessionDestroyed();
    }
    
    public void onAudioInfoChanged(final PlaybackInfo playbackInfo) {
    }
    
    public void onExtrasChanged(final Bundle bundle) {
    }
    
    public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
    }
    
    public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
    }
    
    public void onQueueChanged(final List<MediaSessionCompat.QueueItem> list) {
    }
    
    public void onQueueTitleChanged(final CharSequence charSequence) {
    }
    
    public void onSessionDestroyed() {
    }
    
    public void onSessionEvent(final String s, final Bundle bundle) {
    }
    
    private class MessageHandler extends Handler
    {
        private static final int MSG_DESTROYED = 8;
        private static final int MSG_EVENT = 1;
        private static final int MSG_UPDATE_EXTRAS = 7;
        private static final int MSG_UPDATE_METADATA = 3;
        private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
        private static final int MSG_UPDATE_QUEUE = 5;
        private static final int MSG_UPDATE_QUEUE_TITLE = 6;
        private static final int MSG_UPDATE_VOLUME = 4;
        
        public MessageHandler(final Looper looper) {
            super(looper);
        }
        
        public void handleMessage(final Message message) {
            if (!Callback.this.mRegistered) {
                return;
            }
            switch (message.what) {
                default: {}
                case 1: {
                    Callback.this.onSessionEvent((String)message.obj, message.getData());
                }
                case 2: {
                    Callback.this.onPlaybackStateChanged((PlaybackStateCompat)message.obj);
                }
                case 3: {
                    Callback.this.onMetadataChanged((MediaMetadataCompat)message.obj);
                }
                case 5: {
                    Callback.this.onQueueChanged((List<MediaSessionCompat.QueueItem>)message.obj);
                }
                case 6: {
                    Callback.this.onQueueTitleChanged((CharSequence)message.obj);
                }
                case 7: {
                    Callback.this.onExtrasChanged((Bundle)message.obj);
                }
                case 4: {
                    Callback.this.onAudioInfoChanged((PlaybackInfo)message.obj);
                }
                case 8: {
                    Callback.this.onSessionDestroyed();
                }
            }
        }
        
        public void post(final int n, final Object o, final Bundle data) {
            final Message obtainMessage = this.obtainMessage(n, o);
            obtainMessage.setData(data);
            obtainMessage.sendToTarget();
        }
    }
    
    private class StubApi21 implements MediaControllerCompatApi21.Callback
    {
        @Override
        public void onMetadataChanged(final Object o) {
            MediaControllerCompat.Callback.this.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(o));
        }
        
        @Override
        public void onPlaybackStateChanged(final Object o) {
            MediaControllerCompat.Callback.this.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(o));
        }
        
        @Override
        public void onSessionDestroyed() {
            MediaControllerCompat.Callback.this.onSessionDestroyed();
        }
        
        @Override
        public void onSessionEvent(final String s, final Bundle bundle) {
            MediaControllerCompat.Callback.this.onSessionEvent(s, bundle);
        }
    }
    
    private class StubCompat extends Stub
    {
        public void onEvent(final String s, final Bundle bundle) throws RemoteException {
            Callback.this.mHandler.post(1, s, bundle);
        }
        
        public void onExtrasChanged(final Bundle bundle) throws RemoteException {
            Callback.this.mHandler.post(7, bundle, null);
        }
        
        public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
            Callback.this.mHandler.post(3, mediaMetadataCompat, null);
        }
        
        public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) throws RemoteException {
            Callback.this.mHandler.post(2, playbackStateCompat, null);
        }
        
        public void onQueueChanged(final List<MediaSessionCompat.QueueItem> list) throws RemoteException {
            Callback.this.mHandler.post(5, list, null);
        }
        
        public void onQueueTitleChanged(final CharSequence charSequence) throws RemoteException {
            Callback.this.mHandler.post(6, charSequence, null);
        }
        
        public void onSessionDestroyed() throws RemoteException {
            Callback.this.mHandler.post(8, null, null);
        }
        
        public void onVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
            Object o = null;
            if (parcelableVolumeInfo != null) {
                o = new PlaybackInfo(parcelableVolumeInfo.volumeType, parcelableVolumeInfo.audioStream, parcelableVolumeInfo.controlType, parcelableVolumeInfo.maxVolume, parcelableVolumeInfo.currentVolume);
            }
            Callback.this.mHandler.post(4, o, null);
        }
    }
}
