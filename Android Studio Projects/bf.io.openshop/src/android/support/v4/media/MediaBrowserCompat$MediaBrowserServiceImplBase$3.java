package android.support.v4.media;

class MediaBrowserCompat$MediaBrowserServiceImplBase$3 implements Runnable {
    final /* synthetic */ ItemCallback val$cb;
    final /* synthetic */ String val$mediaId;
    
    @Override
    public void run() {
        this.val$cb.onError(this.val$mediaId);
    }
}