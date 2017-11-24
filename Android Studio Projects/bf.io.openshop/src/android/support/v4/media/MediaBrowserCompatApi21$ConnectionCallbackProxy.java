package android.support.v4.media;

import android.media.browse.*;

static class ConnectionCallbackProxy<T extends ConnectionCallback> extends MediaBrowser$ConnectionCallback
{
    protected final T mConnectionCallback;
    
    public ConnectionCallbackProxy(final T mConnectionCallback) {
        this.mConnectionCallback = mConnectionCallback;
    }
    
    public void onConnected() {
        ((ConnectionCallback)this.mConnectionCallback).onConnected();
    }
    
    public void onConnectionFailed() {
        ((ConnectionCallback)this.mConnectionCallback).onConnectionFailed();
    }
    
    public void onConnectionSuspended() {
        ((ConnectionCallback)this.mConnectionCallback).onConnectionSuspended();
    }
}
