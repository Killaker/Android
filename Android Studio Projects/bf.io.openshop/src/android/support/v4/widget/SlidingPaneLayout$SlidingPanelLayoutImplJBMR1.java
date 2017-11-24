package android.support.v4.widget;

import android.view.*;
import android.support.v4.view.*;

static class SlidingPanelLayoutImplJBMR1 extends SlidingPanelLayoutImplBase
{
    @Override
    public void invalidateChildRegion(final SlidingPaneLayout slidingPaneLayout, final View view) {
        ViewCompat.setLayerPaint(view, ((LayoutParams)view.getLayoutParams()).dimPaint);
    }
}
