package android.support.v4.media;

import android.view.*;

class TransportMediator$1 implements TransportMediatorCallback {
    @Override
    public long getPlaybackPosition() {
        return TransportMediator.this.mCallbacks.onGetCurrentPosition();
    }
    
    @Override
    public void handleAudioFocusChange(final int n) {
        TransportMediator.this.mCallbacks.onAudioFocusChange(n);
    }
    
    @Override
    public void handleKey(final KeyEvent keyEvent) {
        keyEvent.dispatch(TransportMediator.this.mKeyEventCallback);
    }
    
    @Override
    public void playbackPositionUpdate(final long n) {
        TransportMediator.this.mCallbacks.onSeekTo(n);
    }
}