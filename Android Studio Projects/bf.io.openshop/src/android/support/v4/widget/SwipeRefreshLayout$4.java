package android.support.v4.widget;

import android.view.animation.*;

class SwipeRefreshLayout$4 extends Animation {
    final /* synthetic */ int val$endingAlpha;
    final /* synthetic */ int val$startingAlpha;
    
    public void applyTransformation(final float n, final Transformation transformation) {
        SwipeRefreshLayout.access$100(SwipeRefreshLayout.this).setAlpha((int)(this.val$startingAlpha + n * (this.val$endingAlpha - this.val$startingAlpha)));
    }
}