package android.support.v4.media;

import android.util.*;
import android.os.*;

class MediaBrowserServiceCompat$ServiceImpl$1 implements Runnable {
    final /* synthetic */ ServiceCallbacks val$callbacks;
    final /* synthetic */ String val$pkg;
    final /* synthetic */ Bundle val$rootHints;
    final /* synthetic */ int val$uid;
    
    @Override
    public void run() {
        final IBinder binder = this.val$callbacks.asBinder();
        MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).remove(binder);
        final ConnectionRecord connectionRecord = new ConnectionRecord();
        connectionRecord.pkg = this.val$pkg;
        connectionRecord.rootHints = this.val$rootHints;
        connectionRecord.callbacks = this.val$callbacks;
        connectionRecord.root = ServiceImpl.this.this$0.onGetRoot(this.val$pkg, this.val$uid, this.val$rootHints);
        Label_0182: {
            if (connectionRecord.root != null) {
                break Label_0182;
            }
            Log.i("MediaBrowserServiceCompat", "No root for client " + this.val$pkg + " from service " + this.getClass().getName());
            try {
                this.val$callbacks.onConnectFailed();
                return;
            }
            catch (RemoteException ex) {
                Log.w("MediaBrowserServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + this.val$pkg);
                return;
            }
            try {
                MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).put(binder, connectionRecord);
                if (ServiceImpl.this.this$0.mSession != null) {
                    this.val$callbacks.onConnect(connectionRecord.root.getRootId(), ServiceImpl.this.this$0.mSession, connectionRecord.root.getExtras());
                }
            }
            catch (RemoteException ex2) {
                Log.w("MediaBrowserServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + this.val$pkg);
                MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).remove(binder);
            }
        }
    }
}