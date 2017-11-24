package android.support.v4.view;

import android.view.accessibility.*;
import android.support.v4.view.accessibility.*;
import android.view.*;

static class AccessibilityDelegateIcsImpl extends AccessibilityDelegateStubImpl
{
    @Override
    public boolean dispatchPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        return AccessibilityDelegateCompatIcs.dispatchPopulateAccessibilityEvent(o, view, accessibilityEvent);
    }
    
    @Override
    public Object newAccessiblityDelegateBridge(final AccessibilityDelegateCompat accessibilityDelegateCompat) {
        return AccessibilityDelegateCompatIcs.newAccessibilityDelegateBridge((AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge)new AccessibilityDelegateCompatIcs.AccessibilityDelegateBridge() {
            @Override
            public boolean dispatchPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                return accessibilityDelegateCompat.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public void onInitializeAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.onInitializeAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public void onInitializeAccessibilityNodeInfo(final View view, final Object o) {
                accessibilityDelegateCompat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(o));
            }
            
            @Override
            public void onPopulateAccessibilityEvent(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.onPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            
            @Override
            public boolean onRequestSendAccessibilityEvent(final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
                return accessibilityDelegateCompat.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            
            @Override
            public void sendAccessibilityEvent(final View view, final int n) {
                accessibilityDelegateCompat.sendAccessibilityEvent(view, n);
            }
            
            @Override
            public void sendAccessibilityEventUnchecked(final View view, final AccessibilityEvent accessibilityEvent) {
                accessibilityDelegateCompat.sendAccessibilityEventUnchecked(view, accessibilityEvent);
            }
        });
    }
    
    @Override
    public Object newAccessiblityDelegateDefaultImpl() {
        return AccessibilityDelegateCompatIcs.newAccessibilityDelegateDefaultImpl();
    }
    
    @Override
    public void onInitializeAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompatIcs.onInitializeAccessibilityEvent(o, view, accessibilityEvent);
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfo(final Object o, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityDelegateCompatIcs.onInitializeAccessibilityNodeInfo(o, view, accessibilityNodeInfoCompat.getInfo());
    }
    
    @Override
    public void onPopulateAccessibilityEvent(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompatIcs.onPopulateAccessibilityEvent(o, view, accessibilityEvent);
    }
    
    @Override
    public boolean onRequestSendAccessibilityEvent(final Object o, final ViewGroup viewGroup, final View view, final AccessibilityEvent accessibilityEvent) {
        return AccessibilityDelegateCompatIcs.onRequestSendAccessibilityEvent(o, viewGroup, view, accessibilityEvent);
    }
    
    @Override
    public void sendAccessibilityEvent(final Object o, final View view, final int n) {
        AccessibilityDelegateCompatIcs.sendAccessibilityEvent(o, view, n);
    }
    
    @Override
    public void sendAccessibilityEventUnchecked(final Object o, final View view, final AccessibilityEvent accessibilityEvent) {
        AccessibilityDelegateCompatIcs.sendAccessibilityEventUnchecked(o, view, accessibilityEvent);
    }
}
