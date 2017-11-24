package android.support.v4.media;

import android.util.*;
import android.content.*;
import android.os.*;

private class MediaServiceConnection implements ServiceConnection
{
    private boolean isCurrent(final String s) {
        if (MediaBrowserServiceImplBase.access$700(MediaBrowserServiceImplBase.this) != this) {
            if (MediaBrowserServiceImplBase.access$1400(MediaBrowserServiceImplBase.this) != 0) {
                Log.i("MediaBrowserCompat", s + " for " + MediaBrowserServiceImplBase.access$1700(MediaBrowserServiceImplBase.this) + " with mServiceConnection=" + MediaBrowserServiceImplBase.access$700(MediaBrowserServiceImplBase.this) + " this=" + this);
            }
            return false;
        }
        return true;
    }
    
    private void postOrRun(final Runnable runnable) {
        if (Thread.currentThread() == MediaBrowserServiceImplBase.access$1300(MediaBrowserServiceImplBase.this).getLooper().getThread()) {
            runnable.run();
            return;
        }
        MediaBrowserServiceImplBase.access$1300(MediaBrowserServiceImplBase.this).post(runnable);
    }
    
    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        this.postOrRun(new Runnable() {
            @Override
            public void run() {
                if (!MediaServiceConnection.this.isCurrent("onServiceConnected")) {
                    return;
                }
                MediaBrowserServiceImplBase.access$1102(MediaBrowserServiceImplBase.this, new ServiceBinderWrapper(binder));
                MediaBrowserServiceImplBase.access$1202(MediaBrowserServiceImplBase.this, new Messenger((Handler)MediaBrowserServiceImplBase.access$1300(MediaBrowserServiceImplBase.this)));
                MediaBrowserServiceImplBase.access$1300(MediaBrowserServiceImplBase.this).setCallbacksMessenger(MediaBrowserServiceImplBase.access$1200(MediaBrowserServiceImplBase.this));
                MediaBrowserServiceImplBase.access$1402(MediaBrowserServiceImplBase.this, 1);
                try {
                    MediaBrowserServiceImplBase.access$1100(MediaBrowserServiceImplBase.this).connect(MediaBrowserServiceImplBase.access$1500(MediaBrowserServiceImplBase.this), MediaBrowserServiceImplBase.access$1600(MediaBrowserServiceImplBase.this), MediaBrowserServiceImplBase.access$1200(MediaBrowserServiceImplBase.this));
                }
                catch (RemoteException ex) {
                    Log.w("MediaBrowserCompat", "RemoteException during connect for " + MediaBrowserServiceImplBase.access$1700(MediaBrowserServiceImplBase.this));
                }
            }
        });
    }
    
    public void onServiceDisconnected(final ComponentName componentName) {
        this.postOrRun(new Runnable() {
            @Override
            public void run() {
                if (!MediaServiceConnection.this.isCurrent("onServiceDisconnected")) {
                    return;
                }
                MediaBrowserServiceImplBase.access$1102(MediaBrowserServiceImplBase.this, null);
                MediaBrowserServiceImplBase.access$1202(MediaBrowserServiceImplBase.this, null);
                MediaBrowserServiceImplBase.access$1300(MediaBrowserServiceImplBase.this).setCallbacksMessenger(null);
                MediaBrowserServiceImplBase.access$1402(MediaBrowserServiceImplBase.this, 3);
                MediaBrowserServiceImplBase.access$900(MediaBrowserServiceImplBase.this).onConnectionSuspended();
            }
        });
    }
}
