package android.support.v4.media;

import android.media.*;

class TransportMediatorJellybeanMR2$5 implements RemoteControlClient$OnGetPlaybackPositionListener {
    public long onGetPlaybackPosition() {
        return TransportMediatorJellybeanMR2.this.mTransportCallback.getPlaybackPosition();
    }
}