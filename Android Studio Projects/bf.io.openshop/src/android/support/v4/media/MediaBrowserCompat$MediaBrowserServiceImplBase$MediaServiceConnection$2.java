package android.support.v4.media;

import android.content.*;
import android.os.*;

class MediaBrowserCompat$MediaBrowserServiceImplBase$MediaServiceConnection$2 implements Runnable {
    @Override
    public void run() {
        if (!MediaServiceConnection.access$1000(MediaServiceConnection.this, "onServiceDisconnected")) {
            return;
        }
        MediaBrowserServiceImplBase.access$1102(MediaServiceConnection.this.this$0, null);
        MediaBrowserServiceImplBase.access$1202(MediaServiceConnection.this.this$0, null);
        MediaBrowserServiceImplBase.access$1300(MediaServiceConnection.this.this$0).setCallbacksMessenger(null);
        MediaBrowserServiceImplBase.access$1402(MediaServiceConnection.this.this$0, 3);
        MediaBrowserServiceImplBase.access$900(MediaServiceConnection.this.this$0).onConnectionSuspended();
    }
}