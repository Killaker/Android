package android.support.v7.widget;

import android.support.v4.view.*;
import android.view.*;
import android.support.v4.view.accessibility.*;
import android.os.*;

class RecyclerViewAccessibilityDelegate$1 extends AccessibilityDelegateCompat {
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (!RecyclerViewAccessibilityDelegate.access$000(RecyclerViewAccessibilityDelegate.this) && RecyclerViewAccessibilityDelegate.this.mRecyclerView.getLayoutManager() != null) {
            RecyclerViewAccessibilityDelegate.this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        return super.performAccessibilityAction(view, n, bundle) || (!RecyclerViewAccessibilityDelegate.access$000(RecyclerViewAccessibilityDelegate.this) && RecyclerViewAccessibilityDelegate.this.mRecyclerView.getLayoutManager() != null && RecyclerViewAccessibilityDelegate.this.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, n, bundle));
    }
}