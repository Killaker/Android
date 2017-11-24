package android.support.design.widget;

import android.view.animation.*;

class StateListAnimator$1 implements Animation$AnimationListener {
    public void onAnimationEnd(final Animation animation) {
        if (StateListAnimator.access$000(StateListAnimator.this) == animation) {
            StateListAnimator.access$002(StateListAnimator.this, null);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}