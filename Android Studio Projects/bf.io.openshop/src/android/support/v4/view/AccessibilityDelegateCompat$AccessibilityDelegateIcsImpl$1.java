package android.support.v4.view;

import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.view.*;

class AccessibilityDelegateCompat$AccessibilityDelegateIcsImpl$1 implements AccessibilityDelegateBridge {
    final /* synthetic */ AccessibilityDelegateCompat val$compat;
    
    @Override
    public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        return this.val$compat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
        this.val$compat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }
    
    @Override
    public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return this.val$compat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }
    
    @Override
    public void sendAccessibilityEvent(final View view, final int n) {
        this.val$compat.sendAccessibilityEvent(view, n);
    }
    
    @Override
    public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
        this.val$compat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}