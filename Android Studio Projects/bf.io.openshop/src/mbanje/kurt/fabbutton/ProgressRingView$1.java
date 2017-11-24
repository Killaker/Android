package mbanje.kurt.fabbutton;

import android.animation.*;

class ProgressRingView$1 extends AnimatorListenerAdapter {
    boolean wasCancelled = false;
    
    public void onAnimationCancel(final Animator animator) {
        this.wasCancelled = true;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (!this.wasCancelled) {
            ProgressRingView.this.resetAnimation();
        }
    }
}