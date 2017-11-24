package android.support.design.widget;

import android.view.*;

class Snackbar$5 implements OnAttachStateChangeListener {
    @Override
    public void onViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onViewDetachedFromWindow(final View view) {
        if (Snackbar.this.isShownOrQueued()) {
            Snackbar.access$100().post((Runnable)new Runnable() {
                @Override
                public void run() {
                    Snackbar.access$300(Snackbar.this, 3);
                }
            });
        }
    }
}