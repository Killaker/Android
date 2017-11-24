package android.support.v4.widget;

import android.support.v4.view.*;
import android.view.*;
import android.support.v4.view.accessibility.*;

final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat
{
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        if (!DrawerLayout.access$700(view)) {
            accessibilityNodeInfoCompat.setParent(null);
        }
    }
}
