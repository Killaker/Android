package android.support.v4.media;

import android.media.*;

class TransportMediatorJellybeanMR2$4 implements AudioManager$OnAudioFocusChangeListener {
    public void onAudioFocusChange(final int n) {
        TransportMediatorJellybeanMR2.this.mTransportCallback.handleAudioFocusChange(n);
    }
}