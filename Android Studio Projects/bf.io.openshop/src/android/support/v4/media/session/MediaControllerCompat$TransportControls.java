package android.support.v4.media.session;

import android.os.*;
import android.net.*;
import android.support.v4.media.*;

public abstract static class TransportControls
{
    public abstract void fastForward();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void playFromMediaId(final String p0, final Bundle p1);
    
    public abstract void playFromSearch(final String p0, final Bundle p1);
    
    public abstract void playFromUri(final Uri p0, final Bundle p1);
    
    public abstract void rewind();
    
    public abstract void seekTo(final long p0);
    
    public abstract void sendCustomAction(final PlaybackStateCompat.CustomAction p0, final Bundle p1);
    
    public abstract void sendCustomAction(final String p0, final Bundle p1);
    
    public abstract void setRating(final RatingCompat p0);
    
    public abstract void skipToNext();
    
    public abstract void skipToPrevious();
    
    public abstract void skipToQueueItem(final long p0);
    
    public abstract void stop();
}
