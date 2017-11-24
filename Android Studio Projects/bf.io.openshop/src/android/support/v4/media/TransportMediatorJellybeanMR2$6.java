package android.support.v4.media;

import android.media.*;

class TransportMediatorJellybeanMR2$6 implements RemoteControlClient$OnPlaybackPositionUpdateListener {
    public void onPlaybackPositionUpdate(final long n) {
        TransportMediatorJellybeanMR2.this.mTransportCallback.playbackPositionUpdate(n);
    }
}