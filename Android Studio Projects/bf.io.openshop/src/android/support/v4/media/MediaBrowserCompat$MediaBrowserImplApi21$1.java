package android.support.v4.media;

class MediaBrowserCompat$MediaBrowserImplApi21$1 implements Runnable {
    final /* synthetic */ ItemCallback val$cb;
    final /* synthetic */ String val$mediaId;
    
    @Override
    public void run() {
        this.val$cb.onError(this.val$mediaId);
    }
}