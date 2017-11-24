package android.support.design.widget;

import android.view.*;

class OnPreDrawListener implements ViewTreeObserver$OnPreDrawListener
{
    public boolean onPreDraw() {
        CoordinatorLayout.this.dispatchOnDependentViewChanged(false);
        return true;
    }
}
