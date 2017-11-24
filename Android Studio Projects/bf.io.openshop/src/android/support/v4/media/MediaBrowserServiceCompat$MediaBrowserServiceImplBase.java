package android.support.v4.media;

import android.content.*;
import android.os.*;

class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl
{
    private Messenger mMessenger;
    
    @Override
    public IBinder onBind(final Intent intent) {
        if ("android.media.browse.MediaBrowserService".equals(intent.getAction())) {
            return this.mMessenger.getBinder();
        }
        return null;
    }
    
    @Override
    public void onCreate() {
        this.mMessenger = new Messenger((Handler)MediaBrowserServiceCompat.access$100(MediaBrowserServiceCompat.this));
    }
}
