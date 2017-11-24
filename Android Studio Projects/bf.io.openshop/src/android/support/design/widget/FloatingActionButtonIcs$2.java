package android.support.design.widget;

import android.animation.*;

class FloatingActionButtonIcs$2 extends AnimatorListenerAdapter {
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ InternalVisibilityChangedListener val$listener;
    
    public void onAnimationEnd(final Animator animator) {
        if (this.val$listener != null) {
            this.val$listener.onShown();
        }
    }
    
    public void onAnimationStart(final Animator animator) {
        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, this.val$fromUser);
    }
}