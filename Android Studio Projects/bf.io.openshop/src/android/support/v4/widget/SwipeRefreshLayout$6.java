package android.support.v4.widget;

import android.view.animation.*;

class SwipeRefreshLayout$6 extends Animation {
    public void applyTransformation(final float n, final Transformation transformation) {
        int n2;
        if (!SwipeRefreshLayout.access$1000(SwipeRefreshLayout.this)) {
            n2 = (int)(SwipeRefreshLayout.access$1100(SwipeRefreshLayout.this) - Math.abs(SwipeRefreshLayout.this.mOriginalOffsetTop));
        }
        else {
            n2 = (int)SwipeRefreshLayout.access$1100(SwipeRefreshLayout.this);
        }
        SwipeRefreshLayout.access$1200(SwipeRefreshLayout.this, SwipeRefreshLayout.this.mFrom + (int)(n * (n2 - SwipeRefreshLayout.this.mFrom)) - SwipeRefreshLayout.access$500(SwipeRefreshLayout.this).getTop(), false);
        SwipeRefreshLayout.access$100(SwipeRefreshLayout.this).setArrowScale(1.0f - n);
    }
}