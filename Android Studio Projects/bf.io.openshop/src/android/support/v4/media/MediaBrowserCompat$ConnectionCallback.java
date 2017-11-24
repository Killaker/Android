package android.support.v4.media;

import android.os.*;

public static class ConnectionCallback
{
    private ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;
    
    public ConnectionCallback() {
        if (Build$VERSION.SDK_INT >= 21) {
            this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback((MediaBrowserCompatApi21.ConnectionCallback)new StubApi21());
            return;
        }
        this.mConnectionCallbackObj = null;
    }
    
    public void onConnected() {
    }
    
    public void onConnectionFailed() {
    }
    
    public void onConnectionSuspended() {
    }
    
    void setInternalConnectionCallback(final ConnectionCallbackInternal mConnectionCallbackInternal) {
        this.mConnectionCallbackInternal = mConnectionCallbackInternal;
    }
    
    interface ConnectionCallbackInternal
    {
        void onConnected();
        
        void onConnectionFailed();
        
        void onConnectionSuspended();
    }
    
    private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback
    {
        @Override
        public void onConnected() {
            if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnected();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnected();
        }
        
        @Override
        public void onConnectionFailed() {
            if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionFailed();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
        }
        
        @Override
        public void onConnectionSuspended() {
            if (MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal != null) {
                MediaBrowserCompat.ConnectionCallback.this.mConnectionCallbackInternal.onConnectionSuspended();
            }
            MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
        }
    }
}
