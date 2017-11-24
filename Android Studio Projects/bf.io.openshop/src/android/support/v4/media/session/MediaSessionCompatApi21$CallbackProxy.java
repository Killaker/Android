package android.support.v4.media.session;

import android.media.session.*;
import android.os.*;
import android.content.*;
import android.media.*;

static class CallbackProxy<T extends Callback> extends MediaSession$Callback
{
    protected final T mCallback;
    
    public CallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        ((Callback)this.mCallback).onCommand(s, bundle, resultReceiver);
    }
    
    public void onCustomAction(final String s, final Bundle bundle) {
        ((Callback)this.mCallback).onCustomAction(s, bundle);
    }
    
    public void onFastForward() {
        ((Callback)this.mCallback).onFastForward();
    }
    
    public boolean onMediaButtonEvent(final Intent intent) {
        return ((Callback)this.mCallback).onMediaButtonEvent(intent) || super.onMediaButtonEvent(intent);
    }
    
    public void onPause() {
        ((Callback)this.mCallback).onPause();
    }
    
    public void onPlay() {
        ((Callback)this.mCallback).onPlay();
    }
    
    public void onPlayFromMediaId(final String s, final Bundle bundle) {
        ((Callback)this.mCallback).onPlayFromMediaId(s, bundle);
    }
    
    public void onPlayFromSearch(final String s, final Bundle bundle) {
        ((Callback)this.mCallback).onPlayFromSearch(s, bundle);
    }
    
    public void onRewind() {
        ((Callback)this.mCallback).onRewind();
    }
    
    public void onSeekTo(final long n) {
        ((MediaSessionCompatApi18.Callback)this.mCallback).onSeekTo(n);
    }
    
    public void onSetRating(final Rating rating) {
        ((MediaSessionCompatApi19.Callback)this.mCallback).onSetRating(rating);
    }
    
    public void onSkipToNext() {
        ((Callback)this.mCallback).onSkipToNext();
    }
    
    public void onSkipToPrevious() {
        ((Callback)this.mCallback).onSkipToPrevious();
    }
    
    public void onSkipToQueueItem(final long n) {
        ((Callback)this.mCallback).onSkipToQueueItem(n);
    }
    
    public void onStop() {
        ((Callback)this.mCallback).onStop();
    }
}
