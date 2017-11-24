package android.support.design.widget;

import android.animation.*;

class FloatingActionButtonIcs$1 extends AnimatorListenerAdapter {
    private boolean mCancelled;
    final /* synthetic */ boolean val$fromUser;
    final /* synthetic */ InternalVisibilityChangedListener val$listener;
    
    public void onAnimationCancel(final Animator animator) {
        FloatingActionButtonIcs.access$002(FloatingActionButtonIcs.this, false);
        this.mCancelled = true;
    }
    
    public void onAnimationEnd(final Animator animator) {
        FloatingActionButtonIcs.access$002(FloatingActionButtonIcs.this, false);
        if (!this.mCancelled) {
            FloatingActionButtonIcs.this.mView.internalSetVisibility(8, this.val$fromUser);
            if (this.val$listener != null) {
                this.val$listener.onHidden();
            }
        }
    }
    
    public void onAnimationStart(final Animator animator) {
        FloatingActionButtonIcs.access$002(FloatingActionButtonIcs.this, true);
        this.mCancelled = false;
        FloatingActionButtonIcs.this.mView.internalSetVisibility(0, this.val$fromUser);
    }
}