package android.support.v4.widget;

import android.support.v4.view.accessibility.*;
import android.os.*;

private class ExploreByTouchNodeProvider extends AccessibilityNodeProviderCompat
{
    @Override
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(final int n) {
        return ExploreByTouchHelper.access$100(ExploreByTouchHelper.this, n);
    }
    
    @Override
    public boolean performAction(final int n, final int n2, final Bundle bundle) {
        return ExploreByTouchHelper.access$200(ExploreByTouchHelper.this, n, n2, bundle);
    }
}
