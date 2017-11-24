package android.support.v4.view;

import android.view.*;

static class ViewParentCompatKitKatImpl extends ViewParentCompatICSImpl
{
    @Override
    public void notifySubtreeAccessibilityStateChanged(final ViewParent viewParent, final View view, final View view2, final int n) {
        ViewParentCompatKitKat.notifySubtreeAccessibilityStateChanged(viewParent, view, view2, n);
    }
}
