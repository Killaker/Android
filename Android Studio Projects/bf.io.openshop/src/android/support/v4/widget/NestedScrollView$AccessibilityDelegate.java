package android.support.v4.widget;

import android.support.v4.view.*;
import android.view.*;
import android.view.accessibility.*;
import android.widget.*;
import android.support.v4.view.accessibility.*;
import android.os.*;

static class AccessibilityDelegate extends AccessibilityDelegateCompat
{
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        accessibilityEvent.setClassName((CharSequence)ScrollView.class.getName());
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
        record.setScrollable(NestedScrollView.access$000(nestedScrollView) > 0);
        record.setScrollX(nestedScrollView.getScrollX());
        record.setScrollY(nestedScrollView.getScrollY());
        record.setMaxScrollX(nestedScrollView.getScrollX());
        record.setMaxScrollY(NestedScrollView.access$000(nestedScrollView));
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        accessibilityNodeInfoCompat.setClassName(ScrollView.class.getName());
        if (nestedScrollView.isEnabled()) {
            final int access$000 = NestedScrollView.access$000(nestedScrollView);
            if (access$000 > 0) {
                accessibilityNodeInfoCompat.setScrollable(true);
                if (nestedScrollView.getScrollY() > 0) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (nestedScrollView.getScrollY() < access$000) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        if (super.performAccessibilityAction(view, n, bundle)) {
            return true;
        }
        final NestedScrollView nestedScrollView = (NestedScrollView)view;
        if (!nestedScrollView.isEnabled()) {
            return false;
        }
        switch (n) {
            default: {
                return false;
            }
            case 4096: {
                final int min = Math.min(nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop() + nestedScrollView.getScrollY(), NestedScrollView.access$000(nestedScrollView));
                if (min != nestedScrollView.getScrollY()) {
                    nestedScrollView.smoothScrollTo(0, min);
                    return true;
                }
                return false;
            }
            case 8192: {
                final int max = Math.max(nestedScrollView.getScrollY() - (nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom() - nestedScrollView.getPaddingTop()), 0);
                if (max != nestedScrollView.getScrollY()) {
                    nestedScrollView.smoothScrollTo(0, max);
                    return true;
                }
                return false;
            }
        }
    }
}
