package android.support.v4.view;

import android.view.*;
import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.os.*;

class MyAccessibilityDelegate extends AccessibilityDelegateCompat
{
    private boolean canScroll() {
        return ViewPager.access$200(ViewPager.this) != null && ViewPager.access$200(ViewPager.this).getCount() > 1;
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)ViewPager.class.getName());
        final AccessibilityRecordCompat record = AccessibilityEventCompat.asRecord(accessibilityEvent);
        record.setScrollable(this.canScroll());
        if (accessibilityEvent.getEventType() == 4096 && ViewPager.access$200(ViewPager.this) != null) {
            record.setItemCount(ViewPager.access$200(ViewPager.this).getCount());
            record.setFromIndex(ViewPager.access$300(ViewPager.this));
            record.setToIndex(ViewPager.access$300(ViewPager.this));
        }
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(ViewPager.class.getName());
        accessibilityNodeInfoCompat.setScrollable(this.canScroll());
        if (ViewPager.this.canScrollHorizontally(1)) {
            accessibilityNodeInfoCompat.addAction(4096);
        }
        if (ViewPager.this.canScrollHorizontally(-1)) {
            accessibilityNodeInfoCompat.addAction(8192);
        }
    }
    
    @Override
    public boolean performAccessibilityAction(final View view, final int n, final Bundle bundle) {
        if (super.performAccessibilityAction(view, n, bundle)) {
            return true;
        }
        switch (n) {
            default: {
                return false;
            }
            case 4096: {
                if (ViewPager.this.canScrollHorizontally(1)) {
                    ViewPager.this.setCurrentItem(1 + ViewPager.access$300(ViewPager.this));
                    return true;
                }
                return false;
            }
            case 8192: {
                if (ViewPager.this.canScrollHorizontally(-1)) {
                    ViewPager.this.setCurrentItem(-1 + ViewPager.access$300(ViewPager.this));
                    return true;
                }
                return false;
            }
        }
    }
}
