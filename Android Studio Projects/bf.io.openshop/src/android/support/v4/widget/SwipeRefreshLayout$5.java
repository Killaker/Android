package android.support.v4.widget;

import android.view.animation.*;

class SwipeRefreshLayout$5 implements Animation$AnimationListener {
    public void onAnimationEnd(final Animation animation) {
        if (!SwipeRefreshLayout.access$800(SwipeRefreshLayout.this)) {
            SwipeRefreshLayout.access$900(SwipeRefreshLayout.this, null);
        }
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}