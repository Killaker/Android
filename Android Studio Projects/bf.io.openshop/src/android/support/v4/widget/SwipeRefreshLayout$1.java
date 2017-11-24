package android.support.v4.widget;

import android.view.animation.*;

class SwipeRefreshLayout$1 implements Animation$AnimationListener {
    public void onAnimationEnd(final Animation animation) {
        if (SwipeRefreshLayout.access$000(SwipeRefreshLayout.this)) {
            SwipeRefreshLayout.access$100(SwipeRefreshLayout.this).setAlpha(255);
            SwipeRefreshLayout.access$100(SwipeRefreshLayout.this).start();
            if (SwipeRefreshLayout.access$200(SwipeRefreshLayout.this) && SwipeRefreshLayout.access$300(SwipeRefreshLayout.this) != null) {
                SwipeRefreshLayout.access$300(SwipeRefreshLayout.this).onRefresh();
            }
            SwipeRefreshLayout.access$402(SwipeRefreshLayout.this, SwipeRefreshLayout.access$500(SwipeRefreshLayout.this).getTop());
            return;
        }
        SwipeRefreshLayout.access$600(SwipeRefreshLayout.this);
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
    }
}