package android.support.v4.media.session;

import android.app.*;
import android.content.*;
import android.support.v4.media.*;
import java.util.*;
import android.os.*;

static class MediaSessionImplApi21 implements MediaSessionImpl
{
    private PendingIntent mMediaButtonIntent;
    private final Object mSessionObj;
    private final Token mToken;
    
    public MediaSessionImplApi21(final Context context, final String s) {
        this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
        this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    public MediaSessionImplApi21(final Object o) {
        this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
        this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    @Override
    public Object getMediaSession() {
        return this.mSessionObj;
    }
    
    @Override
    public Object getRemoteControlClient() {
        return null;
    }
    
    @Override
    public Token getSessionToken() {
        return this.mToken;
    }
    
    @Override
    public boolean isActive() {
        return MediaSessionCompatApi21.isActive(this.mSessionObj);
    }
    
    @Override
    public void release() {
        MediaSessionCompatApi21.release(this.mSessionObj);
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
        MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
    }
    
    @Override
    public void setActive(final boolean b) {
        MediaSessionCompatApi21.setActive(this.mSessionObj, b);
    }
    
    @Override
    public void setCallback(final Callback callback, final Handler handler) {
        final Object mSessionObj = this.mSessionObj;
        Object mCallbackObj;
        if (callback == null) {
            mCallbackObj = null;
        }
        else {
            mCallbackObj = callback.mCallbackObj;
        }
        MediaSessionCompatApi21.setCallback(mSessionObj, mCallbackObj, handler);
    }
    
    @Override
    public void setExtras(final Bundle bundle) {
        MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
    }
    
    @Override
    public void setFlags(final int n) {
        MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
    }
    
    @Override
    public void setMediaButtonReceiver(final PendingIntent mMediaButtonIntent) {
        this.mMediaButtonIntent = mMediaButtonIntent;
        MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, mMediaButtonIntent);
    }
    
    @Override
    public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        final Object mSessionObj = this.mSessionObj;
        Object mediaMetadata;
        if (mediaMetadataCompat == null) {
            mediaMetadata = null;
        }
        else {
            mediaMetadata = mediaMetadataCompat.getMediaMetadata();
        }
        MediaSessionCompatApi21.setMetadata(mSessionObj, mediaMetadata);
    }
    
    @Override
    public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
        final Object mSessionObj = this.mSessionObj;
        Object playbackState;
        if (playbackStateCompat == null) {
            playbackState = null;
        }
        else {
            playbackState = playbackStateCompat.getPlaybackState();
        }
        MediaSessionCompatApi21.setPlaybackState(mSessionObj, playbackState);
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
        MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
        MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
    }
    
    @Override
    public void setQueue(final List<QueueItem> list) {
        List<Object> list2 = null;
        if (list != null) {
            list2 = new ArrayList<Object>();
            final Iterator<QueueItem> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(iterator.next().getQueueItem());
            }
        }
        MediaSessionCompatApi21.setQueue(this.mSessionObj, list2);
    }
    
    @Override
    public void setQueueTitle(final CharSequence charSequence) {
        MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
    }
    
    @Override
    public void setRatingType(final int n) {
        if (Build$VERSION.SDK_INT < 22) {
            return;
        }
        MediaSessionCompatApi22.setRatingType(this.mSessionObj, n);
    }
    
    @Override
    public void setSessionActivity(final PendingIntent pendingIntent) {
        MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
    }
}
