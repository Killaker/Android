package android.support.v4.media.session;

import android.os.*;
import android.content.*;

interface Callback extends MediaSessionCompatApi19.Callback
{
    void onCommand(final String p0, final Bundle p1, final ResultReceiver p2);
    
    void onCustomAction(final String p0, final Bundle p1);
    
    void onFastForward();
    
    boolean onMediaButtonEvent(final Intent p0);
    
    void onPause();
    
    void onPlay();
    
    void onPlayFromMediaId(final String p0, final Bundle p1);
    
    void onPlayFromSearch(final String p0, final Bundle p1);
    
    void onRewind();
    
    void onSkipToNext();
    
    void onSkipToPrevious();
    
    void onSkipToQueueItem(final long p0);
    
    void onStop();
}
