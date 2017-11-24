package android.support.v4.media.session;

import android.os.*;
import android.net.*;
import android.content.*;
import android.support.v4.media.*;

private class StubApi21 implements MediaSessionCompatApi21.Callback
{
    @Override
    public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        MediaSessionCompat.Callback.this.onCommand(s, bundle, resultReceiver);
    }
    
    @Override
    public void onCustomAction(final String s, final Bundle bundle) {
        if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
            MediaSessionCompat.Callback.this.onPlayFromUri((Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI"), (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
            return;
        }
        MediaSessionCompat.Callback.this.onCustomAction(s, bundle);
    }
    
    @Override
    public void onFastForward() {
        MediaSessionCompat.Callback.this.onFastForward();
    }
    
    @Override
    public boolean onMediaButtonEvent(final Intent intent) {
        return MediaSessionCompat.Callback.this.onMediaButtonEvent(intent);
    }
    
    @Override
    public void onPause() {
        MediaSessionCompat.Callback.this.onPause();
    }
    
    @Override
    public void onPlay() {
        MediaSessionCompat.Callback.this.onPlay();
    }
    
    @Override
    public void onPlayFromMediaId(final String s, final Bundle bundle) {
        MediaSessionCompat.Callback.this.onPlayFromMediaId(s, bundle);
    }
    
    @Override
    public void onPlayFromSearch(final String s, final Bundle bundle) {
        MediaSessionCompat.Callback.this.onPlayFromSearch(s, bundle);
    }
    
    @Override
    public void onRewind() {
        MediaSessionCompat.Callback.this.onRewind();
    }
    
    @Override
    public void onSeekTo(final long n) {
        MediaSessionCompat.Callback.this.onSeekTo(n);
    }
    
    @Override
    public void onSetRating(final Object o) {
        MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(o));
    }
    
    @Override
    public void onSkipToNext() {
        MediaSessionCompat.Callback.this.onSkipToNext();
    }
    
    @Override
    public void onSkipToPrevious() {
        MediaSessionCompat.Callback.this.onSkipToPrevious();
    }
    
    @Override
    public void onSkipToQueueItem(final long n) {
        MediaSessionCompat.Callback.this.onSkipToQueueItem(n);
    }
    
    @Override
    public void onStop() {
        MediaSessionCompat.Callback.this.onStop();
    }
}
