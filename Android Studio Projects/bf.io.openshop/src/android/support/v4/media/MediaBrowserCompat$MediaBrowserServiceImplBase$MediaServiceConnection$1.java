package android.support.v4.media;

import android.content.*;
import android.util.*;
import android.os.*;

class MediaBrowserCompat$MediaBrowserServiceImplBase$MediaServiceConnection$1 implements Runnable {
    final /* synthetic */ IBinder val$binder;
    
    @Override
    public void run() {
        if (!MediaServiceConnection.access$1000(MediaServiceConnection.this, "onServiceConnected")) {
            return;
        }
        MediaBrowserServiceImplBase.access$1102(MediaServiceConnection.this.this$0, new ServiceBinderWrapper(this.val$binder));
        MediaBrowserServiceImplBase.access$1202(MediaServiceConnection.this.this$0, new Messenger((Handler)MediaBrowserServiceImplBase.access$1300(MediaServiceConnection.this.this$0)));
        MediaBrowserServiceImplBase.access$1300(MediaServiceConnection.this.this$0).setCallbacksMessenger(MediaBrowserServiceImplBase.access$1200(MediaServiceConnection.this.this$0));
        MediaBrowserServiceImplBase.access$1402(MediaServiceConnection.this.this$0, 1);
        try {
            MediaBrowserServiceImplBase.access$1100(MediaServiceConnection.this.this$0).connect(MediaBrowserServiceImplBase.access$1500(MediaServiceConnection.this.this$0), MediaBrowserServiceImplBase.access$1600(MediaServiceConnection.this.this$0), MediaBrowserServiceImplBase.access$1200(MediaServiceConnection.this.this$0));
        }
        catch (RemoteException ex) {
            Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserServiceImplBase.access$1700(MediaServiceConnection.this.this$0));
        }
    }
}