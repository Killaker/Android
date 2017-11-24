package android.support.v4.media;

import android.support.v4.media.session.*;
import android.util.*;
import android.os.*;
import java.util.*;

class MediaBrowserServiceCompat$1 implements Runnable {
    final /* synthetic */ MediaSessionCompat.Token val$token;
    
    @Override
    public void run() {
        for (final IBinder binder : MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).keySet()) {
            final ConnectionRecord connectionRecord = (ConnectionRecord)MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).get(binder);
            try {
                connectionRecord.callbacks.onConnect(connectionRecord.root.getRootId(), this.val$token, connectionRecord.root.getExtras());
            }
            catch (RemoteException ex) {
                Log.w("MediaBrowserServiceCompat", "Connection for " + connectionRecord.pkg + " is no longer valid.");
                MediaBrowserServiceCompat.access$500(MediaBrowserServiceCompat.this).remove(binder);
            }
        }
    }
}