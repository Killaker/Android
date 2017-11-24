package android.support.design.widget;

import android.view.*;

private class HierarchyChangeListener implements ViewGroup$OnHierarchyChangeListener
{
    public void onChildViewAdded(final View view, final View view2) {
        if (CoordinatorLayout.access$300(CoordinatorLayout.this) != null) {
            CoordinatorLayout.access$300(CoordinatorLayout.this).onChildViewAdded(view, view2);
        }
    }
    
    public void onChildViewRemoved(final View view, final View view2) {
        CoordinatorLayout.this.dispatchDependentViewRemoved(view2);
        if (CoordinatorLayout.access$300(CoordinatorLayout.this) != null) {
            CoordinatorLayout.access$300(CoordinatorLayout.this).onChildViewRemoved(view, view2);
        }
    }
}
