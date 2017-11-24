package android.support.v4.media;

import android.os.*;

class MediaBrowserServiceCompat$ServiceImpl$6 implements Runnable {
    final /* synthetic */ ServiceCallbacks val$callbacks;
    
    @Override
    public void run() {
        final IBinder binder = this.val$callbacks.asBinder();
        MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).remove(binder);
        final ConnectionRecord connectionRecord = new ConnectionRecord();
        connectionRecord.callbacks = this.val$callbacks;
        MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).put(binder, connectionRecord);
    }
}