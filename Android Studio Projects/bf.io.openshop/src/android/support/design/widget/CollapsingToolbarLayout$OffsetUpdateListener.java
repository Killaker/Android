package android.support.design.widget;

import android.support.v4.view.*;
import android.view.*;

private class OffsetUpdateListener implements OnOffsetChangedListener
{
    @Override
    public void onOffsetChanged(final AppBarLayout appBarLayout, final int n) {
        CollapsingToolbarLayout.access$302(CollapsingToolbarLayout.this, n);
        int systemWindowInsetTop;
        if (CollapsingToolbarLayout.access$400(CollapsingToolbarLayout.this) != null) {
            systemWindowInsetTop = CollapsingToolbarLayout.access$400(CollapsingToolbarLayout.this).getSystemWindowInsetTop();
        }
        else {
            systemWindowInsetTop = 0;
        }
        final int totalScrollRange = appBarLayout.getTotalScrollRange();
        for (int i = 0; i < CollapsingToolbarLayout.this.getChildCount(); ++i) {
            final View child = CollapsingToolbarLayout.this.getChildAt(i);
            final CollapsingToolbarLayout.LayoutParams layoutParams = (CollapsingToolbarLayout.LayoutParams)child.getLayoutParams();
            final ViewOffsetHelper access$500 = CollapsingToolbarLayout.access$500(child);
            switch (layoutParams.mCollapseMode) {
                case 1: {
                    if (n + (CollapsingToolbarLayout.this.getHeight() - systemWindowInsetTop) >= child.getHeight()) {
                        access$500.setTopAndBottomOffset(-n);
                        break;
                    }
                    break;
                }
                case 2: {
                    access$500.setTopAndBottomOffset(Math.round(-n * layoutParams.mParallaxMult));
                    break;
                }
            }
        }
        if (CollapsingToolbarLayout.access$600(CollapsingToolbarLayout.this) != null || CollapsingToolbarLayout.access$700(CollapsingToolbarLayout.this) != null) {
            final CollapsingToolbarLayout this$0 = CollapsingToolbarLayout.this;
            final int n2 = n + CollapsingToolbarLayout.this.getHeight();
            final int n3 = systemWindowInsetTop + CollapsingToolbarLayout.this.getScrimTriggerOffset();
            boolean scrimsShown = false;
            if (n2 < n3) {
                scrimsShown = true;
            }
            this$0.setScrimsShown(scrimsShown);
        }
        if (CollapsingToolbarLayout.access$700(CollapsingToolbarLayout.this) != null && systemWindowInsetTop > 0) {
            ViewCompat.postInvalidateOnAnimation((View)CollapsingToolbarLayout.this);
        }
        CollapsingToolbarLayout.access$800(CollapsingToolbarLayout.this).setExpansionFraction(Math.abs(n) / (CollapsingToolbarLayout.this.getHeight() - ViewCompat.getMinimumHeight((View)CollapsingToolbarLayout.this) - systemWindowInsetTop));
        if (Math.abs(n) == totalScrollRange) {
            ViewCompat.setElevation((View)appBarLayout, appBarLayout.getTargetElevation());
            return;
        }
        ViewCompat.setElevation((View)appBarLayout, 0.0f);
    }
}
