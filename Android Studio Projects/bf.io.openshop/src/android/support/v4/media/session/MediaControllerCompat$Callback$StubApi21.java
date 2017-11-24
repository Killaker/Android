package android.support.v4.media.session;

import android.support.v4.media.*;
import android.os.*;

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
