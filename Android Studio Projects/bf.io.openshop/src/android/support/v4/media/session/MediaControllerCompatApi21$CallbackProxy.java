package android.support.v4.media.session;

import android.media.*;
import android.media.session.*;
import android.os.*;

static class CallbackProxy<T extends Callback> extends MediaController$Callback
{
    protected final T mCallback;
    
    public CallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onMetadataChanged(final MediaMetadata mediaMetadata) {
        ((Callback)this.mCallback).onMetadataChanged(mediaMetadata);
    }
    
    public void onPlaybackStateChanged(final PlaybackState playbackState) {
        ((Callback)this.mCallback).onPlaybackStateChanged(playbackState);
    }
    
    public void onSessionDestroyed() {
        ((Callback)this.mCallback).onSessionDestroyed();
    }
    
    public void onSessionEvent(final String s, final Bundle bundle) {
        ((Callback)this.mCallback).onSessionEvent(s, bundle);
    }
}
