package android.support.v4.media;

private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback
{
    @Override
    public void onConnected() {
        if (MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this) != null) {
            MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this).onConnected();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
    }
    
    @Override
    public void onConnectionFailed() {
        if (MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this) != null) {
            MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this).onConnectionFailed();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
    }
    
    @Override
    public void onConnectionSuspended() {
        if (MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this) != null) {
            MediaBrowserCompat.ConnectionCallback.access$200(MediaBrowserCompat.ConnectionCallback.this).onConnectionSuspended();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
    }
}
