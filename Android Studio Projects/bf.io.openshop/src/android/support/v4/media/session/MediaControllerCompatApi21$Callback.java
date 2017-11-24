package android.support.v4.media.session;

import android.os.*;

public interface Callback
{
    void onMetadataChanged(final Object p0);
    
    void onPlaybackStateChanged(final Object p0);
    
    void onSessionDestroyed();
    
    void onSessionEvent(final String p0, final Bundle p1);
}
