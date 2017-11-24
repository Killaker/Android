package android.support.v4.media;

import android.view.*;

class TransportMediatorJellybeanMR2$2 implements ViewTreeObserver$OnWindowFocusChangeListener {
    public void onWindowFocusChanged(final boolean b) {
        if (b) {
            TransportMediatorJellybeanMR2.this.gainFocus();
            return;
        }
        TransportMediatorJellybeanMR2.this.loseFocus();
    }
}