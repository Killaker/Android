package android.support.v4.widget;

import android.view.*;
import android.support.v4.view.*;

static class SlidingPanelLayoutImplBase implements SlidingPanelLayoutImpl
{
    @Override
    public void invalidateChildRegion(final SlidingPaneLayout slidingPaneLayout, final View view) {
        ViewCompat.postInvalidateOnAnimation((View)slidingPaneLayout, view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
    }
}
