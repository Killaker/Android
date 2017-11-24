package android.support.v4.media;

class MediaBrowserServiceCompat$ServiceImpl$2 implements Runnable {
    final /* synthetic */ ServiceCallbacks val$callbacks;
    
    @Override
    public void run() {
        if (MediaBrowserServiceCompat.access$500(ServiceImpl.this.this$0).remove(this.val$callbacks.asBinder()) != null) {}
    }
}