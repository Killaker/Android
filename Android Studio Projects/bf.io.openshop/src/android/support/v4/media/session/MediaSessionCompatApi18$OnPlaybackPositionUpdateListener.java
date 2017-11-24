package android.support.v4.media.session;

import android.media.*;

static class OnPlaybackPositionUpdateListener<T extends Callback> implements RemoteControlClient$OnPlaybackPositionUpdateListener
{
    protected final T mCallback;
    
    public OnPlaybackPositionUpdateListener(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onPlaybackPositionUpdate(final long n) {
        ((Callback)this.mCallback).onSeekTo(n);
    }
}
