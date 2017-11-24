package android.support.v4.media;

import android.content.*;

class MediaBrowserCompat$MediaBrowserServiceImplBase$1 implements Runnable {
    final /* synthetic */ ServiceConnection val$thisConnection;
    
    @Override
    public void run() {
        if (this.val$thisConnection == MediaBrowserServiceImplBase.access$700(MediaBrowserServiceImplBase.this)) {
            MediaBrowserServiceImplBase.access$800(MediaBrowserServiceImplBase.this);
            MediaBrowserServiceImplBase.access$900(MediaBrowserServiceImplBase.this).onConnectionFailed();
        }
    }
}