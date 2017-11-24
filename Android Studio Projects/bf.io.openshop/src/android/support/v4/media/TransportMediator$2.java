package android.support.v4.media;

import android.view.*;

class TransportMediator$2 implements KeyEvent$Callback {
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return TransportMediator.isMediaKey(n) && TransportMediator.this.mCallbacks.onMediaButtonDown(n, keyEvent);
    }
    
    public boolean onKeyLongPress(final int n, final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean onKeyMultiple(final int n, final int n2, final KeyEvent keyEvent) {
        return false;
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return TransportMediator.isMediaKey(n) && TransportMediator.this.mCallbacks.onMediaButtonUp(n, keyEvent);
    }
}